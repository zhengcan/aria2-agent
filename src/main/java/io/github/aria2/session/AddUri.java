package io.github.aria2.session;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aria2.Download;
import io.github.aria2.impl.Aria2Impl;

/**
 * Created by canzheng on 2017/5/31.
 */
class AddUri extends AbstractCommand<Download> {
  private final String uri;

  public AddUri(Aria2SessionImpl session, String uri) {
    super(session, ((Aria2Impl) session.getAria2()).addUri(uri));
    this.uri = uri;
  }

  @Override
  public void onResponse(JsonNode response) {
    String gid = response.asText();
    this.future.complete(new DownloadImpl(this.session, gid));
  }
}
