package io.github.aria2.session;

import io.github.aria2.Download;
import io.github.aria2.DownloadStatus;
import io.github.aria2.FileInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by canzheng on 2017/5/31.
 */
class DownloadStatusImpl implements DownloadStatus {
  private final Aria2SessionImpl session;
  private final Download download;
  private int bitField;
  private long completedLength;
  private int connections;
  private String dir;
  private int downloadSpeed;
  private int errorCode;
  private String errorMessage;
  private List<FileInfo> files;
  private int numPieces;
  private int pieceLength;
  private String status;
  private long totalLength;
  private long uploadLength;
  private int uploadSpeed;

  public DownloadStatusImpl(Aria2SessionImpl session, Download download) {
    this.session = session;
    this.download = download;
    this.files = new ArrayList<>();
  }

  @Override
  public Download getDownload() {
    return this.download;
  }

  @Override
  public String getGid() {
    return this.download.getGid();
  }

  @Override
  public int getBitField() {
    return bitField;
  }

  public void setBitField(int bitField) {
    this.bitField = bitField;
  }

  @Override
  public long getCompletedLength() {
    return completedLength;
  }

  public void setCompletedLength(long completedLength) {
    this.completedLength = completedLength;
  }

  @Override
  public int getConnections() {
    return connections;
  }

  public void setConnections(int connections) {
    this.connections = connections;
  }

  @Override
  public String getDir() {
    return dir;
  }

  public void setDir(String dir) {
    this.dir = dir;
  }

  @Override
  public int getDownloadSpeed() {
    return downloadSpeed;
  }

  public void setDownloadSpeed(int downloadSpeed) {
    this.downloadSpeed = downloadSpeed;
  }

  @Override
  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public List<FileInfo> getFiles() {
    return this.files;
  }

  public void addFile(FileInfo file) {
    this.files.add(file);
  }

  @Override
  public int getNumPieces() {
    return numPieces;
  }

  public void setNumPieces(int numPieces) {
    this.numPieces = numPieces;
  }

  @Override
  public int getPieceLength() {
    return pieceLength;
  }

  public void setPieceLength(int pieceLength) {
    this.pieceLength = pieceLength;
  }

  @Override
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public long getTotalLength() {
    return totalLength;
  }

  public void setTotalLength(long totalLength) {
    this.totalLength = totalLength;
  }

  @Override
  public long getUploadLength() {
    return uploadLength;
  }

  public void setUploadLength(long uploadLength) {
    this.uploadLength = uploadLength;
  }

  @Override
  public int getUploadSpeed() {
    return uploadSpeed;
  }

  public void setUploadSpeed(int uploadSpeed) {
    this.uploadSpeed = uploadSpeed;
  }

  @Override
  public String toString() {
    return "DownloadStatusImpl{" +
        "session=" + session +
        ", download=" + download +
        ", bitField=" + bitField +
        ", completedLength=" + completedLength +
        ", connections=" + connections +
        ", dir='" + dir + '\'' +
        ", downloadSpeed=" + downloadSpeed +
        ", errorCode=" + errorCode +
        ", errorMessage='" + errorMessage + '\'' +
        ", files=" + files +
        ", numPieces=" + numPieces +
        ", pieceLength=" + pieceLength +
        ", status='" + status + '\'' +
        ", totalLength=" + totalLength +
        ", uploadLength=" + uploadLength +
        ", uploadSpeed=" + uploadSpeed +
        '}';
  }
}
