import java.io.Serializable;
import java.util.Map;


public class Movement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int movement;
	private int movType;
	private DirectedPoint pointToBorn;
	private DirectedPoint newPoint;
	private Client client;
	private Map<String, DirectedPoint> clientsLastMove;
	public static final int MOV_TO_BORN = 0;
	public static final int MOV_TO_MOVE = 1;

	
	public Movement(String name, int movement) {
		super();
		this.name = name;
		this.movement = movement;
		this.setMovType(Movement.MOV_TO_MOVE);
	}
	public Movement(Client client, DirectedPoint pointToBorn) {
		super();
		this.client = client;
		this.name = client.getName();
		this.pointToBorn = pointToBorn;
		this.setMovType(Movement.MOV_TO_BORN);
	}
	public DirectedPoint getPointToBorn() {
		return pointToBorn;
	}

	public void setPointToBorn(DirectedPoint pointToBorn) {
		this.pointToBorn = pointToBorn;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMovement() {
		return movement;
	}
	public void setMovement(int movement) {
		this.movement = movement;
	}
	public int getMovType() {
		return movType;
	}
	public void setMovType(int movType) {
		this.movType = movType;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public DirectedPoint getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(DirectedPoint newPoint) {
		this.newPoint = newPoint;
	}
	public Map<String, DirectedPoint> getClientsLastMove() {
		return clientsLastMove;
	}
	public void setClientsLastMove(Map<String, DirectedPoint> clientsLastMove) {
		this.clientsLastMove = clientsLastMove;
	}
	
	
}
