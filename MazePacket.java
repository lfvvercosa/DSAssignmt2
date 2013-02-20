import java.io.Serializable;
 /**
 * MazePacket
 * ============
 * 
 * Packet format of the packets exchanged between the Maze Server and the Maze Client
 * 
 */


public class MazePacket implements Serializable {

	/* define constants */
	/* for part 1/2/3 */
/*	public static final int BROKER_NULL    = 0;
	public static final int BROKER_QUOTE   = 102;
	public static final int BROKER_ERROR   = 103;
	public static final int BROKER_FORWARD = 104;
	public static final int BROKER_BYE     = 199;*/
	public int type;
	public int message;
	public int error_code;
	
	public MazePacket(){}
	public MazePacket(int type, int message){
		this.type = type;
		this.message = message;
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
	/* for part 2/3 */
	/*public static final int EXCHANGE_ADD    = 201;
	public static final int EXCHANGE_UPDATE = 202;
	public static final int EXCHANGE_REMOVE = 203;
	public static final int EXCHANGE_REPLY  = 204;*/
	
	
	/* for part 3 */
	/*public static final int LOOKUP_REQUEST  = 301;
	public static final int LOOKUP_REPLY    = 302;
	public static final int LOOKUP_REGISTER = 303;*/
	
	/* error codes */
	/* for part 2/3 */
	/*public static final int ERROR_INVALID_SYMBOL   = -101;
	public static final int ERROR_OUT_OF_RANGE     = -102;
	public static final int ERROR_SYMBOL_EXISTS    = -103;
	public static final int ERROR_INVALID_EXCHANGE = -104;*/
	
	/* message header */
	/* for part 1/2/3 */
	
	/* request quote */
	/* for part 1/2/3 */
	
	/* quote */
	/* for part 1/2/3 */
	
	/* report errors */
	/* for part 2/3 */
	
	/* exchange lookup */
	/* for part 3 */
	
}
