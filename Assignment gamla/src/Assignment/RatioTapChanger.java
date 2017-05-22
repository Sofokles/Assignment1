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

public class RatioTapChanger extends Base_constructor {
		
		private static double step;								
						
						//method used to load data and store the data into an arraylist of objects
						public static void ratioTapChanger(ArrayList<RatioTapChanger>RatioTapChangerList){		
								
							try {
								//read EQ file
								File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
								DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
								DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
								Document doc = dBuilder.parse(XmlFile);
								doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
								
								//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
								NodeList ratioTapChangerList = doc.getElementsByTagName("cim:RatioTapChanger"); 
											
								//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
								System.out.println("-------------RatioTapChangerList----------------");	
								for (int i = 0; i <ratioTapChangerList.getLength(); i++) {				
									Node theNode = ratioTapChangerList.item(i);				
									RatioTapChangerList.add(extractMethod(theNode));					
									}							
								}
							catch(Exception e){
								e.printStackTrace();
							}						
						}	
						
						//method to extract data and store it into an new base_voltage object
						public static RatioTapChanger extractMethod (Node node){				
							
							//Searching for values with the method parameter in the class ReadNode		
							String rdfID = ReadNode.parameter(node,"rdf:ID");
							String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");							
							
							//need to go through SSH file
							try {
								//read SSH file
								File XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
								DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
								DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
								Document doc2 = dBuilder.parse(XmlFile);
								doc2.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
								
								//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
								NodeList regulatingControl2 = doc2.getElementsByTagName("cim:RatioTapChanger"); 
											
								//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 								
								for (int i = 0; i <regulatingControl2.getLength(); i++) {				
									Node theNode2 = regulatingControl2.item(i);				
									String rdfID2 = ReadNode.parameter(theNode2,"rdf:about").substring(1);																
									
									if (rdfID2.equals(rdfID)){
										//System.out.println("rdfID: " + rdfID2);
										step = Double.parseDouble(ReadNode.parameter(theNode2,"cim:TapChanger.step"));	
									}
									
								}							
							}
							catch(Exception e){
								e.printStackTrace();
							}
							
							
							//create an object and set values
							RatioTapChanger obj = new RatioTapChanger();		
							obj.setRdfID(rdfID);
							obj.setName(name);		
							obj.setStep(step);	
							
							//print
							System.out.println("rdfID: " + rdfID + "; Name: " + name + "; Step: " + step );		
							
							//save data in SQL database
							try{
								Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
								String query = "insert into RatioTapChanger values (?,?,?)";
								PreparedStatement preparedStmt = conn1.prepareStatement(query);
								preparedStmt.setString(1, rdfID);
								preparedStmt.setString(2, name);				
								preparedStmt.setDouble(3, step);
								preparedStmt.execute();
							}
							catch(Exception e){
								System.out.println(e);
							}	
							
							//return the object
							return obj;		
						}					
						
						
		}

		


	

