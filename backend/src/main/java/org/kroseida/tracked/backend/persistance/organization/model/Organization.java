package org.kroseida.tracked.backend.persistance.organization.model;

import lombok.*;
import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.kroseida.tracked.backend.persistance.report.model.Report;
import org.kroseida.tracked.backend.persistance.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a organization stored in the database.
 * <p>
 * The organization is identified by its name or id.
 * <p>
 * The username is the unique external identifier of the user in purpose of authentication.
 * (e.g Username/Password Authentication)
 * <p>
 * Users can have multiple authentication options.
 */
@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Organization {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String name;
  private String description;
  private boolean active;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
  private List<Project> projects;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
  private List<Activity> activities;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
  private List<Report> reports;
  @ManyToMany(fetch = FetchType.LAZY)
  private List<User> users;

}
