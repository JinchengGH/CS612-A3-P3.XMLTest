import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class domParserDTD {
	public static void main(String[] args) {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		try {
			DocumentBuilder documentBuilder = domFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("message.xml");
			
			NodeList toNode = document.getElementsByTagName("to");
			NodeList fromNode = document.getElementsByTagName("from");
			NodeList messageNode = document.getElementsByTagName("body");
			NodeList headingNode = document.getElementsByTagName("heading");
			
			String to = toNode.item(0).getFirstChild().getNodeValue();
			String from = fromNode.item(0).getFirstChild().getNodeValue();
			String message = messageNode.item(0).getFirstChild().getNodeValue();
			String heading = headingNode.item(0).getFirstChild().getNodeValue();
			
			System.out.println("HEADING: " + heading);
			System.out.println("MESSAGE: " + message);
			System.out.println("from " + from + " to " + to);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
