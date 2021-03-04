package com.whitehatgaming.movevalidator;

/**
 * Move validation result
 */
public class MoveValidationResult {

    private final boolean valid;
    private final String reason;

    public MoveValidationResult(boolean valid, String reason) {
        this.valid = valid;
        this.reason = reason;
    }

    public boolean isValid() {
        return valid;
    }

    public String getReason() {
        return reason;
    }
}
