import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class MovementsCollectorThread extends Thread{

	Queue<Movement> movements;
	List<Socket> clients;
	static List<ObjectOutputStream> objOutputStreamClients;
	ObjectOutputStream toClient;
	MazePacket mazePacket;

	public MovementsCollectorThread(List<Socket> clients, Queue<Movement> movements){
		this.clients = clients;
		this.movements = movements;
		objOutputStreamClients = new LinkedList<ObjectOutputStream>();
	}

	@Override
	public void run() {
		//super.run();
		while(true){
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendMovementsToClients();
		}
	}
	public Queue<Movement> collectMovements(){
		Queue<Movement> movementsCopy = new LinkedList<Movement>();
		synchronized (movements) {
			while(!movements.isEmpty()){
				movementsCopy.add(movements.remove());
			}
		}
		return movementsCopy;
	}
	public void sendMovementsToClients(){
		//System.out.println("method sendMovementsToClients has been called");
		Iterator<Socket> iteratorClients = clients.iterator();
		Queue<Movement> movementsCopy = collectMovements();
		//the index is necessary to associate the ObjectOutputStream to the correct socket
		int index = 0;
		if(!movementsCopy.isEmpty()){
			while(iteratorClients.hasNext()){
				sendMovementsToClient(iteratorClients.next(), movementsCopy, index++);
			}
		}
		movementsCopy = null;
	}
	public void sendMovementsToClient(Socket client, Queue<Movement> movementsCopy, int index){
		System.out.println("movements array has been sent to client");
		try {
			mazePacket = new MazePacket(MazePacket.MAZE_REPLY, movementsCopy);
			objOutputStreamClients.get(index).writeObject(mazePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
