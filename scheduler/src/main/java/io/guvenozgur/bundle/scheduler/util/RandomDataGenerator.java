package io.guvenozgur.bundle.scheduler.util;

import io.guvenozgur.bundle.model.RandomData;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class RandomDataGenerator {

    private static final Integer FROM = 0;
    private static final Integer TO = 100;

    public static RandomData generateRandomData() throws NoSuchAlgorithmException {
        Long timestamp = new Date().getTime();
        Integer randomValue = generateRandomNumber();
        String hash = hash(String.valueOf(timestamp), String.valueOf(randomValue));
        return new RandomData(timestamp, randomValue,hash.subSequence(hash.length()-2, hash.length()).toString());
    }

    private static Integer generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(TO -FROM) + FROM;
    }

    private static String hash(String... values) throws NoSuchAlgorithmException {
        String hash = "";
        if (values == null || values.length == 0) {
            return hash;
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (String val : values) {
            md.update(val.getBytes());
        }
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
