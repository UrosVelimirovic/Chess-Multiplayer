package BoardPieces;

public class Rook extends ChessPiece {

	public Rook(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}
	
	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		switch(this.number) {
		case 1:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1A" : "8H";
			break;
		case 2:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1H" : "8A";
			break;
		case 3:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8A" : "1H";
			break;
		case 4:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8H" : "1A";
			break;
		}
		
		this.setPosition(initialPosition);
	}

	@Override
	public Boolean canMove(String targetPosition) {
		return ( targetPosition.charAt(0) == this.position.charAt(0) ) 
				|| ( targetPosition.charAt(1) == this.position.charAt(1) );
	}
	
	
	
	
}
