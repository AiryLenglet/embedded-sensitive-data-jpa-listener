package me.lenglet.entity;

import org.hibernate.annotations.Parent;

import javax.persistence.*;

@MappedSuperclass
@Embeddable
@EntityListeners(SensitiveDataListener.class)
public abstract class AbstractSensitiveData<T> implements SensitiveData<T> {
    public static final String ENCRYPTED_TAG = "encrypted::";
    protected String value;

    @Transient
    protected String v;

    @Parent
    protected SensitiveContainer parent;

    AbstractSensitiveData() {
    }

    protected AbstractSensitiveData(
            String value,
            SensitiveContainer parent
    ) {
        this.value = value;
        this.parent = parent;
    }

    @Override
    public SensitiveContainer getParent() {
        return parent;
    }

    void setParent(SensitiveContainer parent) {
        this.parent = parent;
    }

    public synchronized String getStringValue() {
        if (this.value.startsWith(ENCRYPTED_TAG)) {
            return this.getParent().getEncryptionService().decrypt(this.value.substring(ENCRYPTED_TAG.length()));
        }
        return this.value;
    }

    @PrePersist
    @PreUpdate
    void prePersistAndUpdate() {
        if (this.parent.isMnpi() && !this.value.startsWith(ENCRYPTED_TAG)) {
            this.value = ENCRYPTED_TAG + this.getParent().getEncryptionService().encrypt(this.value);
        }
    }
}
