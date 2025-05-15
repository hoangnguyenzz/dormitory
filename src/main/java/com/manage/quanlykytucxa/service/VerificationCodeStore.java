package com.manage.quanlykytucxa.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class VerificationCodeStore {
    private final ConcurrentHashMap<String, CodeWithExpiry> codeMap = new ConcurrentHashMap<>();

    private static final long EXPIRATION_MINUTES = 5;

    public void saveCode(String email, String code) {
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
        codeMap.put(email, new CodeWithExpiry(code, expiry));
    }

    public boolean isCodeValid(String email, String inputCode) {
        CodeWithExpiry stored = codeMap.get(email);
        if (stored == null)
            return false;
        if (stored.expiry.isBefore(LocalDateTime.now())) {
            codeMap.remove(email); // hết hạn thì xoá luôn
            return false;
        }
        return stored.code.equals(inputCode);
    }

    public void removeCode(String email) {
        codeMap.remove(email);
    }

    public String getCodeByEmail(String email) {
        CodeWithExpiry codeWithExpiry = codeMap.get(email);
        return codeWithExpiry != null ? codeWithExpiry.getCode() : null;
    }

    @Getter
    @Setter
    private static class CodeWithExpiry {
        String code;
        LocalDateTime expiry;

        public CodeWithExpiry(String code, LocalDateTime expiry) {
            this.code = code;
            this.expiry = expiry;
        }
    }

}
