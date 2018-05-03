package com.jez.commons;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by JEZ on 2017/5/8.
 */
public class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  private JsonUtils() {
  }

  public static String toJson(Object object) {
    try {
      return object != null ? OBJECT_MAPPER.writeValueAsString(object) : null;
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return json != null && clazz != null ? OBJECT_MAPPER.readerFor(clazz).readValue(json) : null;
    } catch (Exception e) {
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyType, Class<V> valueType) {
    return fromJson(json, Map.class);
  }

  public static Map<String, Object> fromJsonToMap(String json) {
    return fromJsonToMap(json, String.class, Object.class);
  }

  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, V> convertMap(Object object, Class<K> keyType, Class<V> valueType) {
    return OBJECT_MAPPER.convertValue(object, Map.class);
  }

  public static Map<String, Object> convertMap(Object object) {
    return convertMap(object, String.class, Object.class);
  }

  public static JsonNode fromJsonToJsonNode(String json) {
    try {
      return OBJECT_MAPPER.readTree(json);
    } catch (IOException e) {
      return null;
    }
  }

}
