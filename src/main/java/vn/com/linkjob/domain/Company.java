package vn.com.linkjob.domain;

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
    List<User> users;
}
