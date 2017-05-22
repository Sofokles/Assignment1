package Assignment;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Ybus {

	//calculating Ybus using ConnectedBus2BusList, PowerTransformerList, ACLineSegmentList
		public static void ybus(ArrayList<ConnectedBus2Bus>ConnectedBus2BusList){		
		
		int size = ConnectedBus2BusList.get(0).getMatrix_size();//use the size of the corrected busmatrix
		
		//initalize complex values and store data				
		Complex zero = new Complex(0,0);
		Complex Z0 = new Complex(1,0);		
		Complex [][] data = new Complex [size][size];
		String [] names = new String [size];
		
		//calculate Y-bus--------------
		//int size = ConnectedBus2BusList.size();
		for (int i = 0; i <ConnectedBus2BusList.size(); i++) {	
			int bus1 = ConnectedBus2BusList.get(i).getBusNum();
			int bus2 = ConnectedBus2BusList.get(i).getBus2Num();
			
			//Set base values					
			double Base_V = ConnectedBus2BusList.get(i).getBase_volt();
			double Base_S = ConnectedBus2BusList.get(i).getBase_S();	
			//Base_S=1;
			double Z_base = Base_V*Base_V/Base_S;
			
			//calculate admittances Y for all one line connections
			if (ConnectedBus2BusList.get(i).getLineNum()>-1){
				
				//get data
				double r_line1 = ConnectedBus2BusList.get(i).getR_Line1();
				double x_line1 = ConnectedBus2BusList.get(i).getX_Line1();						
				
				//per unit calc
				double r_line1_pu = r_line1/Z_base;
				double x_line1_pu = x_line1/Z_base;
				Complex Z_line1_pu = new Complex(r_line1_pu,x_line1_pu);
				
				//Calculating the element of Ybus matrix
				Complex Y_L1 = zero.minus(Z0.divides(Z_line1_pu));
				
				//store data
				data[bus1][bus2] = Y_L1;
				data[bus2][bus1] = Y_L1;
			}
			//calculate admittances Y for all two line connections
			if (ConnectedBus2BusList.get(i).getLine2Num()>-1){
				//get data
				double r_line1 = ConnectedBus2BusList.get(i).getR_Line1();
				double x_line1 = ConnectedBus2BusList.get(i).getX_Line1();		
				double r_line2 = ConnectedBus2BusList.get(i).getR_Line2();
				double x_line2 = ConnectedBus2BusList.get(i).getX_Line2();						
				
				//per unit calc
				double r_line1_pu = r_line1/Z_base;
				double x_line1_pu = x_line1/Z_base;
				Complex Z_line1_pu = new Complex(r_line1_pu,x_line1_pu);
				double r_line2_pu = r_line2/Z_base;
				double x_line2_pu = x_line2/Z_base;
				Complex Z_line2_pu = new Complex(r_line2_pu,x_line2_pu);						
				
				//Calculating the element of Ybus matrix
				Complex Y_L1 = zero.minus(Z0.divides(Z_line1_pu));
				Complex Y_L2 = zero.minus(Z0.divides(Z_line2_pu));
				
				//System.out.println( Y_L1+ "	" + Y_L2 + "	" + r_line2 + "	" + x_line2 + "	" + r_line1 + "	" + x_line1);
				//store data
				data[bus1][bus2] = Y_L2.plus(Y_L1);
				data[bus2][bus1] = Y_L2.plus(Y_L1);
			}
			//calculate admittances Y for all powertransformer connections
			else if (ConnectedBus2BusList.get(i).getTrafoNum()>-1){
				//get data
				double r_trafo = ConnectedBus2BusList.get(i).getR_trafo();
				double x_trafo = ConnectedBus2BusList.get(i).getX_trafo();						
				
				//per unit calc
				double r_trafo_pu = r_trafo/Z_base;
				double x_trafo_pu = x_trafo/Z_base;
				Complex Z_trafo_pu = new Complex(r_trafo_pu,x_trafo_pu);
				
				//Calculating the element of Ybus matrix
				Complex Y_T1 = zero.minus(Z0.divides(Z_trafo_pu));
				
				//store data
				data[bus1][bus2] = Y_T1;
				data[bus2][bus1] = Y_T1;
			}
			//set all others to zero.. 
			else {
				Complex Y_0 = zero;
				data[bus1][bus2] = Y_0;
				data[bus2][bus1] = Y_0;
			}	
		}
		
		//calculate the Yxx, summerize...
		for (int i = 0; i <size; i++) {	
			data[i][i] = zero;
			for (int i2 = 0; i2 <size; i2++) {	
				if (i!=i2){
					data[i][i] = data[i][i].minus(data[i][i2]);
				}
			}
			//set column names
			names[i] = "Y"+(i+1);
		}
		
		//add shunts. The shunts are stored in ConnectedBus2BusList and there position in the array is the bussnumber.  
		//for calculation details see: https://www.powerworld.com/files/S01SystemModeling.pdf
		for (int i = 0; i <ConnectedBus2BusList.size(); i++) {	
				double b_shunt = ConnectedBus2BusList.get(i).getB_Shunt();
				if (b_shunt>0){
					double g_shunt = ConnectedBus2BusList.get(i).getG_Shunt();
					//Set base values			
					double Base_S = ConnectedBus2BusList.get(i).getBase_S();
					//per unit calc
					double b_shunt_pu = b_shunt/Base_S;
					double g_shunt_pu = g_shunt/Base_S;
					Complex BG_Shunt_pu = new Complex(-b_shunt_pu,g_shunt_pu);//b absorbs and g injects.
					data[i][i] = data[i][i].plus(BG_Shunt_pu);
				}
		}		
		
		//plott the Y-bus
		JFrame frame = new JFrame();				
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Object rowData[][] = data;
	    Object columnNames[] = names;
	    
	    JTable table = new JTable(rowData, columnNames);
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1024, 500);
	    frame.setTitle("Y-bus");
	    frame.setVisible(true);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
}
      
