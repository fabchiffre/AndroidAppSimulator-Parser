package edu.performance.simulation.model;

public class SimulationNativeFactory implements SimulationFactory {

	@Override
	public SimulationInterface createSimulation() {
		return new SimulationNative();
	}
}
