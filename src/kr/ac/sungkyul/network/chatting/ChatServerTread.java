package kr.ac.sungkyul.network.chatting;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import util.InputValues;

public class ChatServerTread extends Thread {

	private String nickname;
	private Socket socket;
	public static List<Writer> listWriters = new ArrayList<Writer>();

	public ChatServerTread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

			while (true) {
				String request = br.readLine();
				String[] tokens = request.split(":");
				if (request == null) {
					log("클라이언트로부터 연결 끊김");
					doQuit(printWriter);
					break;
				}

				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(printWriter);
				} else {
					ChatServer.log("에러 : 알수없는 요청" + tokens[0]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void log(String s) {
		System.out.println("[echo server thread#" + getId() + "] " + s);
	}
	
	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;
		PrintWriter printWriter = (PrintWriter) writer;
		
		String data = nickName + "님이 참가하였습니다";
		broadcast(data);
		addWriter(writer);
		System.out.println(data);
	}

	private void printWriter(Writer writer) {
		// 구현해야함
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void doMessage(String string) {
		// 구현해야함
		String data = this.nickname + ">>" + string ;
		broadcast(data);
		System.out.println(data);
		
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter( writer );
	   String data = nickname + "님이 퇴장 하였습니다."; 
	   broadcast( data );

	}

	private void removeWriter(Writer writer) {
		// 구현해야함
		synchronized (listWriters) {
		listWriters.remove(writer);
	}
	}
}
