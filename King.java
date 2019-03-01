import java.net.URL;
import javax.swing.ImageIcon;

public class King extends Piece{

	public King(int player){
		super(player, "images2/King" + player + ".png");
	}

	public boolean isValidMove(Location from, Location to, Piece[][]b){

		//makes sure king only goes one column at a time or in the same column

		if(b[to.getRow()][to.getColumn()]==null && ((from.getColumn() == to.getColumn() && from.getRow()==(to.getRow()+1) || 

				//makes sure king only goes one row at a time or in the same row

				(from.getRow() == to.getRow() && from.getColumn()==(to.getColumn()+1))||

				//makes sure king can go diagonal top left

				(from.getRow() == to.getRow()-1) && (from.getColumn()==to.getColumn()-1)) ||

				//makes sure king goes diagonal top right

				(from.getRow()==to.getRow()-1 && from.getColumn()==to.getColumn()+1) ||

				//makes sure king diagonal bottom left

				(from.getRow()==to.getRow()+1 && from.getColumn()==to.getColumn()-1) ||

				//makes sure king only goes diagonal bottom right

				(from.getRow()==to.getRow()+1 && from.getColumn()==to.getColumn()+1)))

					return true;

		return false;

	}
  
}

