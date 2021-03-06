import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class MainInterface {

	public static void main(String[] args) throws Exception {

		boolean input = true;

		String text = "";

		if (args.length == 0) {
			usage(System.out);
			input = false;
		} else
			text = args[0];

		if (!input) {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(
								fileChooser.getSelectedFile())));
				reader.readLine(); // ignore
				text = reader.readLine();
				reader.close();
				input = true;
			}
		}

		if (!input) {
			System.out.print("Enter the text : ");
			text = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
		}

		text += "$";

		String[] patterns;
		if (args.length > 0)
			patterns = new String[args.length - 1];
		else
			patterns = new String[0];

		SuffixTree suffixTree = new SuffixTree(text);

		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File outputFile = fileChooser.getSelectedFile();
				PrintStream fileStream = new PrintStream(outputFile);
				suffixTree.print(fileStream);
				fileStream.close();
			} else {
				System.out.println();
				suffixTree.print(System.out);
			}
		}

		System.out.println();
		System.out.println("Node Count : " + SuffixTree.node_count);
		System.out.println("Edge Count : " + SuffixTree.edge_count);

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
