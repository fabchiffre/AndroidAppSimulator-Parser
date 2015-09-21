package edu.performance.simulation.model;

public interface SimulationInterface {

	public final static String NAME = "name";
	public final static String CLASS_NAME = "className";
	public final static String MAX_TIME = "maxTime";
	public final static String INT_LEVEL = "intLevel";
	public final static String BATTERY_TEST = "batteryTest";
	public final static String BATTERY_TEST_LEVEL = "batteryTestLevel";
	public final static String DOUBLE_LEVEL = "doubleLevel";
	public final static String STRING_LEVEL = "stringLevel";

	public final static String STRING_ARRAY_LEVEL = "stringArrayLevel";

	public final static int URL = 0;
	public final static int FILENAME = 1;
	public final static int SEARCHABLE = 2;
	public final static int STRETCH = 3;
	public final static int DESTINATION = 4;
	public final static int MAIL_TEXT = 5;

	public String getName();

	public void setName(String name);

	public String getClassName();

	public void setClassName(String classname);

	public int getIntLevel();

	public boolean hasIntLevelParameter();

	public void setIntLevel(int level);

	public long getMaxTime();

	public boolean hasMaxTimeParameter();

	public void setMaxTime(long maxItems);

	public double getDoubleLevel();

	public boolean hasDoubleLevelParameter();

	public void setDoubleLevel(double level);

	public boolean isBatteryTest();

	public boolean hasBatteryTestParameter();

	public void setBatteryTest(boolean batteryTest);

	public int getBatteryTestLevel();

	public boolean hasBatteryTestLevelParameter();

	public void setBatteryTestLevel(int batteryTestLevel);

	public String getStringLevel(int index);

	public boolean hasStringLevelParameter(int index);

	public void setStringLevel(String level, int index);

	public String toString();

	public String[] getStringArrayLevel();

	public boolean hasStringArrayLevel();

	public void setStringArrayLevel(String[] stringArray);

}
