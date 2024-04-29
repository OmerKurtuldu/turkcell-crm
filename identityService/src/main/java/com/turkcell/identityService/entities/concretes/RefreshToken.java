package com.turkcell.identityService.entities.concretes;

import com.turkcell.identityService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity<Integer> {
    private String revokedByIp;
    private LocalDateTime revokedDate;
    private String token;
    private LocalDateTime expirationDate;
    private String revokeReason;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
}
