//Class: Chess
//Written By: Carlo Favaro
//Date: 2/26/19
//description: This class extends piece and contains the restrictions of the pawn's moves.

import java.net.URL;
import javax.swing.ImageIcon;

public class Pawn extends Piece{

	private ImageIcon image;
	private int player;


	public Pawn(int player) {
		super(player, "images2/Pawn" + player+ ".png");
	}



	public boolean isValidMove(Location from, Location to, Piece[][]b){

		//white team

		

		//if pawn is not in its first spot and move one spot up

		if(getPlayer()==1 && from.getRow()!=7 && (from.getRow()==to.getRow()+1 || 

		//pawn moving diagonal top left

		(from.getRow()==to.getRow()+1)&&(from.getColumn()==to.getColumn()+1))||

		//pawn moving diagonal top right

		(from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()-1) ||

		//pawn moves twice when in first spot

		from.getRow()==7 && from.getRow()==to.getRow()+2) {

				return true;

			}



		//Black Team

		

			//if pawn is not in its first spot and move one spot up

			if((getPlayer()==2 && from.getRow()!=1 && (from.getRow()==to.getRow()-1) || 

			//pawn moving diagonal bottom left

			((from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()+1)))||

			//pawn moving diagonal bottom right

			((from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()-1)) ||

			//pawn moving twice in first move

			from.getRow()==1 && from.getRow()==to.getRow()-2) {

				return true;

			}

		



		return false;

	}



}
