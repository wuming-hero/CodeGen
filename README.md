# CodeGen

This plugin helps you to generate specific template code by create table statement or database .

[![release](https://img.shields.io/badge/IDEA-v0.8-blue.svg)](https://plugins.jetbrains.com/plugin/9574-codegen) [![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/hehaiyangwork/CodeGen/blob/master/LICENSE)

![CodeGen](https://raw.githubusercontent.com/hehaiyangwork/CodeGen/master/codegen.gif)

## Install

Preferences -> Plugins -> Browse repositories -> [search] CodeGen

## Usage

`shift + command + g` OR `Tools -> CodeGen`

## Options

Preferences -> Tools -> CodeGen
    
1. Generation type(Text/Database)
2. In-house variables and (handlebars) helpers
3. Predefined variables
4. Custom template groups
5. Data sources manager

### In-house Variables

```
{{$.Year}} current year

{{$.Month}} current month

{{$.Day}} current day of the month

{{$.Hour}} current hour

{{$.Minute}} current minute

{{$.Second}} current second

{{$.Date}} current system date

{{$.Now}} current system time

{{$.serialVersionUID}} current model serialVersionUID

{{$.Project}} the name of the current project

{{$.Module1} the name of the current selected module

{{$.Package1} the name of the current selected package
```

### In-house Helpers

```
- 首尾拼接字符
{{Join 'ABC' '#' '%'}}  => #ABC%

- 首字母小写
{{LowerCase 'ABC'}} => aBC

- 首字母大写
{{LowerCase 'abc'}} => Abc

- 驼峰分割
{{Split 'ABcD' '_'}} => A_bc_d

* 组合用法
{{Split (Join (LowerCase 'AbcDefGhi') '$' '%') '_'}} => $abc_def_ghi%
```

### Text Cases

- markdown

```
| id    |  BIGINT(20)  | ID     |
| pid   |  BIGINT(20)  | 父级ID  |
| name  |  VARCHAR(64) | 名称    |
| level |  INT(11)     | 级别    |
| pinyin | VARCHAR(100) | 拼音 |
| english_name | VARCHAR(100) | 英文名 |
| unicode_code | VARCHAR(200) | ASCII码 |
| checker_name | VARCHAR(64) | 审核用户名称 |
| order_no | INT(11) | 排序号 |
```

- sqlScript

```sql
CREATE TABLE `t_addresses` (
  `id` BIGINT(20) NOT NULL,
  `pid` BIGINT(20) DEFAULT NULL COMMENT '父级ID',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '名称',
  `level` INT(11) DEFAULT NULL COMMENT '级别',
  `pinyin` VARCHAR(100) DEFAULT NULL COMMENT '拼音',
  `english_name` VARCHAR(100) DEFAULT NULL COMMENT '英文名',
  `unicode_code` VARCHAR(200) DEFAULT NULL COMMENT 'ASCII码',
  `order_no` INT(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## Idea sdk docs

http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started.html

> How to setup

* Clone the project, and open with IDEA (Community).
* Modify module type in `.idea/CodeGen.iml` from `JAVA_MODULE` to `PLUGIN_MODULE`.
* Change the project's module compile output path to `/XXX/XXX/CodeGen/out` in `Project Structure -> Modules -> CodeGen -> paths`. You can also modify the Plugin Deployment `plugin.xml path`.
* ~~Install lombok plugin. In **Preferences -> Build -> Annotation Processors**.~~ (lombok has been removed.)
	* ~~**Enable annotation processing**~~.
	* ~~Modify **Production sources directory** to **out**~~
	* ~~Modify **Test sources directory** to **out**~~
* Run CodeGen and enjoy it.

> Welcome to contribute

## TODO

* [TODO](https://github.com/hehaiyangwork/CodeGen/projects/1)

## Guide

* [Guide_cn](https://github.com/hehaiyangwork/CodeGen/blob/master/Guide_cn.md)

## Change Logs

* [CHANGELOG](https://github.com/hehaiyangwork/CodeGen/blob/master/CHANGELOG.md)

## Contributions

* [hehaiyangwork](https://github.com/hehaiyangwork)
* [IceMimosa](https://github.com/IceMimosa)

## License
Copyright © 2017 [MIT License](https://spdx.org/licenses/MIT.html)


