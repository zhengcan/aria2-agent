package io.github.aria2;

import java.util.concurrent.CompletionStage;

/**
 * Aria2 session
 * Created by canzheng on 2017/5/31.
 */
public interface Aria2Session {
  /**
   * Get the aria2 instance
   *
   * @return
   */
  Aria2 getAria2();

  /**
   * Add a new download task
   *
   * @param uri The remote url to download
   * @return
   */
  CompletionStage<Download> addUri(String uri);

  /**
   * Session onClose event
   *
   * @return
   */
  CompletionStage<Aria2Session> onClose();

  /**
   * Session onError event
   *
   * @return
   */
  CompletionStage<Throwable> onError();
}
