package kr.ac.sungkyul.network.chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	private static final String SERVER_IP = "220.67.115.222";
	private static final int SERVER_PORT = 1000;

	public static void main(String[] args) throws IOException {
		Scanner scanner = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter printWriter = null;
		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in);

			// 2. socket 생성
			socket = new Socket();

			// 3. 연결
			InetSocketAddress serverSocketAddress = new InetSocketAddress(SERVER_IP, SERVER_PORT);
			socket.connect(serverSocketAddress);

			// 4. reader/writer 생성
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			// 5. join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			printWriter.println("join:" + nickname);
			printWriter.flush();

			// 6. ChatClientReceiveThread 시작
			new ClientTread(socket, br).start();

			// 7. 키보드 입력 처리
			while (true) {
				String input = scanner.nextLine();

				if ("quit".equals(input) == true) {
					// 8. quit 프로토콜 처리
					printWriter.println("quit: ");
					printWriter.flush();
					try {
						// 소켓 닫기
						if (socket != null && socket.isClosed() == false) {
							socket.close();
						}

						// 키보드 닫기
						if (scanner != null) {
							scanner.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				} else {
					// 9. 메시지 처리
					printWriter.println("message:" + input);
					printWriter.flush();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// log( "error:" + ex );
		} finally {
			// 10. 자원정리
			///////////////////////////
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (br != null) {
					br.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			//////////////////////////
		}
	}
}
