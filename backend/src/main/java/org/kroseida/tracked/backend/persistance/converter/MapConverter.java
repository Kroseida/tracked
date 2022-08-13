package org.kroseida.tracked.backend.persistance.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class MapConverter implements AttributeConverter<Map<String, String>, String> {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  @Override
  public String convertToDatabaseColumn(Map<String, String> map) {
    return GSON.toJson(map);
  }

  @Override
  public Map<String, String> convertToEntityAttribute(String str) {
    return GSON.fromJson(str, Map.class);
  }

}
