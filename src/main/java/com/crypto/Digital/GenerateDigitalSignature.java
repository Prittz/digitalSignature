package com.crypto.Digital;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

public class GenerateDigitalSignature {
    public static void main(String[] args) {
        try {
            // Get instance and initialize a KeyPairGenerator object.
            //KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            //SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
           // keyGen.initialize(1024, random);

            
            CertificateDetails certDetails = CertificateUtil.getCertificateDetails("d:\\test.keystore", "changeit");
            System.out.println(certDetails.getPrivateKey());
            System.out.println(certDetails.getX509Certificate());
            //byte[] privateKeyEncoded = Files.readAllBytes(Paths.get("D:\\PrivateKeyTest.txt"));

            //PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privateKeyEncoded);
             //KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SUN");

             //PrivateKey privateKey = keyFactory.generatePrivate(privSpec);
            // Get a PrivateKey from the generated key pair.
           // KeyPair keyPair = keyGen.generateKeyPair();
           // PrivateKey privateKey = keyPair.getPrivate();
            

            // Get an instance of Signature object and initialize it.
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(certDetails.getPrivateKey());

            // Supply the data to be signed to the Signature object
            // using the update() method and generate the digital
            // signature.
            byte[] bytes = Files.readAllBytes(Paths.get("D:\\test.txt"));
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();

            // Save digital signature and the public key to a file.
            //Files.write(Paths.get("D:\\signature.txt"), digitalSignature);
            System.out.println(new String(Base64.encodeBase64(digitalSignature)));
           // Files.write(Paths.get("D:\\publickey.txt"), keyPair.getPublic().getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
