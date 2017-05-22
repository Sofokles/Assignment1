package Assignment;


import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ReadNode {
			
		// class called parameter returnes parametervalue
		public static String parameter(Node node, String parameter) {
			
			//Converts the node to element in order to search for the data inside it.
			Element node_element = (Element) node;
			String parametervalue = "";
			
			if (node_element.hasAttribute(parameter)) {
				
				parametervalue = node_element.getAttribute(parameter);
				
			} else {
				
				Element node_attribute = (Element) node_element.getElementsByTagName(parameter).item(0);
				
				parametervalue = node_attribute.getTextContent();
				
				if (parametervalue == "") {
					parametervalue = parameter(node_attribute, "rdf:resource");
				}			
				
					
			}
			return parametervalue;
		}	
	}

