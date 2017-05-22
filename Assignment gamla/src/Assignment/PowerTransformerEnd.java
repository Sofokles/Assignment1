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

public class PowerTransformerEnd extends Base_constructor {	
		
		//method used to load data and store the data into an arraylist of objects
		public static void powerTransformerEnd(ArrayList<PowerTransformerEnd>PowerTransformerEndList){		
				
			try {
				//read EQ file
				File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XmlFile);
				doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
				
				//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
				NodeList powerTransformerEndList = doc.getElementsByTagName("cim:PowerTransformerEnd"); 
							
				//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
				System.out.println("-------------PowerTransformerEnd----------------");
				for (int i = 0; i <powerTransformerEndList.getLength(); i++) {				
					Node theNode = powerTransformerEndList.item(i);				
					PowerTransformerEndList.add(extractMethod(theNode));					
					}							
				}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}	
		
		//method to extract data and store it into an new object
		public static PowerTransformerEnd extractMethod (Node node){				
			
			//Searching for values with the method parameter in the class ReadNode		
			String rdfID = ReadNode.parameter(node,"rdf:ID");
			String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
			double transformer_r = Double.parseDouble(ReadNode.parameter(node,"cim:PowerTransformerEnd.r"));	
			double transformer_x = Double.parseDouble(ReadNode.parameter(node,"cim:PowerTransformerEnd.x"));	
			String transForm_rdfID = ReadNode.parameter(node,"cim:PowerTransformerEnd.PowerTransformer").substring(1);
			String baseVolt_rdfID = ReadNode.parameter(node,"cim:TransformerEnd.BaseVoltage").substring(1);
						
			//create an object and set values				
			//create an object and set values
			PowerTransformerEnd obj = new PowerTransformerEnd();		
			obj.setRdfID(rdfID);
			obj.setName(name);
			obj.setTransformer_r(transformer_r);
			obj.setTransformer_x(transformer_x);
			obj.setTransForm_rdfID(transForm_rdfID);	
			obj.setBase_volt_rdfID(baseVolt_rdfID);
			
			//print					
			System.out.println("rdfID: " + rdfID + "; Name: " + name + "; transformer_r: " + transformer_r + "; transformer_x: " + transformer_x + "; transForm_rdfID: " + transForm_rdfID + "; baseVolt_rdfID: " + baseVolt_rdfID );				
			
			//save data in SQL database
			try{
				Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
				String query = "insert into TransformerWinding values (?,?,?,?,?,?)";
				PreparedStatement preparedStmt = conn1.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);				
				preparedStmt.setDouble(3, transformer_r);
				preparedStmt.setDouble(4, transformer_x );				
				preparedStmt.setString(5, transForm_rdfID);
				preparedStmt.setString(6, baseVolt_rdfID);
				preparedStmt.execute();
			}
			catch(Exception e){
				System.out.println(e);
			}	
			
			//return the object
			return obj;		
		}		
		
	}



