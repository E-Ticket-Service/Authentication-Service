package abb.tech.auth_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_gen")
    @SequenceGenerator(name = "permission_gen", sequenceName = "permissions_seq", allocationSize = 50)
    Long id;
    String name;

    @ManyToMany(mappedBy = "permissions")
    Set<Role>roles=new HashSet<>();
}
