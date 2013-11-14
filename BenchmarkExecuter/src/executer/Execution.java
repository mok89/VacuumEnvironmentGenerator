package executer;

import aima.core.agent.Environment;

public class Execution extends Thread {

	private Environment env;
	
	public Execution(Environment env) {
		this.env = env;
	}
	
	@Override
	public void run() {
		env.stepUntilDone();
		Executer.executionEnded = true;
	}
}
