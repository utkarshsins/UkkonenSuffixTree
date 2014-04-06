public class Utils {

	public static final int SYMBOL_SET_SIZE = 36;

	public static final boolean SHOW_TIMINGS = true;

	private static int ID_COUNTER = 1;

	public static int nextNodeId() {
		return ID_COUNTER++;
	}

	public static String ellipsize(String input) {
		int maxCharacters = 20;
		int charactersAfterEllipsis = 5;

		if (input == null || input.length() < maxCharacters) {
			return input;
		}
		return input.substring(0, maxCharacters - 3 - charactersAfterEllipsis)
				+ "..."
				+ input.substring(input.length() - charactersAfterEllipsis);
	}

	public static int getSymbolIndex(char x) {
		Character c = Character.toLowerCase(x);
		if (c >= '0' && c <= '9')
			return (int) c - (int) '0';
		else if (c >= 'a' && c <= 'z')
			return (int) c - (int) 'a' + 10;
		else
			return -1;
	}

	public static char getIndexSymbox(int i) {
		if (i >= 0 && i <= 9)
			return (char) ((int) '0' + i);
		else if (i >= 10 && i <= 36)
			return (char) (i + (int) 'a');
		else
			return (char) 0;
	}

	public static class Timer {

		Long startTime;
		Long endTime;

		public Timer() {
			startTime = System.nanoTime();
		}

		public void stop() {
			endTime = System.nanoTime();
		}

		public void stopAndPrint(String prepend) {
			stop();
			print(prepend);
		}

		public void print(String prepend) {
			System.out.println(prepend + " in "
					+ ((float) ((endTime - startTime) / 1000) / 1000.0)
					+ " ms.");
		}
	}
}
