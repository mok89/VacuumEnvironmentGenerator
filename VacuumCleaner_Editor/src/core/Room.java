package core;

/**
 * 
 * @author fabrizio
 *
 */

public class Room {

	public static final int DEFAULT_SIZE = 10;
	public static final int CLEAN = 0;
	public static final int DIRTY = 1;
	public static final int WALL = 2;
	public static final int BASE = -1;
	
	private int[][] cell;
	
	public Room() {
		cell = new int[DEFAULT_SIZE][DEFAULT_SIZE];
		for(int i=0; i<DEFAULT_SIZE; i++)
			for(int j=0; j<DEFAULT_SIZE; j++)
				cell[i][j] = CLEAN;
	}

	public int[][] getCell() {
		return cell;
	}

	public void setCell(int i, int j, int state) {
		cell[i][j]=state;
	}
	
	public boolean addBase(int x, int y) {
		/*
		 * Only one base
		 */
		for(int i=0; i<DEFAULT_SIZE; i++)
			for(int j=0; j<DEFAULT_SIZE; j++)
				if(cell[i][j]==-1)
					return false;
		cell[x][y]=-1;
		return true;
	}
	
		
}
