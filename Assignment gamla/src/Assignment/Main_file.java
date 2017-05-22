package Assignment;

import java.util.ArrayList;

public class Main_file {
	//EXTRA FEATURES:
	//1. THIS PROGRAM WORKS FOR ALL CIM/XML FILES NO MATHER OF SYSTEM SIZES (HOWEVER, MAX 2 LINES BETWEEN 2 BUSSES AT THE MOMENT.)
	//2. IT CONSIDER BREAKER STATES
	//3. IT PRINTS THE CONNECTIONS BETWEEN BUSSES AND CORRESPONDING LINES/POWERTRANSFORMERS
	//4. ALL IMPORTANT DATA ARE STORED AS OBJECTS WITH CORRESPONDING SET/GET METHODS
	//5. THE Y-BUS CALCULATION TAKES SHUNTS AT BUSSES INTO ACCOUNT
	
	//CREATING ARRAYLISTS TO STORE THE OBJECTS
	public static ArrayList<Base_voltage> Base_voltageList = new ArrayList<Base_voltage>(); 
	public static ArrayList<Substation> SubstationList = new ArrayList<Substation>();
	public static ArrayList<Voltage_level> Voltage_levelList = new ArrayList<Voltage_level>(); 
	public static ArrayList<GeneratingUnit> GeneratingUnitList = new ArrayList<GeneratingUnit>(); 
	public static ArrayList<RegulatingControl> RegulatingControlList = new ArrayList<RegulatingControl>(); 
	public static ArrayList<SynchronousMachine> SynchronousMachineList = new ArrayList<SynchronousMachine>(); 
	public static ArrayList<PowerTransformer> PowerTransformerList = new ArrayList<PowerTransformer>(); 
	public static ArrayList<EnergyConsumer> EnergyConsumerList = new ArrayList<EnergyConsumer>(); 
	public static ArrayList<PowerTransformerEnd> PowerTransformerEndList = new ArrayList<PowerTransformerEnd>(); 
	public static ArrayList<Breaker> BreakerList = new ArrayList<Breaker>();
	public static ArrayList<RatioTapChanger> RatioTapChangerList = new ArrayList<RatioTapChanger>(); 
	public static ArrayList<ACLineSegment> ACLineSegmentList = new ArrayList<ACLineSegment>(); 
	public static ArrayList<Busbar> BusbarList = new ArrayList<Busbar>(); 
	public static ArrayList<Terminal> TerminalList = new ArrayList<Terminal>(); 
	public static ArrayList<Shunts> ShuntsList = new ArrayList<Shunts>(); 	
	public static ArrayList<ConnectedBus2Bus> ConnectedBus2BusList = new ArrayList<ConnectedBus2Bus>(); 
	public static ArrayList<ConnectedBus2Shunt> ConnectedBus2ShuntList = new ArrayList<ConnectedBus2Shunt>();
	
