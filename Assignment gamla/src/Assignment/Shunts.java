package Assignment;


	import java.io.File;
import java.util.ArrayList;

	import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

	import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

	public class Shunts extends Base_constructor {	
		
		//method used to load data and store the data into an arraylist of objects
		public static void shunts(ArrayList<Shunts> ShuntsList){		
			
			try {
				//read EQ file
				File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XmlFile);
				doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
				
				//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
				NodeList shuntsList = doc.getElementsByTagName("cim:LinearShuntCompensator"); 
							
				//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
				System.out.println("-------------ShuntList----------------");	
				for (int i = 0; i < shuntsList.getLength(); i++) {				
					Node theNode = shuntsList.item(i);		
					ShuntsList.add(extractMethod(theNode));									
					}							
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}	
		
		//method to extract data and store it into an new base_voltage object
		public static Shunts extractMethod (Node node){				
			
			//Searching for values with the method parameter in the class ReadNode		
			String rdfID = ReadNode.parameter(node,"rdf:ID");
			String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
			double b = Double.parseDouble(ReadNode.parameter(node,"cim:LinearShuntCompensator.bPerSection"));
			double g = Double.parseDouble(ReadNode.parameter(node,"cim:LinearShuntCompensator.gPerSection"));
			//String baseVolt_rdfID = ReadNode.parameter(node,"cim:ConductingEquipment.BaseVoltage").substring(1);
						
			//print
			System.out.println("rdfID: " + rdfID + "; Name: " + name + "; b: " + b + "; g: " + g);
				
			//save data in SQL database
			
			
			//create an object and set values
			Shunts obj = new Shunts();		
			obj.setRdfID(rdfID);
			obj.setName(name);				
			obj.setB(b);
			obj.setG(g);		
			
			return obj;
		}

			
	}




