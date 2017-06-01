package io.github.aria2.rpc;

/**
 * Created by canzheng on 2017/4/4.
 */
public interface RpcActionFactory<T extends RpcActionFactory> {
  RpcAction addUri(String uri);
  RpcAction addUri(String... uris);
  RpcAction remove(String gid);
  RpcAction forceRemove(String gid);
  RpcAction pause(String gid);
  RpcAction pauseAll();
  RpcAction forcePause(String gid);
  RpcAction forcePauseAll();
  RpcAction unpause(String gid);
  RpcAction unpauseAll();
  RpcAction tellStatus(String gid);
  RpcAction tellStatus(String gid, String... keys);
}
