package io.github.aria2.session;

import com.fasterxml.jackson.databind.JsonNode;
import org.asynchttpclient.ws.WebSocket;

import java.util.concurrent.CompletionStage;

/**
 * Created by canzheng on 2017/5/31.
 */
interface Command {
  String getId();

  void execute(WebSocket webSocket);

  void onResponse(JsonNode response);
}
