

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Main_file {

	public static void main(String[] args) {
				// TODO Auto-generated method stub
				
		//read EQ file
		
		
				//Base Voltage..
				//creates a arraylist of the constructor of objects Base_voltage, ... etc
				//Is not needed as I will use a SQL server to store the data later
				ArrayList<Base_voltage> Base_voltageList = new ArrayList<Base_voltage>();
				//Base_voltageList.base_voltage();			
				//Creating an Base_voltage object to access the class
				//Base_voltage BV_object = new Base_voltage();
				Base_voltage.base_voltage(Base_voltageList);				
				//Base_voltageList(0).base_voltage(Base_voltageList);	
				int t1 = Base_voltageList.size();
				//int t2 = basvoltList.get
				System.out.println("Size:" + t1);	
				
				//for-loop using method to print values to see if it is correct
				for (int i=0;i<Base_voltageList.size();i++) { // Use enhance looping									
					String x = Base_voltageList.get(i).getRdfID();
					int q = Base_voltageList.size();
					
					System.out.println("rdfID: " + x + "; Quantity: " + q);				
					}
				String x = Base_voltageList.get(1).getRdfID();
				System.out.println(x + "rdfID" );	
				
	}

}
