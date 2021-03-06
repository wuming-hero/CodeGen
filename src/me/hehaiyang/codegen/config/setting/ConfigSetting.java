package me.hehaiyang.codegen.config.setting;

/**
 * Desc:
 * Mail: hehaiyang@terminus.io
 * Date: 2017/5/10
 */
public class ConfigSetting {

  private boolean textRadio = false;
  private boolean databaseRadio = true;
  private String ignoreFields = "id,created_at,updated_at";

  public boolean isTextRadio() {
    return textRadio;
  }

  public void setTextRadio(boolean textRadio) {
    this.textRadio = textRadio;
  }

  public boolean isDatabaseRadio() {
    return databaseRadio;
  }

  public void setDatabaseRadio(boolean databaseRadio) {
    this.databaseRadio = databaseRadio;
  }

  public String getIgnoreFields() {
    return ignoreFields;
  }

  public void setIgnoreFields(String ignoreFields) {
    this.ignoreFields = ignoreFields;
  }
}
