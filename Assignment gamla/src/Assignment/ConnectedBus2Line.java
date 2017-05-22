package Assignment;

public class ConnectedBus2Line extends ConnectedBus2Trafo {

	public int lineNum;		
	
	//Creating a constructor
	public ConnectedBus2Line(){		
		this.lineNum=-1;		
		
	}
	//set.RdfID Method			
	public void setLineNum(int lineNum_1){		 
		this.lineNum=lineNum_1;		
	}	
		
	//get.RdfID Method
	public int getLineNum(){		 
		return this.lineNum;		
	}
}