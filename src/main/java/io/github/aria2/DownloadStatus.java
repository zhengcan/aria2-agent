package io.github.aria2;

import java.util.List;

/**
 * The status of download task
 * Created by canzheng on 2017/5/31.
 */
public interface DownloadStatus {
  /**
   * Get the download task
   * @return
   */
  Download getDownload();

  /**
   * Get the GID of download task
   * @return
   */
  String getGid();

  int getNumPieces();

  int getPieceLength();

  String getStatus();

  long getTotalLength();

  int getBitField();

  long getCompletedLength();

  int getConnections();

  String getDir();

  int getDownloadSpeed();

  int getErrorCode();

  String getErrorMessage();

  List<FileInfo> getFiles();

  long getUploadLength();

  int getUploadSpeed();
}
