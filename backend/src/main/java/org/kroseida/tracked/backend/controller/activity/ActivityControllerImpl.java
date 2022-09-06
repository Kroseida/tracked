package org.kroseida.tracked.backend.controller.activity;

import org.kroseida.tracked.backend.controller.activity.dto.in.ActivityCreationDto;
import org.kroseida.tracked.backend.controller.activity.dto.out.ActivityDto;
import org.kroseida.tracked.backend.logic.activity.ActivityLogicLayer;
import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.kroseida.tracked.backend.util.dto.DtoUtils;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ActivityControllerImpl implements ActivityController {

  private final ActivityLogicLayer activityLogicLayer;

  @Autowired
  public ActivityControllerImpl(ActivityLogicLayer activityLogicLayer) {
    this.activityLogicLayer = activityLogicLayer;
  }

  @Override
  public ResponseEntity<ResponseData<ActivityDto>> create(ActivityCreationDto creation) {
    return ResponseUtils.handle(() -> {
      Activity activity = this.activityLogicLayer.createActivity(
          creation.getName(),
          creation.getDescription(),
          creation.isActive(),
          creation.getOrganizationId()
      );
      return DtoUtils.dto(activity, ActivityDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Page<ActivityDto>>> list(String organizationId, String filter, Pageable pageable) {
    return ResponseUtils.handle(() -> {
      UUID organizationFilter = null;
      if (!organizationId.equals("*")) {
        organizationFilter = UUID.fromString(organizationId);
      }
      Page<Activity> activities = activityLogicLayer.getActivities(organizationFilter, filter, pageable);

      return DtoUtils.dtoPage(activities, ActivityDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> delete(String id) {
    return ResponseUtils.handle(() -> {
      activityLogicLayer.deleteActivity(UUID.fromString(id));
      return true;
    });
  }

  @Override
  public ResponseEntity<ResponseData<ActivityDto>> get(String id) {
    return ResponseUtils.handle(() -> {
      Activity activity = activityLogicLayer.getActivity(UUID.fromString(id));
      return DtoUtils.dto(activity, ActivityDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> patch(String id, ActivityCreationDto creation) {
    return ResponseUtils.handle(() -> {
      activityLogicLayer.updateActivity(
          UUID.fromString(id),
          creation.getName(),
          creation.getDescription(),
          creation.isActive()
      );
      return true;
    });
  }

}
