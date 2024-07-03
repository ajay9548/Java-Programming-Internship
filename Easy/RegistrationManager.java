import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RegistrationManager extends Frame implements ActionListener {

	// Components for the registration form
	TextField nameField, emailField ;
	CheckboxGroup genderGroup;
	Checkbox male, female, other  ;
	Choice countryChoice;
	Button submitButton, displayButton, exportButton;


	// List to store registered users
	ArrayList<User> users;

	public RegistrationManager() {
		// Initialize components
		nameField = new TextField(20);
		emailField = new TextField(20);

		genderGroup = new CheckboxGroup();
		male = new Checkbox("Male", genderGroup, false);
		female = new Checkbox("Female", genderGroup, false);
		other = new Checkbox("Other", genderGroup, false);

		countryChoice = new Choice();
		countryChoice.add("Select Country");
		countryChoice.add("INDIA");
		countryChoice.add("NEPAL");
		countryChoice.add("SWITZERLAND");
		countryChoice.add("Australia");
		countryChoice.add("Other");

		submitButton = new Button("Submit");
		displayButton = new Button("Display");
		exportButton = new Button("Export");

		// Initialize user list
		users = new ArrayList<>(); //
		// Set up the layout
		setLayout(new GridLayout(8, 2));

		// Add components to the frame
		add(new Label("Name:"));
		add(nameField);

		add(new Label("Email:"));
		add(emailField);
		add(new Label("Gender:"));


		Panel genderPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(male);
		genderPanel.add(female);
		genderPanel.add(other);
		add(genderPanel);
		add(new Label("Country:"));
		add(countryChoice);
		add(submitButton);
		add(displayButton);
		add(exportButton);



		// Add action listeners
		submitButton.addActionListener(this);
		displayButton.addActionListener(this);
		exportButton.addActionListener(this);

		// Frame settings
		setTitle("User Registration Manager");
		setSize(400, 300);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new RegistrationManager();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			handleSubmit();
		} else if (e.getSource() == displayButton) {
			handleDisplay();
		} else if (e.getSource() == exportButton) {
			handleExport();
		}
	}

	private void handleSubmit() {
		String name = nameField.getText();
		String email = emailField.getText();
		String gender = genderGroup.getSelectedCheckbox().getLabel();
		String country = countryChoice.getSelectedItem();

		User user = new User(name, email, gender, country);
		users.add(user);

		// Clear the form
		nameField.setText("");
		emailField.setText("");
		genderGroup.setSelectedCheckbox(null);
		countryChoice.select(0);
	}

	private void handleDisplay() {
		// Display user information in a dialog
		StringBuilder info = new StringBuilder();
		for (User user : users) {
			info.append(user).append("\n");
		}
		TextArea textArea = new TextArea(info.toString(), 10, 40);
		textArea.setEditable(false);
		Dialog dialog = new Dialog(this, "User Information", true);
		dialog.setLayout(new BorderLayout());
		dialog.add(textArea, BorderLayout.CENTER);
		Button okButton = new Button("OK");
		okButton.addActionListener(ae -> dialog.setVisible(false));
		dialog.add(okButton, BorderLayout.SOUTH);
		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}

	private void handleExport() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
			for (User user : users) {
				writer.println(user);
			}
			Dialog dialog = new Dialog(this, "Export Successful", true);
			dialog.setLayout(new BorderLayout());
			dialog.add(new Label("User information exported to users.txt"), BorderLayout.CENTER);
			Button okButton = new Button("OK");
			okButton.addActionListener(ae -> dialog.setVisible(false));
			dialog.add(okButton, BorderLayout.SOUTH);
			dialog.setSize(300, 200);
			dialog.setVisible(true);
		} catch (IOException ignored) {

		}
	}
}

class User implements Serializable {
	private final String name;
	private final String email;
	private final String gender;
	private final String country;

	public User(String name, String email, String gender, String country) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Email: " + email + ", Gender: " + gender + ", Country: " + country;
	}
}
