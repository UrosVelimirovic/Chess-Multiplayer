package BoardPieces;



public class King extends ChessPiece{

	public King(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}

	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		switch(this.number) {
		case 1:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1E" : "8E";
			break;
		case 2:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8E" : "1E";
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
		
		Boolean rightNumber = Math.abs(targetPositionNum - myPositionNum) <= 1;
		Boolean rightLetter = Math.abs(targetPositionLetter - myPositionLetter) <= 1;
		return rightNumber && rightLetter;
	}

}