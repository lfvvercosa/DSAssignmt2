import java.util.Iterator;


public class GUIManagerOutputSingleton extends GUIManagerSingleton{
	private static GUIManagerOutputSingleton instance;
	private GUIManagerOutputSingleton(){}
	
	public static synchronized GUIManagerOutputSingleton getInstance()
	{
		if (instance == null){
			instance = new GUIManagerOutputSingleton();
		}
		return instance;
	}
	public void sendRotatingMovementToServer(Movement movement){
		if(movement == null){
			System.out.println("movement is null");
		}
		if(maze == null){
			System.out.println("maze is null");
		}
		if(movement.getName() == null){
			System.out.println("movement.getName() is null");
		}
		if(movement.getMovement() == MazePacket.MOV_TURN_LEFT){
			DirectedPoint lastMove = maze.returnRotateClientLeft(movement.getName());
			if(lastMove == null){
				System.out.println("lastMove is null");
			}else{
				System.out.println("lastMove is not null");
			}
			System.out.println();
			movement.setNewPoint(lastMove);
		}else if(movement.getMovement() == MazePacket.MOV_TURN_RIGHT){
			movement.setNewPoint(maze.returnRotateClientRight(movement.getName()));
		}
		ControlerOutputSingleton.getInstance().sendMovementToServer(movement);
	}
	public void sendMovingMovementToServer(Movement movement){
		if(movement.getMovement() == MazePacket.MOV_FORWARD){
			movement.setNewPoint(maze.returnMoveClientForward(movement.getName()));			
		}else if(movement.getMovement() == MazePacket.MOV_BACKWARD){
			movement.setNewPoint(maze.returnMoveClientBackward(movement.getName()));
		}
		ControlerOutputSingleton.getInstance().sendMovementToServer(movement);
	}
	public void sendShotMovementToServer(Movement movement){
		ControlerOutputSingleton.getInstance().sendMovementToServer(movement);
	}
	public void sendBorningMovementToServer(Movement movement){
		movement.setNewPoint(movement.getPointToBorn());
		ControlerOutputSingleton.getInstance().sendMovementToServer(movement);
	}
}
