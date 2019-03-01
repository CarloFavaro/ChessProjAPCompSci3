
    
// Project: Chess
// Written by: Jack Shapiro, Nico, Carlo Favaro
// Date: 2/28/19
// Description: This project simulates the game of chess. The board is an array, with various child classes of the "Piece" class
		//representing all of the chess pieces.	
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements MouseListener{



	private final int SQUARE_WIDTH = 90;    // The width of one space on the board.  Constant used for drawing board.
	private final int OFFSET = 0;
	private Location from;   			    // holds the coordinates of the square that the user would like to move from.
	private Location to;   				    // holds the coordinates of the square that the user would like to move to.
	private boolean click;   				// false until the game has started by somebody clicking on the board.  should also be set to false
	private int fromAndToCounter; 			// This is used to distingiush between "from" clicks and "to" clicks
	private Graphics2D g2;					// I moved this up here so we could draw the rectangles 
	private int turn = 1;
	private Piece[][] board = new Piece[8][8];	// an 8x8 board of 'Pieces'.  Each spot should be filled by one of the chess pieces or a 'space'.
	private int whiteKingCount; //used in determining if game is over
	private int blackKingCount; //used in determining if game is over
	private boolean gameOver = false;

	public GraphicsPanel() {
		setPreferredSize(new Dimension(SQUARE_WIDTH*8+OFFSET+2,SQUARE_WIDTH*8+OFFSET+2));   // Set these dimensions to the width 
		// of your background picture.   
		this.setFocusable(true);					 // for keylistener
		this.addMouseListener(this);

		board[0][0] = new Rook(1);
		board[0][1] = new Knight(1);
		board[0][2] = new Bishop(1);
		board[0][3] = new Queen(1);
		board[0][4] = new King(1);
		board[0][5] = new Bishop(1);
		board[0][6] = new Knight(1);
		board[0][7] = new Rook(1);
		board[1][0] = new Pawn(1);
		board[1][1] = new Pawn(1);
		board[1][2] = new Pawn(1);
		board[1][3] = new Pawn(1);
		board[1][4] = new Pawn(1);
		board[1][5] = new Pawn(1);
		board[1][6] = new Pawn(1);
		board[1][7] = new Pawn(1);
		board[7][0] = new Rook(2);
		board[7][1] = new Knight(2);
		board[7][2] = new Bishop(2);
		board[7][3] = new Queen(2);
		board[7][4] = new King(2);
		board[7][5] = new Bishop(2);
		board[7][6] = new Knight(2);
		board[7][7] = new Rook(1);
		board[6][0] = new Pawn(2);
		board[6][1] = new Pawn(2);
		board[6][2] = new Pawn(2);
		board[6][3] = new Pawn(2);
		board[6][4] = new Pawn(2);
		board[6][5] = new Pawn(2);
		board[6][6] = new Pawn(2);
		board[6][7] = new Pawn(2);
		// instantiate the instance variables.
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered. This method also highlights the pieces that the players slect to move in a given turn, and
	//				and checks if the game is over by checking for the death of one king and the life of the other,
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;

		// Draw the board
		g2.setColor(Color.gray);
		g2.drawLine(SQUARE_WIDTH*8+OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, 0+OFFSET);
		g2.drawLine(OFFSET, OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET);

		for(int i = 0; i <8; i+=2)
			for (int j = 0; j<8; j+=2)
			{
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		for(int i = 1; i <8; i+=2)
			for (int j = 1; j<8; j+=2)
			{
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		// these if statements highlight for "to" and "from" blocks
		if (fromAndToCounter == 1 && turn == 1) {
			g2.setColor(Color.yellow);
			g2.fillRect(from.getRow()*90, from.getColumn()*90, 90, 90); // I don't know how big the acual sqaures are in px
		}
		else if (fromAndToCounter == 1 && turn == 2) {
			g2.setColor(Color.blue);
			g2.fillRect(from.getRow()*90, from.getColumn()*90, 90, 90); // I don't know how big the acual sqaures are in px
		}
		else if (fromAndToCounter == 2) {
			fromAndToCounter = 0;
		}
		// instead of drawing a single piece you should loop through the two-dimensional array and draw each piece except for 
		// empty spaces.

		for (int i = 0; i < board.length; i++) { // for-loops loop through board and draw all pieces
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) { // if an actual piece is there
					board[i][j].draw(g, this, new Location(i,j));
				}
			}
		}
		
		// checks if game over
		for (int i = 0; i < board.length; i++) { // for-loops loop through board and draw all pieces
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] instanceof King && board[i][j].getPlayer() == 1) { // if there is a white king...
					whiteKingCount++;
				}
				else if (board[i][j] instanceof King && board[i][j].getPlayer() == 2) { // if there is a white king...
					blackKingCount++;
			}
			} // end of inner for-loop
		} // end of for-loop
		
		if (whiteKingCount == 1 && blackKingCount == 0) { // if black king dies...
			System.out.println("WHITE WINS"); // change to graphics later
			gameOver = true;
			g2.setColor(Color.blue);
			g2.drawString("WHITE WINS", 300, 300);
		}
		else if (whiteKingCount == 0 && blackKingCount == 1) { // if white king dies...
			System.out.println("BLACK WINS"); // change to graphics later
			gameOver = true; 
			g2.setColor(Color.blue);
			g2.drawString("BLACK WINS", 300, 300);
		}
	}

	@Override
	// method: mouseClicked
	// description: This method is activated when the user clicks anywhere won the board. If the game is not over,
	//		the logic checks whose turn it is, and highlights the piece the user clicked on yellow (for white)
	//		or blue (for black). When the user clicks the place they wish for their piece to go, this method checks whether it
	//		is a valid move, and if the move is valid, the piece is moved. If not, the user can try again.
	public void mouseClicked(MouseEvent e) {
		if (gameOver == false) { // if the game is over, clicking will do nothing

			if (turn == 1) { // white turn
				if (fromAndToCounter == 0) { // if the user clicks on a "from piece" piece
					from = new Location(e.getX()/90, e.getY()/90);
					if (board[from.getRow()][from.getColumn()] != null && board[from.getRow()][from.getColumn()].getPlayer() == 1) {
						fromAndToCounter++;
						this.repaint();
					}

				}
				else if (fromAndToCounter == 1) { // execute the move
					to = new Location(e.getX()/90, e.getY()/90);			
					if (board[from.getRow()][from.getColumn()].isValidMove(from, to, board) == true && !from.equals(to) && board[from.getRow()][from.getColumn()].getPlayer() == 1) { // if the move is valid
						board[to.getRow()][to.getColumn()] = board[from.getRow()][from.getColumn()]; // this moves the piece to its new location
						board[from.getRow()][from.getColumn()] = null; // sets old spot to null
						fromAndToCounter++;
						turn = 2; // if valid move, switch to black turn
						this.repaint();
					} 
					else {
						fromAndToCounter = 0;
					}
				}			
				this.repaint();
			} // end of white turn

			
			
			
			
			
			
			else { // if turn == 2/black turn
				if (fromAndToCounter == 0) { // if the user clicks on a "from piece" piece
					from = new Location(e.getX()/90, e.getY()/90);
					if (board[from.getRow()][from.getColumn()] != null && board[from.getRow()][from.getColumn()].getPlayer() == 2) {
						fromAndToCounter++;
						this.repaint();
					}
				}
				else if (fromAndToCounter == 1) { // execute the move
					to = new Location(e.getX()/90, e.getY()/90);			
					if (board[from.getRow()][from.getColumn()].isValidMove(from, to, board) == true && !from.equals(to) && board[from.getRow()][from.getColumn()].getPlayer() == 2) { // if the move is valid
						board[to.getRow()][to.getColumn()] = board[from.getRow()][from.getColumn()]; // this moves the piece to its new location
						board[from.getRow()][from.getColumn()] = null; // sets old spot to null
						fromAndToCounter++;	
						turn = 1; // if valid move, switch to white turn
						this.repaint();
					} 
					else {
						fromAndToCounter = 0; 
					}
				}			
				this.repaint();
			}
		} // end of gameOver check
	} // end of mouse-clicked

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
