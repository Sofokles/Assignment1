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

public class Substation extends Base_constructor {	
	
	//method used to load data and store the data into an arraylist of objects
	public static void substation(ArrayList<Substation> SubstationList){		
			
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList substationList = doc.getElementsByTagName("cim:Substation"); 
						
			//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
			System.out.println("-------------SubstationList----------------");	
			for (int i = 0; i < substationList.getLength(); i++) {				
				Node theNode = substationList.item(i);				
				SubstationList.add(new Substation());
				extractMethod(theNode, SubstationList.get(i));					
				}							
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	//method to extract data and store it into an new base_voltage object
	public static void extractMethod (Node node, Substation obj){				
		
		//Searching for values with the method parameter in the class ReadNode		
		String rdfID = ReadNode.parameter(node,"rdf:ID");
		String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
		String reg_rdfID = ReadNode.parameter(node,"cim:Substation.Region").substring(1);		
		
		//set values to object		
		obj.setRdfID(rdfID);
		obj.setName(name);		
		obj.setReg_rdfID(reg_rdfID);
		
		//print
		System.out.println("rdfID: " + rdfID + "; Name: " + name + "; regRdfID: " + reg_rdfID );	
		
		//save data in SQL database
				try{
					Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
					String query = "insert into Substation values (?,?,?)";
					PreparedStatement preparedStmt = conn1.prepareStatement(query);
					preparedStmt.setString(1, rdfID);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, reg_rdfID);
					preparedStmt.execute();
					}
				catch(Exception e){
					System.out.println(e);
					}
		
	}

		
}

