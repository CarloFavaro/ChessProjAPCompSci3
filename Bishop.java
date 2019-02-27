
public class Bishop extends Piece {
	  public Bishop(int player){
			super(player, "images2/Bishop" + player);
		}
	public boolean isValidMove(Location from, Location to, Piece[][] b) {
		boolean tf = true;
		if (b[to.getRow()][to.getColumn()] == null && b[to.getRow()][to.getColumn()].getPlayer() != getPlayer())
			tf = true;
		else {
			tf = false;
		}
		for (int row = 0; row < Math.abs(to.getRow() - from.getRow()); row++) {
			for (int column = 0; row < Math.abs(to.getColumn() - from.getColumn()); column++) {
				if (row == column && b[from.getRow() + row][from.getColumn() + column] != null) {
					tf = false;
				}
			}
		}
		
		return tf;
	}
}
