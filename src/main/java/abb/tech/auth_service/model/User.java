package abb.tech.auth_service.model;

import abb.tech.auth_service.model.enums.UserRole;
import abb.tech.auth_service.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(length = 100)
    String name;
    @Column(length = 100)
    String lastname;
    @Column(unique = true,nullable = false)
    String email;  //@Pattern (what sign it will be, etc., note these through annotation) in DTO
    @Column(name = "phone_number",unique = true,nullable = false)
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    UserRole role;
    @Enumerated(EnumType.STRING)
    UserStatus status;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;
    @CreationTimestamp
    LocalDateTime updateAt;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.LAZY)
    List<Role>roles;
}
