package io.github.aria2.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import io.github.aria2.Aria2;
import io.github.aria2.Aria2Session;
import io.github.aria2.Constants;
import io.github.aria2.rpc.RpcAction;
import io.github.aria2.rpc.RpcActionFactory;
import io.github.aria2.session.Aria2SessionImpl;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletionStage;

/**
 * Aria2 instance
 * Created by canzheng on 2017/4/15.
 */
public class Aria2Impl extends OptionAwareImpl<Aria2> implements Aria2, RpcActionFactory<Aria2Impl> {
  private final String webSocketUrl;
  private final String secret;
  private final ObjectMapper mapper;
  private final AsyncHttpClient httpClient;

  public Aria2Impl(@NotNull Config config) {
    if (config == null) {
      throw new IllegalArgumentException("config should not be null.");
    }

    config = config.withFallback(
        ConfigFactory.parseResources(Aria2.class, "reference.conf").resolveWith(ConfigFactory.load())
    );

    this.webSocketUrl = config.getString(Constants.CONFIG_WEBSOCKET_URL);
    if (StringUtils.isEmpty(this.webSocketUrl)) {
      throw new IllegalArgumentException("No " + Constants.CONFIG_WEBSOCKET_URL);
    }

    if (config.hasPath(Constants.CONFIG_WEBSOCKET_SECRET)) {
      this.secret = config.getString(Constants.CONFIG_WEBSOCKET_SECRET);
    } else {
      this.secret = null;
    }

    this.mapper = new ObjectMapper();
    this.options = this.mapper.createObjectNode();

    if (config.hasPath(Constants.CONFIG_OPTIONS)) {
      ConfigObject options = config.getObject(Constants.CONFIG_OPTIONS);
      for (Map.Entry<String, ConfigValue> entry : options.entrySet()) {
        this.setOption(entry.getKey(), entry.getValue().unwrapped().toString());
      }
    }

    this.httpClient = new DefaultAsyncHttpClient(
        new DefaultAsyncHttpClientConfig.Builder()
            .setConnectTimeout(config.getInt(Constants.CONFIG_WEBSOCKET_TIMEOUT))
            .build()
    );
  }

  @Override
  public CompletionStage<Aria2Session> newSession() {
    ListenableFuture<WebSocket> future = this.httpClient.prepareGet(this.webSocketUrl).execute(
        new WebSocketUpgradeHandler.Builder().build()
    );
    return future.toCompletableFuture().thenApply(webSocket -> new Aria2SessionImpl(this, webSocket));
  }

  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public String getSecret() {
    return secret;
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  private RpcActionImpl createMethod(String method) {
    return new RpcActionImpl(this.mapper, method, this.secret, null);
  }

  private RpcActionImpl createMethod(String method, ObjectNode options) {
    return new RpcActionImpl(this.mapper, method, this.secret, options);
  }

  @Override
  public RpcAction addUri(String uri) {
    return this.createMethod(Constants.ADD_URI, this.options).addParam(Arrays.asList(uri));
  }

  @Override
  public RpcAction addUri(String... uris) {
    return this.createMethod(Constants.ADD_URI, this.options).addParam(uris);
  }

  @Override
  public RpcAction remove(String gid) {
    return this.createMethod(Constants.REMOVE).addParam(gid);
  }

  @Override
  public RpcAction forceRemove(String gid) {
    return this.createMethod(Constants.FORCE_REMOVE).addParam(gid);
  }

  @Override
  public RpcAction pause(String gid) {
    return this.createMethod(Constants.PAUSE).addParam(gid);
  }

  @Override
  public RpcAction pauseAll() {
    return this.createMethod(Constants.PAUSE_ALL);
  }

  @Override
  public RpcAction forcePause(String gid) {
    return this.createMethod(Constants.FORCE_PAUSE).addParam(gid);
  }

  @Override
  public RpcAction forcePauseAll() {
    return this.createMethod(Constants.FORCE_PAUSE_ALL);
  }

  @Override
  public RpcAction unpause(String gid) {
    return this.createMethod(Constants.UNPAUSE).addParam(gid);
  }

  @Override
  public RpcAction unpauseAll() {
    return this.createMethod(Constants.UNPAUSE_ALL);
  }

  @Override
  public RpcAction tellStatus(String gid) {
    return this.createMethod(Constants.TELL_STATUS).addParam(gid);
  }

  @Override
  public RpcAction tellStatus(String gid, String... keys) {
    return this.createMethod(Constants.TELL_STATUS).addParam(gid).addParam(keys);
  }
}
