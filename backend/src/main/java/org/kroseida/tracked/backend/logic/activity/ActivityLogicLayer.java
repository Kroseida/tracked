package org.kroseida.tracked.backend.logic.activity;

import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface ActivityLogicLayer {

  /**
   * This method will return the activity with the given id.
   *
   * @param id The id of the activity.
   * @return The activity with the given id.
   */
  Activity getActivity(UUID id);

  /**
   * This method will return all the activities stored in the database.
   *
   * @param organizationId The id of the organization to get the activity from. If null, it will return all activities.
   * @param filter         Filter by name or description.
   * @param pageable       The pageable object.
   * @return a list of all the activities.
   */
  Page<Activity> getActivities(UUID organizationId, String filter, Pageable pageable);

  /**
   * This method will create a new activity in the database.
   *
   * @param name           The name of the activity.
   * @param description    The description of the activity.
   * @param active         The active status of the activity.
   * @param organizationId The id of the organization to create the activity in.
   * @return the created activity.
   */
  Activity createActivity(String name, String description, boolean active, UUID organizationId);

  /**
   * This method will delete the activity with the given id.
   *
   * @param id The id of the activity.
   */
  void deleteActivity(UUID id);

  /**
   * This method will update the activity with the given id.
   * <p>
   * Setting the one field to null will not update the target field.
   * e.g. if the name is null, the name will not be updated.
   *
   * @param id          The id of the activity.
   * @param name        The new name of the activity.
   * @param description The new description of the activity.
   * @param active      The new active state of the activity.
   */
  void updateActivity(UUID id, String name, String description, Boolean active);
}
