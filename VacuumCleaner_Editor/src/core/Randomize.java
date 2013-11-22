package core;

import java.awt.Point;

public class Randomize {

	int numberTielsWithDirt;
	int numberTielsAsObstacle;
	int numberOfIstance;
	Point agent;
	Point base;
	int sizeN;
	int sizeM;


	public Randomize(int sizeN,int sizeM,int numberTielsWithDirt, int numberTielsAsObstacle,
			int numberOfIstance, Point agent, Point base) {
		super();
		this.sizeN=sizeN;
		this.sizeM=sizeM;
		this.numberTielsWithDirt = numberTielsWithDirt;
		this.numberTielsAsObstacle = numberTielsAsObstacle;
		this.numberOfIstance = numberOfIstance;

		if(agent==null){
			this.agent=new Point(1, 1);
		}else{
			this.agent = new Point(agent.x+1, agent.y+1);
		}
		if(base==null){
			this.agent=new Point(sizeN-1, sizeM-1);
		}else{
			this.base = new Point(base.x+1, base.y+1);
		}
	}

	private void inizializeCell(int [][]cell) {
		for(int i=0; i<sizeN; i++)
			for(int j=0; j<sizeM; j++)
				cell[i][j] = Room.CLEAN;
	}

	public Randomize() {
	}
	
//	public ArrayList<int[][]> randomize() {
//		String OS = System.getProperty("os.name").toLowerCase();
//		String command="";
//		if(OS.indexOf("win") >= 0){
//			//non so se funziona questo su windows
//			command=".\\res\\getEnv.pl OS=W.exe ";
//		}else if(OS.indexOf("mac") >= 0){
//			command="./res/getEnv.pl OS=A ";
//		}else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0){
//			command="./res/getEnv.pl OS=L ";
//		}
//		//riempimento comando
//		command=command+"Dt="+numberTielsWithDirt+" Ob="+
//		numberTielsAsObstacle+" N="+sizeN+" Xb="+
//		base.x+" Yb="+base.y+" Xa="+agent.x+" Ya="+agent.y+" NENV="+numberOfIstance;
//		//"/home/luigi/dlvIS/getEnv.pl Dt=10 Ob=10 N=10 Xb=1 Yb=2 Xa=8 Ya=9 NENV=3"
//		try{
//			Runtime rt = Runtime.getRuntime();
//			Process proc = rt.exec(command);
//
//			BufferedReader stdInput = new BufferedReader(new 
//		             InputStreamReader(proc.getInputStream()));
//
////		        BufferedReader stdError = new BufferedReader(new 
////		             InputStreamReader(proc.getErrorStream()));
//
//		        // read the output from the command
////		        System.out.println("Here is the standard output of the command:\n");
//		        String s;
//		        //is a result
//		        //{base(1,2), agent(8,9), obstacle(8,6,1), obstacle(9,5,2), obstacle(10,1,3), obstacle(10,2,4), obstacle(10,3,5), obstacle(10,4,6), obstacle(10,5,7), obstacle(10,6,8), obstacle(10,7,9), obstacle(10,10,10), dirt(9,6,1), dirt(8,7,2), dirt(9,7,3), dirt(8,10,4), dirt(9,10,5), dirt(8,8,6), dirt(9,8,7), dirt(10,8,8), dirt(10,9,9), dirt(9,9,10)}
//				//
//		        StringBuilder sb=new StringBuilder("");
////		        s = stdInput.readLine();
////		        sb.append(s);
////		        s = stdInput.readLine();
////		        while ((s) != null) {
////		        	sb.append(";");
////		            sb.append(s);
////		            s = stdInput.readLine();
////		        }
//		        while ((s= stdInput.readLine()) != null) {
//		            sb.append(s);
//		            sb.append(";");
//		        }
//		        
//		        String []resultSet=sb.toString().split(";");
//		        ArrayList<int[][]>results=new ArrayList<int [][]>();
//		        //ogni set è una soluzione al problema
//		        int x,y;
//		        for (String set : resultSet) {
//		        	int [][]cell = new int[sizeN][sizeN];;
//		        	inizializeCell(cell);
//					if(!set.equals("")){
//						set=set.replaceAll("\\{", "");
//						set=set.replaceAll("\\}", "");
//						set=set.replaceAll(" ", "");
//						String []items=set.split(",");
//						int dit=0,ob=0;
//						for (int i = 0; i < items.length; i++) {
//							if(items[i].startsWith("base(")){
//								x=Integer.valueOf(items[i].replaceAll("base\\(", ""))-1;
//								y=Integer.valueOf(items[i+1].replaceAll("\\)", ""))-1;
//								cell[x][y]=Room.BASE;
//							}else if(items[i].startsWith("agent(")){
//								x=Integer.valueOf(items[i].replaceAll("agent\\(", ""))-1;
//								y=Integer.valueOf(items[i+1].replaceAll("\\)", ""))-1;
//								cell[x][y]=Room.AGENT;
//							}else if(items[i].startsWith("obstacle(")){
//								x=Integer.valueOf(items[i].replaceAll("obstacle\\(", ""))-1;
//								y=Integer.valueOf(items[i+1])-1;
//								cell[x][y]=Room.WALL;
//								ob++;
//							}else if(items[i].startsWith("dirt(")){
//								x=Integer.valueOf(items[i].replaceAll("dirt\\(", ""))-1;
//								y=Integer.valueOf(items[i+1])-1;
//								cell[x][y]=Room.DIRTY;
//								dit++;
//							}
//						}
////						System.out.println(dit+" "+ob);
//						results.add(cell);
//					}
//				}
//		        
//		        // read any errors from the attempted command
////		        System.out.println("Here is the standard error of the command (if any):\n");
////		        while ((s = stdError.readLine()) != null) {
////		            System.out.println(s);
////		        }
//		        return results;
//		}catch(Exception e){
//
//		}
//		return null;
//	}
	
	public void randomize(Tile[][] cell, int sizeN, int sizeM){
		boolean baseIsLocated=false;
				int r;
//				System.out.println(numberTielsWithDirt+" dirt");
				for(int i=0;i<sizeN;i++){
					for(int j=0;j<sizeM;j++){
						r=(int) ((Math.random()*100)%100);
						if(!baseIsLocated && r>50 && r<55){
							cell[i][j].setState(Room.BASE);
							baseIsLocated=true;
						}else if(r>100-numberTielsWithDirt){
							cell[i][j].setState(Room.DIRTY);
							int rv=(int) ((Math.random()*100)%4)+1;
							cell[i][j].setValue(rv);
						}else if(r<numberTielsAsObstacle){
							cell[i][j].setState(Room.WALL);
						}else
							cell[i][j].setState(Room.CLEAN);
					}
				}
				if(!baseIsLocated){
					r=(int) ((Math.random()*100)%sizeN);
					int c=(int) ((Math.random()*100)%sizeM);
					cell[r][c].setState(Room.BASE);
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
//		public static void main(String[] args) {
//			String s="base(sadkfnkjdv}";
//			s=s.replaceAll("base\\(", "");
//			System.out.println(s);
//		}


}
//InputStream stdin = proc.getInputStream();
//InputStreamReader isr = new InputStreamReader(stdin);
//BufferedReader br = new BufferedReader(isr);
//
//String line = null;
//System.out.println("<OUTPUT>");
//int exitVal = proc.waitFor();
//
//while ( (line = br.readLine()) != null)
//	System.out.println(line);
//
//System.out.println("</OUTPUT>");
//System.out.println("Process exitValue: " + exitVal);
