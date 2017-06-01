package io.github.aria2.session;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by canzheng on 2017/5/31.
 */
class CompletionStageAdapter<T> implements CompletionStage<T> {
  protected final CompletableFuture<T> future;

  public CompletionStageAdapter(CompletableFuture<T> future) {
    this.future = future;
  }

  @Override
  public <U> CompletionStage<U> thenApply(Function<? super T, ? extends U> fn) {
    return this.future.thenApply(fn);
  }

  @Override
  public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
    return this.future.thenApplyAsync(fn);
  }

  @Override
  public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
    return this.future.thenApplyAsync(fn, executor);
  }

  @Override
  public CompletionStage<Void> thenAccept(Consumer<? super T> action) {
    return this.future.thenAccept(action);
  }

  @Override
  public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action) {
    return this.future.thenAcceptAsync(action);
  }

  @Override
  public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
    return this.future.thenAcceptAsync(action, executor);
  }

  @Override
  public CompletionStage<Void> thenRun(Runnable action) {
    return this.future.thenRun(action);
  }

  @Override
  public CompletionStage<Void> thenRunAsync(Runnable action) {
    return this.future.thenRunAsync(action);
  }

  @Override
  public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor) {
    return this.future.thenRunAsync(action, executor);
  }

  @Override
  public <U, V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
    return this.future.thenCombine(other, fn);
  }

  @Override
  public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
    return this.future.thenCombineAsync(other, fn);
  }

  @Override
  public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
    return this.future.thenCombineAsync(other, fn, executor);
  }

  @Override
  public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
    return this.future.thenAcceptBoth(other, action);
  }

  @Override
  public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
    return this.future.thenAcceptBothAsync(other, action);
  }

  @Override
  public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
    return this.future.thenAcceptBothAsync(other, action, executor);
  }

  @Override
  public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
    return this.future.runAfterBoth(other, action);
  }

  @Override
  public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
    return this.future.runAfterBothAsync(other, action);
  }

  @Override
  public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
    return this.future.runAfterBothAsync(other, action, executor);
  }

  @Override
  public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
    return this.future.applyToEither(other, fn);
  }

  @Override
  public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
    return this.future.applyToEitherAsync(other, fn);
  }

  @Override
  public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
    return this.future.applyToEitherAsync(other, fn, executor);
  }

  @Override
  public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
    return this.future.acceptEither(other, action);
  }

  @Override
  public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
    return this.future.acceptEitherAsync(other, action);
  }

  @Override
  public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
    return this.future.acceptEitherAsync(other, action, executor);
  }

  @Override
  public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
    return this.future.runAfterEither(other, action);
  }

  @Override
  public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
    return this.future.runAfterEitherAsync(other, action);
  }

  @Override
  public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
    return this.future.runAfterEitherAsync(other, action, executor);
  }

  @Override
  public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
    return this.future.thenCompose(fn);
  }

  @Override
  public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
    return this.future.thenComposeAsync(fn);
  }

  @Override
  public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
    return this.future.thenComposeAsync(fn, executor);
  }

  @Override
  public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn) {
    return this.future.exceptionally(fn);
  }

  @Override
  public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
    return this.future.whenComplete(action);
  }

  @Override
  public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
    return this.future.whenCompleteAsync(action);
  }

  @Override
  public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
    return this.future.whenCompleteAsync(action, executor);
  }

  @Override
  public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
    return this.future.handle(fn);
  }

  @Override
  public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
    return this.future.handleAsync(fn);
  }

  @Override
  public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
    return this.future.handleAsync(fn, executor);
  }

  @Override
  public CompletableFuture<T> toCompletableFuture() {
    return this.future;
  }
}
