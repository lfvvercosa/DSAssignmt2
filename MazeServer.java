import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MazeServer {
    public static void main(String[] args) throws IOException {
        
    	Queue<Movement> movements = new LinkedList<Movement>();
    	List<Socket> clients = new LinkedList<Socket>();
    	Map<String, DirectedPoint> clientsLastMove = new HashMap<String, DirectedPoint>();
    	ServerSocket serverSocket = null;
        boolean listening = true;

        try {
          if(args.length == 1) {
        		serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        	} else {
        		System.err.println("ERROR: Invalid arguments!");
        		System.exit(-1);
        	}
        } catch (IOException e) {
            System.err.println("ERROR: Could not listen on port!");
            System.exit(-1);
        }

        while (listening) {
        	Socket client = serverSocket.accept();
        	new MazeServerHandlerThread(client, movements, clientsLastMove).start();
        	clients.add(client);
        	if(clients.size() == 1){
        		System.out.println("MovementsCollectorThread was created");
        		new MovementsCollectorThread(clients, movements).start();
        	}
        }

        serverSocket.close();
    }
}
