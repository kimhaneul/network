package kr.ac.sungkyul.network.chatting;

import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientTread extends Thread {

	private Socket socket;
	private BufferedReader br;

	public ClientTread(Socket socket, BufferedReader br) {
		this.socket = socket;
		this.br = br;
	}

	@Override
	public void run() {
		while (true) {
			// System.out.println("쓰레드 시작");
			String messageEcho = null;
			try {
				messageEcho = br.readLine();
				if (messageEcho == null) { // 서버가 연결을 끊음
					System.out.println("[client] close by server");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
			}

			// 받은 메세지 출력
			System.out.println("<<" + messageEcho);
		}
	}

	private void log(String s) {
		System.out.println("[echo server thread#" + getId() + "] " + s);
	}

}
