package com.crypto.Digital;

import java.util.logging.Logger;

import com.payneteasy.tlv.BerTlv;
import com.payneteasy.tlv.BerTlvLogger;
import com.payneteasy.tlv.BerTlvParser;
import com.payneteasy.tlv.BerTlvs;
import com.payneteasy.tlv.HexUtil;
import com.payneteasy.tlv.IBerTlvLogger;

public class Parser {

	
	public static void main(String[] args) {
		
		
		byte[] bytes = HexUtil.parseHex("50045649534157131000023100000033D44122011003400000481F");

		  BerTlvParser parser = new BerTlvParser();
		  BerTlvs tlvs = parser.parse(bytes, 0, bytes.length);
		  
		  for(BerTlv b : tlvs.getList()){
		  
		  System.out.println(b.getTextValue());
		  System.out.println(b.getTag());
		  System.out.println(b.getHexValue());
		  System.out.println();
		  }
		  

		  
		  //BerTlvLogger.log("    ", tlvs, null);
		
		

	}
	
	public static String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b));
		   return sb.toString();
		}

}
