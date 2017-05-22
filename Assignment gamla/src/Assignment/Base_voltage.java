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

//Creating an object
public class Base_voltage extends Base_constructor {
		
	//method used to load data from XML-CIM file and store the data into mySQL database
	public static void base_voltage(ArrayList<Base_voltage> Base_voltageList){
		
			
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList basvoltList = doc.getElementsByTagName("cim:BaseVoltage"); 
						
			//for-loop to to store the loaded XML-data list in a SQL database
			System.out.println("-------------Base_voltageList----------------");	
			for (int i = 0; i < basvoltList.getLength(); i++) {				
				Node theNode = basvoltList.item(i);	
				Base_voltageList.add(new Base_voltage());
				extractMethod(theNode, Base_voltageList.get(i));				
				}							
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	//method to extract data and store it into an new base_voltage object
	public static void extractMethod (Node node, Base_voltage obj){
		
		//Searching for values with the method parameter in the class ReadNode		
		String rdfID = ReadNode.parameter(node,"rdf:ID");
		double nom_val = Double.parseDouble(ReadNode.parameter(node,"cim:BaseVoltage.nominalVoltage"));		
		
		//set values to object		
		obj.setRdfID(rdfID);
		obj.setNom_val(nom_val);		
				
		//for-loop using method to print values to see if it is correct								
		System.out.println("rdfID: " + rdfID + "; nomVal: " + nom_val);	
		
		//save data in SQL database
		try{
			Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
			String query = "insert into BaseVoltage values (?,?)";
			PreparedStatement preparedStmt = conn1.prepareStatement(query);
			preparedStmt.setString(1, rdfID);
			preparedStmt.setDouble(2, nom_val);		
			preparedStmt.execute();
		}
		catch(Exception e){System.out.println(e);
		}		
		
	}
		
}

