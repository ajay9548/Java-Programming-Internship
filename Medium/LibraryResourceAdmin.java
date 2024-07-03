import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class LibraryResourceAdmin extends Frame implements ActionListener {

	// Menu items
	MenuItem addVolume, viewVolumes, searchVolumes;
	MenuItem addPatron, viewPatrons, searchPatrons;
	MenuItem manageLoans, viewLoans;

	// Lists to store volumes, patrons, and loans
	ArrayList<Volume> volumes;
	ArrayList<Patron> patrons;
	ArrayList<Loan> loans;

	public LibraryResourceAdmin() {
		// Initialize lists
		volumes = new ArrayList<>();
		patrons = new ArrayList<>();
		loans = new ArrayList<>();

		// Set up the frame
		setTitle("Library Resource Administrator");
		setSize(800, 600);
		setLayout(new BorderLayout());

		// Create and set up the menu bar
		MenuBar menuBar = new MenuBar();

		// Volumes menu
		Menu volumesMenu = new Menu("Volumes");
		addVolume = new MenuItem("Add Volume");
		viewVolumes = new MenuItem("View Volumes");
		searchVolumes = new MenuItem("Search Volumes");
		volumesMenu.add(addVolume);
		volumesMenu.add(viewVolumes);
		volumesMenu.add(searchVolumes);
		menuBar.add(volumesMenu);

		// Patrons menu
		Menu patronsMenu = new Menu("Patrons");
		addPatron = new MenuItem("Add Patron");
		viewPatrons = new MenuItem("View Patrons");
		searchPatrons = new MenuItem("Search Patrons");
		patronsMenu.add(addPatron);
		patronsMenu.add(viewPatrons);
		patronsMenu.add(searchPatrons);
		menuBar.add(patronsMenu);

		// Loans menu
		Menu loansMenu = new Menu("Loans");
		manageLoans = new MenuItem("Manage Loans");
		viewLoans = new MenuItem("View Loans");
		loansMenu.add(manageLoans);
		loansMenu.add(viewLoans);
		menuBar.add(loansMenu);

		// Set the menu bar
		setMenuBar(menuBar);

		// Add action listeners
		addVolume.addActionListener(this);
		viewVolumes.addActionListener(this);
		searchVolumes.addActionListener(this);
		addPatron.addActionListener(this);
		viewPatrons.addActionListener(this);
		searchPatrons.addActionListener(this);
		manageLoans.addActionListener(this);
		viewLoans.addActionListener(this);

		// Frame settings
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuItem source = (MenuItem) e.getSource();
		if (source == addVolume) {
			showAddVolumeDialog();
		} else if (source == viewVolumes) {
			showViewVolumesDialog();
		} else if (source == searchVolumes) {
			showSearchVolumesDialog();
		} else if (source == addPatron) {
			showAddPatronDialog();
		} else if (source == viewPatrons) {
			showViewPatronsDialog();
		} else if (source == searchPatrons) {
			showSearchPatronsDialog();
		} else if (source == manageLoans) {
			showManageLoansDialog();
		} else if (source == viewLoans) {
			showViewLoansDialog();
		}
	}

	private void showAddVolumeDialog() {
		Dialog dialog = new Dialog(this, "Add Volume", true);
		dialog.setLayout(new GridLayout(4, 2));

		// Form fields
		dialog.add(new Label("Title:"));
		TextField titleField = new TextField();
		dialog.add(titleField);

		dialog.add(new Label("Author:"));
		TextField authorField = new TextField();
		dialog.add(authorField);

		dialog.add(new Label("ISBN:"));
		TextField isbnField = new TextField();
		dialog.add(isbnField);

		Button addButton = new Button("Add");
		dialog.add(addButton);
		Button cancelButton = new Button("Cancel");
		dialog.add(cancelButton);

		// Add action listeners for buttons
		addButton.addActionListener(ae -> {
			String title = titleField.getText();
			String author = authorField.getText();
			String isbn = isbnField.getText();
			volumes.add(new Volume(title, author, isbn));
			dialog.setVisible(false);
		});
		cancelButton.addActionListener(ae -> dialog.setVisible(false));

		dialog.setSize(300, 200);
		dialog.setVisible(true);
	}

	private void showViewVolumesDialog() {
		Dialog dialog = new Dialog(this, "View Volumes", true);
		dialog.setLayout(new BorderLayout());

		TextArea textArea = new TextArea();
		StringBuilder sb = new StringBuilder();
		for (Volume v : volumes) {
			sb.append(v).append("\n");
		}
		textArea.setText(sb.toString());
		dialog.add(textArea, BorderLayout.CENTER);

		Button okButton = new Button("OK");
		okButton.addActionListener(ae -> dialog.setVisible(false));
		dialog.add(okButton, BorderLayout.SOUTH);

		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}

	private void showSearchVolumesDialog() {
		Dialog dialog = new Dialog(this, "Search Volumes", true);
		dialog.setLayout(new GridLayout(3, 2));

		dialog.add(new Label("Search by Title:"));
		TextField searchField = new TextField();
		dialog.add(searchField);

		Button searchButton = new Button("Search");
		dialog.add(searchButton);
		Button cancelButton = new Button("Cancel");
		dialog.add(cancelButton);

		searchButton.addActionListener(ae -> {
			String query = searchField.getText().toLowerCase();
			StringBuilder sb = new StringBuilder();
			for (Volume v : volumes) {
				if (v.getTitle().toLowerCase().contains(query)) {
					sb.append(v).append("\n");
				}
			}
			showSearchResultsDialog("Volume Search Results", sb.toString());
			dialog.setVisible(false);
		});
		cancelButton.addActionListener(ae -> dialog.setVisible(false));

		dialog.setSize(300, 150);
		dialog.setVisible(true);
	}

	private void showAddPatronDialog() {
		Dialog dialog = new Dialog(this, "Add Patron", true);
		dialog.setLayout(new GridLayout(4, 2));

		dialog.add(new Label("Name:"));
		TextField nameField = new TextField();
		dialog.add(nameField);

		dialog.add(new Label("Email:"));
		TextField emailField = new TextField();
		dialog.add(emailField);

		dialog.add(new Label("Phone:"));
		TextField phoneField = new TextField();
		dialog.add(phoneField);

		Button addButton = new Button("Add");
		dialog.add(addButton);
		Button cancelButton = new Button("Cancel");
		dialog.add(cancelButton);

		addButton.addActionListener(ae -> {
			String name = nameField.getText();
			String email = emailField.getText();
			String phone = phoneField.getText();
			patrons.add(new Patron(name, email, phone));
			dialog.setVisible(false);
		});
		cancelButton.addActionListener(ae -> dialog.setVisible(false));

		dialog.setSize(300, 200);
		dialog.setVisible(true);
	}

	private void showViewPatronsDialog() {
		Dialog dialog = new Dialog(this, "View Patrons", true);
		dialog.setLayout(new BorderLayout());

		TextArea textArea = new TextArea();
		StringBuilder sb = new StringBuilder();  // it converts the text into bytes
		for (Patron p : patrons) {
			sb.append(p).append("\n");
		}
		textArea.setText(sb.toString());
		dialog.add(textArea, BorderLayout.CENTER);

		Button okButton = new Button("OK");
		okButton.addActionListener(ae -> dialog.setVisible(false));
		dialog.add(okButton, BorderLayout.SOUTH);

		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}

	private void showSearchPatronsDialog() {
		Dialog dialog = new Dialog(this, "Search Patrons", true);
		dialog.setLayout(new GridLayout(3, 2));

		dialog.add(new Label("Search by Name:"));
		TextField searchField = new TextField();
		dialog.add(searchField);

		Button searchButton = new Button("Search");
		dialog.add(searchButton);
		Button cancelButton = new Button("Cancel");
		dialog.add(cancelButton);

		searchButton.addActionListener(ae -> {
			String query = searchField.getText().toLowerCase();
			StringBuilder sb = new StringBuilder();
			for (Patron p : patrons) {
				if (p.getName().toLowerCase().contains(query)) {
					sb.append(p).append("\n");
				}
			}
			showSearchResultsDialog("Patron Search Results", sb.toString());
			dialog.setVisible(false);
		});
		cancelButton.addActionListener(ae -> dialog.setVisible(false));

		dialog.setSize(300, 150);
		dialog.setVisible(true);
	}

	private void showManageLoansDialog() {
		Dialog dialog = new Dialog(this, "Manage Loans", true);
		dialog.setLayout(new GridLayout(4, 2));

		dialog.add(new Label("Patron Name:"));
		TextField patronField = new TextField();
		dialog.add(patronField);

		dialog.add(new Label("Volume Title:"));
		TextField volumeField = new TextField();
		dialog.add(volumeField);

		Button loanButton = new Button("Loan");
		dialog.add(loanButton);
		Button returnButton = new Button("Return");
		dialog.add(returnButton);

		loanButton.addActionListener(ae -> {
			String patronName = patronField.getText();
			String volumeTitle = volumeField.getText();
			Patron patron = findPatronByName(patronName);
			Volume volume = findVolumeByTitle(volumeTitle);
			if (patron != null && volume != null) {
				loans.add(new Loan(patron, volume));
			}
			dialog.setVisible(false);
		});
		returnButton.addActionListener(ae -> {
			String patronName = patronField.getText();
			String volumeTitle = volumeField.getText();
			Loan loan = findLoan(patronName, volumeTitle);
			if (loan != null) {
				loans.remove(loan);
			}
			dialog.setVisible(false);
		});

		dialog.setSize(300, 200);
		dialog.setVisible(true);
	}

	private void showViewLoansDialog() {
		Dialog dialog = new Dialog(this, "View Loans", true);
		dialog.setLayout(new BorderLayout());

		TextArea textArea = new TextArea();
		StringBuilder sb = new StringBuilder();
		for (Loan l : loans) {
			sb.append(l).append("\n");
		}
		textArea.setText(sb.toString());
		dialog.add(textArea, BorderLayout.CENTER);

		Button okButton = new Button("OK");
		okButton.addActionListener(ae -> dialog.setVisible(false));
		dialog.add(okButton, BorderLayout.SOUTH);

		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}

	private void showSearchResultsDialog(String title, String results) {
		Dialog dialog = new Dialog(this, title, true);
		dialog.setLayout(new BorderLayout());

		TextArea textArea = new TextArea(results, 10, 40);
		textArea.setEditable(false);
		dialog.add(textArea, BorderLayout.CENTER);

		Button okButton = new Button("OK");
		okButton.addActionListener(ae -> dialog.setVisible(false));
		dialog.add(okButton, BorderLayout.SOUTH);

		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}

	private Patron findPatronByName(String name) {
		for (Patron p : patrons) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	private Volume findVolumeByTitle(String title) {
		for (Volume v : volumes) {
			if (v.getTitle().equalsIgnoreCase(title)) {
				return v;
			}
		}
		return null;
	}

	private Loan findLoan(String patronName, String volumeTitle) {
		for (Loan l : loans) {
			if (l.patron().getName().equalsIgnoreCase(patronName) &&
					l.volume().getTitle().equalsIgnoreCase(volumeTitle)) {
				return l;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		new LibraryResourceAdmin();
		// Create and show the GUI on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			LibraryResourceAdmin admin = new LibraryResourceAdmin();
			admin.setVisible(true); // Make the main frame visible
		});
	}

	// Data classes for Volume, Patron, and Loan

	static class Volume {
		private final String title;
		private final String author;
		private final String isbn;

		public Volume(String title, String author, String isbn) {
			this.title = title;
			this.author = author;
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		@Override
		public String toString() {
			return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn;
		}
	}

	static class Patron {
		private final String name;
		private final String email;
		private final String phone;

		public Patron(String name, String email, String phone) {
			this.name = name;
			this.email = email;
			this.phone = phone;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Name: " + name + ", Email: " + email + ", Phone: " + phone;
		}
	}

	record Loan(Patron patron, Volume volume) {
		@Override
		public String toString() {
			return "Patron: " + patron + ", Volume: " + volume;
		}
	}
}
