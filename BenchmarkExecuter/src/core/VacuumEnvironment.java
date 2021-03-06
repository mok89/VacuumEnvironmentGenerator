package core;

import instanceXMLParser.Instance;

import java.awt.Point;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.EnvironmentState;
import aima.core.agent.Percept;
import aima.core.agent.impl.AbstractEnvironment;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): pg 58.<br>
 * <br>
 * Let the world contain just two locations. Each location may or may not
 * contain dirt, and the agent may be in one location or the other. There are 8
 * possible world states, as shown in Figure 3.2. The agent has three possible
 * actions in this version of the vacuum world: <em>Left</em>, <em>Right</em>,
 * and <em>Suck</em>. Assume for the moment, that sucking is 100% effective. The
 * goal is to clean up all the dirt.
 * 
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class VacuumEnvironment extends AbstractEnvironment {

	public enum LocationState {
		Clean, Dirty, Obstacle
	};

	protected VacuumEnvironmentState envState = null;
	protected boolean isDone = false;
	private boolean energyLessThenZero = false;

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed at random.
	 */
	public VacuumEnvironment(final Instance instanceBean, final Agent agent) {

		this.envState = new VacuumEnvironmentState(instanceBean, agent);

		this.addAgent(agent);

	}

	@Override
	public EnvironmentState executeAction(final Agent agent, final Action agentAction) {

		// If the Agent tries to use more energy than it has, we kill it
		if (!agentAction.isNoOp() && this.envState.getCurrentEnergy(agent) < this.envState.getEnergyCost(agentAction)) {

			this.energyLessThenZero = true;
			this.isDone = true;
			// System.out.println("******************************************************************************");
			// System.out.println("* Agent killed by the system because it tried to use more energy than it has *");
			// System.out.println("******************************************************************************");
			//
			// this.updatePerformanceMeasure(agent);

		} else if (this.envState.getActionFromName("suck").equals(agentAction)) {
			// if
			// (LocationState.Dirty.equals(this.envState.getLocationState(this.envState.getAgentLocation(agent))))
			// {
			this.envState.suckTile(this.envState.getAgentLocation(agent));
			this.envState.updateCurrentEnergy(agent, this.envState.getEnergyCost(agentAction));
			// }
		} else if (agentAction.isNoOp()) {
			// In the Vacuum Environment we consider things done if
			// the agent generates a NoOp.
			this.isDone = true;
			// this.updatePerformanceMeasure(agent);
		} else {
			this.envState.moveAgent(agent, agentAction);
			this.envState.updateCurrentEnergy(agent, this.envState.getEnergyCost(agentAction));
		}

		return this.envState;

	}

	public Point getAgentLocation(final Agent agent) {
		return this.envState.getAgentLocation(agent);
	}

	@Override
	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	public LocationState getLocationState(final Point location) {
		return this.envState.getLocationState(location);
	}

	@Override
	public Percept getPerceptSeenBy(final Agent anAgent) {

		return new LocalVacuumEnvironmentPerceptTaskEnvironmentB(this.envState.getCellLogicalState(this.envState.getAgentLocation(anAgent)), this.envState.getInitialEnergy(), this.envState.getCurrentEnergy(anAgent),
				this.envState.getActionEnergyCosts(), this.envState.getN(), this.envState.getM(), this.envState.isMovedLastTime(), this.envState.getBaseLocation().equals(this.envState.getAgentLocation(anAgent)));

	}

	@Override
	public boolean isDone() {
		return super.isDone() || this.isDone;
	}

	public void updatePerformanceMeasure(final Agent agent) {

		final double ET = this.envState.getCurrentEnergy(agent);
		final double BdT = this.envState.getDistanceFromBase(agent);
		final double E0 = this.envState.getInitialEnergy();
		final double CT = this.envState.getCleanedTiles(agent);
		final double D0 = this.envState.getDirtyInitialTiles();
		this.envState.computeAverageSquareDistance();
		final double avgSqrDist = this.envState.getAvarage();
		final double maxD = this.envState.getMaxDirtyDistance();
		final double avgSqrDistNorm;
		if (avgSqrDist == 0 || maxD == 0)
			avgSqrDistNorm = 1;
		else
			avgSqrDistNorm = avgSqrDist / maxD;

		// Task Environment C
		final Double performanceMeasure = (Math.pow((CT + 1) / (D0 + 1), 4) + Math.pow(avgSqrDistNorm, 2)) * Math.ceil((ET - BdT + 1) / (E0 + 1));

		this.performanceMeasures.put(agent, performanceMeasure);

	}

	public boolean isEnergyLessThenZero() {
		return energyLessThenZero;
	}

	public void setEnergyLessThenZero(boolean energyLessThenZero) {
		this.energyLessThenZero = energyLessThenZero;
	}
}