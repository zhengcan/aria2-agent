package io.github.aria2.option;

/**
 * Boolean option
 * Created by canzheng on 2017/4/4.
 */
public class BooleanOption {
  /**
   * Option name
   */
  private final String name;
  /**
   * Default value
   */
  private final boolean defaultValue;

  BooleanOption(String name, boolean defaultValue) {
    this.name = name;
    this.defaultValue = defaultValue;
  }

  /**
   * Get option name
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Get default value
   *
   * @return
   */
  public boolean getDefaultValue() {
    return defaultValue;
  }
}
