package BoardPieces;

public class Bishop extends ChessPiece {

	public Bishop(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}

	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		switch(this.number) {
		case 1:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1C" : "8F";
			break;
		case 2:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1F" : "8C";
			break;
		case 3:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8C" : "1F";
			break;
		case 4:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8F" : "1C";
			break;
		}
		
		this.setPosition(initialPosition);
		
	}

	@Override
	public Boolean canMove(String targetPosition) {
		char targetPositionNum = targetPosition.charAt(0);
		char targetPositionLetter = targetPosition.charAt(1);
		char myPositionNum = this.position.charAt(0);
		char myPositionLetter = this.position.charAt(1);
		
		return Math.abs(targetPositionNum - myPositionNum) == Math.abs(targetPositionLetter - myPositionLetter);
	}

}
