package beacon.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class BeaconAPITest {

	@Test
	public void testGetXMLDocument() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		assertNotNull(BeaconAPI.getXMLDocument(url));
	}
	
	@Test (expected= FileNotFoundException.class)
	public void testGetXMLDocumentWrongURL() throws SAXException, IOException, ParserConfigurationException {
		String urlWrrong ="htsasdfa";
		BeaconAPI.getXMLDocument(urlWrrong);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void testGetXMLDocumentEmptyURL() throws SAXException, IOException, ParserConfigurationException {
		BeaconAPI.getXMLDocument(null);
	}
	
	@Test
	public void testGetElemenByTag() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		String tag ="outputValue";
		BeaconAPI beaconAPI =new BeaconAPI();
		beaconAPI.document = beaconAPI.getXMLDocument(url);
		assertNotNull(beaconAPI.getElemenByTag(tag));
	}
	
	@Test (expected= NullPointerException.class)
	public void testGetElemenByTagWrongTag() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		String tag ="outputValue!@#!#!@#";
		BeaconAPI beaconAPI =new BeaconAPI();
		beaconAPI.document = beaconAPI.getXMLDocument(url);
		assertNotNull(beaconAPI.getElemenByTag(tag));
	}
	
	@Test (expected= NullPointerException.class)
	public void testGetElemenByTagEmpty() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		String tag =null;
		BeaconAPI beaconAPI =new BeaconAPI();
		beaconAPI.document = beaconAPI.getXMLDocument(url);
		assertNotNull(beaconAPI.getElemenByTag(tag));
	}
	
	@Test (expected= NullPointerException.class)
	public void testGetElemenByTagXMLIsEmpty() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		String tag ="outputValue";
		BeaconAPI beaconAPI =new BeaconAPI();
		beaconAPI.document = null;
		assertNotNull(beaconAPI.getElemenByTag(tag));
	}
	
	@Test
	public void testgetMapOfKeyAndAmount() throws SAXException, IOException, ParserConfigurationException {
		String url ="https://beacon.nist.gov/rest/record/last";
		String tag ="outputValue";
		String element ;
		BeaconAPI beaconAPI =new BeaconAPI();
		beaconAPI.document = beaconAPI.getXMLDocument(url);
		element = beaconAPI.getElemenByTag(tag);
		assertNotNull(beaconAPI.getMapOfKeyAndAmount(element));
	}
	
	@Test(expected= NullPointerException.class)
	public void testgetMapOfKeyAndAmountElementIsEmpty() throws SAXException, IOException, ParserConfigurationException {
		BeaconAPI beaconAPI =new BeaconAPI();
		assertNotNull(beaconAPI.getMapOfKeyAndAmount(null));
	}
	
}