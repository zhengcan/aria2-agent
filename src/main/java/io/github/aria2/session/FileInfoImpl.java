package io.github.aria2.session;

import io.github.aria2.FileInfo;

/**
 * Created by canzheng on 2017/6/1.
 */
class FileInfoImpl implements FileInfo {
  private long completedLength;
  private int index;
  private long length;
  private String path;
  private boolean selected;

  @Override
  public long getCompletedLength() {
    return completedLength;
  }

  public void setCompletedLength(long completedLength) {
    this.completedLength = completedLength;
  }

  @Override
  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }

  @Override
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public String toString() {
    return "FileInfoImpl{" +
        "completedLength=" + completedLength +
        ", index=" + index +
        ", length=" + length +
        ", path='" + path + '\'' +
        ", selected=" + selected +
        '}';
  }
}
