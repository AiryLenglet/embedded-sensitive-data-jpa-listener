package me.lenglet.entity;

import me.lenglet.EncryptionService;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

public class SensitiveDataListener {

    private final EncryptionService encryptionService;

    public SensitiveDataListener(
            EncryptionService encryptionService
    ) {
        this.encryptionService = encryptionService;
    }

    @PostLoad
    @PrePersist
    public void setEncryptionService(SensitiveContainer sensitiveContainer) {
        sensitiveContainer.setEncryptionService(this.encryptionService);
    }
}
