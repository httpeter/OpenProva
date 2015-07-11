package org.op.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESEncryptor implements Serializable
{

    private Cipher encryptor;
    private Cipher decryptor;

    public AESEncryptor(String sessionKey, String iv)
    {

        byte[] keyBytes;
        byte[] vectorBytes;
        try
        {
            //new BASE64Decoder().decodeBuffer(sessionKey);
            keyBytes = sessionKey.getBytes();
            //new BASE64Decoder().decodeBuffer(iv);
            vectorBytes = iv.getBytes();
            encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encryptor.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(keyBytes, "AES"),
                    new IvParameterSpec(vectorBytes));
            decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decryptor.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(keyBytes, "AES"),
                    encryptor.getParameters());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
    {
        // get bytes from string, encrypt, encode
        byte[] utf8bytes = plainText.getBytes("utf-8");
        byte[] ciphertext = encryptor.doFinal(utf8bytes);
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    public String decrypt(String cipherText) throws IOException, IllegalBlockSizeException, BadPaddingException
    {
        // decode, decrypt, use bytes to create string
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);
        byte[] plaintext = decryptor.doFinal(encryptedBytes);
        return new String(plaintext, "UTF-8");
    }
}
