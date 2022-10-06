package io.muenchendigital.digiwf.verification.integration.registration.domain.service;

import io.muenchendigital.digiwf.verification.integration.verification.api.resource.VerificationController;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class LinkService {

    private final String baseAddress;

    public String generateLink(final UUID token){
        return baseAddress +
                VerificationController.ENDPOINT_VERIFICATION +
                "?token=" + token.toString();
    }

}
