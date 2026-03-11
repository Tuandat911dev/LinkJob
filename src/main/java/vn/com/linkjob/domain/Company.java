package vn.com.linkjob.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    String address;

    String logo;

    Instant createdAt;

    Instant updatedAt;

    String createdBy;

    String updatedBy;
}
