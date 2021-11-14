import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.validation.*;
import java.io.*;

public class domParserSchema {

	public static void main(String[] args) throws Exception {
		// schema
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaLocation = new File("message.xsd");
		Schema schema = schemaFactory.newSchema(schemaLocation);
		Validator validator = schema.newValidator();
		// xml
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = domFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse("message.xml");
		// result
		DOMSource source = new DOMSource(doc);
		DOMResult result = new DOMResult();
		
		try {
			validator.validate(source, result);
			Document augmented = (Document) result.getNode();
			
			NodeList toNode = augmented.getElementsByTagName("to");
			NodeList fromNode = augmented.getElementsByTagName("from");
			NodeList messageNode = augmented.getElementsByTagName("body");
			NodeList headingNode = augmented.getElementsByTagName("heading");
			
			String to = toNode.item(0).getFirstChild().getNodeValue();
			String from = fromNode.item(0).getFirstChild().getNodeValue();
			String message = messageNode.item(0).getFirstChild().getNodeValue();
			String heading = headingNode.item(0).getFirstChild().getNodeValue();
			
			System.out.println("HEADING: " + heading);
			System.out.println("MESSAGE: " + message);
			System.out.println("from " + from + " to " + to);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
