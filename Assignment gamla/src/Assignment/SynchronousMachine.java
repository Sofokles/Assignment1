package Assignment;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.*;

public class SynchronousMachine extends Base_constructor {
	
	private static double P;
	private static double Q;
	
	//method used to load data and store the data into an arraylist of objects
	public static void synchronousMachine(ArrayList<SynchronousMachine>SynchronousMachineList){		
			
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList synchronousMachineList = doc.getElementsByTagName("cim:SynchronousMachine"); 
						
			//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
			
			System.out.println("-------------SynchronousMachineList----------------");	
			
			for (int i = 0; i <synchronousMachineList.getLength(); i++) {				
				Node theNode = synchronousMachineList.item(i);				
				SynchronousMachineList.add(extractMethod(theNode));					
				}							
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	//method to extract data and store it into an new object
	public static SynchronousMachine extractMethod (Node node){				
		
		//Searching for values with the method parameter in the class ReadNode		
		String rdfID = ReadNode.parameter(node,"rdf:ID");
		String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
		double ratedS = Double.parseDouble(ReadNode.parameter(node,"cim:RotatingMachine.ratedS"));					
		String genUnit_rdfID = ReadNode.parameter(node,"cim:RotatingMachine.GeneratingUnit").substring(1);
		String regControl_rdfID = ReadNode.parameter(node,"cim:RegulatingCondEq.RegulatingControl").substring(1);
		String eq_con_rdfID = ReadNode.parameter(node,"cim:Equipment.EquipmentContainer").substring(1);	
		
		try {
			//read SSH file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc2 = dBuilder.parse(XmlFile);
			doc2.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList synchronousMachineList2 = doc2.getElementsByTagName("cim:SynchronousMachine"); 
						
			//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 			
			for (int i = 0; i <synchronousMachineList2.getLength(); i++) {				
				Node theNode2 = synchronousMachineList2.item(i);				
				String rdfID2 = ReadNode.parameter(theNode2,"rdf:about").substring(1);							
				
				if (rdfID2.equals(rdfID)){
					//System.out.println("rdfID: " + rdfID2);
					P = Double.parseDouble(ReadNode.parameter(theNode2,"cim:RotatingMachine.p"));
					Q = Double.parseDouble(ReadNode.parameter(theNode2,"cim:RotatingMachine.q"));					
					
				}
				
				}							
			}
		catch(Exception e){
			e.printStackTrace();
		}		
		
		//Set values
		SynchronousMachine obj = new SynchronousMachine();		
		obj.setRdfID(rdfID);
		obj.setName(name);		
		obj.setRatedS(ratedS);	
		obj.setGenUnit_rdfID(genUnit_rdfID);
		obj.setRegControl_rdfID(regControl_rdfID);
		obj.setEq_con_rdfID(eq_con_rdfID);
		obj.setP(P);
		obj.setQ(Q);		
										
		//print					
		System.out.println("rdfID: " + rdfID + "; Name: " + name + "; P: " + P + "; Q: " + Q + "; Eq_con_rdfID: " + eq_con_rdfID );				
				
		//save data in SQL database
		try{
			Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
			String query = "insert into SynchronousMachine values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn1.prepareStatement(query);
			preparedStmt.setString(1, rdfID);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, ratedS);
			preparedStmt.setDouble(4, P);
			preparedStmt.setDouble(5, Q);
			preparedStmt.setString(6, genUnit_rdfID);
			preparedStmt.setString(7, regControl_rdfID);
			preparedStmt.setString(8, eq_con_rdfID);			
			preparedStmt.execute();
		}
		catch(Exception e){
			System.out.println(e);
		}	
		
		
		//return the object
		return obj;
	}
		
	

}
