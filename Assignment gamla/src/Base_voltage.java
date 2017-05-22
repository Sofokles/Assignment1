

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Creating an object
public class Base_voltage extends Base_constructor {

	private static String rdfID;
	private String nom_val;	
	private static int quantity = 0;
	//Create a constructor
	//public Base_voltage(){	
	//	this("", "");
	//}
	//public Base_voltage(String rdfID){	
	//	this(rdfID, "");
	//}
	//public Base_voltage(String rdfID_1, String nom_val_1){
	//	rdfID=rdfID_1;
	//	nom_val=nom_val_1;
	//	quantity++;
	//	System.out.println(rdfID + " " + nom_val + " " + quantity);
		
	//}
	
	//method base_voltage used to load data (with a nodelist of xml data) and store the data into an arraylist of objects
	public static void base_voltage(ArrayList<Base_voltage> Base_voltageList){
		
			
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
			//ArrayList<Base_voltage> Base_voltageList = new ArrayList<Base_voltage>();
			//ArrayList<Base_voltage> Base_voltageList = bavo_const;
			
			//for-loop to to stire the loaded XML-data "basvoltList" and use the method extractBasvolt to create and store objects in the arraylist of objects
			for (int i = 0; i < basvoltList.getLength(); i++) {
				//takes all 
				Node theNode = basvoltList.item(i);					
				//rund the "extractBasvolt" for each "theNode" and stores it to a array list
				Base_voltageList.add(extractBasvolt(theNode));					
				}	
					
			
			int t0 = basvoltList.getLength();	
			int t1 = Base_voltageList.size();
			//int t2 = basvoltList.get
			System.out.println("Length:" + t0 + ", Size:" + t1 );
						
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//method to extract data and store it into an new base_voltage object
	public static Base_voltage extractBasvolt (Node node){
				
		//Converts the node to element in order to search for the data inside it.
		Element element = (Element) node;	
		//Searching for rdfID and nom value 		
		String rdfID = element.getAttribute("rdf:ID");
		String nom_val = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();			
		//create an Base_voltage  object 
		Base_voltage base_V = new Base_voltage();		//rdfID, nom_val
		//add the values to the object	
		base_V.rdfID = rdfID;
		base_V.nom_val = nom_val;		
		//return the object to main 
		return base_V;		
	}
	
	//get.RdfID Method
	public static String getRdfID(){		 
		return rdfID;		
	}
	
	//get.quantity Method
	public static int getQuantity(){		 
		return quantity;		
	}
}

