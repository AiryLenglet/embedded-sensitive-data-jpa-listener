package me.lenglet.entity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import me.lenglet.EncryptionService;

public class SensitiveDataListener {

    private final EncryptionService encryptionService;

    public SensitiveDataListener(
            EncryptionService encryptionService
    ) {
        this.encryptionService = encryptionService;
    }

    @PostLoad
    @PrePersist
    public void setEncryptionService(SensitiveString sensitiveData) {
        sensitiveData.setEncryptionService(this.encryptionService);
    }
}
