package core;

public class Tile {
	int state;
	int value;
	/*
	public static final int CLEAN = 0;
	public static final int DIRTY = 1;
	public static final int WALL = 2;
	public static final int BASE = -1;
	public static final int AGENT = -2;
	*/
	
	public Tile(int state, int value) {
		super();
		this.state = state;
		this.value = value;
	}
	public int getState() {
		return state;
	}
	
	public void decrVal(){
		value--;
	}
	
	public void setState(int state) {
		if(this.state == state && state==Room.DIRTY){
			value=((value)%4)+1;
		}else{
			this.state = state;
		}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
