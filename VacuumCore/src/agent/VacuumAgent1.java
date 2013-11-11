package agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.impl.AbstractAgent;
import aima.core.environment.map.Map;
import aima.core.util.datastructure.Pair;
import core.LocalVacuumEnvironmentPercept;

public class VacuumAgent1 extends AbstractAgent {

	public VacuumAgent1() {
		super(new AgentProgram() {
			@Override
			public Action execute(final Percept percept) {
				final LocalVacuumEnvironmentPercept vep = (LocalVacuumEnvironmentPercept) percept;
				final Set<Action> actionsKeySet = vep.getActionEnergyCosts().keySet();


				new Random();
				final int randomInt = new Random().nextInt(actionsKeySet.size());
				final Iterator<Action> iterator = actionsKeySet.iterator();
				for (int i = 0; i < randomInt; i++)
					iterator.next();
				return iterator.next();
			}
		});
	}


	public class Graph{
		int muro=2;
		int base=-1;
		int size;
		int hashBase;
		HashMap<Integer, Node>graph=new HashMap<Integer,Node>();
		Graph(int [][]matrix,int size){
			this.size=size;
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++){
					if(matrix[i][j]!=muro){
						Node n=new Node();
						n.x=i;n.y=j;n.state=matrix[i][j];
						graph.put(getHash(i, j), n);
						if(matrix[i][j]==base){
							hashBase=getHash(i, j);
						}
					}
				}
			}
			Set<Entry<Integer, Node>> entrySet = graph.entrySet();
			for (Entry<Integer, Node> entry : entrySet) {
				entry.getValue().fillAdj(matrix,this);
			}
		}

		public Node getNode(int x, int y){
			Integer hash=getHash(x, y);
			if(graph.containsKey(hash)){
				return graph.get(hash);
			}else
				return null;
		}

		public int getHash(int x,int y){
			return (x+1)*1000+(y+1);
		}
		/**
		 * Obstacle is not include in state of Node, because it's implicit from the Edge;
		 * If the Edge is not present between two node, then the Node is unaccessible
		 * @author luigi
		 *
		 */
		public class Node{
			int x,y;
			int state;
			int hash;

			Node(){
				hash=(x+1)*1000+(y+1);
			}
			final ArrayList<Node> adjacent=new ArrayList<Node>();

			/**
			 * This is a waste method, use with criteria
			 * @param n
			 * @return
			 */
			public int getDistanceFrom(Node n){
				int count=0;
				int nhash=n.hash;
				
				ArrayList<Node>current=new ArrayList<Node>();
				current.addAll(adjacent);
				do{
					for(Node aj:current){
						if(aj.hash==nhash){
							return count;
						}
					}
					count++;
					
				}while(current.size()<1);

				return state;
			}

			public void fillAdj(int [][]matrix, Graph graph){
				Integer hash=0;
				if(x-1 > 0){
					hash=graph.getHash(x-1, y);
					if(graph.graph.containsKey(hash)){
						adjacent.add(graph.graph.get(hash));
					}
				}
				if(y-1 > 0){
					hash=graph.getHash(x, y-1);
					if(graph.graph.containsKey(hash)){
						adjacent.add(graph.graph.get(hash));
					}
				}
				if(x+1 < graph.size){
					hash=graph.getHash(x+1, y);
					if(graph.graph.containsKey(hash)){
						adjacent.add(graph.graph.get(hash));
					}
				}
				if(y+1 < graph.size){
					hash=graph.getHash(x, y+1);
					if(graph.graph.containsKey(hash)){
						adjacent.add(graph.graph.get(hash));
					}
				}
			}
		}
	}

}
