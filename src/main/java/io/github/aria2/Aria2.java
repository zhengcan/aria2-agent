package io.github.aria2;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import io.github.aria2.impl.Aria2Impl;
import io.github.aria2.option.OptionAware;

import java.util.concurrent.CompletionStage;

/**
 * Aria2 instance
 * Created by canzheng on 2017/4/4.
 */
public interface Aria2 extends OptionAware<Aria2> {
  /**
   * Create a new aria2 instance
   *
   * @param webSocketUrl WebSocket RPC address
   * @return
   */
  static Aria2 create(String webSocketUrl) {
    return new Aria2Impl(
        ConfigFactory.empty()
            .withValue(Constants.CONFIG_WEBSOCKET_URL, ConfigValueFactory.fromAnyRef(webSocketUrl))
    );
  }

  /**
   * Create a new aria2 instance
   *
   * @param webSocketUrl WebSocket RPC address
   * @param secret       RPC secret key
   * @return
   */
  static Aria2 create(String webSocketUrl, String secret) {
    return new Aria2Impl(
        ConfigFactory.empty()
            .withValue(Constants.CONFIG_WEBSOCKET_URL, ConfigValueFactory.fromAnyRef(webSocketUrl))
            .withValue(Constants.CONFIG_WEBSOCKET_SECRET, ConfigValueFactory.fromAnyRef(secret))
    );
  }

  /**
   * Create a new aria2 instance
   *
   * @param config Aria2 config
   * @return
   */
  static Aria2 create(Config config) {
    return new Aria2Impl(config);
  }

  /**
   * Launch a new aria2 session via webSocket rpc
   *
   * @return
   */
  CompletionStage<Aria2Session> newSession();
}
