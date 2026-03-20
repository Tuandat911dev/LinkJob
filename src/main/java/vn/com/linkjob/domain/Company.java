package vn.com.linkjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Company extends Auditable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    String address;

    String logo;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    List<User> users;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    List<Job> jobs;
}
