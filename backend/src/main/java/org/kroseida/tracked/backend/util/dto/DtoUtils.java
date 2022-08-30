package org.kroseida.tracked.backend.util.dto;

import org.kroseida.tracked.backend.controller.project.dto.out.ProjectDto;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.ReflectionUtils;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used to convert Entities to DTOs.
 */
public class DtoUtils {

  private static final Logger logger = LoggerFactory.getLogger(DtoUtils.class);

  private DtoUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Converts an Entity to a DTO.
   *
   * @param sourceObject The Entity to convert.
   * @param targetDto    The DTO class to convert to.
   * @param <T>          Type of the DTO.
   * @return The converted DTO.
   */
  public static <T extends Dto> T dto(Object sourceObject, Class<T> targetDto) {
    try {
      if (sourceObject == null) {
        return null;
      }
      T dto = targetDto.newInstance();
      for (Field targetField : targetDto.getDeclaredFields()) {
        Field sourceObjectField = ReflectionUtils.findField(sourceObject.getClass(), targetField.getName());
        if (sourceObjectField == null) {
          continue;
        }
        sourceObjectField.setAccessible(true);
        targetField.setAccessible(true);

        Object content;
        if (targetField.getType().isAssignableFrom(Dto.class) || List.of(targetField.getType().getInterfaces()).contains(Dto.class)) {
          // If the field is a DTO, we need to convert it to a DTO.
          content = dto(sourceObjectField.get(sourceObject), (Class<T>) targetField.getType());
        } else if (targetField.getType().isAssignableFrom(List.class)) {
          // If the field is a List, we need to convert it to a List of DTOs.
          ParameterizedType type = (ParameterizedType) targetField.getGenericType();
          content = dtoList((Iterable) sourceObjectField.get(sourceObject), (Class<T>) type.getActualTypeArguments()[0]);
        } else if (sourceObjectField.getType().isEnum() && targetField.getType().equals(String.class)) {
          // If the field is an enum and the target is a String, we need to convert it to a String.
          content = sourceObjectField.get(sourceObject).toString();
        } else if (sourceObjectField.getType().isEnum() && targetField.getType().isEnum()) {
          // If the field is an enum and the target is an enum, we need to convert it to target enum.
          content = targetField.getType()
              .getMethod("valueOf", String.class)
              .invoke(null, sourceObjectField.get(sourceObject).toString());
        } else if (sourceObjectField.getType().equals(LocalDate.class) && targetField.getType().equals(String.class)) {
          LocalDate object = (LocalDate) sourceObjectField.get(sourceObject);
          if (object == null) {
            content = null;
          } else {
            content = object.toString();
          }
        } else {
          // Fallback to the 1-1 conversion.
          content = sourceObjectField.get(sourceObject);
        }
        targetField.set(dto, content);
      }
      return dto;
    } catch (Exception e) {
      logger.error("", e);
    }
    return null;
  }

  /**
   * Converts a list of Entities to a list of DTOs.
   *
   * @param sourceObjects The list of Entities to convert.
   * @param targetDto     The DTO class to convert to.
   * @param <T>           Type of the DTO.
   * @return The converted list of DTOs.
   */
  public static <T extends Dto> List<T> dtoList(Iterable<?> sourceObjects, Class<T> targetDto) {
    List<T> targetList = new ArrayList<>();
    for (Object sourceObject : sourceObjects) {
      targetList.add(dto(sourceObject, targetDto));
    }
    return targetList;
  }

  public static <T extends Dto> Page<T> dtoPage(Page<?> sourceObjects, Class<T> targetDto) {
    List<T> targetList = new ArrayList<>();
    for (Object sourceObject : sourceObjects.getContent()) {
      targetList.add(dto(sourceObject, targetDto));
    }
    return new PageImpl<>(
        targetList,
        sourceObjects.getPageable(),
        sourceObjects.getTotalElements()
    );
  }

}
