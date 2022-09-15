package com.example.digitalturbinedtwalltest;

import junit.framework.TestCase;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SecondFragmentTest extends TestCase {

    @Test
    public void testGetSha1Hex() throws Exception{
        String input = "12345678910";
        String output = "";
        String expected = "9048ead9080d9b27d6b2b6ed363cbf8cce795f7f";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = messageDigest.digest();
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            output = buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1)).toString();
        }

       assertEquals(expected, output);
    }
}