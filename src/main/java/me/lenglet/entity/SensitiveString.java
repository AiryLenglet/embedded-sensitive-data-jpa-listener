package me.lenglet.entity;

import jakarta.persistence.*;
import me.lenglet.EncryptionService;

@Embeddable
@EntityListeners(SensitiveDataListener.class)
public class SensitiveString implements SensitiveData {

    public static final String ENCRYPTED_TAG = "encrypted::";
    private String value;

    @Transient
    private boolean encrypted;
    @Transient
    private EncryptionService encryptionService;

    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostLoad
    void postLoad() {
        this.encrypted = this.value.startsWith(ENCRYPTED_TAG);
        if (this.encrypted) {
            this.value = this.encryptionService.decrypt(this.value.substring(ENCRYPTED_TAG.length()));
        }
    }

    @PrePersist
    @PreUpdate
    void prePersistAndUpdate() {
        if (this.encrypted) {
            this.value = ENCRYPTED_TAG + this.encryptionService.encrypt(this.value);
        }
    }
}
