package com.awp.userAuth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "user_auth_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Enter a valid email!")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 8, message = "Password should have minimum 8 characters!")
    private String password;

    @Column(name = "user_active_status", nullable = false)
    private Boolean active;

    @Column(name = "user_created_At", nullable = false)
    private Instant createdAt;

    @Column(name = "user_updated_At", nullable = false)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
