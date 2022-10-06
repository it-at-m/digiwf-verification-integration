package io.muenchendigital.digiwf.verification.integration.registration.domain.service;

import io.muenchendigital.digiwf.verification.integration.registration.domain.exception.RegistrationException;
import io.muenchendigital.digiwf.verification.integration.registration.domain.model.Registration;
import io.muenchendigital.digiwf.verification.integration.shared.domain.entity.VerificationEntity;
import io.muenchendigital.digiwf.verification.integration.shared.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    final VerificationRepository verificationRepository;
    final LinkService linkService;

    public String getVerificationLink(final Registration registration, final String processInstanceId) throws RegistrationException {
        log.info("Get verification link for: {}", registration.getCorrelationKey());

        if (StringUtils.isEmpty(registration.getCorrelationKey())) {
            throw new RegistrationException("No correlation key provided");
        }
        if (verificationRepository.findByProcessInstanceIdAndCorrelationKey(processInstanceId, registration.getCorrelationKey()).isPresent()){
            throw new RegistrationException("Correlation key already exists");
        }
        final UUID token = generateToken();
        persistVerification(registration, processInstanceId, token);

        final String link = linkService.generateLink(token);
        log.debug("Generated link: {}", link);
        return link;
    }

    private void persistVerification(final Registration registration, final String processInstanceId, final UUID token) {
        final VerificationEntity verificationEntity = VerificationEntity.builder()
                .correlationKey(registration.getCorrelationKey())
                .processInstanceId(processInstanceId)
                .expiryTime(registration.getExpiryTime())
                .subject(registration.getSubject())
                .token(token.toString())
                .build();

        verificationRepository.save(verificationEntity);
    }

    private UUID generateToken() {
        return UUID.randomUUID();
    }

}
