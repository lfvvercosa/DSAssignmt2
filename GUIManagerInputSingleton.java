import java.util.Iterator;


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
	public void updateClients(int movement){
		Iterator clientsIterator = maze.getClients();
		Client client;
		
		while(clientsIterator.hasNext()){
			client = (Client)clientsIterator.next();
			updateClient(client, movement);
			
		}
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
