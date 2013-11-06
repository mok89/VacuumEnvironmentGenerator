package core;

public class Randomize {

	public void randomize(int[][] cell, int size) {
		boolean baseIsLocated=false;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				int r=(int) ((Math.random()*100)%100);
				if(!baseIsLocated && r>50 && r<55){
					cell[i][j]=Room.BASE;
					baseIsLocated=true;
				}else if(r>90){
					cell[i][j]=Room.DIRTY;
				}else if(r<30){
					cell[i][j]=Room.WALL;
				}else
					cell[i][j]=Room.CLEAN;
				if(!baseIsLocated){
					r=(int) ((Math.random()*100)%size);
					int c=(int) ((Math.random()*100)%size);
					cell[r][c]=Room.BASE;
					baseIsLocated=true;
				}
					
			}
		}
	}
//	private void print(int[][]cell,int size){
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				System.out.print(cell[i][j]);
//			}
//			System.out.println();
//		}
//	}
//	public static void main(String[] args) {
//		Randomize r=new Randomize();
//		int [][]cell = new int[Room.DEFAULT_SIZE][Room.DEFAULT_SIZE];
//		for(int i=0; i<Room.DEFAULT_SIZE; i++)
//			for(int j=0; j<Room.DEFAULT_SIZE; j++)
//				cell[i][j] = Room.CLEAN;
//		r.randomize(cell, Room.DEFAULT_SIZE);
//		r.print(cell, Room.DEFAULT_SIZE);
//	}

}
