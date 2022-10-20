package me.lenglet.entity;

import me.lenglet.EncryptionService;

public interface SensitiveContainer {
    boolean isMnpi();

    boolean mnpiFlagHasChanged();

    EncryptionService getEncryptionService();

    void setEncryptionService(EncryptionService encryptionService);
}
