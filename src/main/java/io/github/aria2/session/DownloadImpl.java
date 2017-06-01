package io.github.aria2.session;

import io.github.aria2.Constants;
import io.github.aria2.Download;
import io.github.aria2.DownloadStatus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by canzheng on 2017/5/31.
 */
class DownloadImpl implements Download {
  private final Aria2SessionImpl session;
  private final String gid;
  private final CompletableFuture<Download> onStartFuture;
  private final CompletableFuture<Download> onPauseFuture;
  private final CompletableFuture<Download> onStopFuture;
  private final CompletableFuture<Download> onCompleteFuture;
  private final CompletableFuture<Download> onErrorFuture;


  public DownloadImpl(Aria2SessionImpl session, String gid) {
    this.session = session;
    this.gid = gid;
    this.onStartFuture = new CompletableFuture<>();
    this.onPauseFuture = new CompletableFuture<>();
    this.onStopFuture = new CompletableFuture<>();
    this.onCompleteFuture = new CompletableFuture<>();
    this.onErrorFuture = new CompletableFuture<>();
  }

  @Override
  public String getGid() {
    return this.gid;
  }

  @Override
  public CompletionStage<DownloadStatus> tellStatus() {
    return this.session.execute(new TellStatus(this.session, this));
  }

  public void onNotification(String method) {
    switch (method) {
      case Constants.ON_DOWNLOAD_START:
        this.onStartFuture.complete(this);
        break;

      case Constants.ON_DOWNLOAD_PAUSE:
        this.onPauseFuture.complete(this);
        break;

      case Constants.ON_DOWNLOAD_STOP:
        this.onStopFuture.complete(this);
        break;

      case Constants.ON_DOWNLOAD_COMPLETE:
        this.onCompleteFuture.complete(this);
        break;

      case Constants.ON_DOWNLOAD_ERROR:
        this.onErrorFuture.complete(this);
        break;

      default:
        break;
    }
  }

  @Override
  public CompletionStage<Download> onStart() {
    return this.onStartFuture;
  }

  @Override
  public CompletionStage<Download> onPause() {
    return this.onPauseFuture;
  }

  @Override
  public CompletionStage<Download> onStop() {
    return this.onStopFuture;
  }

  @Override
  public CompletionStage<Download> onComplete() {
    return this.onCompleteFuture;
  }

  @Override
  public CompletionStage<Download> onError() {
    return this.onErrorFuture;
  }
}
