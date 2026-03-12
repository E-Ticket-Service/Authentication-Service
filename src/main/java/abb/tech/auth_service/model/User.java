package abb.tech.auth_service.model;

import abb.tech.auth_service.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    Long id;
    @Column(length = 100)
    String name;
    @Column(length = 100)
    String lastname;
    @Column(unique = true, nullable = false)
    String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    UserStatus status;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;
    @CreationTimestamp
    LocalDateTime updateAt;

    @ManyToMany(mappedBy = "users")
    Set<Role> roles = new HashSet<>();
}
