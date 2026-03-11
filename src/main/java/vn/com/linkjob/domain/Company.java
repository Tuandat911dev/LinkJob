package vn.com.linkjob.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Company {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    String address;

    String logo;

    Instant createdAt;

    Instant updatedAt;

    String createdBy;

    String updatedBy;
}
