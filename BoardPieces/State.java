package BoardPieces;

public class State {
	public enum BoardState{
		WHITE_ORIENTED, BLACK_ORIENTED, INITIAL
	}
	
	private BoardState state;
	public State(BoardState state){
		this.state = state;
	}
	
	public BoardState getBoardState() {
		return this.state;
	}
	
	public void setState(BoardState state) {
		this.state = state;
	}
	
}
