package BoardPieces;

public class Queen extends ChessPiece{

	public Queen(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}

	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		switch(this.number) {
		case 1:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "1D" : "8D";
			break;
		case 2:
			initialPosition = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED? "8D" : "1D";
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
		
		Boolean rookMove = ( targetPosition.charAt(0) == this.position.charAt(0) ) 
		|| ( targetPosition.charAt(1) == this.position.charAt(1) );
		
		Boolean bishopMove = Math.abs(targetPositionNum - myPositionNum) == Math.abs(targetPositionLetter - myPositionLetter);
		
		return rookMove || bishopMove;
	}
		
	
}
