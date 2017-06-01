package io.github.aria2.option;

import java.util.Map;

/**
 * Aria2 options
 * Created by canzheng on 2017/4/4.
 */
public interface OptionAware<T extends OptionAware> {
  /**
   * Set option
   *
   * @param option
   * @param value
   * @return
   */
  T setOption(String option, String value);

  /**
   * Set string option
   *
   * @param option
   * @param value
   * @return
   */
  T setOption(StringOption option, String value);

  /**
   * Set numeric option
   *
   * @param option
   * @param value
   * @return
   */
  T setOption(NumericOption option, int value);

  /**
   * Set boolean option
   *
   * @param option
   * @param value
   * @return
   */
  T setOption(BooleanOption option, boolean value);

  /**
   * Set checksum option
   *
   * @param option
   * @param type
   * @param digest
   * @return
   */
  T setOption(CheckSumOption option, CheckSumType type, String digest);

  /**
   * Get string option
   *
   * @param option
   * @return
   */
  String getOption(StringOption option);

  /**
   * Get numeric option
   *
   * @param option
   * @return
   */
  int getOption(NumericOption option);

  /**
   * Get boolean option
   *
   * @param option
   * @return
   */
  boolean getOption(BooleanOption option);

  /**
   * Get all options
   *
   * @return
   */
  Map<String, String> getOptions();
}
