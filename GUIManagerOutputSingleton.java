import java.util.Iterator;


public class GUIManagerOutputSingleton {
	private static GUIManagerOutputSingleton instance;
	private static Maze maze;
	private GUIManagerOutputSingleton(){}
	
	public static synchronized GUIManagerOutputSingleton getInstance()
	{
		if (instance == null){
			instance = new GUIManagerOutputSingleton();
		}
		return instance;
	}
	public void sendMovementToServer(Movement movement){
		ControlerOutputSingleton.getInstance().sendMovementToServer(movement);
	}
}
