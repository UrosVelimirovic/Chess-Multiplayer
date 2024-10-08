package BoardPieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChessPiece extends ImageView{
	
	public enum PieceType{
		PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
	}
	public enum Color{
		BLACK, WHITE;
	}
	
	private static String[][] files = new String[6][2];
	
    static {
       files[0][0] = "Black_Pawn.png";
       files[0][1] = "White_Pawn.png";
       files[1][0] = "Black_Rook.png";
       files[1][1] = "White_Rook.png";
       files[2][0] = "Black_Knight.png";
       files[2][1] = "White_Knight.png";
       files[3][0] = "Black_Bishop.png";
       files[3][1] = "White_Bishop.png";
       files[4][0] = "Black_Queen.png";
       files[4][1] = "White_Queen.png";
       files[5][0] = "Black_King.png";
       files[5][1] = "White_King.png";
    }
	
	protected PieceType type;
	protected Color color;
	protected String position;
	protected State state;
	protected int number;
	protected Image image;
	
	public ChessPiece(PieceType type, Color color, int number, State state) {
		this.type = type;
		this.color = color;
		this.number = number;
		this.state = state;
		this.mySetImage();
		this.setImage(image);
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public Color getColor() {
		return this.color;
	}
	public void mySetImage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pieces/");
		
		sb.append(files[type.ordinal()][color.ordinal()]);
		image = new Image(sb.toString());
	}
	
	public PieceType getType() {
		return this.type;
	}
	
	protected abstract void setInitialPosition();
	
	public abstract Boolean canMove(String targetPosition);
}

