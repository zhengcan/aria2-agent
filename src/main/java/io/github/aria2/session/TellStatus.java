package io.github.aria2.session;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aria2.Download;
import io.github.aria2.DownloadStatus;
import io.github.aria2.impl.Aria2Impl;

import java.util.Iterator;

/**
 * Created by canzheng on 2017/5/31.
 */
class TellStatus extends AbstractCommand<DownloadStatus> {
  private final Download download;

  public TellStatus(Aria2SessionImpl session, Download download) {
    super(session, ((Aria2Impl) session.getAria2()).tellStatus(download.getGid()));
    this.download = download;
  }

  @Override
  public void onResponse(JsonNode response) {
    DownloadStatusImpl downloadStatus = new DownloadStatusImpl(this.session, this.download);
    downloadStatus.setBitField(response.findPath("bitfield").asInt());
    downloadStatus.setCompletedLength(response.findParent("completedLength").asLong());
    downloadStatus.setConnections(response.findPath("connections").asInt());
    downloadStatus.setDir(response.findPath("dir").asText());
    downloadStatus.setDownloadSpeed(response.findPath("downloadSpeed").asInt());
    downloadStatus.setErrorCode(response.findPath("errorCode").asInt());
    downloadStatus.setErrorMessage(response.findPath("errorMessage").asText());
    Iterator<JsonNode> iterable = response.findPath("files").elements();
    while (iterable.hasNext()) {
      JsonNode fileNode = iterable.next();
      FileInfoImpl fileInfo = new FileInfoImpl();
      fileInfo.setCompletedLength(fileNode.findPath("completedLength").asLong());
      fileInfo.setIndex(fileNode.findPath("index").asInt());
      fileInfo.setLength(fileNode.findPath("length").asLong());
      fileInfo.setPath(fileNode.findPath("path").asText());
      fileInfo.setSelected(fileNode.findPath("selected").asBoolean());
      // Ignore uris
      downloadStatus.addFile(fileInfo);
    }
    downloadStatus.setNumPieces(response.findPath("numPieces").asInt());
    downloadStatus.setPieceLength(response.findPath("pieceLength").asInt());
    downloadStatus.setStatus(response.findPath("status").asText());
    downloadStatus.setTotalLength(response.findPath("totalLength").asLong());
    downloadStatus.setUploadLength(response.findPath("uploadLength").asLong());
    downloadStatus.setUploadSpeed(response.findPath("uploadSpeed").asInt());
    this.future.complete(downloadStatus);
  }
}
