package edu.performance.simulation.model.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import edu.performance.simulation.model.SimulationInterface;
import edu.performance.simulation.model.SimulationNativeFactory;
import edu.performance.simulation.model.SimulationUtil;

public class SimulationUtilTestParsing {

	private static final String TEST_FILE_DIRECTORY = "resources/test/";

	private SimulationNativeFactory factory;
	private List<SimulationInterface> simulationList;

	@Before
	public void init() {
		factory = new SimulationNativeFactory();
	}

	@Test
	public void testParsingIntegerOperation() throws XmlPullParserException, IOException {
		File file = new File(TEST_FILE_DIRECTORY + "test_int_op.xml");

		FileInputStream fis = new FileInputStream(file);
		simulationList = SimulationUtil.readSimulationFile(factory, fis);

		Assert.assertEquals(1, simulationList.size());
		Assert.assertEquals("Integer Operation", simulationList.get(0).getName());
		Assert.assertEquals("edu.performance.test.integeroperation.IntegerOperationActivity",
				simulationList.get(0).getClassName());
		Assert.assertEquals(170000, simulationList.get(0).getMaxTime());
		Assert.assertEquals(100, simulationList.get(0).getIntLevel());
	}

	@Test
	public void testParsingFloatOperation() throws XmlPullParserException, IOException {
		File file = new File(TEST_FILE_DIRECTORY + "test_float_op.xml");

		FileInputStream fis = new FileInputStream(file);
		simulationList = SimulationUtil.readSimulationFile(factory, fis);

		Assert.assertEquals(1, simulationList.size());
		Assert.assertEquals("Float Operation", simulationList.get(0).getName());
		Assert.assertEquals("edu.performance.test.integeroperation.FloatOperationActivity",
				simulationList.get(0).getClassName());
		Assert.assertEquals(700000, simulationList.get(0).getMaxTime());
		Assert.assertEquals(5009, simulationList.get(0).getDoubleLevel(), 0);
	}

	@Test
	public void testParsingStringOperation() throws XmlPullParserException, IOException {

		String[] snippets = "Elememt 1;;Element 2;;Element 3".split(";;");

		File file = new File(TEST_FILE_DIRECTORY + "test_string_op.xml");

		FileInputStream fis = new FileInputStream(file);
		simulationList = SimulationUtil.readSimulationFile(factory, fis);

		Assert.assertEquals(1, simulationList.size());
		Assert.assertEquals("String Operation", simulationList.get(0).getName());
		Assert.assertEquals("edu.performance.test.stringoperation.StringOperationActivity",
				simulationList.get(0).getClassName());
		Assert.assertEquals(170000, simulationList.get(0).getMaxTime());
		Assert.assertEquals(1, simulationList.get(0).getIntLevel());
		Assert.assertEquals("Searchable", simulationList.get(0).getStringLevel(SimulationInterface.SEARCHABLE));
		Assert.assertArrayEquals(snippets, simulationList.get(0).getStringArrayLevel());
	}

	@Test
	public void testParsingMailOperation() throws XmlPullParserException, IOException {
		File file = new File(TEST_FILE_DIRECTORY + "test_mail_op.xml");

		FileInputStream fis = new FileInputStream(file);
		simulationList = SimulationUtil.readSimulationFile(factory, fis);

		Assert.assertEquals(1, simulationList.size());
		Assert.assertEquals("Mail Operation", simulationList.get(0).getName());
		Assert.assertEquals("edu.performance.test.mailoperation.MailOperationActivity",
				simulationList.get(0).getClassName());
		Assert.assertEquals(170000, simulationList.get(0).getMaxTime());
		Assert.assertEquals(100, simulationList.get(0).getIntLevel());
		Assert.assertEquals("/data/data/edu.performance.test/app_performanceDir/tiny_g.txt",
				simulationList.get(0).getStringLevel(SimulationInterface.FILENAME));
		Assert.assertEquals("Text mail",
				simulationList.get(0).getStringLevel(SimulationInterface.MAIL_TEXT));
		Assert.assertEquals("***@ymail.com",
				simulationList.get(0).getStringLevel(SimulationInterface.DESTINATION));
		
	}

	@Test
	public void testParsingFileOperation() throws XmlPullParserException, IOException {
		File file = new File(TEST_FILE_DIRECTORY + "test_file_op.xml");

		FileInputStream fis = new FileInputStream(file);
		simulationList = SimulationUtil.readSimulationFile(factory, fis);

		Assert.assertEquals(1, simulationList.size());
		Assert.assertEquals("File Operation", simulationList.get(0).getName());
		Assert.assertEquals("edu.performance.test.filesequentialoperation.FileSequentialOperationActivity",
				simulationList.get(0).getClassName());
		Assert.assertEquals(400000, simulationList.get(0).getMaxTime());
		Assert.assertEquals(1, simulationList.get(0).getIntLevel());
		Assert.assertEquals("/data/data/edu.performance.test/app_performanceDir/small.txt",
				simulationList.get(0).getStringLevel(SimulationInterface.FILENAME));
		Assert.assertEquals("Stretch...", simulationList.get(0).getStringLevel(SimulationInterface.STRETCH));
	}

}
