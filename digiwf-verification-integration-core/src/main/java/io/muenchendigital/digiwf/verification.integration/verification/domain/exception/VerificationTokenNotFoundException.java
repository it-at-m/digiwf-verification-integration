package io.muenchendigital.digiwf.verification.integration.verification.domain.exception;

public class VerificationTokenNotFoundException extends Exception {

    private static final long serialVersionUID = -8830142200673439453L;

    public VerificationTokenNotFoundException(final String message, final Exception exception) {
        super(message, exception);
    }

    public VerificationTokenNotFoundException(final String message) {
        super(message);
    }

}
