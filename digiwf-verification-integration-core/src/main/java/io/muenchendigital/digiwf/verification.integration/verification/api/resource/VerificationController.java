package io.muenchendigital.digiwf.verification.integration.verification.api.resource;

import io.muenchendigital.digiwf.verification.integration.verification.domain.exception.CorrelationException;
import io.muenchendigital.digiwf.verification.integration.verification.domain.exception.VerificationExpiredException;
import io.muenchendigital.digiwf.verification.integration.verification.domain.exception.VerificationTokenNotFoundException;
import io.muenchendigital.digiwf.verification.integration.verification.domain.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web endpoint to receive the activated token.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class VerificationController {

    public static final String ENDPOINT_VERIFICATION = "/verify";

    private final VerificationService verificationService;

    @GetMapping(value = ENDPOINT_VERIFICATION)
    public String verify(@RequestParam final String token) {
        log.info("Incoming verification for token: {}", token);
        try {
            verificationService.verify(token);
        } catch (final VerificationTokenNotFoundException e) {
            log.warn("Verification token not found: {}", token);
            return "Fehler: Der Token wurde nicht gefunden";
        } catch (final VerificationExpiredException e) {
            log.warn("Verification expired for token: {}", token);
            return "Fehler: Die Bestätigungsfrist ist abgelaufen";
        } catch (final CorrelationException e) {
            log.error("Correlation failed for token: {}", token);
            return "Fehler: Die Bestätigung konnte leider nicht zugestellt werden";
        }
        log.info("Verification successful");
        return "Geschafft: vielen Dank für Ihre Bestätigung";
    }

}
