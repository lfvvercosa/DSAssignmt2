import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

public class MazeServerHandlerThread extends Thread {
	private Socket socket = null;
	Queue<Movement> movements;

	public MazeServerHandlerThread(Socket socket, Queue<Movement> movements) {
		super("MazeServerHandlerThread");
		this.socket = socket;
		this.movements = movements;
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
		synchronized (movements) {
			movements.add(movement);
		}
	}
	public void sendObjOutputStreamToMovementsCollectorThread(ObjectOutputStream objOutputStream){
		synchronized (MovementsCollectorThread.objOutputStreamClients) {
			MovementsCollectorThread.objOutputStreamClients.add(objOutputStream);
		}
	}
}
