
public class Knight extends Piece{
	public Knight(int player) {
		super(player, "images2/Knight" + player);
	}
	public boolean isValidMove(Location from, Location to, Piece[][] b) {
		if(b[to.getRow()][to.getColumn()].getPlayer() != getPlayer()) {
			return true;
		}
		else {
			return false;
		}
	}
}
