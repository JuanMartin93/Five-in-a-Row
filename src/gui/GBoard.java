package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.Board;
import model.GameListener;
import model.Line;
import model.Location;
import model.Board.State;
import model.Game;
import model.Player;

public class GBoard extends JFrame implements GameListener   {

	private  JPanel center = new JPanel();  // Board. 9X9  grids of JPanels
	private  JPanel Panelabel = new JPanel(); // Panel that will contains label with the board's state
	private  JLabel  gameState = new JLabel(); //Keeps track of who's turn is. Show winner if game ends.
	Player currentPlayer;
	private Game currentGame;  // An instance of the current game 
	private Board currentBoard; // An instance of the current board	
	private boolean enableBoard = true; // After the game has finished, disables the board 

	public class Cell extends JPanel implements MouseListener  {


		private final int i, j;
		private boolean selected;

		public Cell(int i, int j) {
			this.i = i; this.j = j;
			selected = false;
			setPreferredSize(new Dimension(90,90));
			addMouseListener(this);
		}


		public void toggle() {
			this.selected = !this.selected;
			repaint();
		}


		public @Override void paint(Graphics g) {

			g.fillRect(0, 0, getWidth()-1, getHeight()-1);

			g.setColor(Color.WHITE);
			g.drawRect(0, 0, getWidth()-1, getHeight()-1);



			if ( currentBoard.get(i, j) == Player.X ) {

				g.setColor(Color.WHITE);
				g.drawLine(20, 20, getWidth() -20 , getHeight()-20);
				g.drawLine(getWidth()-20 , 20, 20 , getHeight()-20);
			}
			
			if ( currentBoard.get(i, j) == Player.O ) { 
				
				g.drawArc(10, 10, getWidth()-20, getHeight()-20, 0, 360);

			}


			// Repaint the winnig line in red 
			if ( currentBoard.getState() == State.HAS_WINNER ){

				String wins = currentBoard.getWinner().line.toString();

				for (int k = 1; k < wins.length() -3; k+=6) {
					if (i == Integer.parseInt(wins.charAt(k)+"") 
							&& j == Integer.parseInt(wins.charAt(k+2)+"")){
						g.setColor(Color.RED);
						g.fillRect(0, 0, getWidth()-1, getHeight()-1);

						if (currentBoard.getWinner().winner == Player.O){
							g.setColor(Color.WHITE);
							g.drawArc(10, 10, getWidth()-20, getHeight()-20, 0, 360);
						}else{
							g.setColor(Color.WHITE);
							g.drawLine(20, 20, getWidth() -20 , getHeight()-20);
							g.drawLine(getWidth()-20 , 20, 20 , getHeight()-20);
						}
					}
				}
			}


		}


		@Override
		public void mouseClicked(MouseEvent e) {

			if (!enableBoard) // If the game is over, disables listener
				return;
			
			else{
				toggle();
				currentGame.submitMove(currentPlayer, new Location(this.i, this.j));
			}



		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}


	public GBoard() {
		super("Board");


		center.setLayout(new GridLayout(9,9));
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++){

				center.add( new Cell(i,j));
			}

		Panelabel.add(gameState);
		add(center, BorderLayout.CENTER);
		add(gameState, BorderLayout.NORTH);
		pack();
		
	}






	@Override
	public void gameChanged(Game g) {


		currentGame = g;
		currentBoard = g.getBoard();
		currentPlayer = g.nextTurn();

		gameState.setText("It's " + currentPlayer + "'s turns");

		switch(currentBoard.getState()) {

		case HAS_WINNER:
			enableBoard = false;
			center.paintImmediately(center.getVisibleRect());
			gameState.setText("Player: " + currentBoard.getWinner().winner + " won!!!!!!!!");
			break;
		case DRAW:
			center.paintImmediately(center.getVisibleRect());
			gameState.setText("It's a DRAW!!!!!");
			break;
		case NOT_OVER:
			gameState.setText(" It is " + currentPlayer + "'s turn");
			center.paintImmediately(center.getVisibleRect());
			gameState.paintImmediately(gameState.getVisibleRect());
			break;

		}

	}
}