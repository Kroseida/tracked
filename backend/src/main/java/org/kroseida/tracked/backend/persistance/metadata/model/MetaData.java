package org.kroseida.tracked.backend.persistance.metadata.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MetaData {

  public static String STATUS = "status";
  public static String VERSION = "version";

  @Id
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;
  private String name;
  private String data;

}
