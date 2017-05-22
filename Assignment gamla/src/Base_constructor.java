

//this constructor is used as a base constructor for all classes
public class Base_constructor {
	
	private static String rdfID;
	//private static int quantity = 0;
	
	//Creating a constructor
	public Base_constructor(){	
		this("");
	}
	public Base_constructor(String rdfID_1){	
		rdfID=rdfID_1;
		//quantity++;
	}

	//get.RdfID Method
	public static String getRdfID(){		 
		return rdfID;		
	}	
	//get.quantity Method
	//public static int getQuantity(){		 
	//	return quantity;		
	//}
	//method to print values 
	//public static void print(Base_constructor base_con){		
		//String rdfID=base_con.rdfID;
		//int quantity=base_con.quantity;
		//String name=baseV_X.nom_val;		
		//System.out.println(rdfID + "" + quantity);
			
	//}

}
