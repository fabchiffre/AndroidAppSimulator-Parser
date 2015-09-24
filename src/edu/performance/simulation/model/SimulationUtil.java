package edu.performance.simulation.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/*
 * This class contains methods to:
 * - parse the list of possible tests, generating a list of simulations;
 * - create the file which tells the application what simulations to run.
 * 
 * Also, this class uses the StAX API to read and write a XML file.
 */
public class SimulationUtil {
	private static final String NAMESPACE = "";
	
	/*
	 * Parse the simulation file.
	 */
	public static List<SimulationInterface> readSimulationFile(SimulationFactory simulationFactory, InputStream xmlFile)
			throws XmlPullParserException, IOException {
		
		List<SimulationInterface> simulationList = new ArrayList<>();
		SimulationInterface currentSimulation = null; // TODO Watch for null
														// pointers
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(xmlFile, null);

		// Reads the XML and creates a list of simulations.
		// Reads the content of the inside the elements then relate it to
		// the
		// closing tag.
		// TODO Change the logic to the starting element.
		String buffer = "";
		int eventType;

		eventType = parser.getEventType();
		int index = 0;
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				switch (parser.getName()) {
				case "simulation":
					currentSimulation = simulationFactory.createSimulation();
					break;
				case SimulationInterface.STRING_LEVEL:
					index = Integer.parseInt(parser.getAttributeValue(0));
					break;
				default:
					break;
				}

			} else if (eventType == XmlPullParser.END_TAG) {
				switch (parser.getName()) {
				case "simulation":
					simulationList.add(currentSimulation);
					break;
				case SimulationInterface.NAME:
					currentSimulation.setName(buffer);
					break;
				case SimulationInterface.CLASS_NAME:
					currentSimulation.setClassName(buffer);
					break;
				case SimulationInterface.MAX_TIME:
					parser.next();
					currentSimulation.setMaxTime(Long.parseLong(buffer));
					break;
				case SimulationInterface.INT_LEVEL:
					currentSimulation.setIntLevel(Integer.parseInt(buffer));
					break;
				case SimulationInterface.BATTERY_TEST:
					currentSimulation.setBatteryTest(Boolean.parseBoolean(buffer));
					break;
				case SimulationInterface.BATTERY_TEST_LEVEL:
					currentSimulation.setBatteryTestLevel(Integer.parseInt(buffer));
					break;
				case SimulationInterface.DOUBLE_LEVEL:
					currentSimulation.setDoubleLevel(Double.parseDouble(buffer));
					break;
				case SimulationInterface.STRING_LEVEL:
					currentSimulation.setStringLevel(buffer, index);
					break;
				case SimulationInterface.STRING_ARRAY_LEVEL:
					currentSimulation.setStringArrayLevel(buffer.split(";;"));
					// Add the simulation
					break;
				default:
					break;
				}
			} else if (eventType == XmlPullParser.TEXT) {
				buffer = parser.getText();
			}

			eventType = parser.next();
		}
		return simulationList;

	}

	/*
	 * Creates a simulation file with the list of simulations received.
	 */
	// TODO Make a temporary folder to write this
	public static void createAndroidSimulationFile(List<SimulationInterface> simulationList, File resultantFile)
			throws IOException, XmlPullParserException {

		XmlPullParserFactory factory = XmlPullParserFactory
				.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
		XmlSerializer serializer = factory.newSerializer();
		serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
		serializer.setOutput(new FileWriter(resultantFile));
		serializer.startDocument(null, null);

		serializer.startTag(NAMESPACE, "simulationList");
		for (SimulationInterface simu : simulationList) {
			serializer.startTag(NAMESPACE, "simulation");
			
			serializer.startTag(NAMESPACE, SimulationInterface.NAME);
			serializer.text(simu.getName());
			serializer.endTag(NAMESPACE, SimulationInterface.NAME);
			
			serializer.startTag(NAMESPACE, SimulationInterface.CLASS_NAME);
			serializer.text(simu.getClassName());
			serializer.endTag(NAMESPACE, SimulationInterface.CLASS_NAME);
			
			if(simu.hasMaxTimeParameter()) {
				serializer.startTag(NAMESPACE, SimulationInterface.MAX_TIME);
				serializer.text(Long.toString(simu.getMaxTime()));
				serializer.endTag(NAMESPACE, SimulationInterface.MAX_TIME);
			}
			
			
			if(simu.hasBatteryTestParameter()) {
				serializer.startTag(NAMESPACE, SimulationInterface.BATTERY_TEST);
				serializer.text(Boolean.toString(simu.isBatteryTest()));
				serializer.endTag(NAMESPACE, SimulationInterface.BATTERY_TEST);
			}
			
			if(simu.hasBatteryTestLevelParameter()) {
				serializer.startTag(NAMESPACE, SimulationInterface.BATTERY_TEST_LEVEL);
				serializer.text(Integer.toString(simu.getBatteryTestLevel()));
				serializer.endTag(NAMESPACE, SimulationInterface.BATTERY_TEST_LEVEL);
			}
			
			if(simu.hasIntLevelParameter()) {
				serializer.startTag(NAMESPACE, SimulationInterface.INT_LEVEL);
				serializer.text(Integer.toString(simu.getIntLevel()));
				serializer.endTag(NAMESPACE, SimulationInterface.INT_LEVEL);
			}
			
			if(simu.hasDoubleLevelParameter()) {
				serializer.startTag(NAMESPACE, SimulationInterface.DOUBLE_LEVEL);
				serializer.text(Double.toString(simu.getDoubleLevel()));
				serializer.endTag(NAMESPACE, SimulationInterface.DOUBLE_LEVEL);
			}
			
			for (int i = 0; i < 10; ++i) {
				if(simu.hasStringLevelParameter(i)) {
					serializer.startTag(NAMESPACE, SimulationInterface.STRING_LEVEL);
					serializer.attribute(NAMESPACE, "index", Integer.toString(i));
					serializer.text(simu.getStringLevel(i));
					serializer.endTag(NAMESPACE, SimulationInterface.STRING_LEVEL);
				}
			}
			
			if (simu.hasStringArrayLevel()) {
				serializer.startTag(NAMESPACE, SimulationInterface.STRING_ARRAY_LEVEL);
				for (int i = 0; i < simu.getStringArrayLevel().length; i++) {
					serializer.text(simu.getStringArrayLevel()[i]);
					if (i != simu.getStringArrayLevel().length - 1)
						serializer.text(";;");
				}
				serializer.endTag(NAMESPACE, SimulationInterface.STRING_ARRAY_LEVEL);
			}

			serializer.endTag(NAMESPACE, "simulation");
		}

		serializer.endTag(NAMESPACE, "simulationList");
		serializer.endDocument();
	}
}