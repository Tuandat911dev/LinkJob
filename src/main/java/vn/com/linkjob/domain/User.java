package vn.com.linkjob.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.GenderEnum;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    GenderEnum gender;
    int age;
    String address;
    @Column(columnDefinition = "MEDIUMTEXT")
    String refreshToken;
}
