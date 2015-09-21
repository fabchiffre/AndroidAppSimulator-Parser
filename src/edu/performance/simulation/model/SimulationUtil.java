package edu.performance.simulation.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/*
 * This class contains methods to:
 * - parse the list of possible tests, generating a list of simulations;
 * - create the file which tells the application what simulations to run.
 * 
 * Also, this class uses the StAX API to read and write a XML file.
 */
public class SimulationUtil {

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
			throws IOException {
		/*
		 * XMLOutputFactory factory = XMLOutputFactory.newInstance();
		 * 
		 * FileWriter fileWriter = new FileWriter(resultantFile);
		 * XMLStreamWriter writer = factory.createXMLStreamWriter(fileWriter);
		 * 
		 * writer.writeStartDocument();
		 * writer.writeStartElement("simulationList"); for (SimulationInterface
		 * aSimulation : simulationList) {
		 * writer.writeStartElement("simulation");
		 * 
		 * writer.writeStartElement(SimulationInterface.NAME);
		 * writer.writeCharacters(aSimulation.getName());
		 * writer.writeEndElement();
		 * 
		 * writer.writeStartElement(SimulationInterface.CLASS_NAME);
		 * writer.writeCharacters(aSimulation.getClassName());
		 * writer.writeEndElement();
		 * 
		 * if (aSimulation.hasMaxTimeParameter()) {
		 * writer.writeStartElement(SimulationInterface.MAX_TIME);
		 * writer.writeCharacters(Long.toString(aSimulation.getMaxTime()));
		 * writer.writeEndElement(); }
		 * 
		 * if (aSimulation.hasIntLevelParameter()) {
		 * writer.writeStartElement(SimulationInterface.INT_LEVEL);
		 * writer.writeCharacters(Integer.toString(aSimulation.getIntLevel()));
		 * writer.writeEndElement(); }
		 * 
		 * if (aSimulation.hasDoubleLevelParameter()) {
		 * writer.writeStartElement(SimulationInterface.DOUBLE_LEVEL);
		 * writer.writeCharacters(Double.toString(aSimulation.getDoubleLevel()))
		 * ; writer.writeEndElement(); }
		 * 
		 * if (aSimulation.hasBatteryTestParameter()) {
		 * writer.writeStartElement(SimulationInterface.BATTERY_TEST);
		 * writer.writeCharacters(Boolean.toString(aSimulation.isBatteryTest()))
		 * ; writer.writeEndElement(); }
		 * 
		 * if (aSimulation.hasBatteryTestLevelParameter()) {
		 * writer.writeStartElement(SimulationInterface.BATTERY_TEST_LEVEL);
		 * writer.writeCharacters(Integer.toString(aSimulation.
		 * getBatteryTestLevel())); writer.writeEndElement(); }
		 * 
		 * // TODO adapt to the max number of string param for (int i = 0; i <
		 * 100; ++i) {
		 * writer.writeStartElement(SimulationInterface.STRING_LEVEL);
		 * writer.writeAttribute("index", Integer.toString(i));
		 * writer.writeCharacters(aSimulation.getStringLevel(i));
		 * writer.writeEndElement(); }
		 * 
		 * if (aSimulation.hasStringArrayLevel()) {
		 * writer.writeStartElement(SimulationInterface.STRING_ARRAY_LEVEL); for
		 * (int i = 0; i < aSimulation.getStringArrayLevel().length; i++) {
		 * writer.writeCharacters(aSimulation.getStringArrayLevel()[i]); if (i
		 * != aSimulation.getStringArrayLevel().length - 1)
		 * writer.writeCharacters(";;"); } writer.writeEndElement(); }
		 * 
		 * writer.writeEndElement();
		 * 
		 * writer.flush(); }
		 * 
		 * writer.writeEndDocument();
		 * 
		 * writer.close();
		 * 
		 * System.out.println("file written");
		 */
	}
}