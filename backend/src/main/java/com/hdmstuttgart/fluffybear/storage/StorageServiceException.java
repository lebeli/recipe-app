package com.hdmstuttgart.fluffybear.storage;

/**
 * Exception for storage service releated errors.
 */
public class StorageServiceException extends Exception {
    /**
     * The error code for debugging
     */
    private StorageErrorCode code;

    /**
     * Simple constructor for storage service releated errors.
     *
     * @param message message to be printed.
     */
    public StorageServiceException(String message) { super(message); }

    /**
     * Constructor with error code for storage service releated errors.
     *
     * @param message message to be printed.
     * @param code which describes the error.
     */
    public StorageServiceException(String message, StorageErrorCode code) {
        super(message);
        this.code = code;
    }

    public StorageErrorCode getCode() {
        return code;
    }
}
