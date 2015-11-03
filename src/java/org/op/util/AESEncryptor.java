package org.op.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class AESEncryptor implements Serializable
{

    private final Cipher encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
    private final Cipher decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");



    public AESEncryptor() throws Exception
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        String sessionKey = externalContext.getInitParameter("sixteenBitEncryptionKey");
        String iv = externalContext.getInitParameter("sixteenBitEncryptionSalt");

        byte[] keyBytes;
        byte[] vectorBytes;

        //new BASE64Decoder().decodeBuffer(sessionKey);
        keyBytes = sessionKey.getBytes();
        //new BASE64Decoder().decodeBuffer(iv);
        vectorBytes = iv.getBytes();

        encryptor.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                new IvParameterSpec(vectorBytes));

        decryptor.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                encryptor.getParameters());
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
