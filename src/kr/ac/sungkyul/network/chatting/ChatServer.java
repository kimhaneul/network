package kr.ac.sungkyul.network.chatting;

import java.io.IOException;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		
		final int PORT = 1000;
		

		// 소켓 생성
		ServerSocket severSocket = new ServerSocket();

		// 바인딩

		InetAddress inetAddress = InetAddress.getLocalHost();
		String localHostAddress = inetAddress.getHostAddress();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(localHostAddress, PORT);
		severSocket.bind(inetSocketAddress);
	

		System.out.println("==========시작==========");
		// 요청대기
		while (true) {
			Socket socket = severSocket.accept();
			new ChatServerTread(socket).start();
		}
	}

	static void log(String s) {
		
	}
}
