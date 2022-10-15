package me.lenglet;

import me.lenglet.EncryptionService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Base64EncryptionService implements EncryptionService {
    @Override
    public String decrypt(String cypher) {
        return new String(Base64.getDecoder().decode(cypher));
    }

    @Override
    public String encrypt(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
