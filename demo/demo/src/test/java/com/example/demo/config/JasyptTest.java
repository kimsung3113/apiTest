package com.example.demo.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {
	
  String key = System.getProperty("jasypt.encrypt.password");

  @Test
  void encryptTest() {
    String id = "r";
    String password = "rz";

    System.out.println("id : " + jasyptEncoding(id));
    System.out.println("password : " + jasyptEncoding(password));
    
    System.out.println("decoding id : " + jasyptDecoding(jasyptEncoding(id)));
    System.out.println("decoding password : " + jasyptDecoding(jasyptEncoding(password)));
  }

  public String jasyptEncoding(String value) {
    
    StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
    pbeEnc.setAlgorithm("PBEWithMD5AndDES");
    pbeEnc.setPassword(key);
    return pbeEnc.encrypt(value);
  }
  
  public String jasyptDecoding(String value) {
      StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
      pbeEnc.setAlgorithm("PBEWithMD5AndDES");
      pbeEnc.setPassword(key);
      return pbeEnc.decrypt(value);
  }
}
