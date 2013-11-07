package core;

/**
 * 
 * @author fabrizio
 *
 */

public class Room {

	public static final int DEFAULT_SIZE = 10;
	public static final double DEFAULT_ENERGY = 100;
	public static final double DEFAULT_PERC_DIRTY = 10;
	public static final double DEFAULT_PERC_CLEAN = 10;
	public static final double DEFAULT_COST_MOVE_UP = 1;
	public static final double DEFAULT_COST_MOVE_DOWN = 1;
	public static final double DEFAULT_COST_MOVE_LEFT = 1;
	public static final double DEFAULT_COST_MOVE_RIGHT = 1;
	public static final double DEFAULT_COST_SUCK = 2;
	
	public static final int CLEAN = 0;
	public static final int DIRTY = 1;
	public static final int WALL = 2;
	public static final int BASE = -1;
	public static final int AGENT = -2;
	
	private int[][] cell;
	private int size;
	private double energy;
	private double perc_dirty;
	private double perc_clean;
	private double cost_move_up;
	private double cost_move_down;
	private double cost_move_left;
	private double cost_move_right;
	private double cost_suck;
	
	public Room() {
		this.size=DEFAULT_SIZE;
		this.energy=DEFAULT_ENERGY;
		this.perc_dirty=DEFAULT_PERC_DIRTY;
		this.perc_clean=DEFAULT_PERC_CLEAN;
		this.cost_move_up=DEFAULT_COST_MOVE_UP;
		this.cost_move_down=DEFAULT_COST_MOVE_DOWN;
		this.cost_move_left=DEFAULT_COST_MOVE_LEFT;
		this.cost_move_right=DEFAULT_COST_MOVE_RIGHT;
		this.cost_suck=DEFAULT_COST_SUCK;
		cell = new int[size][size];
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				cell[i][j] = CLEAN;
	}
	
	public Room(int size, double energy, double perc_dirty, double perc_clean, double cost_move_up, double cost_move_down, double cost_move_left, double cost_move_right, double cost_suck) {
		this.size=size;
		this.energy=energy;
		this.perc_dirty=perc_dirty;
		this.perc_clean=perc_clean;
		this.cost_move_up=cost_move_up;
		this.cost_move_down=cost_move_down;
		this.cost_move_left=cost_move_left;
		this.cost_move_right=cost_move_right;
		this.cost_suck=cost_suck;
		cell = new int[size][size];
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				cell[i][j] = CLEAN;
	}

	public int[][] getCell() {
		return cell;
	}

	public void setCell(int i, int j, int state) {
		if(state==Room.BASE) {
			if(thereIsOneBase())
				cell[i][j]=state;
		}
		else {
			cell[i][j]=state;
		}
	}
		
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getPerc_dirty() {
		return perc_dirty;
	}

	public void setPerc_dirty(double perc_dirty) {
		this.perc_dirty = perc_dirty;
	}

	public double getPerc_clean() {
		return perc_clean;
	}

	public void setPerc_clean(double perc_clean) {
		this.perc_clean = perc_clean;
	}

	public double getCost_move_up() {
		return cost_move_up;
	}

	public void setCost_move_up(double cost_move_up) {
		this.cost_move_up = cost_move_up;
	}

	public double getCost_move_down() {
		return cost_move_down;
	}

	public void setCost_move_down(double cost_move_down) {
		this.cost_move_down = cost_move_down;
	}

	public double getCost_move_left() {
		return cost_move_left;
	}

	public void setCost_move_left(double cost_move_left) {
		this.cost_move_left = cost_move_left;
	}

	public double getCost_move_right() {
		return cost_move_right;
	}

	public void setCost_move_right(double cost_move_right) {
		this.cost_move_right = cost_move_right;
	}

	public double getCost_suck() {
		return cost_suck;
	}

	public void setCost_suck(double cost_suck) {
		this.cost_suck = cost_suck;
	}

	public boolean thereIsOneBase() {
		/*
		 * Only one base
		 * if the base it is present return false, true otherwise
		 */
		for(int i=0; i<DEFAULT_SIZE; i++)
			for(int j=0; j<DEFAULT_SIZE; j++)
				if(cell[i][j]==Room.BASE)
					return false;
		return true;
	}

	public void randomizeCell() {
//		Randomize r=new Randomize();
//		r.randomize(cell, DEFAULT_SIZE);
	}
	
		
}
