
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
	public void sendMovementToServer(int movement){
		SocketManager.sendPacket(MazePacket.MAZE_REQUEST,movement);
	}
}
