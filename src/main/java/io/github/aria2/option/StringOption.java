package io.github.aria2.option;

/**
 * String option
 * Created by canzheng on 2017/4/4.
 */
public class StringOption {
  /**
   * Option name
   */
  private final String name;

  /**
   * Create a new string option
   *
   * @param name
   */
  StringOption(String name) {
    this.name = name;
  }

  /**
   * Get option name
   *
   * @return
   */
  public String getName() {
    return name;
  }
}
