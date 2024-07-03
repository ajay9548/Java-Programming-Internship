import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class quiz extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;

	public quiz() {
		setTitle("Online Quiz Application");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		mainPanel.add(createWelcomePanel(), "Welcome");
		mainPanel.add(createQuizPanel(), "Quiz");
		mainPanel.add(createResultPanel(), "Result");

		add(mainPanel);
		cardLayout.show(mainPanel, "Welcome");
	}

	private JPanel createWelcomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome to the Online Quiz", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(welcomeLabel, BorderLayout.CENTER);

		JButton startButton = new JButton("Start Quiz");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "Quiz");
			}
		});
		panel.add(startButton, BorderLayout.SOUTH);

		return panel;
	}

	private JPanel createQuizPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));

		JLabel questionLabel = new JLabel("What is the capital of France?");
		panel.add(questionLabel);

		ButtonGroup optionsGroup = new ButtonGroup();
		JRadioButton option1 = new JRadioButton("Berlin");
		JRadioButton option2 = new JRadioButton("Madrid");
		JRadioButton option3 = new JRadioButton("Paris");
		JRadioButton option4 = new JRadioButton("Rome");

		optionsGroup.add(option1);
		optionsGroup.add(option2);
		optionsGroup.add(option3);
		optionsGroup.add(option4);

		panel.add(option1);
		panel.add(option2);
		panel.add(option3);
		panel.add(option4);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Grading logic here
				if (option3.isSelected()) {
					JOptionPane.showMessageDialog(panel, "Correct!");
				} else {
					JOptionPane.showMessageDialog(panel, "Incorrect. The correct answer is Paris.");
				}
				cardLayout.show(mainPanel, "Result");
			}
		});
		panel.add(submitButton);

		return panel;
	}

	private JPanel createResultPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel resultLabel = new JLabel("Your Results", JLabel.CENTER);
		resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(resultLabel, BorderLayout.CENTER);

		JButton restartButton = new JButton("Restart Quiz");
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "Welcome");
			}
		});
		panel.add(restartButton, BorderLayout.SOUTH);

		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new quiz().setVisible(true));
	}
}
