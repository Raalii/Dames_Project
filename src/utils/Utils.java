package utils;

import java.util.Scanner;

public class Utils {
	public static int readInt() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int i = 0;
		try {
			i = sc.nextInt();
		}

		catch (Exception e) {
			i = 0;
		}

		return i;
	}

	static public String readString() {
		String userInput;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		userInput = sc.nextLine();

		return userInput;
	}
}
