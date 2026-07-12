package com.awp.userProfile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_auth_id", nullable = false)
    private Long userAuthId;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @Column(name = "user_phone", nullable = false, unique = true)
    @NotBlank(message = "Phone cannot be blank!")
    @Size(min = 10, message = "Enter a valid phone number!")
    private String phone;

    @Column(name = "user_address", nullable = false)
    @NotBlank(message = "Address cannot be blank!")
    private String address;

    @Column(name = "user_created_At", nullable = false)
    private Instant createdAt;

    @Column(name = "user_updated_At", nullable = false)
    private Instant updatedAt;

    @Column(name = "user_active_status", nullable = false)
    private Boolean active;

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