	//MAIN CLASS EXECUTING THE PROGRAM
	public static void main(String[] args) {		
		
		//----------------------------------------------------------------------------------------------------
		//TASK 1 and 2, IMPORT THE XML-CIM DATA AND STORE IT IN A RELATIONSHIP DATABASE IN SQL
		//-------------------------------------------------------------------------------
		//I ALSO STORE THE DATA IN OBJECTS TO BE USED FOR THE YBUS CALCULATION IN TASK 3
		//ADDITIANALY, TERMINAL, BUSBAR and AC-LINE DATA ARE STORED IN OBJECTS TO PERFORM TASK 3
		
		//HERE WE CALL METHODS WITHIN THE CLASSES TO UPLOAD DATA AND STORE THE DATA IN THE ARRAYLISTS OF OBJECTS AND IN SQL
			Base_voltage.base_voltage(Base_voltageList);	
			Substation.substation(SubstationList);	
			Voltage_level.voltage_level(Voltage_levelList);	
			GeneratingUnit.generatingUnit(GeneratingUnitList);	
			RegulatingControl.regulatingControl(RegulatingControlList);
			SynchronousMachine.synchronousMachine(SynchronousMachineList);	
			PowerTransformer.powerTransformer(PowerTransformerList);
			EnergyConsumer.energyConsumer(EnergyConsumerList);	
			PowerTransformerEnd.powerTransformerEnd(PowerTransformerEndList);
			Breaker.breaker(BreakerList);	
			RatioTapChanger.ratioTapChanger(RatioTapChangerList);	
			
			//EXTRA TO PERFORM TASK 3---------------------------------------------------------------
			ACLineSegment.aCLineSegment(ACLineSegmentList);	
			Busbar.busbar(BusbarList);	//Busbar, extra: The basbarList only contains Busbars with uniqe Eq_con_rdfID
			Terminal.terminal(TerminalList);	
			Shunts.shunts(ShuntsList);	
		
		//----------------------------------------------------------------------------------------------------
		//TASK 3, CALCULATE THE YBUS
		//Creating a Bus2Bus arraylist
		
		//Call the method and CREATE ConnectedBus2BusList with all connections
		ConnectedBus2BusList = findConnections(TerminalList, BreakerList, BusbarList, PowerTransformerList, PowerTransformerEndList, ACLineSegmentList,
				SynchronousMachineList, Base_voltageList, ShuntsList);
			
		//USING THE METHOD YBUS IN THE YBUS CLASS TO CALCULATE THE YBUS MATRIX USING THE ConnectedBus2BusList ARRAYLIST
		Ybus.ybus(ConnectedBus2BusList);
	}
	
	
	//METHOD TO FIND CONNECTIONS BETWEEN BUSSES AND STORE THEM IN AN OBJECT ARRAYLIST "CONNECTEDBUS2BUSLIST" 
	//This method creates three arrays with objects of connections. 
	//1. ConnectedBus2TrafoList is the connection between busses and transformers
	//2. ConnectedBus2LineList is the connection between busses and Lines
	//3. The two lists above are used to create the ConnectedBus2BusList with all connEctions between bussus end with the corresponding trafos/lines 
	//connected between them
	public static ArrayList<ConnectedBus2Bus> findConnections(ArrayList<Terminal>TerminalList, ArrayList<Breaker>BreakerList, ArrayList<Busbar>BusbarList, 
			ArrayList<PowerTransformer>PowerTransformerList, ArrayList<PowerTransformerEnd>PowerTransformerEndList, ArrayList<ACLineSegment>ACLineSegmentList,
			ArrayList<SynchronousMachine>SynchronousMachineList, ArrayList<Base_voltage>Base_voltageList, ArrayList<Shunts>ShuntsList ) {
		
		//ConnectionBus2Trafo
		ArrayList<ConnectedBus2Trafo> ConnectedBus2TrafoList = new ArrayList<ConnectedBus2Trafo>(); 
		//ConnectionBus2Line
		ArrayList<ConnectedBus2Line> ConnectedBus2LineList = new ArrayList<ConnectedBus2Line>();
		//ConnectionBus2Shunt
		
		
		
		//1. Starting with each busbar
		for (int i = 0; i <BusbarList.size(); i++) {	//BusbarList.size()										
			
			//using the RdfID and the Eq_con Strings
			String Rdf_bus = BusbarList.get(i).getRdfID();
			String Eq_con_rdfID_bus = BusbarList.get(i).getEq_con_rdfID();	
					
			//2. Find the terminals corresponding to the bus
			for (int i2 = 0; i2 <TerminalList.size(); i2++) {											
				
				String Terminal_ConductingEquipment_t1 = TerminalList.get(i2).getTerminal_ConductingEquipment();
				
				//2.a. Using a If statement
				if (Terminal_ConductingEquipment_t1.equals(Rdf_bus)){
					String ConnectivityNode_t1 = TerminalList.get(i2).getConnectivityNode();
					
					//3.a. DIRECTLY CONNECTION BETWEEN BUS AND TRAFO (Bus2Trafo)
					//Find the terminal that has the same connectivity node but not the same conducting equipment. 
					for (int i3 = 0; i3 <TerminalList.size(); i3++) {								
						
						String ConnectivityNode_t3 = TerminalList.get(i3).getConnectivityNode();	
						String Terminal_ConductingEquipment_t3 = TerminalList.get(i3).getTerminal_ConductingEquipment();	
						
						//if eq_con is not the same as the breaker but it is the same connectivity node
						if (ConnectivityNode_t1.equals(ConnectivityNode_t3) && !Terminal_ConductingEquipment_t3.equals(Terminal_ConductingEquipment_t1)) {
						
							//4. STORE THE BUSNUMBER AND THE TRAFO NUMBER FOR EACH CONNECTION (bus2trafo objects)
							for (int i6 = 0; i6 <PowerTransformerList.size(); i6++) {								
								
								String RfdID_p1 = PowerTransformerList.get(i6).getRdfID();	
								
								//if the found con_eq is a transformer
								if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
									
									//create an object
									ConnectedBus2Trafo obj = new ConnectedBus2Trafo();
									obj.setBusNum(i);
									obj.setTrafoNum(i6);
									
									//store object in list
									ConnectedBus2TrafoList.add(obj);
								}
							}
							//7.c STORE THE BUSNUMBER AND CONNECTED SHUNTS NUMBER in an arraylist as bus2line objects
							for (int i6 = 0; i6 <ShuntsList.size(); i6++) {								
								
								String RfdID_p1 = ShuntsList.get(i6).getRdfID();	
								
								//if the founded con_eq is a line
								if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
									
									//create an object
									ConnectedBus2Shunt obj = new ConnectedBus2Shunt();
									obj.setBusNum(i);
									obj.setShuntNum(i6);
									
									//store object in list
									ConnectedBus2ShuntList.add(obj);
								}
							}
						}
					}
					
					//3.b. nNOT DIRECT CONENCTION INSTEAD THE IT IS A CONNECTION BETWEEN BREAKER AND BUS
					//Find the breaker connected to the bus and later, //find connections between Bus2Trafo, Bus2Bus or Bus2Line
					for (int i3 = 0; i3 <BreakerList.size(); i3++) {
						String Eq_con_rdfID_br = BreakerList.get(i3).getEq_con_rdfID();						
													
						//if eq_con is the same for breaker and bus	
						if (Eq_con_rdfID_br.equals(Eq_con_rdfID_bus)){
																	
							Boolean state_br = BreakerList.get(i3).getState();
							
							//4. CHECK IF THE BREAKER IS CLOSED OTHERWISE DO NOT CONTINUE
							if (state_br.equals(false)){
								String RdfID_br = BreakerList.get(i3).getRdfID();
								
								//5. FIND THE TERMINAL ON THE OTHERSIDE OF THE BREAKER TO GET THE NEXT CONNECTIONS
								for (int i4 = 0; i4 <TerminalList.size(); i4++) {								
									
									String Terminal_ConductingEquipment_t = TerminalList.get(i4).getTerminal_ConductingEquipment();	
									
									//find the terminals with the same eq_con as the breaker 
									if (Terminal_ConductingEquipment_t.equals(RdfID_br)){
										
										String ConnectivityNode_t2 = TerminalList.get(i4).getConnectivityNode();
										
										//Choose the breaker side terminal that is not connected to the investigated bus by its connectivity node.									
										if (!ConnectivityNode_t2.equals(ConnectivityNode_t1)){
																																						
											//6. FIND THE TRAFOS AND/OR THE AC-LINES CONNETED TO THE OTHER SIDE OF THE BREAKER 
											for (int i5 = 0; i5 <TerminalList.size(); i5++) {								
												
												String ConnectivityNode_t3 = TerminalList.get(i5).getConnectivityNode();	
												String Terminal_ConductingEquipment_t3 = TerminalList.get(i5).getTerminal_ConductingEquipment();	
												
												//if eq_con is not the same as the breaker but it is the same connectivity node
												if (!Eq_con_rdfID_br.equals(Terminal_ConductingEquipment_t3) && ConnectivityNode_t2.equals(ConnectivityNode_t3) && !Terminal_ConductingEquipment_t3.equals(Terminal_ConductingEquipment_t)) {
												
													//7.a STORE THE BUSNUMBER AND CONNECTED TROFONUMBER in an arraylist as bus2trafo objects 
													for (int i6 = 0; i6 <PowerTransformerList.size(); i6++) {								
														
														String RfdID_p1 = PowerTransformerList.get(i6).getRdfID();	
													
														//only if the con_eq is a transformer
														if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
															
															//create an object
															ConnectedBus2Trafo obj = new ConnectedBus2Trafo();
															obj.setBusNum(i);
															obj.setTrafoNum(i6);
															
															//store object in list
															ConnectedBus2TrafoList.add(obj);
														}
													}
													
													//7.b STORE THE BUSNUMBER AND CONNECTED LINES NUMBER in an arraylist as bus2line objects
													for (int i6 = 0; i6 <ACLineSegmentList.size(); i6++) {								
														
														String RfdID_p1 = ACLineSegmentList.get(i6).getRdfID();	
														
														//if the founded con_eq is a line
														if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
															
															//create an object
															ConnectedBus2Line obj = new ConnectedBus2Line();
															obj.setBusNum(i);
															obj.setLineNum(i6);
															
															//store object in list
															ConnectedBus2LineList.add(obj);
														}
													}
													//7.c STORE THE BUSNUMBER AND CONNECTED SHUNTS NUMBER in an arraylist as bus2line objects
													for (int i6 = 0; i6 <ShuntsList.size(); i6++) {								
														
														String RfdID_p1 = ShuntsList.get(i6).getRdfID();	
														
														//if the founded con_eq is a line
														if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
															
															//create an object
															ConnectedBus2Shunt obj = new ConnectedBus2Shunt();
															obj.setBusNum(i);
															obj.setShuntNum(i6);
															
															//store object in list
															ConnectedBus2ShuntList.add(obj);
														}
													}
												}
											}													
										}
									}
								}	
							}		
						}	
					}
				}	
			}
		}
		
	//CREATE A BUS2BUS CONNECTION OBJECTS AND STORE THEM IN AN ARRAYLIST, ConnectedBus2BusList
	ArrayList<ConnectedBus2Bus> ConnectedBus2BusList = new ArrayList<ConnectedBus2Bus>(); //creating an arraylist of objects
	
	//THE SIZE OF THE YBUS IS THE BusbarList.size().
	int matrix_size = BusbarList.size();
	
	//print ConnectedBus2LineList
			for (int i = 0; i <ConnectedBus2ShuntList.size(); i++) {								
			
				int busNum = ConnectedBus2ShuntList.get(i).getBusNum();	
				int lineNum = ConnectedBus2ShuntList.get(i).getShuntNum();
				int pp = ConnectedBus2ShuntList.size();
				
				String name = BusbarList.get(busNum).getName();
				String name2 = ACLineSegmentList.get(lineNum).getName();
				
				System.out.println("busNum: " + busNum + name + "; lineNum: " + lineNum + name2 + "; size" + pp);	
			}
	//creating a list of objects bus2bus with bus2lines and bus2trafo connections.
	for (int i = 0; i <matrix_size-1; i++) {//all busses -1
		for (int i2 = i+1; i2 <matrix_size; i2++) {//all busses starts +1
			//create an object
			ConnectedBus2Bus obj = new ConnectedBus2Bus();
			ConnectedBus2BusList.add(obj);
			
			//numbers
			obj.setBusNum(i);
			obj.setBus2Num(i2);
			obj.setMatrix_size(matrix_size);
			
			//base Power
			double ratedS = SynchronousMachineList.get(0).getRatedS();
			obj.setBase_S(ratedS);	
			
			//1.a. Bus2line						
			for (int i3 = 0; i3 <ConnectedBus2LineList.size()-1; i3++) {
				for (int i4 = i3+1; i4 <ConnectedBus2LineList.size(); i4++) {
					int conBusL1 = ConnectedBus2LineList.get(i3).getBusNum();	
					int conBusL2 = ConnectedBus2LineList.get(i4).getBusNum();
					int conLineL1 = ConnectedBus2LineList.get(i3).getLineNum();	
					int conLineL2 = ConnectedBus2LineList.get(i4).getLineNum();
						
					//Test if busses are correct and same Line connection
					if (conBusL1==i && conBusL2==i2 && conLineL1==conLineL2){									
						int conLine = ConnectedBus2LineList.get(i3).getLineNum();									
						int testLine = obj.getLineNum();
						
						//set Line data r and x
						double r = ACLineSegmentList.get(conLine).getR();
						double x = ACLineSegmentList.get(conLine).getX();
						
						//set base_volt, finds the base_volt value and stores it
						String BaseVolt_rdfID = ACLineSegmentList.get(conLine).getBase_volt_rdfID();
						for (int i5 = 0; i5 <Base_voltageList.size(); i5++) {	
							String BaseVolt_rdfID_2 = Base_voltageList.get(i5).getRdfID();	
							if (BaseVolt_rdfID_2.equals(BaseVolt_rdfID)){
								double BaseVolt_nom = Base_voltageList.get(i5).getNom_val();	
								obj.setBase_volt(BaseVolt_nom);
							}
						}			
						if (testLine == -1){//if no line-connection all-ready exists
							obj.setLineNum(conLine);
							obj.setR_line1(r);
							obj.setX_line1(x);
							//System.out.println("conBusL1: " + conBusL1 + "; conBusL2: " + conBusL2 + "; conLineL1: " +conLineL1 + "; conLineL2 : " + conLineL2 );	
							testLine=conLine;
						}	else{//if there all-ready is a line-connection
							obj.setLine2Num(conLine);
							obj.setR_line2(r);
							obj.setX_line2(x);
						}
					}
				}
			}
			
			//1.b. bus2trafo					
			for (int i3 = 0; i3 <ConnectedBus2TrafoList.size()-1; i3++) {
				for (int i4 = i3+1; i4 <ConnectedBus2TrafoList.size(); i4++) {
					int conBusT1 = ConnectedBus2TrafoList.get(i3).getBusNum();	
					int conBusT2 = ConnectedBus2TrafoList.get(i4).getBusNum();
					int conTrafoT1 = ConnectedBus2TrafoList.get(i3).getTrafoNum();	
					int conTrafoT2 = ConnectedBus2TrafoList.get(i4).getTrafoNum();
					//Test if busses arer correct and same Line connection
					if (conBusT1==i && conBusT2==i2 && conTrafoT1==conTrafoT2){
						int conTrafo = ConnectedBus2TrafoList.get(i3).getTrafoNum();	
						obj.setTrafoNum(conTrafo);
						//System.out.println("conBusT1: " + conBusT1 + "; conBusT2: " + conBusT2 + "; conTrafoT1: " +conTrafoT1 + "; conTrafoT2: " + conTrafoT2 );	
						
						//set base_volt, finds the base_volt value and stores it
						String Trafo_name = PowerTransformerList.get(conTrafo).getName();
						for (int i5 = 0; i5 <PowerTransformerEndList.size(); i5++) {	
							String PowerTransformerEnd_name = PowerTransformerEndList.get(i5).getName();	
							if (PowerTransformerEnd_name.equals(Trafo_name)){
								double r = PowerTransformerEndList.get(i5).getTransformer_r();	
								double x = PowerTransformerEndList.get(i5).getTransformer_x();	
								if (r>0){
									obj.setR_trafo(r);
									obj.setX_trafo(x);
									String BaseVolt_rdfID = PowerTransformerEndList.get(i5).getBase_volt_rdfID();
									
									for (int i6 = 0; i6 <Base_voltageList.size(); i6++) {	
										String BaseVolt_rdfID_2 = Base_voltageList.get(i6).getRdfID();	
										if (BaseVolt_rdfID_2.equals(BaseVolt_rdfID)){
											double BaseVolt_nom = Base_voltageList.get(i6).getNom_val();	
											obj.setBase_volt(BaseVolt_nom);
										}
									}	
								}
							}
						}	
					}
				}
			}
		}	
				
	}
	
	//1.b. bus2shunt. Store values in the ConnectedBus2BusList. The position indicates the corresponding busnumber					
	for (int i3 = 0; i3 <ConnectedBus2ShuntList.size(); i3++) {
		
			int conBus = ConnectedBus2ShuntList.get(i3).getBusNum();	
			int conshunt = ConnectedBus2ShuntList.get(i3).getShuntNum();
			
			double b = ShuntsList.get(conshunt).getB();	
			double g = ShuntsList.get(conshunt).getG();	
			
			ConnectedBus2BusList.get(conBus).setB_shunt(b);
			ConnectedBus2BusList.get(conBus).setG_shunt(g);
	}
			
	//print
	System.out.println("------------Connections Between Busses--------------------");	
	System.out.println("	Bus1	Bus2	Line1	Line2	Trafo	RatedS	RatedU");	
	for (int i = 0; i <ConnectedBus2BusList.size(); i++) {								
		
		int busNum = ConnectedBus2BusList.get(i).getBusNum();	
		int bus2Num = ConnectedBus2BusList.get(i).getBus2Num();
		int conLine = ConnectedBus2BusList.get(i).getLineNum();
		int conLine2= ConnectedBus2BusList.get(i).getLine2Num();
		int conTrafo= ConnectedBus2BusList.get(i).getTrafoNum();	
		double Base_S = ConnectedBus2BusList.get(i).getBase_S();
		double Base_V = ConnectedBus2BusList.get(i).getBase_volt();	
		
		//only print the the connections..
		int sum = conLine + conLine2 + conTrafo;
		if (sum>-3){
		
			System.out.println("	" + busNum + "	" + bus2Num + "	" + conLine + "	" + conLine2 + "	" + conTrafo + "	" + Base_S + "	" + Base_V );	
		}
	}
	return ConnectedBus2BusList;
	}
	
}
