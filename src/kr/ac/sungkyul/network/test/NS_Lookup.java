package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import util.MyScanner;

public class NS_Lookup {
	public static void main(String[] args) {

		while (true) {
			System.out.print(">>");
			String Host = MyScanner.InputString();
			if ("exit".equals(Host)) {
				break;
			}
			try {
				InetAddress[] inetAddress = InetAddress.getAllByName(Host);

//				for (int i = 0; i < inetAddress.length; i++) {
//					System.out.println(inetAddress[i]);
//				}
				
				for(InetAddress inetAddresses : inetAddress){
					System.out.println(inetAddresses.getHostAddress());
				}
				

			} catch (UnknownHostException e) {
				System.out.println("unKnown Host");
			}

		}
		System.out.println("종료");
	}
}
