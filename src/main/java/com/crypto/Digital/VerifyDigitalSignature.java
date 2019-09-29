package com.crypto.Digital;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

public class VerifyDigitalSignature {
    public static void main(String[] args) {
        try {
            //byte[] publicKeyEncoded = Files.readAllBytes(Paths.get("D:\\publickey.txt"));
           // byte[] digitalSignature = Files.readAllBytes(Paths.get("D:\\signature.txt"));
            String digitalSignature1 = JOptionPane.showInputDialog("Type your signature here");
            byte[] digitalSignature =  Base64.decodeBase64(digitalSignature1.getBytes());
            
            CertificateDetails certDetails = CertificateUtil.getCertificateDetails("d:\\test.keystore", "changeit");
            System.out.println(certDetails.getPrivateKey());
            System.out.println(certDetails.getX509Certificate());

            //X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyEncoded);
            //KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

            //PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            PublicKey publicKey = certDetails.getX509Certificate().getPublicKey();
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);

            byte[] bytes = Files.readAllBytes(Paths.get("D:\\test1.txt"));
            signature.update(bytes);

            boolean verified = signature.verify(digitalSignature);
            if (verified) {
                System.out.println("Data verified.");
            } else {
                System.out.println("Cannot verify data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
