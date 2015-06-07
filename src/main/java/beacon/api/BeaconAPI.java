package beacon.api;


import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class BeaconAPI {

	static String URL_LAST = "https://beacon.nist.gov/rest/record/last";
	static String TAG_OUTPUTVALUE_NAME = "outputValue";
	static String TAG_OUTPUTVALUE_VALUE = "";
	static TreeMap<Character,Integer> mapOfOutputValue ;
	static Document document;
	
	public static void main(String[] args) throws ParserConfigurationException, Exception, IOException {
		
		document = getXMLDocument (URL_LAST);
		
		TAG_OUTPUTVALUE_VALUE = getElemenByTag (TAG_OUTPUTVALUE_NAME);
		
		mapOfOutputValue  = getMapOfKeyAndAmount (TAG_OUTPUTVALUE_VALUE);
		
		printMap(mapOfOutputValue);
		
	}
	
	/*
	 * print a list in format "<key>, <value>"
	 */
	private static void printMap(TreeMap<Character, Integer> mapOfOutputValue) {
		for (Entry<Character, Integer> entry : mapOfOutputValue.entrySet()) {
			Integer value = entry.getValue();
	        Character key = entry.getKey();
	        System.out.println(key+","+value);
	   }
	}

	/*
	 * Retrieve a list of all elements in a row and the amount of repetitions
	 */
	 static TreeMap<Character, Integer> getMapOfKeyAndAmount(
			String outputValue) {
		TreeMap<Character,Integer> resultMapOfOutputValue = new TreeMap<Character,Integer>();
		for (char element : outputValue.toCharArray()){
			Integer n = resultMapOfOutputValue.get( element );
	        if ( n == null ) resultMapOfOutputValue.put( element, 1 );
	        else resultMapOfOutputValue.put(element, ++n );
		}
		return resultMapOfOutputValue;
	}
	 
	/*
	 * Getting the value of the tag by its name from the document xml
	 */
	static String getElemenByTag(String nameOfTag) {
		String outputValue =  document.getElementsByTagName(nameOfTag).item(0).getTextContent();
		return outputValue;
	}

	/*
	 * Receive the document format XML
	 */
	static Document getXMLDocument (String url) throws SAXException, IOException, ParserConfigurationException  {
		Document resultDocument = null;
		DocumentBuilderFactory factory1= DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory1.newDocumentBuilder();
		resultDocument = builder.parse(url);
		return resultDocument;
		
	}

}
