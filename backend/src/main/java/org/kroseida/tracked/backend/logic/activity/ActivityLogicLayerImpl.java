package org.kroseida.tracked.backend.logic.activity;

import org.kroseida.tracked.backend.logic.activity.exception.AcitvityNotFoundException;
import org.kroseida.tracked.backend.logic.activity.exception.ActivityAlreadyExistsException;
import org.kroseida.tracked.backend.logic.organization.OrganizationLogicLayer;
import org.kroseida.tracked.backend.persistance.activity.ActivityRepository;
import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.kroseida.tracked.backend.util.logic.LogicUtils.updateField;

@Component
public class ActivityLogicLayerImpl implements ActivityLogicLayer {

  private final OrganizationLogicLayer organizationLogicLayer;
  private final ActivityRepository activityRepository;

  @Autowired
  public ActivityLogicLayerImpl(OrganizationLogicLayer organizationLogicLayer, ActivityRepository activityRepository) {
    this.organizationLogicLayer = organizationLogicLayer;
    this.activityRepository = activityRepository;
  }

  @Override
  public Activity getActivity(UUID id) {
    return activityRepository.findById(id)
        .orElseThrow(() -> new AcitvityNotFoundException());
  }

  @Override
  public Page<Activity> getActivities(UUID organizationId, String filter, Pageable pageable) {
    return activityRepository.findAll(organizationId, pageable, filter);
  }

  @Override
  public Activity createActivity(String name, String description, boolean active, UUID organizationId) {
    Organization organization = organizationLogicLayer.getOrganization(organizationId);

    if (activityRepository.findByOrganizationIdAndName(organization.getId(), name) != null) {
      throw new ActivityAlreadyExistsException();
    }

    Activity activity = Activity.builder()
        .id(UUID.randomUUID())
        .name(name)
        .description(description)
        .active(active)
        .organization(organization)
        .build();

    activityRepository.save(activity);

    return activity;
  }

  @Override
  public void deleteActivity(UUID id) {
    getActivity(id);
    activityRepository.deleteById(id);
  }

  @Override
  public void updateActivity(UUID id, String name, String description, Boolean active) {
    Activity activity = getActivity(id);
    updateField(name, activity::setName);
    updateField(description, activity::setDescription);

    updateField(active, activity::setActive);

    activityRepository.save(activity);
  }

}
