package io.github.aria2.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aria2.option.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by canzheng on 2017/4/4.
 */
public abstract class OptionAwareImpl<T extends OptionAware<T>> implements OptionAware<T> {
  protected ObjectNode options;

  @Override
  public T setOption(String option, String value) {
    if (this.options != null) {
      this.options.put(option, value);
    }
    return (T) this;
  }

  @Override
  public T setOption(StringOption option, String value) {
    if (this.options != null) {
      this.options.put(option.getName(), value);
    }
    return (T) this;
  }

  @Override
  public T setOption(NumericOption option, int value) {
    if (this.options != null) {
      this.options.put(option.getName(), value);
    }
    return (T) this;
  }

  @Override
  public T setOption(BooleanOption option, boolean value) {
    if (this.options != null) {
      this.options.put(option.getName(), String.valueOf(value));
    }
    return (T) this;
  }

  @Override
  public T setOption(CheckSumOption option, CheckSumType type, String digest) {
    if (this.options != null) {
      this.options.put(option.getText(), String.format("%s=%s", type.getValue(), digest));
    }
    return (T) this;
  }

  @Override
  public String getOption(StringOption option) {
    if (this.options == null) {
      return null;
    }
    JsonNode jsonNode = this.options.get(option.getName());
    return jsonNode == null ? null : jsonNode.asText();
  }

  @Override
  public int getOption(NumericOption option) {
    if (this.options == null) {
      return option.getDefaultValue();
    }
    JsonNode jsonNode = this.options.get(option.getName());
    return jsonNode == null ? option.getDefaultValue() : jsonNode.asInt(option.getDefaultValue());
  }

  @Override
  public boolean getOption(BooleanOption option) {
    if (this.options == null) {
      return option.getDefaultValue();
    }
    JsonNode jsonNode = this.options.get(option.getName());
    return jsonNode == null ? option.getDefaultValue() : jsonNode.asBoolean(option.getDefaultValue());
  }

  @Override
  public Map<String, String> getOptions() {
    if (this.options == null) {
      return Collections.emptyMap();
    }
    Map<String, String> result = new HashMap<>();
    for (Iterator<Map.Entry<String, JsonNode>> it = this.options.fields(); it.hasNext(); ) {
      Map.Entry<String, JsonNode> entry = it.next();
      result.put(entry.getKey(), entry.getValue().textValue());
    }
    return result;
  }
}
