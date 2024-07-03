
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class TextEditor extends JFrame implements ActionListener {
	// Declare GUI components
	private final JTextArea textArea;
	private final JFileChooser fileChooser;

	// Constructor to set up the GUI
	public TextEditor() {
		// Set up the frame
		setTitle("Simple Text Editor");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Initialize components
		textArea = new JTextArea();
		fileChooser = new JFileChooser();

		// Add a scroll pane to the text area
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);

		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create file menu
		JMenu fileMenu = new JMenu("File");

		// Create menu items
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");

		// Add action listeners to the menu items
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);

		// Add menu items to file menu
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);

		// Add file menu to menu bar
		menuBar.add(fileMenu);

		// Set the menu bar
		setJMenuBar(menuBar);
	}

	// Implement the actionPerformed method
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "Open":
				openFile();
				break;
			case "Save":
				saveFile();
				break;
			case "Exit":
				System.exit(0);
				break;
		}
	}

	// Method to open a file
	private void openFile() {
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
				textArea.read(reader, null);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// Method to save a file
	private void saveFile() {
		int returnValue = fileChooser.showSaveDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
				textArea.write(writer);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// Main method to launch the application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TextEditor editor = new TextEditor();
			editor.setVisible(true);
		});
	}
}
