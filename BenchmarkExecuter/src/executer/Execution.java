package executer;

import aima.core.agent.Environment;

public class Execution extends Thread {

	private Environment env;

	public Execution(Environment env) {
		this.env = env;
	}

	@Override
	public void run() {
		try {
			env.stepUntilDone();
			Executer.getExecutionEnded(true, true);
		} catch (Exception e) {
			Executer.getExecutionException(true, true);
		}
	}
}
