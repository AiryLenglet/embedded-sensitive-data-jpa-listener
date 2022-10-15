package me.lenglet.entity;

import jakarta.persistence.*;
import me.lenglet.EncryptionService;
import org.hibernate.annotations.Parent;

@Embeddable
@EntityListeners(SensitiveDataListener.class)
public class SensitiveString implements SensitiveData {

    public static final String ENCRYPTED_TAG = "encrypted::";
    private String value;

    @Parent
    private SensitiveContainer parent;
    @Transient
    private EncryptionService encryptionService;

    SensitiveString() {
    }

    public SensitiveString(
            String value,
            SensitiveContainer parent
    ) {
        this.value = value;
        this.parent = parent;
    }

    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    SensitiveContainer getParent() {
        return parent;
    }

    void setParent(SensitiveContainer parent) {
        this.parent = parent;
    }

    @PostLoad
    void postLoad() {
        if (this.value.startsWith(ENCRYPTED_TAG)) {
            this.value = this.encryptionService.decrypt(this.value.substring(ENCRYPTED_TAG.length()));
        }
    }

    @PrePersist
    @PreUpdate
    void prePersistAndUpdate() {
        if (this.parent.isMnpi()) {
            this.value = ENCRYPTED_TAG + this.encryptionService.encrypt(this.value);
        }
    }
}
