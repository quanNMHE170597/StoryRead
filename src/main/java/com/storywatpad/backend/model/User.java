package com.storywatpad.backend.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    @Column(name = "AvatarUrl")
    private String avatarUrl;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "Role")
    private String role;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private String status;
}
