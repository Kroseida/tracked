package org.kroseida.tracked.backend.persistance.activity.model;

import lombok.*;
import org.kroseida.tracked.backend.persistance.project.model.Project;
import org.kroseida.tracked.backend.persistance.report.model.Report;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String name;
  private String description;
  @ManyToOne
  private Project project;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
  private List<Report> reports;

}
