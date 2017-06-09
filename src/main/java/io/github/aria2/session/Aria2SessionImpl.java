package io.github.aria2.session;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aria2.Aria2;
import io.github.aria2.Aria2Session;
import io.github.aria2.Constants;
import io.github.aria2.Download;
import io.github.aria2.impl.Aria2Impl;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketTextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by canzheng on 2017/5/31.
 */
public class Aria2SessionImpl implements Aria2Session, WebSocketTextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(Aria2SessionImpl.class);

  private final Aria2Impl aria2;
  private final WebSocket webSocket;
  private final Map<String, Command> commands;
  private final Map<String, Download> downloads;
  private final CompletableFuture<Aria2Session> onCloseFuture;
  private final CompletableFuture<Throwable> onErrorFuture;

  public Aria2SessionImpl(Aria2Impl aria2, WebSocket websocket) {
    this.aria2 = aria2;
    this.webSocket = websocket;
    this.commands = new ConcurrentHashMap<>();
    this.downloads = new ConcurrentHashMap<>();
    this.onCloseFuture = new CompletableFuture<>();
    this.onErrorFuture = new CompletableFuture<>();

    webSocket.addWebSocketListener(this);
  }

  @Override
  public Aria2 getAria2() {
    return this.aria2;
  }

  @Override
  public void close() {
    if (this.webSocket != null) {
      try {
        this.webSocket.close();
      } catch (IOException ignore) {
        // Ignore
      }
    }
  }

  @Override
  public CompletionStage<Aria2Session> onClose() {
    return this.onCloseFuture;
  }

  @Override
  public CompletionStage<Throwable> onError() {
    return this.onErrorFuture;
  }

  @Override
  public CompletionStage<Download> addUri(String uri) {
    return this.execute(new AddUri(this, uri)).thenApply(download -> {
      this.downloads.put(download.getGid(), download);
      return download;
    });
  }

  public <C extends Command> C execute(C command) {
    this.commands.put(command.getId(), command);
    command.execute(this.webSocket);
    return command;
  }

  @Override
  public void onOpen(WebSocket websocket) {
    // NO-OP
  }

  @Override
  public void onClose(WebSocket websocket) {
    this.onCloseFuture.complete(this);
    this.webSocket.removeWebSocketListener(this);
  }

  @Override
  public void onError(Throwable t) {
    this.onErrorFuture.complete(t);
  }

  @Override
  public void onMessage(String message) {
    LOGGER.debug("Receive message: {}", message);

    JsonNode json = this.parseToJson(message);
    if (!json.isObject()) {
      throw new RuntimeException("The received message is not a json object.");
    }

    ObjectNode object = (ObjectNode) json;

    if (object.has("id")) {
      String id = object.get("id").asText();
      Command command = this.commands.remove(id);
      if (command != null) {
        JsonNode result = object.get("result");
        command.onResponse(result);
      }
      return;
    }

    if (object.has("method")) {
      ArrayNode params = (ArrayNode) object.get("params");
      String gid = params.get(0).get("gid").asText();
      Download download = this.downloads.get(gid);
      if (download != null) {
        String method = object.get("method").asText();
        ((DownloadImpl) download).onNotification(method);
        if (method.equals(Constants.ON_DOWNLOAD_STOP)
            || method.equals(Constants.ON_DOWNLOAD_COMPLETE)
            || method.equals(Constants.ON_DOWNLOAD_ERROR)) {
          this.downloads.remove(gid);
        }
        return;
      }
    }

    LOGGER.info("Drop unknown message: {}", message);
  }

  private JsonNode parseToJson(String text) {
    try {
      return this.aria2.getMapper().readTree(text);
    } catch (IOException e) {
      throw new RuntimeException("Unable to parse received message to json.", e);
    }
  }
}
