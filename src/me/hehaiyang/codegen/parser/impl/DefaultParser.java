package me.hehaiyang.codegen.parser.impl;

import me.hehaiyang.codegen.model.Field;
import me.hehaiyang.codegen.model.Table;
import me.hehaiyang.codegen.parser.Parser;
import me.hehaiyang.codegen.utils.StringUtils;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 默认的解析器, 使用 JSqlParser 进行解析.
 *
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2017/6/20
 */
public class DefaultParser implements Parser {

    @Override
    public Table parseSQL(String sql) {
        List<Field> fields = new ArrayList<>();
        Table table = new Table(fields);

        if (StringUtils.isBlank(sql)) {
            return table;
        }
        // 解析sql语句
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            if (!(statement instanceof CreateTable)) {
                throw new RuntimeException("Only support create table statement !!!");
            }
            CreateTable createTable = (CreateTable) statement;
            table.setTableName(removeQuotes(createTable.getTable().getName()));
            createTable.getColumnDefinitions().forEach(it -> {
                Field field = new Field();
                // 字段名称
                String columnName = removeQuotes(it.getColumnName());
                field.setColumn(columnName); // 同时设置了 FieldName

                // 字段类型
                ColDataType colDataType = it.getColDataType();
                field.setColumnType(colDataType.getDataType()); // 同时设置了字段类型
                field.setColumnSize(firstOrNull(colDataType.getArgumentsStringList()));

                // comment注释
                field.setComment(getColumnComment(it.getColumnSpecStrings()));

                fields.add(field);
            });
        } catch (Exception e) {
            // 需要异常统一下
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 去除一些特殊的引号字符
     */
    private String removeQuotes(String input) {
        if (StringUtils.isBlank(input)) return "";

        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch == '`' || ch == '\'' || ch == '\"') {
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 获取第一个元素或者null
     */
    private <T> T firstOrNull(List<T> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        return lists.get(0);
    }

    /**
     * 获取字段的注释
     */
    private String getColumnComment(List<String> specs) {
        if (specs == null || specs.isEmpty()) {
            return null;
        }
        for (int size = specs.size(), i = 0; i < size; i++) {
            String spec = specs.get(i);
            if (spec.toUpperCase().equals("COMMENT")) {
                // 下一个为comment内容, 查看是否越界
                if (i + 1 >= size) {
                    return null;
                }
                return removeQuotes(specs.get(i + 1));
            }
        }
        return null;
    }

}
