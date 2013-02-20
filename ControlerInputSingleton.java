
public class ControlerInputSingleton {
	private static ControlerInputSingleton instance;
	private ControlerInputSingleton(){}
	
	public static synchronized ControlerInputSingleton getInstance()
	{
		if (instance == null){
			instance = new ControlerInputSingleton();
		}
		return instance;
	}
	public void updateClients(int movement){
		GUIManagerInputSingleton.getInstance().updateClients(movement);
	}
}
