package io.github.aria2.option;

/**
 * Predefined aria2 options
 * Created by canzheng on 2017/4/4.
 */
public final class Options {
  public static final StringOption GID = new StringOption("gid");
  public static final StringOption DIR = new StringOption("dir");
  public static final StringOption OUT = new StringOption("out");
  public static final StringOption ALL_PROXY = new StringOption("all-proxy");
  public static final StringOption ALL_PROXY_USER = new StringOption("all-proxy-user");
  public static final StringOption ALL_PROXY_PASSWD = new StringOption("all-proxy-passwd");
  public static final StringOption HTTP_USER = new StringOption("http-user");
  public static final StringOption HTTP_PASSWD = new StringOption("http-passwd");
  public static final StringOption USER_AGENT = new StringOption("user-agent");
  public static final StringOption FTP_USER = new StringOption("ftp_user");
  public static final StringOption FTP_PASSWD = new StringOption("ftp_passwd");
  public static final StringOption SAVE_SESSION = new StringOption("save-session");
  public static final StringOption INPUT_FILE = new StringOption("input-file");
  public static final NumericOption MAX_CONCURRENT_DOWNLOADS = new NumericOption("max-concurrent-downloads", 5);
  public static final NumericOption CONNECT_TIMEOUT = new NumericOption("newSession-timeout", 60);
  public static final NumericOption MAX_CONNECTION_PER_SERVER = new NumericOption("max-connection-per-server", 1);
  public static final BooleanOption DRY_RUN = new BooleanOption("dry-run", false);
  public static final BooleanOption CONTINUE = new BooleanOption("continue", false);
  public static final BooleanOption CHECK_INTEGRITY = new BooleanOption("check-integrity", false);
  public static final BooleanOption HTTP_ACCEPT_GZIP = new BooleanOption("http-accept-gzip", false);
  public static final BooleanOption HTTP_AUTH_CHALLENGE = new BooleanOption("http-auth-challenge", false);
  public static final BooleanOption FTP_PASV = new BooleanOption("ftp-pasv", true);
  public static final BooleanOption PAUSE = new BooleanOption("pause", false);
  public static final BooleanOption ALLOW_OVERWRITE = new BooleanOption("allow-overwrite", false);
  public static final CheckSumOption CHECK_SUM = new CheckSumOption("checksum");
}
