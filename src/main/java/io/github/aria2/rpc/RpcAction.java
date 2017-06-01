package io.github.aria2.rpc;

import io.github.aria2.option.OptionAware;

import java.util.concurrent.CompletionStage;

/**
 * Created by canzheng on 2017/4/4.
 */
public interface RpcAction<T> extends OptionAware<RpcAction<T>> {
  String getId();

  String getMethod();

  CompletionStage<T> toCompletionStage(Class<T> clazz);

  boolean complete(T value);

  boolean completeExceptionally(Throwable throwable);
}
