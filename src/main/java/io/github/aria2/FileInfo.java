package io.github.aria2;

/**
 * The info of downloaded file
 * Created by canzheng on 2017/5/31.
 */
public interface FileInfo {
  String getPath();

  long getCompletedLength();

  int getIndex();

  long getLength();

  boolean isSelected();
}
