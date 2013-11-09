package core;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Randomize {

	int numberTielsWithDirt;
	int numberTielsAsObstacle;
	int numberOfIstance;
	Point agent;
	Point base;
	int size;


	public Randomize(int numberTielsWithDirt, int numberTielsAsObstacle,
			int numberOfIstance, Point agent, Point base, int size) {
		super();
		this.numberTielsWithDirt = numberTielsWithDirt;
		this.numberTielsAsObstacle = numberTielsAsObstacle;
		this.numberOfIstance = numberOfIstance;
		this.size=size;

		if(agent==null){
			this.agent=new Point(1, 1);
		}else{
			this.agent = agent;
		}
		if(base==null){
			this.agent=new Point(size-1, size-1);
		}else{
			this.base = base;
		}
	}

	public Randomize() {
	}
	
	public void randomize() {
		String OS = System.getProperty("os.name").toLowerCase();
		String command="";
		if(OS.indexOf("win") >= 0){
			//non so se funziona questo su windows
			command=".\\res\\getEnv.pl OS=W.exe Dt=10 Ob=10 N=10 Xb=1 Yb=2 Xa=8 Ya=9 NENV=3";
		}else if(OS.indexOf("mac") >= 0){
			command="./res/getEnv.pl OS=A Dt=10 Ob=10 N=10 Xb=1 Yb=2 Xa=8 Ya=9 NENV=3";
		}else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0){
			command="./res/getEnv.pl OS=L Dt=10 Ob=10 N=10 Xb=1 Yb=2 Xa=8 Ya=9 NENV=3";
		}
		//"/home/luigi/dlvIS/getEnv.pl Dt=10 Ob=10 N=10 Xb=1 Yb=2 Xa=8 Ya=9 NENV=3"
		try{
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);

			BufferedReader stdInput = new BufferedReader(new 
		             InputStreamReader(proc.getInputStream()));

		        BufferedReader stdError = new BufferedReader(new 
		             InputStreamReader(proc.getErrorStream()));

		        // read the output from the command
		        System.out.println("Here is the standard output of the command:\n");
		        String s;
				while ((s = stdInput.readLine()) != null) {
		            System.out.println(s);
		        }

		        // read any errors from the attempted command
		        System.out.println("Here is the standard error of the command (if any):\n");
		        while ((s = stdError.readLine()) != null) {
		            System.out.println(s);
		        }
//			InputStream stdin = proc.getInputStream();
//			InputStreamReader isr = new InputStreamReader(stdin);
//			BufferedReader br = new BufferedReader(isr);
//
//			String line = null;
//			System.out.println("<OUTPUT>");
//			int exitVal = proc.waitFor();
//
//			while ( (line = br.readLine()) != null)
//				System.out.println(line);
//
//			System.out.println("</OUTPUT>");
//			System.out.println("Process exitValue: " + exitVal);
		}catch(Exception e){

		}
	}
	
	public void randomize(int[][] cell, int size){
		boolean baseIsLocated=false;
				int r;
				for(int i=0;i<size;i++){
					for(int j=0;j<size;j++){
						r=(int) ((Math.random()*100)%100);
						if(!baseIsLocated && r>50 && r<55){
							cell[i][j]=Room.BASE;
							baseIsLocated=true;
						}else if(r>90){
							cell[i][j]=Room.DIRTY;
						}else if(r<30){
							cell[i][j]=Room.WALL;
						}else
							cell[i][j]=Room.CLEAN;
					}
				}
				if(!baseIsLocated){
					r=(int) ((Math.random()*100)%size);
					int c=(int) ((Math.random()*100)%size);
					cell[r][c]=Room.BASE;
					baseIsLocated=true;
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
		public static void main(String[] args) {
			Randomize r=new Randomize();
//			int [][]cell = new int[Room.DEFAULT_SIZE][Room.DEFAULT_SIZE];
//			for(int i=0; i<Room.DEFAULT_SIZE; i++)
//				for(int j=0; j<Room.DEFAULT_SIZE; j++)
//					cell[i][j] = Room.CLEAN;
//			r.randomize(cell, Room.DEFAULT_SIZE);
			r.randomize();
		}


}
