package org.kroseida.tracked.backend.persistance.report.model;

import lombok.*;
import org.kroseida.tracked.backend.persistance.activity.model.Activity;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.kroseida.tracked.backend.persistance.user.model.User;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String description;
  private long start;
  private long end;
  @ManyToOne
  private Organization organization;
  @ManyToOne
  private Project project;
  @ManyToOne
  private Activity activity;
  @ManyToOne
  private User user;

}
