package core;

import java.awt.Point;

/**
 * 
 * @author fabrizio
 *
 */

public class Room {

	public static final int DEFAULT_SIZE = 10;
	public static final double DEFAULT_ENERGY = 100;
	public static final double DEFAULT_PERC_DIRTY = 10;
	public static final double DEFAULT_PERC_WALL = 10;
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

	private Tile[][] cell;
	public void setCell(Tile[][] cell) {
		this.cell = cell;
	}

//	private int size;
	private int sizeM;
	private int sizeN;
	private double energy;
	private double perc_dirty;
	private double perc_wall;

	private double perc_clean;
	private double cost_move_up;
	private double cost_move_down;
	private double cost_move_left;
	private double cost_move_right;
	private double cost_suck;

	public Room() {
		this.sizeM=DEFAULT_SIZE;
		this.sizeN=DEFAULT_SIZE;
		this.energy=DEFAULT_ENERGY;
		this.perc_dirty=DEFAULT_PERC_DIRTY;
		this.perc_wall=DEFAULT_PERC_WALL;
		this.perc_clean=DEFAULT_PERC_CLEAN;
		this.cost_move_up=DEFAULT_COST_MOVE_UP;
		this.cost_move_down=DEFAULT_COST_MOVE_DOWN;
		this.cost_move_left=DEFAULT_COST_MOVE_LEFT;
		this.cost_move_right=DEFAULT_COST_MOVE_RIGHT;
		this.cost_suck=DEFAULT_COST_SUCK;
		cell = new Tile[sizeN][sizeM];
		for(int i=0; i<sizeN; i++)
			for(int j=0; j<sizeM; j++){
				cell[i][j]=new Tile(CLEAN, 1);
			}
	}

	public Room(int sizeN,int sizeM, double energy, double perc_dirty,double perc_wall, double perc_clean, double cost_move_up, double cost_move_down, double cost_move_left, double cost_move_right, double cost_suck) {
		this.sizeN=sizeN;
		this.sizeM=sizeM;
		this.energy=energy;
		this.perc_dirty=perc_dirty;
		this.perc_wall=perc_wall;
		this.perc_clean=perc_clean;
		this.cost_move_up=cost_move_up;
		this.cost_move_down=cost_move_down;
		this.cost_move_left=cost_move_left;
		this.cost_move_right=cost_move_right;
		this.cost_suck=cost_suck;
		cell = new Tile[sizeN][sizeM];
		for(int i=0; i<sizeN; i++)
			for(int j=0; j<sizeM; j++) {
				cell[i][j]=new Tile(CLEAN, 1);
				cell[i][j].setState(CLEAN);
			}
	}

	public Tile[][] getCell() {
		return cell;
	}

	public void setCell(int i, int j, int state) {
		try{
			if(state==Room.BASE) {
				if(thereIsOneBase())
					cell[i][j].setState(state);;
			}
			else {
				cell[i][j].setState(state);;
			}
		}catch(Exception e){}
	}

	public int getSizeM() {
		return sizeM;
	}

	public void setSizeM(int sizeM) {
		this.sizeM = sizeM;
	}

	public int getSizeN() {
		return sizeN;
	}

	public void setSizeN(int sizeN) {
		this.sizeN = sizeN;
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

	public double getPerc_wall() {
		return perc_wall;
	}

	public void setPerc_wall(double perc_wall) {
		this.perc_wall = perc_wall;
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
		for(int i=0; i<sizeN; i++)
			for(int j=0; j<sizeM; j++)
				if(cell[i][j].getState()==Room.BASE)
					return false;
		return true;
	}

	public void randomize() {
		Randomize r=new Randomize(sizeN,sizeM, (int)getPerc_dirty(), (int)getPerc_wall(), 3, getPointAgent(), getPointBase());
		r.randomize(cell,sizeN,sizeM);
		//		return r.randomize();

	}

	public boolean isAgent() {
		for(int i=0;i<sizeN;i++){
			for(int j=0;j<sizeM;j++){
				if(cell[i][j].getState()==Room.AGENT){
					return true;
				}
			}	
		}
		return false;
	}

	public boolean isBase() {
		for(int i=0;i<sizeN;i++){
			for(int j=0;j<sizeM;j++){
				if(cell[i][j].getState()==Room.BASE){
					return true;
				}
			}	
		}
		return false;
	}

	public Point getPointAgent() {
		for(int i=0;i<sizeN;i++){
			for(int j=0;j<sizeM;j++){
				if(cell[i][j].getState()==Room.AGENT){
					return new Point(i,j);
				}
			}	
		}
		return null;
	}

	public Point getPointBase() {
		for(int i=0;i<sizeN;i++){
			for(int j=0;j<sizeM;j++){
				if(cell[i][j].getState()==Room.BASE){
					return new Point(i,j);
				}
			}	
		}
		return null;
	}

	public Tile getAt(int x,int y){
		return cell[x][y];
	}
	
}
