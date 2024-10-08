package BoardPieces;

public class Pawn extends ChessPiece{

	public Pawn(PieceType type, Color color, int number, State state) {
		super(type, color, number, state);
		setInitialPosition();
	}

	@Override
	protected void setInitialPosition() {
		String initialPosition = "";
		StringBuilder sb = new StringBuilder();
		if(number <= 8) {
			sb.append(state.getBoardState() == State.BoardState.WHITE_ORIENTED? 2:7);
		}
		else {
			sb.append(state.getBoardState() == State.BoardState.WHITE_ORIENTED? 7:2);
		}
		int moddedNumber = number%8;
    	sb.append(Character.toString(state.getBoardState() == State.BoardState.WHITE_ORIENTED?(char) (65 + moddedNumber):(65 + 7 - moddedNumber))); // ASCII value A, B, C...
    	
		
		
		initialPosition = sb.toString();
		this.setPosition(initialPosition);
	}

	@Override
	public Boolean canMove(String targetPosition) {
		char targetPositionNum = targetPosition.charAt(0);
		char targetPositionLetter = targetPosition.charAt(1);
		char myPositionNum = this.position.charAt(0);
		char myPositionLetter = this.position.charAt(1);
		
		Boolean isWhiteOriented = this.state.getBoardState() == State.BoardState.WHITE_ORIENTED;
		Boolean positionedForTwoSteps =  myPositionNum == '2' || myPositionNum == '7';
		
		Boolean twoSteps = positionedForTwoSteps && (Math.abs(targetPositionNum - myPositionNum) == 2);
		
		Boolean rightDirection = isWhiteOriented && this.getColor() == Color.BLACK && targetPositionNum < myPositionNum
									|| isWhiteOriented && this.getColor() == Color.WHITE && targetPositionNum > myPositionNum
										|| !isWhiteOriented && this.getColor() == Color.BLACK && targetPositionNum > myPositionNum
												|| !isWhiteOriented && this.getColor() == Color.WHITE && targetPositionNum < myPositionNum;
														
		Boolean rightAmount = ( Math.abs(targetPositionNum - myPositionNum) == 1 )
								&& (targetPositionLetter == myPositionLetter);
		
		return twoSteps || (rightDirection && rightAmount);
	}
	
	

}
