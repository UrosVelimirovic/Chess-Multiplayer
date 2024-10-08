package BoardPieces;

public class Knight extends ChessPiece{

	public Knight(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}

	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		switch(this.number) {
		case 1:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1B" : "8G";
			break;
		case 2:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1G" : "8B";
			break;
		case 3:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8B" : "1G";
			break;
		case 4:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8G" : "1B";
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
		
		Boolean pos1 = (myPositionNum + 2 == targetPositionNum) && (myPositionLetter + 1 == targetPositionLetter);
		Boolean pos2 = (myPositionNum + 2 == targetPositionNum) && (myPositionLetter - 1 == targetPositionLetter);
		Boolean pos3 = (myPositionNum - 2 == targetPositionNum) && (myPositionLetter + 1 == targetPositionLetter);
		Boolean pos4 = (myPositionNum - 2 == targetPositionNum) && (myPositionLetter - 1 == targetPositionLetter);
		Boolean pos5 = (myPositionNum + 1 == targetPositionNum) && (myPositionLetter + 2 == targetPositionLetter);
		Boolean pos6 = (myPositionNum + 1 == targetPositionNum) && (myPositionLetter - 2 == targetPositionLetter);
		Boolean pos7 = (myPositionNum - 1 == targetPositionNum) && (myPositionLetter + 2 == targetPositionLetter);
		Boolean pos8 = (myPositionNum - 1 == targetPositionNum) && (myPositionLetter - 2 == targetPositionLetter);
		
		return pos1 || pos2 || pos3 || pos4 || pos5 || pos6 || pos7 || pos8;
	}
	
}
