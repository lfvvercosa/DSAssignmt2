import java.io.Serializable;
import java.util.Queue;
 /**
 * MazePacket
 * ============
 * 
 * Packet format of the packets exchanged between the Maze Server and the Maze Client
 * 
 */


public class MazePacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* define constants */
	/* for part 1/2/3 */
/*	public static final int BROKER_NULL    = 0;
	public static final int BROKER_QUOTE   = 102;
	public static final int BROKER_ERROR   = 103;
	public static final int BROKER_FORWARD = 104;
	public static final int BROKER_BYE     = 199;*/
	public int type;
	public int message;
	public Movement movement;
	Queue<Movement> movements;
	public int error_code;
	
	public MazePacket(){}
	public MazePacket(int type, int message){
		this.type = type;
		this.message = message;
	}
	public MazePacket(int type, Queue<Movement> movements){
		this.type = type;
		this.movements = movements;
	}
	public MazePacket(int type, Movement movement){
		this.type = type;
		this.movement = movement;
	}
	
	public static final int MAZE_NULL = -1;
	public static final int MAZE_REQUEST = 0;
	public static final int MAZE_REPLY = 1;
	
	public static final int MOV_FORWARD    = 2;
	public static final int MOV_BACKWARD   = 3;
	public static final int MOV_TURN_RIGHT   = 4;
	public static final int MOV_TURN_LEFT = 5;
	public static final int MOV_SHOOT     = 6;
	public static final int MOV_QUIT     = 7;
	
	
}
