package org.hngxfreelunch.LiquidApplicationApi.data.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationInvites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String token;

    private LocalDateTime TTL;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organizations organizations;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private final Boolean isDeleted = false;

}
