import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Queue;

public class MazeServerHandlerThread extends Thread {
	private Socket socket = null;
	Queue<Movement> movements;
	Map<String, DirectedPoint> clientsLastMove;

	public MazeServerHandlerThread(Socket socket, Queue<Movement> movements, Map<String, DirectedPoint> clientsLastMove) {
		super("MazeServerHandlerThread");
		this.socket = socket;
		this.movements = movements;
		this.clientsLastMove = clientsLastMove;
		System.out.println("Created new Thread to handle client");
	}

	public void run() {

		boolean gotByePacket = false;

		try {
			//			/* stream to read from client */
			ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
			MazePacket packetFromClient;
			//
			/* stream to write back to client */
			ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
			sendObjOutputStreamToMovementsCollectorThread(toClient);

			while (( packetFromClient = (MazePacket) fromClient.readObject()) != null) {
				/* create a packet to send reply back to client */
				//				MazePacket packetToClient = new MazePacket();
				//				packetToClient.type = MazePacket.MAZE_REPLY;

				/* process message */
				/* just echo in this example */
				if(packetFromClient.type == MazePacket.MAZE_REQUEST) {
					//					packetToClient.message = packetFromClient.message;
					//					System.out.println("From Client: " + packetFromClient.message);
					//
					//					/* send reply back to client */
					//					toClient.writeObject(packetToClient);
					//
					//					/* wait for next packet */
					addMovementToQueue(packetFromClient.movement);
					continue;
				}

				/* Sending an MAZE_NULL || MAZE_BYE means quit */
				//				if (packetFromClient.type == MazePacket.MAZE_NULL) {
				//					gotByePacket = true;
				//					packetToClient = new MazePacket();
				//					//packetToClient.message = MazePacket.MAZE_BYE;
				//					//packetToClient.message = "Bye!";
				//					toClient.writeObject(packetToClient);
				//					break;
				//				}

				/* if code comes here, there is an error in the packet */
				System.err.println("ERROR: Unknown MAZE_* packet!!");
				System.exit(-1);
			}

			/* cleanup when client exits */
			//			fromClient.close();
			//			toClient.close();
			//			socket.close();

		} catch (IOException e) {
			if(!gotByePacket)
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			if(!gotByePacket)
				e.printStackTrace();
		}
	}
	public void addMovementToQueue(Movement movement){
		System.out.println("The movement from "+movement.getName()+", moving "+movement.getMovement()+
				" has been added to the queue");
		if(movement.getMovType() == Movement.MOV_TO_BORN){
			movement.setClientsLastMove(clientsLastMove);				

		}
		changeClientLastMovement(movement.getName(), movement.getNewPoint());
		System.out.println("changeClientLastMovement has been called");
		synchronized (movements) {
			movements.add(movement);
		}
	}
	public void sendObjOutputStreamToMovementsCollectorThread(ObjectOutputStream objOutputStream){
		synchronized (MovementsCollectorThread.objOutputStreamClients) {
			MovementsCollectorThread.objOutputStreamClients.add(objOutputStream);
		}
	}
	public void changeClientLastMovement(String clientName, DirectedPoint newPoint){
		if(newPoint != null){
			System.out.println("newPoint was not null in the server");
			synchronized (clientsLastMove) {
				System.out.println("client "+clientName+" last movement has been added to the list");
				clientsLastMove.put(clientName, newPoint);
			}			
		}else{
			System.out.println("newPoint was null");
		}
		
		
	}
}
