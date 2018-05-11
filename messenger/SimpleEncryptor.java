/*
 * Encryption based on ASCII
 */

import java.util.*;

public class SimpleEncryptor {
	
   private Integer pattern[];
   private String encryptedData ="";

    public SimpleEncryptor(int MaximumLength) {
        //initialize encryption Key pattern variable
        pattern = new Integer[MaximumLength];
        //Generate pattern
        pattern =GenarateEncryptionPattern(MaximumLength);
    }

    private Integer[] GenarateEncryptionPattern(int Length) {
        Integer[] randomPattern = new Integer[Length];
        //generate encryption pattern
        for (int i = 0; i < Length; i++) {
            //make random encrypt key
            Random random = new Random();
            randomPattern[i] = random.nextInt(9);
        }
        return randomPattern;
    }

    public void simpleEncryptData(String data) {

        for (int i=0; i<data.length();i++){
            //get character from data
            char character = data.charAt(i);
            //convert character to ascii
            int ascii = (int) character;
            //decrypt data with random generated text
            int encryptedascii = ascii + pattern[i];
            //convert ascii to char
            char encryptedchar = (char)encryptedascii;
            //append string with char
            encryptedData += encryptedchar;

        }
    }

    private String convertKey(){
        //convert key to String
        String key = "";
        for (int i = 0;i<pattern.length;i++){
            key += String.valueOf(pattern[i]);
        }
      return key;
    }


    public String getDecryptData(String data, String key){
        String decryptedData = "";
        for (int i=0;i<data.length();i++){
            //get current key from decryptkey
            String number = key.substring(i,i+1);
            //get char from encrypeddata
            char encryptedchar = data.charAt(i);
            //convert char to ascii
            int encryptedascii = (char)encryptedchar;
            //decrypt encrypted char
            int decryptedascii = encryptedascii - Integer.valueOf(number);
            //convert ascii to char
            char decryptedchar = (char)decryptedascii;
            //append decrypted string
            decryptedData += decryptedchar;
        }
        return decryptedData;
    }
    public String getDecryptData(String data, Integer[] key){
        String decryptedData = "";
        for (int i=0;i<data.length();i++){
            //get char from encrypeddata
            char encryptedchar = data.charAt(i);
            //convert char to ascii
            int encryptedascii = (char)encryptedchar;
            //decrypt encrypted char
            int decryptedascii = encryptedascii - key[i];
            //convert ascii to char
            char decryptedchar = (char)decryptedascii;
            //append decrypted string
            decryptedData += decryptedchar;
        }
        return decryptedData;
    }

    public String getEncryptedData(){
        //return encryptedData
        return encryptedData;
    }
    public Integer[] getRawDecryptKey(){
        //return rawkey
        return pattern;
    }
    public String getStringDecryptKey(){
        //return string key
        return convertKey();
    }



}