import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
	private JPanel mainPanel;
	private JButton[][] buttons;
	private char currentPlayer;
	private JLabel statusLabel;

	public TicTacToe() {
		setTitle("Tic-Tac-Toe Game");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		currentPlayer = 'X';
		buttons = new JButton[3][3];
		mainPanel = new JPanel(new GridLayout(3, 3));
		statusLabel = new JLabel("Player X's turn");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
				buttons[i][j].setFocusPainted(false);
				buttons[i][j].addActionListener(new ButtonClickListener(i, j));
				mainPanel.add(buttons[i][j]);
			}
		}

		add(mainPanel, BorderLayout.CENTER);
		add(statusLabel, BorderLayout.SOUTH);
	}

	private class ButtonClickListener implements ActionListener {
		private int x, y;

		public ButtonClickListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (buttons[x][y].getText().equals("")) {
				buttons[x][y].setText(String.valueOf(currentPlayer));
				if (checkForWin()) {
					statusLabel.setText("Player " + currentPlayer + " wins!");
					disableButtons();
				} else if (isBoardFull()) {
					statusLabel.setText("It's a draw!");
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
					statusLabel.setText("Player " + currentPlayer + "'s turn");
				}
			}
		}
	}

	private boolean checkForWin() {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
					buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
					buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
				return true;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
					buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
					buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
				return true;
			}
		}

		if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
				buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
				buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
			return true;
		}

		if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
				buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
				buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
			return true;
		}

		return false;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttons[i][j].getText().equals("")) {
					return false;
				}
			}
		}
		return true;
	}

	private void disableButtons() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TicTacToe().setVisible(true);
			}
		});
	}
}
