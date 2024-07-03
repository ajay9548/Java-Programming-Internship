import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ChatBot {

	private static final String appName = "notepad" ; // Default application name

	public static void main(String[] args) throws IOException, URISyntaxException {
		Scanner scanner = new Scanner(System.in); // it wil read the text from console//
		String input; // initialization//

		System.out.println("\"Hello! boss i am your servant");
		System.out.println("You can ask me to open applications or search the web.");

		while (true) {
			System.out.print("> ");
			input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("exit")) {
				System.out.println("Goodbye!");
				break;
			} else if (input.startsWith("open ")) {
				openApplication(input.substring(5));
			} else if (input.startsWith("search ")) {
				searchWeb(input.substring(7));
			} else {
				System.out.println("Sorry, I didn't understand that command.");
			}
		}

		scanner.close();
	}

	private static void searchWeb(String query) {
		try {
			String encodedQuery = URI.create(query).toASCIIString();
			String searchUrl = "https://www.google.com/search?q=" + encodedQuery;
			Desktop.getDesktop().browse(new URI(searchUrl));
			System.out.println("Searching for: " + query);
		} catch (IOException | URISyntaxException e) {
			System.out.println("Failed to perform web search: " + e.getMessage());
		}
	}

	private static void openApplication(String appName) throws IOException, URISyntaxException {
		String os = System.getProperty("os.name").toLowerCase();// line gets the name of the operating system and converts it to lowercase for easier comparison.
		try {
			if (os.contains("win")) {
				Runtime.getRuntime().exec("cmd /c start " + appName);
			} else {
				System.out.println("Unsupported operating system.");
			}
		} catch (IOException e) {
			System.out.println("Failed to open application: " + e.getMessage());
			os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				if (appName.equalsIgnoreCase("google")) {
					// Open Google in default web browser
					Desktop.getDesktop().browse(new URI("https://www.google.com"));
				} else {
					// For other applications
					Runtime.getRuntime().exec("cmd /c start " + appName);
				}
			} else {
				System.out.println("Unsupported operating system.");
			}
		}
	}
};
