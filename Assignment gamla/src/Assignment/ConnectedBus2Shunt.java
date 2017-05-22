package Assignment;

	public class ConnectedBus2Shunt extends ConnectedBus2Trafo {
		
		public int shuntNum;	
		public double b_shunt;
		public double g_shunt;
		
		//Creating a constructor
		public ConnectedBus2Shunt(){		
			this.shuntNum=-1;		
			
		}
		//set.RdfID Method			
		public void setShuntNum(int shuntNum_1){		 
			this.shuntNum=shuntNum_1;		
		}
		public void setB_shunt(double b_shunt_1){		 
			this.b_shunt=b_shunt_1;		
		}
		public void setG_shunt(double g_shunt_1){		 
			this.g_shunt=g_shunt_1;		
		}
			
		//get.RdfID Method
		public int getShuntNum(){		 
			return this.shuntNum;		
		}
		public double getB_Shunt(){		 
			return this.b_shunt;		
		}
		public double getG_Shunt(){		 
			return this.g_shunt;		
		}
}