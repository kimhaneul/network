package kr.ac.sungkyul.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCP_Client {
	private static final String SERVER_IP = "220.67.115.222";
	private static final int SERVER_PORT = 1000;

	public static void main(String[] args) {
		Socket socket = null;

		try {
			// 1.소켓생성
			socket = new Socket();

			// 2. 서버연결
			InetSocketAddress serverSocketAddress = new InetSocketAddress(SERVER_IP, SERVER_PORT);

			socket.connect(serverSocketAddress);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. data쓰기
			String data = "Hello world\n";
			os.write(data.getBytes("UTF-8"));

			// 5. 데이터 읽기
			byte[] buffer = new byte[256];
			int readBytes = is.read(buffer);
			if (readBytes <= -1) {// 서버가 연결을 끊을때
				System.out.println("[CLient] close by server");
				return;
			}

			data = new String(buffer, 0, readBytes, "UTF-8");
			System.out.println("[client] received:" + data);

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
