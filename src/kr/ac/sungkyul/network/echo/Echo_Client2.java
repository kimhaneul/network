package kr.ac.sungkyul.network.echo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import util.MyScanner;


public class Echo_Client2 {
	private static final String SERVER_IP = "172.16.106.113";
	private static final int SERVER_PORT = 1000;

	public static void main(String[] args) {
		Socket socket = null;

		try {
			while (true) {
				// 1.소켓생성
				socket = new Socket();

				// 2. 서버연결
				InetSocketAddress serverSocketAddress = new InetSocketAddress(SERVER_IP, SERVER_PORT);

				socket.connect(serverSocketAddress);

				// InputStream is = socket.getInputStream();
				// 버퍼리더로 대체

				// OutputStream os = socket.getOutputStream();
				// 프린트 라이트로 대체
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
				//보조 스트림을 통해 스트림을 대체한다/

				// 4. data쓰기
				String data = MyScanner.InputString();
				if ("exit".equals(data)) {
					break;
				}
				pw.write(data);
				pw.flush();

				// 5. 데이터 읽기
//				byte[] buffer = new byte[256];
//				int readBytes = br.read();
				
				String echoMsg = br.readLine();
				
				if (echoMsg == null) {// 서버가 연결을 끊을때
					System.out.println("[CLient] close by server");
					return;
				}

				System.out.println("[client] received:" + echoMsg);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
