package Assignment;

//import IO
import java.io.File;

//ArrayList
import java.util.ArrayList;

//import parser
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

//import DOM
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Read_CIM_XML_test {
	public static void main(String[] args) {
		
		//creates a arraylist of the constructor of objects Base_voltage, ... etc
		//Is not needed as I will use a SQL server to store the data later
		ArrayList<Base_voltage> Base_voltageList = new ArrayList<Base_voltage>();
		
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			// normalize EQ file
			doc.getDocumentElement().normalize();//( which returns root element of the file and then normalize your XML
			//object.)
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList basvoltList = doc.getElementsByTagName("cim:BaseVoltage"); 
			
			//
			for (int i = 0; i < basvoltList.getLength(); i++) {
				//takes all 
				Node theNode = basvoltList.item(i);					
				//rund the "extractBasvolt" for each "theNode" and stores it to a array list
				Base_voltageList.add(extractBasvolt(theNode));					
				}	
			
			//printar ut värden från arraylistorna...
			for (int i=0;i<Base_voltageList.size();i++) { // Use enhance looping
				//System.out.println("The employee name is " + employee.name + "\n");
				// Compute salary and define a working place for each employee				
				print(Base_voltageList.get(i));				
				}		
			
			int t0 = basvoltList.getLength();			
			System.out.println("Print:" + t0 );		
			
						
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//test to print out values
	public static void print(Base_voltage baseV_X){
		
		String rdfID=baseV_X.rdfID;
		String name=baseV_X.nom_val;
		
		System.out.println(name + " " + rdfID);
		
	}
	public static Base_voltage extractBasvolt (Node node){
		
		//Converts the node to element in order to search for the data inside it.
		Element element = (Element) node;	
		//Searching for rdfID and nom value 		
		String rdfID = element.getAttribute("rdf:ID");
		String nom_val = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();			
		//create an Base_voltage  object 
		Base_voltage base_V = new Base_voltage();		
		//add the values to the object	
		base_V.rdfID = rdfID;
		base_V.nom_val = nom_val;		
		//return the object to main 
		return base_V;		
	}
}