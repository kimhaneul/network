package kr.ac.sungkyul.network.echo;

import java.io.*;
import java.net.*;

public class Echo_Server2 {

	private final static int SERVER_PORT = 1000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {

			serverSocket = new ServerSocket(SERVER_PORT);
			Socket socket = serverSocket.accept();
			// 1. 서버 소켓 생성

			while (true) {

				try {

					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));

					// 6.데이터 읽기
					byte[] buffer = new byte[256];
					String EchoMsg = br.readLine();

					if (EchoMsg == null) { // 클라이언트가 연결을 끊었다.(정상종료)
						System.out.println("[server] closed by client");
						return;
					}

					// String data = new String(buffer, 0, readBytes, "utf-8");
					System.out.println("[server] received :" + EchoMsg);

					// 7. 데이터 쓰기
					pw.write(EchoMsg);
					pw.flush();

				} catch (SocketException e) {
					System.out.println("[server] 비정상적으로 클라이언트가 종료");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}
				// 8. 데이터 통신 소켓 닫기
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 9. 서버 소켓 닫기
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
