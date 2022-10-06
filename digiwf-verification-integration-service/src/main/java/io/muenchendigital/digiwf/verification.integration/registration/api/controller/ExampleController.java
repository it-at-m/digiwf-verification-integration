package io.muenchendigital.digiwf.verification.integration.registration.api.controller;

import io.muenchendigital.digiwf.verification.integration.registration.domain.model.Registration;
import io.muenchendigital.digiwf.verification.integration.registration.domain.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to the registration without streaming infrastructure.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final RegistrationService registrationService;

    @GetMapping(value = "/getVerificationLink")
    public void getVerificationLink() {
        final Registration registration = new Registration();
        registration.setCorrelationKey("dummy");
        try {
            registrationService.getVerificationLink(registration, "1234567890");
        } catch (final Exception e) {
            log.error(e.toString());
        }
    }

}
