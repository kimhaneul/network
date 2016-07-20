package util;
import java.io.*;
	public class InputValues {

	private static BufferedReader keyReader = new BufferedReader(
			new InputStreamReader(System.in));

	public static String readString() { // 키보드로 부터 값을 입력받음
		String inputValue = null;
		try {
			// inputValue = keyReader.readLine();
			while ((inputValue = keyReader.readLine()).length() == 0) {
				System.out.println("다시 입력해 주세요 :");
			}
		} catch (IOException e) {

		}
		return inputValue;
	}

	public static int readInt() {
		int inputValue = 0;
		try {
			inputValue = Integer.parseInt(readString()); // int값을 입력하지 않았을때

		} catch (NumberFormatException e) {
			System.out.print("다시 입력해 주세요");
			inputValue = readInt();
		}
		return inputValue;
	}
}
