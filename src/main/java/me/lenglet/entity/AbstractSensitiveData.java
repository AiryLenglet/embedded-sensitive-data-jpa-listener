package me.lenglet.entity;

import org.hibernate.annotations.Parent;

import javax.persistence.*;

@MappedSuperclass
@Embeddable
@EntityListeners(SensitiveDataListener.class)
public abstract class AbstractSensitiveData implements SensitiveData {
    public static final String ENCRYPTED_TAG = "encrypted::";
    protected String value;
    @Parent
    protected SensitiveContainer parent;

    AbstractSensitiveData() {
    }

    public AbstractSensitiveData(
            String value,
            SensitiveContainer parent
    ) {
        this.value = value;
        this.parent = parent;
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
            this.value = this.getParent().getEncryptionService().decrypt(this.value.substring(ENCRYPTED_TAG.length()));
        }
    }

    @PrePersist
    @PreUpdate
    void prePersistAndUpdate() {
        if (this.parent.isMnpi()) {
            this.value = ENCRYPTED_TAG + this.getParent().getEncryptionService().encrypt(this.value);
        }
    }
}
