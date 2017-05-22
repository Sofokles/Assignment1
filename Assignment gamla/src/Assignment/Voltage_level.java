package Assignment;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Voltage_level extends Base_constructor {
			

		
		//method used to load data and store the data into an arraylist of objects
		public static void voltage_level(ArrayList<Voltage_level> Voltage_levelList){		
				
			try {
				//read EQ file
				File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XmlFile);
				doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
				
				//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
				NodeList voltage_levelList = doc.getElementsByTagName("cim:VoltageLevel"); 
							
				//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
				System.out.println("-------------Voltage_levelList----------------");
				for (int i = 0; i < voltage_levelList.getLength(); i++) {				
					Node theNode = voltage_levelList.item(i);				
					Voltage_levelList.add(extractMethod(theNode));					
					}							
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}	
		
		//method to extract data and store it into an new base_voltage object
		public static Voltage_level extractMethod (Node node){				
			
			//Searching for values with the method parameter in the class ReadNode		
			String rdfID = ReadNode.parameter(node,"rdf:ID");
			String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
			String sub_rdfID = ReadNode.parameter(node,"cim:VoltageLevel.Substation").substring(1);
			String base_volt_rdfID = ReadNode.parameter(node,"cim:VoltageLevel.BaseVoltage").substring(1);
			
			//create an object and set values
			Voltage_level obj = new Voltage_level();		
			obj.setRdfID(rdfID);
			obj.setName(name);		
			obj.setSub_rdfID(sub_rdfID);
			obj.setBase_volt_rdfID(base_volt_rdfID);
			
			//print
			System.out.println("rdfID: " + rdfID + "; Name: " + name + "; Sub_rdfID: " + sub_rdfID + "; Base_volt_rdfID: " + base_volt_rdfID);		
			
			//save data in SQL database
			try{
				Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
				String query = "insert into VoltageLevel values (?,?,?,?)";
				PreparedStatement preparedStmt = conn1.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, sub_rdfID);
				preparedStmt.setString(4, base_volt_rdfID);
				preparedStmt.execute();
				}
			catch(Exception e){
				System.out.println(e);
				}	
			
			//return the object
			return obj;		
		}		

			
	}



