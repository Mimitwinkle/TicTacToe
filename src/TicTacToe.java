import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
	
	Random random = new Random(); // for determining which player takes first turn
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9]; // array for containing all buttons
	
	JButton[][] buttonMatrix = new JButton[3][3]; // button matrix
	
	boolean player1_turn;
	
	TicTacToe() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,700);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		
		// iterate through matrix to add all buttons to the panel
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttonMatrix[i][j] = new JButton();
				button_panel.add(buttonMatrix[i][j]);
				buttonMatrix[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
				buttonMatrix[i][j].setFocusable(false);
			}
		}
		
		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		
		firstTurn();
	}
	
	// play game
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(e.getSource()==buttonMatrix[i][j]) { // if button at index [i][j] is clicked:
					if(player1_turn==true) { // if it's player1's turn:
						if(buttonMatrix[i][j].getText()=="") { // if "i" button is blank:
							// change foreground color & mark with an "X"
							buttonMatrix[i][j].setForeground(new Color(255,0,0));
							buttonMatrix[i][j].setText("X");
							player1_turn = false;
							textfield.setText("O turn");
							check(); // check for win
						}
					}
					else { // if it's player2's turn:
						if(buttonMatrix[i][j].getText()=="") { // if "i" button is blank:
							// change foreground color & mark with an "O"
							buttonMatrix[i][j].setForeground(new Color(0,0,255));
							buttonMatrix[i][j].setText("O");
							player1_turn = true;
							textfield.setText("X turn");
							check(); // check for win
						}
					}
				}
			}
		} // end first for loop
	} // end class
	
	// randomly determine who takes the first turn
	public void firstTurn() {
		// delay for 1500 milliseconds until assigning first turn
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// after delay, allow buttons to perform actions
		for (JButton[] row : buttonMatrix) {
			for (JButton button : row) {
				button.addActionListener(this);
			}
		}
		
		// randomly generate either a 0 or 1. if 0, player1 takes first turn
		if (random.nextInt(2)==0) {
			player1_turn = true;
			textfield.setText("X turn");
		}
		else {
			player1_turn = false;
			textfield.setText("O turn");
		}
	}
	
	// check for win
	public void check() {
		// loop through each row & check for same string
		for (int i = 0; i < 3; i++) {
			if (
					(buttonMatrix[i][0].getText().equals(buttonMatrix[i][1].getText())) &&
					(buttonMatrix[i][0].getText().equals(buttonMatrix[i][2].getText()))
					) {
				if (buttonMatrix[i][0].getText().equals("X")) {
					xWins(i,0,i,1,i,2);
				}
				else if (buttonMatrix[i][0].getText().equals("O")) {
					oWins(i,0,i,1,i,2);
				}
			}
		}
		// loop through each column & check for same string
		for (int i = 0; i < 3; i++) {
			if (
					(buttonMatrix[0][i].getText().equals(buttonMatrix[1][i].getText())) &&
					(buttonMatrix[0][i].getText().equals(buttonMatrix[2][i].getText()))
					) {
				if (buttonMatrix[0][i].getText().equals("X")) {
					xWins(0,i,1,i,2,i);
				}
				else if (buttonMatrix[0][i].getText().equals("O")) {
					oWins(0,i,1,i,2,i);
				}
			}
		}
		// check / diagonal
		if (
				(buttonMatrix[0][2].getText().equals(buttonMatrix[1][1].getText())) &&
				(buttonMatrix[0][2].getText().equals(buttonMatrix[2][0].getText()))
				) {
			if (buttonMatrix[0][2].getText().equals("X")) {
				xWins(0,2,1,1,2,0);
			}
			else if (buttonMatrix[0][2].getText().equals("O")) {
				oWins(0,2,1,1,2,0);
			}
		}
		// check \ diagonal
		else if (
				(buttonMatrix[0][0].getText().equals(buttonMatrix[1][1].getText())) &&
				(buttonMatrix[0][0].getText().equals(buttonMatrix[2][2].getText()))
				) {
			if (buttonMatrix[0][0].getText().equals("X")) {
				xWins(0,0,1,1,2,2);
			}
			else if (buttonMatrix[0][0].getText().equals("O")) {
				oWins(0,0,1,1,2,2);
			}
		}
		
	}
	
	public void xWins(int a, int b, int c, int d, int e, int f) {
		// change color of winning buttons
		buttonMatrix[a][b].setBackground(Color.GREEN);
		buttonMatrix[c][d].setBackground(Color.GREEN);
		buttonMatrix[e][f].setBackground(Color.GREEN);
		
		// disable all buttons
		for (JButton[] row : buttonMatrix) {
			for (JButton button : row) {
				button.setEnabled(false);
			}
		}

		// winning message
		textfield.setText("X wins");
	}
	
	public void oWins(int a, int b, int c, int d, int e, int f) {
		// change color of winning buttons
		buttonMatrix[a][b].setBackground(Color.GREEN);
		buttonMatrix[c][d].setBackground(Color.GREEN);
		buttonMatrix[e][f].setBackground(Color.GREEN);
		
		// disable all buttons
		for (JButton[] row : buttonMatrix) {
			for (JButton button : row) {
				button.setEnabled(false);
			}
		}
		
		// winning message
		textfield.setText("O wins");
	}
	
}
