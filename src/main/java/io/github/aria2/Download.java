package io.github.aria2;

import java.util.concurrent.CompletionStage;

/**
 * Aria2 download task
 * Created by canzheng on 2017/5/31.
 */
public interface Download {
  /**
   * Get the GID of current download
   *
   * @return
   */
  String getGid();

  /**
   * Send "tellStatus" to aria2 to get the latest download status.
   *
   * @return
   */
  CompletionStage<DownloadStatus> tellStatus();

  /**
   * aria2.onDownloadStart event
   *
   * @return
   */
  CompletionStage<Download> onStart();

  /**
   * aria2.onDownloadPause event
   *
   * @return
   */
  CompletionStage<Download> onPause();

  /**
   * aria2.onDownloadStop event
   *
   * @return
   */
  CompletionStage<Download> onStop();

  /**
   * aria2.onDownloadComplete event
   *
   * @return
   */
  CompletionStage<Download> onComplete();

  /**
   * aria2.onDownloadError event
   *
   * @return
   */
  CompletionStage<Download> onError();
}
