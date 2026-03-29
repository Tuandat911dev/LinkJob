package vn.com.linkjob.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.ResumeStatusEnum;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Resume extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String email;

    @Enumerated(EnumType.STRING)
    ResumeStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    Job job;
}
