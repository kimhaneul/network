package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {
	public static void main(String[] args) {
		
		try{
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			byte[] addressed = inetAddress.getAddress();
			
			System.out.println(hostname +" " + hostAddress);
			
			for(int i = 0; i<addressed.length; i++){
				System.out.print((int)addressed[i] & 0x000000ff);
				//byte단위로 볼때 &0x000000ff로 비트연산을 해주어야한다
				if(i<addressed.length-1){
					System.out.print(".");
				}
			}
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
	}
}
