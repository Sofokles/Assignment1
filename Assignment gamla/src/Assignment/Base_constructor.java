package Assignment;


//this constructor is used as a base constructor for all classes
//1. all the public variables are declared here
//2. all set and get methods are created here
public class Base_constructor {
	
	public String rdfID;	
	public String name;		
	public String reg_rdfID;	
	public double ratedS;	
	public String genUnit_rdfID;
	public String regControl_rdfID;
	public String eq_con_rdfID;	
	public double nom_val;
	public String sub_rdfID;
	public String base_volt_rdfID;		
	public double maxP;	
	public double minP;	
	
	public double P;	
	public double Q;	
	
	public double targetValue;	
	
	public double transformer_r;	
	public double transformer_x;
	public String transForm_rdfID;
	
	public Boolean state;
	
	public double step;
	
	public double r;	
	public double x;
	
	public double b;	
	public double g;
	
	public String terminal_ConductingEquipment;
	public String connectivityNode;		
	
	//Creating a constructor
	public Base_constructor(){	
		this("");
	}
	//Creating a constructor
	public Base_constructor(String rdfID_1){	
		this.rdfID=rdfID_1;
	}
	public Base_constructor(String rdfID_1, String name_1){	
		this.rdfID=rdfID_1;
		this.name=name_1;
		
	}
	//-------------------------
	
	//Methods
	//set.RdfID Method
	//get method (to obtain value from the class)	
	public void setRdfID(String rdfID_1){		 
		this.rdfID=rdfID_1;		
	}
	public void setName(String name_1){		
		this.name = name_1;				
	}
	public void setReg_rdfID(String reg_rdfID_1){		
		this.reg_rdfID = reg_rdfID_1;				
	}	
	public void setRatedS(double ratedS_1){		
		this.ratedS = ratedS_1;				
	}	
	public void setGenUnit_rdfID(String genUnit_rdfID_1){		
		this.genUnit_rdfID = genUnit_rdfID_1;				
	}
	public void setRegControl_rdfID(String regControl_rdfID_1){		
		this.regControl_rdfID = regControl_rdfID_1;				
	}
	public void setEq_con_rdfID(String eq_con_rdfID_1){		
		this.eq_con_rdfID = eq_con_rdfID_1;				
	}
	public void setNom_val(double nom_val_1){		
		this.nom_val = nom_val_1;				
	}
	public void setSub_rdfID(String sub_rdfID_1){		
		this.sub_rdfID = sub_rdfID_1;				
	}
	public void setBase_volt_rdfID(String base_volt_rdfID_1){		
		this.base_volt_rdfID = base_volt_rdfID_1;				
	}
	public void setMaxP(double maxP_1){		
		this.maxP = maxP_1;				
	}
	public void setMinP(double minP_1){		
		this.minP = minP_1;				
	}
	public void setP(double P_1){		
		this.P = P_1;				
	}
	public void setQ(double Q_1){		
		this.Q = Q_1;				
	}	
	public void setTargetValue(double targetValue_1){		
		this.targetValue = targetValue_1;				
	}
	public void setTransformer_r(double transformer_r_1){		
		this.transformer_r = transformer_r_1;				
	}
	public void setTransformer_x(double transformer_x_1){		
		this.transformer_x = transformer_x_1;				
	}
	public void setTransForm_rdfID(String transForm_rdfID_1){		
		this.transForm_rdfID = transForm_rdfID_1;				
	}	
	public void setState(Boolean state_1){		
		this.state = state_1;				
	}
	public void setStep(double step_1){		
		this.step = step_1;				
	}
	public void setR(double r_1){		
		this.r = r_1;				
	}
	public void setX(double x_1){		
		this.x = x_1;				
	}
	public void setB(double b_1){		
		this.b = b_1;				
	}
	public void setG(double g_1){		
		this.g = g_1;				
	}
	public void setTerminal_ConductingEquipment(String terminal_ConductingEquipment_1){		
		this.terminal_ConductingEquipment = terminal_ConductingEquipment_1;				
	}
	public void setConnectivityNode(String connectivityNode_1){		
		this.connectivityNode = connectivityNode_1;				
	}
	
	//get.RdfID Method
	public String getRdfID(){		 
		return this.rdfID;		
	}	
	public String getName(){		 
		return this.name;		
	}	
	public String getReg_rdfID(){		
		return this.reg_rdfID;				
	}
	public double getRatedS(){		 
		return this.ratedS;		
	}	
	public String getGenUnit_rdfID(){		 
		return this.genUnit_rdfID;		
	}
	public String getRegControl_rdfID(){		 
		return this.regControl_rdfID;		
	}
	public String getEq_con_rdfID(){		 
		return this.eq_con_rdfID;		
	}
	public double getNom_val(){		 
		return this.nom_val;		
	}
	public String getSub_rdfID(){		 
		return this.sub_rdfID;		
	}
	public String getBase_volt_rdfID(){		 
		return this.base_volt_rdfID;		
	}
	public double getMaxP(){		 
		return this.maxP;		
	}
	public double getMinP(){		 
		return this.minP;		
	}
	public double getP(){		 
		return this.P;		
	}
	public double getQ(){		 
		return this.Q;		
	}
	public double getTargetValue(){		 
		return this.targetValue;		
	}
	public double getTransformer_r(){		 
		return this.transformer_r;		
	}
	public double getTransformer_x(){		 
		return this.transformer_x;		
	}
	public String getTransForm_rdfID(){		 
		return this.transForm_rdfID;		
	}	
	public Boolean getState(){		 
		return this.state;		
	}
	public double getStep(){		 
		return this.step;		
	}
	public double getR(){		 
		return this.r;		
	}
	public double getX(){		 
		return this.x;		
	}
	public double getB(){		 
		return this.b;		
	}
	public double getG(){		 
		return this.g;		
	}
	public String getTerminal_ConductingEquipment(){		 
		return this.terminal_ConductingEquipment;		
	}
	public String getConnectivityNode(){		 
		return this.connectivityNode;		
	}

}
