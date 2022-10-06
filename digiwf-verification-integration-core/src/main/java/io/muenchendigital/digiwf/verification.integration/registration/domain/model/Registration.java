package io.muenchendigital.digiwf.verification.integration.registration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    private String correlationKey;

    private java.time.LocalDateTime expiryTime;

    private String subject;
}
