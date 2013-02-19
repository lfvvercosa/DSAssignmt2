
public class GUIManagerSingleton {
	private static GUIManagerSingleton instance;
	private GUIManagerSingleton(){}
	
	public static synchronized GUIManagerSingleton getInstance()
	{
		if (instance == null){
			instance = new GUIManagerSingleton();
		}
		return instance;
	}
}
