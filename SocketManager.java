import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SocketManager {
	static Socket socket = null;
	static ObjectOutputStream  outputStream = null;
	
	public static void openSocket(String hostname, int port){
		MazePacket packet;
		int count = 0;
		
		
			try {
				socket = new Socket(hostname, port);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				new SocketInputStreamThread(socket).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			while(true){
//				packet = new MazePacket();
//				packet.type = MazePacket.MAZE_REQUEST;
//				// TODO get message from listener
//				packet.message = MazePacket.MAZE_NULL;
//				outputStream.writeObject(packet);
//				Thread.sleep(1000);
//			}
			
		 
	}
	public static void sendPacket(int type, int message){
		MazePacket mazePacket = new MazePacket(type, message);
		try {
			outputStream.writeObject(mazePacket);
			System.out.println("A packet's been sent to the server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void closeSocket(){
		// TODO Find a way to call thread and tell it to stop listenning
		try {
			outputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
