package Assignment;

//cunstructor to save Bus2Bus connections
public class ConnectedBus2Bus extends ConnectedBus2Line {

	public int bus2Num;		
	public int line2Num;
	
	//extra
	public double r_trafo;
	public double x_trafo;
	public double r_line1;
	public double x_line1;
	public double r_line2;
	public double x_line2;
	public double b_shunt;
	public double g_shunt;
	public double base_volt;
	public double base_S;
	public int matrix_size;
	
	//Creating a constructor
	public ConnectedBus2Bus(){		
		this.bus2Num=-1;	
		this.line2Num=-1;
		
	}
	//set.RdfID Method			
	public void setBus2Num(int bus2Num_1){		 
		this.bus2Num=bus2Num_1;		
	}				
	public void setLine2Num(int line2Num_1){		 
		this.line2Num=line2Num_1;		
	}
	public void setR_trafo(double r_trafo_1){		 
		this.r_trafo=r_trafo_1;		
	}	
	public void setX_trafo(double x_trafo_1){		 
		this.x_trafo=x_trafo_1;		
	}
	public void setR_line1(double r_line1_1){		 
		this.r_line1=r_line1_1;		
	}	
	public void setX_line1(double x_line1_1){		 
		this.x_line1=x_line1_1;		
	}
	public void setR_line2(double r_line2_1){		 
		this.r_line2=r_line2_1;		
	}	
	public void setX_line2(double x_line2_1){		 
		this.x_line2=x_line2_1;		
	}
	public void setB_shunt(double b_shunt_1){		 
		this.b_shunt=b_shunt_1;		
	}
	public void setG_shunt(double g_shunt_1){		 
		this.g_shunt=g_shunt_1;		
	}
	public void setBase_volt(double base_volt_1){		 
		this.base_volt=base_volt_1;		
	}
	public void setBase_S(double base_S_1){		 
		this.base_S=base_S_1;		
	}
	public void setMatrix_size(int matrix_size_1){		 
		this.matrix_size=matrix_size_1;		
	}
	
	
		
	//get.RdfID Method
	public int getBus2Num(){		 
		return this.bus2Num;		
	}	
	public int getLine2Num(){		 
		return this.line2Num;		
	}
	public double getR_trafo(){		 
		return this.r_trafo;		
	}
	public double getX_trafo(){		 
		return this.x_trafo;		
	}
	public double getR_Line1(){		 
		return this.r_line1;		
	}
	public double getX_Line1(){		 
		return this.x_line1;		
	}
	public double getR_Line2(){		 
		return this.r_line2;		
	}
	public double getX_Line2(){		 
		return this.x_line2;		
	}
	public double getBase_volt(){		 
		return this.base_volt;		
	}
	public double getBase_S(){		 
		return this.base_S;		
	}
	public int getMatrix_size(){		 
		return this.matrix_size;		
	}	
	public double getB_Shunt(){		 
		return this.b_shunt;		
	}
	public double getG_Shunt(){		 
		return this.g_shunt;		
	}
}