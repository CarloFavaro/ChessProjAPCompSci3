import java.net.URL;
import javax.swing.ImageIcon;

public class pawn extends Piece{


  public Pawn(int player){
		super(player, "images2/Pawn" + player);
	 }

	protected void setImageIcon(String imagePath){

		ClassLoader cldr = this.getClass().getClassLoader();	



		URL imageURL = cldr.getResource(imagePath);				

		image = new ImageIcon(imageURL);

	}



	public boolean isValidMove(Location from, Location to, Piece[][]b){

		//white team

		if(getPlayer()==1) {

			//if pawn is not in its first spot and move one spot up

			if(from.getRow()!=7 && (from.getRow()==to.getRow()+1 || 

			//pawn moving diagonal top left

			(from.getRow()==to.getRow()+1)&&(from.getColumn()==to.getColumn()+1))||

			//pawn moving diagonal top right

			(from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()-1)) 



			

			if(from.getRow()==7 && from.getRow()==to.getRow()+2)

				return true;

		}





		else if(getPlayer()==2) {

			//if pawn is not in its first spot and move one spot up

			if(from.getRow()!=1 && (from.getRow()==to.getRow()-1 || 

			//pawn moving diagonal bottom left

			(from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()+1))||

			//pawn moving diagonal bottom right

			(from.getRow()==to.getRow()-1)&&(from.getColumn()==to.getColumn()-1)) 

				return true;

			

			if(from.getRow()==1 && from.getRow()==to.getRow()-2)

				return true;

		}



		return false;

	}



}
