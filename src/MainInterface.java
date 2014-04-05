import java.io.PrintStream;

public class MainInterface {

	public static void main(String[] args) {

		if (args.length == 0) {
			usage(System.out);
		} else if (args.length < 2) {
			errUsage();
		}

		String text = args[0];
		String[] patterns = new String[args.length - 1];

		SuffixTree suffixTree = new SuffixTree(text);

		for (String pattern : patterns) {
			suffixTree.search(pattern);
		}

	}

	public static void errUsage() {
		System.err.println("Incorrect Usage");
		usage(System.err);
	}

	public static void usage(PrintStream stream) {
		stream.println("USAGE : UkkonenSuffixTree <text> <pattern1> <pattern2> <pattern3> ...");
	}
}
