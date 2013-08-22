package org.georg.web.impl.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashCodeUtil {

    @Value("${file.thumbs.strategy}")
    private String thumbsStrategy;

    public String getDigest(String code) {
        try {
            return Hex.encodeHexString(MessageDigest.getInstance(thumbsStrategy).digest(code.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
