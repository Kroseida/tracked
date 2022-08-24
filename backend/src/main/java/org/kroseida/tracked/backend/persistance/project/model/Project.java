package org.kroseida.tracked.backend.persistance.project.model;

import lombok.*;
import org.kroseida.tracked.backend.persistance.organization.model.Organization;
import org.kroseida.tracked.backend.persistance.report.model.Report;
import org.kroseida.tracked.backend.persistance.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String name;
  @ManyToOne
  private Organization organization;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
  private List<Report> reports;
  @ManyToMany(fetch = FetchType.LAZY)
  private List<User> users;

}
