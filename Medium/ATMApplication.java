import javax.swing.*;
import java.awt.*;

public class ATMApplication extends JFrame {

	private final JLabel balanceLabel;
	private final JTextField amountField;

	private double currentBalance = 10000.0 ; // Initial balance

	public ATMApplication() {
		setTitle("ATM Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null ); // Center the window
		// Initialize components
		JPanel mainPanel = new JPanel(new GridLayout(3, 100));

		balanceLabel = new JLabel("Current Balance: $" + currentBalance);
		mainPanel.add(balanceLabel);

		amountField = new JTextField(10);
		mainPanel.add(amountField);

		JButton queryButton = new JButton("Query Balance");
		queryButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Your balance is: $" + currentBalance));
		mainPanel.add(queryButton);

		JButton withdrawButton = getjButton();
		mainPanel.add(withdrawButton);

		add(mainPanel);
	}

	private JButton getjButton() {
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(e -> {
			try {
				double amount = Double.parseDouble(amountField.getText());
				if (amount <= 0) {
					JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid amount.");
					return;
				}
				if (amount > currentBalance) {
					JOptionPane.showMessageDialog(null, "Insufficient funds.");
				} else {
					currentBalance -= amount;
					JOptionPane.showMessageDialog(null, "Withdrawn: $" + amount + "\nNew Balance: $" + currentBalance);
					balanceLabel.setText("Current Balance: $" + currentBalance);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
			}
		});
		return withdrawButton;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ATMApplication atm = new ATMApplication();
			atm.setVisible(true);
		});
	}
}
