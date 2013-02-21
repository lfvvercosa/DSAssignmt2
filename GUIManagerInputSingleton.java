import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;


public class GUIManagerInputSingleton {
	private static GUIManagerInputSingleton instance;
	private static Maze maze;
	private GUIManagerInputSingleton(){}
	
	public static synchronized GUIManagerInputSingleton getInstance()
	{
		if (instance == null){
			instance = new GUIManagerInputSingleton();
		}
		return instance;
	}
	public void setGUIManagerSingletonAttributes(Maze maze){
		this.maze = maze;
	}
	//TODO Extend this method to handle queue
	public void updateClients(Queue<Movement> movements){
		Map<String, Client> clientsMap = createClientsMap();
		Movement movement;
		
		while(!movements.isEmpty()){
			movement = movements.remove();
			if(clientsMap.get(movement.getName()) != null){
				updateClient(clientsMap.get(movement.getName()), movement.getMovement());
				
			}
		}
	}
	public Map<String, Client> createClientsMap(){
		Iterator<Client> clientsIterator = maze.getClients();
		Map<String, Client> clientsMap = new HashMap<String, Client>();
		Client client;
		while(clientsIterator.hasNext()){
			client = clientsIterator.next();
			clientsMap.put(new String(client.getName()), client);
		}
		return clientsMap;
	}
	public void updateClient(Client client, int movement){
		switch(movement){
			case MazePacket.MOV_FORWARD: client.forward();
				break;
			case MazePacket.MOV_BACKWARD: client.backup();
			 	break;
			case MazePacket.MOV_TURN_LEFT: client.turnLeft();
			 	break;
			case MazePacket.MOV_TURN_RIGHT: client.turnRight();
			 	break;
			case MazePacket.MOV_SHOOT: client.fire();
			 	break;
		}
	}
}
