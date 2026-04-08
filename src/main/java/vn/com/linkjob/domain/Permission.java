package vn.com.linkjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Permission extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String apiPath;
    String method;
    String module;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    @JsonIgnore
    List<Role> roles;
}
