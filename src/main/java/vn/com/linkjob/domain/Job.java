package vn.com.linkjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.JobLevelEnum;

import java.time.Instant;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Job extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String location;
    double salary;
    int quantity;
    @Enumerated(EnumType.STRING)
    JobLevelEnum level;
    @Column(columnDefinition = "TEXT")
    String description;
    Instant startDate;
    Instant endDate;
    boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties(value = {"jobs"})
    @JoinTable(name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    List<Skill> skills;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "job")
    @JsonIgnore
    List<Resume> resumes;
}
