
public class Queen extends Piece{
	public Queen(int player){
		super(player, "images2/Queen" + player + ".png");
	}
	
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		
		if (b[to.getRow()][to.getColumn()] != null && b[to.getRow()][to.getColumn()].getPlayer() == getPlayer())
			return false;
		
		if(to.getRow()==from.getRow() && to.getColumn()>from.getColumn()) {
			for(int i=from.getColumn()+1; i<=to.getColumn(); i++)
				if(b[to.getRow()][i] != null)
					return false;
		}
		else if(to.getRow()==from.getRow() && to.getColumn()<from.getColumn()){
			for(int i=from.getColumn()-1; i>=to.getColumn(); i--)
				if(b[to.getRow()][i] != null)
					return false;
		}
		else if(to.getColumn()==from.getColumn() && to.getRow()<from.getRow()){
			for(int i=from.getRow()-1; i>=to.getRow(); i--)
				if(b[i][to.getColumn()] != null)
					return false;
		}
		else if(to.getColumn()==from.getColumn() && to.getRow()>from.getRow()){
			for(int i=from.getRow()+1; i<=to.getRow(); i--)
				if(b[i][to.getColumn()] != null)
					return false;
		}
		else if(to.getColumn()==from.getColumn() && to.getRow()==from.getRow()){
			return false;
		}
		
		for (int row = 0; row < Math.abs(to.getRow() - from.getRow()); row++) {
			for (int column = 0; row < Math.abs(to.getColumn() - from.getColumn()); column++) {
				if (row != column || b[from.getRow() + row][from.getColumn() + column] != null) {
					return false;
				}
			}
		}
		
		return true;
		
	}
}
