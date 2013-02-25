import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;


public class GUIManagerInputSingleton extends GUIManagerSingleton{
	private static GUIManagerInputSingleton instance;
	private GUIManagerInputSingleton(){}

	public static synchronized GUIManagerInputSingleton getInstance()
	{
		if (instance == null){
			instance = new GUIManagerInputSingleton();
		}
		return instance;
	}
	//TODO Extend this method to handle queue
	public void updateClients(Queue<Movement> movements){
		System.out.println("The method updateClients was called");
		Map<String, Client> clientsMap = null; 
		Movement movement;
		clientsMap = createClientsMap();

		while(!movements.isEmpty()){
			movement = movements.remove();
			
			if(movement.getMovType() == Movement.MOV_TO_MOVE){
				if(clientsMap.get(movement.getName()) != null){
					updateClient(clientsMap.get(movement.getName()), movement.getMovement());			
				}
			}else if(movement.getMovType() == Movement.MOV_TO_BORN){
				if(clientsMap.isEmpty()){
					System.out.println("createGUIClient was called here");
					createGUIClient(movement.getClient(), movement.getPointToBorn(), movement.getClientsLastMove());
				}else{
					System.out.println("createRemoteClient was called here");
					createRemoteClient(movement.getClient().getName(), movement.getPointToBorn());
				}
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
	public void createGUIClient(Client client, DirectedPoint pointToBorn, Map<String, DirectedPoint> clientsLastMove){
		if(maze == null){
			System.out.println("maze is null!!!!");
		}
		assert (clientsLastMove) != null;
		maze.addClient(client, pointToBorn);
		createPreviousClients(clientsLastMove, client.getName());
		System.out.println("GUIClient called "+client.getName()+" has been created");
	}
	public void createRemoteClient(String clientName, DirectedPoint pointToBorn){
		if(maze == null){
			System.out.println("maze is null!!!!");
		}
		maze.addClient(new RemoteClient(clientName), pointToBorn);
		System.out.println("RemoteClient called "+clientName+" has been created");
	}
	public void createPreviousClients(Map<String, DirectedPoint> clientsLastMove, String myselfName){
		Iterator<String> iteratorClients = clientsLastMove.keySet().iterator();
		String clientNameTemp;
		
		while(iteratorClients.hasNext()){
			clientNameTemp = iteratorClients.next();
			if(!clientNameTemp.equals(myselfName)){
				createRemoteClient(clientNameTemp, clientsLastMove.get(clientNameTemp));				
			}
		}
	}
}
