package io.github.aria2.session;

import io.github.aria2.rpc.RpcAction;
import org.asynchttpclient.ws.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * Created by canzheng on 2017/5/31.
 */
abstract class AbstractCommand<T> extends CompletionStageAdapter<T> implements Command {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class);

  protected final Aria2SessionImpl session;
  protected final RpcAction rpc;

  public AbstractCommand(Aria2SessionImpl session, RpcAction rpc) {
    super(new CompletableFuture<T>());
    this.session = session;
    this.rpc = rpc;
  }

  @Override
  public String getId() {
    return this.rpc.getId();
  }

  @Override
  public void execute(WebSocket webSocket) {
    String message = this.buildMessage();
    LOGGER.debug("Send message: {}", message);
    webSocket.sendMessage(this.buildMessage());
  }

  protected String buildMessage() {
    return this.rpc.toString();
  }
}
