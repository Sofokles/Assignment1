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

public class RegulatingControl extends Base_constructor {		
									
					private static double targetValue;				
					
					//method used to load data and store the data into an arraylist of objects
					public static void regulatingControl(ArrayList<RegulatingControl>RegulatingControlList){		
							
						try {
							//read EQ file
							File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
							Document doc = dBuilder.parse(XmlFile);
							doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
							
							//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
							NodeList regulatingControlList = doc.getElementsByTagName("cim:RegulatingControl"); 
										
							//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
							System.out.println("-------------RegulatingControlList----------------");	
							for (int i = 0; i <regulatingControlList.getLength(); i++) {				
								Node theNode = regulatingControlList.item(i);				
								RegulatingControlList.add(extractMethod(theNode));					
								}							
							}
						catch(Exception e){
							e.printStackTrace();
						}						
					}	
					
					//method to extract data and store it into an new base_voltage object
					public static RegulatingControl extractMethod (Node node){				
						
						//Searching for values with the method parameter in the class ReadNode		
						String rdfID = ReadNode.parameter(node,"rdf:ID");
						String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");							
						
						//create an object and set values
						RegulatingControl obj = new RegulatingControl();		
						obj.setRdfID(rdfID);
						obj.setName(name);
						
						//need to go through SSH file
						try {
							//read SSH file
							File XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
							Document doc2 = dBuilder.parse(XmlFile);
							doc2.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
							
							//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
							NodeList regulatingControl2 = doc2.getElementsByTagName("cim:RegulatingControl"); 							
							
							//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 								
							for (int i = 0; i <regulatingControl2.getLength(); i++) {				
								Node theNode2 = regulatingControl2.item(i);				
								String rdfID2 = ReadNode.parameter(theNode2,"rdf:about").substring(1);														
								
								if (rdfID2.equals(rdfID)){
									//System.out.println("rdfID: " + rdfID2);
									targetValue = Double.parseDouble(ReadNode.parameter(theNode2,"cim:RegulatingControl.targetValue"));	
									obj.setTargetValue(targetValue);	
								}
								
							}							
						}
						catch(Exception e){
							e.printStackTrace();
						}		
						
						//print
						System.out.println("rdfID: " + rdfID + "; Name: " + name + "; TargetValue: " + targetValue );		
						
						//save data in SQL database
						try{
							Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
							String query = "insert into RegulatingControl values (?,?,?)";
							PreparedStatement preparedStmt = conn1.prepareStatement(query);
							preparedStmt.setString(1, rdfID);
							preparedStmt.setString(2, name);
							preparedStmt.setDouble(3, targetValue);
							preparedStmt.execute();
						}
						catch(Exception e){
							System.out.println(e);
						}					
					
					//return the object
					return obj;		
					}				
					
					
	}

	

