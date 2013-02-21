import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;



public class SocketInputStreamThread extends Thread{
	public Socket socket;
	private ObjectInputStream  inputStream;
	private MazePacket packet;
	
	public SocketInputStreamThread(Socket socket){
		
		this.socket = socket;
		try {
			if(socket != null){
				inputStream = new ObjectInputStream(this.socket.getInputStream());
			}
		} catch (IOException e) {
			System.out.println("IO Error my friend");
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true){
			try {
				if(inputStream != null){
					packet = (MazePacket) inputStream.readObject();
					if(packet.type == MazePacket.MAZE_REPLY){
						System.out.println("Message received: "+packet.message);
						ControlerInputSingleton.getInstance().updateClients(packet);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
