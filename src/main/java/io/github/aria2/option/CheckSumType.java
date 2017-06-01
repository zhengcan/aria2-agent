package io.github.aria2.option;

/**
 * Created by canzheng on 2017/4/4.
 */
public enum CheckSumType {
  SHA1("sha-1"),
  SHA224("sha-224"),
  SHA256("sha-256"),
  SHA384("sha-384"),
  SHA512("sha-512"),
  MD5("md5"),
  ADLER32("adler32"),
  /* over */;

  private final String value;

  CheckSumType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
