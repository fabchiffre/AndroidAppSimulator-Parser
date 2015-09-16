package edu.performance.simulation.model;

import java.util.HashMap;
import java.util.Map;

/*
 * This class models a simulation. It has the same fields as the simulation XML file.
 */
public class SimulationNative implements SimulationInterface {

	private String name;

	private String classname;

	private Map<String, Object> parameters;

	public SimulationNative() {
		parameters = new HashMap<String, Object>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return classname;
	}

	public void setClassName(String classname) {
		this.classname = classname;
	}

	public int getIntLevel() {
		if (hasIntLevelParameter())
			return (int) parameters.get(INT_LEVEL);
		else
			return 0;
	}

	public boolean hasIntLevelParameter() {
		return parameters.containsKey(INT_LEVEL);
	}

	public void setIntLevel(int level) {
		parameters.put(INT_LEVEL, level);
	}

	public int getMaxTime() {
		if (hasMaxTimeParameter())
			return (int) parameters.get(MAX_TIME);
		else {
			return 0;
		}
	}

	public boolean hasMaxTimeParameter() {
		return parameters.containsKey(MAX_TIME);
	}

	public void setMaxTime(long maxTime) {
		parameters.put(MAX_TIME, maxTime);
	}

	@Override
	public double getDoubleLevel() {
		if (hasDoubleLevelParameter())
			return (double) parameters.get(DOUBLE_LEVEL);
		else {
			return 0;
		}
	}

	@Override
	public boolean hasDoubleLevelParameter() {
		return parameters.containsKey(DOUBLE_LEVEL);
	}

	@Override
	public void setDoubleLevel(double level) {
		parameters.put(DOUBLE_LEVEL, level);
	}

	@Override
	public boolean isBatteryTest() {
		if (hasBatteryTestParameter())
			return (boolean) parameters.get(BATTERY_TEST);
		else
			return false;
	}

	public boolean hasBatteryTestParameter() {
		return parameters.containsKey(BATTERY_TEST);
	}

	public void setBatteryTest(boolean isBatteryTest) {
		parameters.put(BATTERY_TEST, isBatteryTest);
	}

	@Override
	public int getBatteryTestLevel() {
		if (hasBatteryTestLevelParameter())
			return (int) parameters.get(BATTERY_TEST_LEVEL);
		else {
			return 0;
		}
	}

	@Override
	public boolean hasBatteryTestLevelParameter() {
		return parameters.containsKey(BATTERY_TEST_LEVEL);
	}

	@Override
	public void setBatteryTestLevel(int level) {
		parameters.put(BATTERY_TEST_LEVEL, level);
	}

	@Override
	public String[] getStringArrayLevel() {
		if (hasStringArrayLevel())
			return (String[]) parameters.get(STRING_ARRAY_LEVEL);
		else
			return null;
	}

	@Override
	public boolean hasStringArrayLevel() {
		return parameters.containsKey(STRING_ARRAY_LEVEL);
	}

	@Override
	public void setStringArrayLevel(String[] stringArray) {
		parameters.put(STRING_ARRAY_LEVEL, stringArray);
	}

	@Override
	public String getStringLevel(int index) {
		if (hasStringLevelParameter(index))
			return (String) parameters.get(STRING_LEVEL + index);
		else
			return null;
	}

	@Override
	public boolean hasStringLevelParameter(int index) {
		return parameters.containsKey(STRING_LEVEL + index);
	}

	@Override
	public void setStringLevel(String level, int index) {
		parameters.put(STRING_LEVEL + index, level);
	}
}
