
public class ControlerOutputSingleton {
	private static ControlerOutputSingleton instance;
	private ControlerOutputSingleton(){}
	
	public static synchronized ControlerOutputSingleton getInstance()
	{
		if (instance == null){
			instance = new ControlerOutputSingleton();
		}
		return instance;
	}
	public void sendMovementToServer(Movement movement){
		SocketManager.sendPacket(MazePacket.MAZE_REQUEST,movement);
	}
}
