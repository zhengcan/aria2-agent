package io.github.aria2.option;

/**
 * Numeric option
 * Created by canzheng on 2017/4/4.
 */
public class NumericOption {
  /**
   * Option name
   */
  private final String name;
  /**
   * Default value
   */
  private final int defaultValue;

  NumericOption(String name, int defaultValue) {
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
  public int getDefaultValue() {
    return defaultValue;
  }
}
