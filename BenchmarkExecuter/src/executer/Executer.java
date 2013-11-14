package executer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import aima.core.agent.impl.AbstractAgent;
import aima.core.environment.vacuum.VacuumEnvironmentState;
import instanceXMLParser.Instance;
import core.VacuumEnvironment;

public class Executer {

	public static boolean executionEnded;

	public static long timeInterval;

	public static void setTimeInterval() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("conf.txt"));
			String time = br.readLine();
			timeInterval = Long.valueOf(time);
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void executeBenchmark() {

		VacuumEnvironment enviroment;
		AbstractAgent agent;

		setTimeInterval();

		File dir = new File("./jars");
		String[] jars = dir.list();

		try {
			PrintWriter writer = new PrintWriter(
					"PerformanceMeasures/performanceMeasures.csv");

			writer.println(";Cleaned tiles;Current energy;Dirty initial tiles;Initial energy;Performance measure");
			
			for (String jar : jars) {
				try {
					URL jarURL = new URL("jar", "", "file:"
							+ dir.getAbsolutePath() + "/" + jar + "!/");

					URLClassLoader cl = new URLClassLoader(new URL[] { jarURL });
					Class Agent = cl.loadClass("agent.VacuumAgent1");

					System.out.println("Agent from file " + jar + " loaded");

					writer.println(jar.split(".jar")[0] + ";");

					File XMLdir = new File("./xml");
					String[] XMLs = XMLdir.list();

					for (String xml : XMLs) {
						Instance i = new Instance();
						i.buildINstanceJDom("./xml/" + xml);
						Object AgentObject = Agent.newInstance();
						agent = (AbstractAgent) AgentObject;
						enviroment = new VacuumEnvironment(i, agent);
						System.out.println("Executing on " + xml);

						long time = System.currentTimeMillis();

						executionEnded = false;

						boolean theAgentTakeTooTime = false;

						Execution exec = new Execution(enviroment);

						exec.start();

						while (!executionEnded) {
							if (System.currentTimeMillis() - time >= timeInterval) {
								exec.stop();
								executionEnded = true;
								theAgentTakeTooTime = true;
								System.out.println("The agent take too time");
							}
						}

						if (theAgentTakeTooTime) {
							writer.println(xml + ";"
									+ "Execution take too time" + ";");
						} else {

							core.VacuumEnvironmentState state = enviroment
									.getEnvState();
							String result = state.getCleanedTiles(agent) + ";"
									+ state.getCurrentEnergy(agent) + ";"
									+ state.getDirtyInitialTiles() + ";"
									+ state.getInitialEnergy() + ";"
									+ enviroment.getPerformanceMeasure(agent)
									+ ";";
							writer.println(xml + ";" + result);
						}
					}

					writer.println();
					System.out.println();
					System.out.println();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			writer.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Executer.executeBenchmark();
	}

}
