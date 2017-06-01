package io.github.aria2.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aria2.rpc.RpcAction;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by canzheng on 2017/4/4.
 */
class RpcActionImpl<T> extends OptionAwareImpl<RpcAction<T>> implements RpcAction<T> {
  private final ObjectMapper mapper;
  private final String id;
  private final ObjectNode request;
  private final ArrayNode params;
//  private final ObjectNode options;
  private final CompletableFuture<T> future;

  public RpcActionImpl(ObjectMapper mapper, String method, String secret, ObjectNode options) {
    this.mapper = mapper;

    this.options = options;

    this.params = mapper.createArrayNode();
    if (secret != null && !secret.isEmpty()) {
      this.params.add("token:" + secret);
    }

    this.id = RandomStringUtils.randomAlphanumeric(16) + "-" + System.currentTimeMillis();

    this.request = mapper.createObjectNode()
      .put("jsonrpc", "2.0")
      .put("id", id)
      .put("method", method)
      .putPOJO("params", this.params);

    this.future = new CompletableFuture<>();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getMethod() {
    return this.request.get("method").asText();
  }

  RpcActionImpl addParam(List<String> values) {
    ArrayNode arrayNode = this.mapper.createArrayNode();
    for (String value : values) {
      arrayNode.add(value);
    }
    this.params.add(arrayNode);
    return this;
  }

  RpcActionImpl addParam(String[] values) {
    ArrayNode arrayNode = this.mapper.createArrayNode();
    for (String value : values) {
      arrayNode.add(value);
    }
    this.params.add(arrayNode);
    return this;
  }

  RpcActionImpl addParam(String value) {
    this.params.add(value);
    return this;
  }

  @Override
  public CompletionStage<T> toCompletionStage(Class<T> clazz) {
    return this.future;
  }

  @Override
  public boolean complete(T value) {
    return this.future.complete(value);
  }

  @Override
  public boolean completeExceptionally(Throwable throwable) {
    return this.future.completeExceptionally(throwable);
  }

  @Override
  public String toString() {
    if (this.options != null && this.options.size() > 0) {
      return this.request.deepCopy()
        .putPOJO("params", this.params.deepCopy().addPOJO(this.options))
        .toString();
    }
    return this.request.toString();
  }
}
