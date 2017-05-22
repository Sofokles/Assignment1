package Assignment;

public class ConnectedBus2Trafo {

	public int busNum;	
	public int trafoNum;		
	
	//Creating a constructor
	public ConnectedBus2Trafo(){	
		this.busNum=-1;
		this.trafoNum=-1;
		
	}
	//set.RdfID Method			
	public void setBusNum(int busNum_1){		 
		this.busNum=busNum_1;		
	}
	public void setTrafoNum(int trafoNum_1){		 
		this.trafoNum=trafoNum_1;		
	}
		
	//get.RdfID Method
	public int getBusNum(){		 
		return this.busNum;		
	}	
	public int getTrafoNum(){		 
		return this.trafoNum;		
	}
}
