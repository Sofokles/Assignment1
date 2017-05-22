

import java.util.ArrayList;

import Assignment.EnergyConsumer;
import Assignment.PowerTransformer;
import Assignment.SynchronousMachine;

public class Extra {
	for (int i=0; i<BusbarList.size(); i++){
		String eq_con_rdfID1 = BusbarList.get(i).getEq_con_rdfID();	
		boolean test = containsLoad(EnergyConsumerList, eq_con_rdfID1);
		boolean test2 = containsSynch(SynchronousMachineList, eq_con_rdfID1);
		boolean test3 = containsPT(PowerTransformerList, eq_con_rdfID1);
		
		if ( test==false && test2==false && test3==false ) {
			//BusbarList.remove(i);
			System.out.println(BusbarList.get(i).getName());
		}
	}
	public static boolean containsSynch(ArrayList<SynchronousMachine> SynchronousMachineList, String targetValue) {
		for (int i=0; i<SynchronousMachineList.size(); i++){
			if (SynchronousMachineList.get(i).getEq_con_rdfID().contains(targetValue))
				return true;
		}
		return false;
	}
	public static boolean containsBus(ArrayList<BusbarList> BusbarListList, String targetValue, int i_start) {
		for (int i=i_start+1; i<BusbarListList.size()-1; i++){
			if (BusbarListList.get(i).getEq_con_rdfID().contains(targetValue))
				return true;
		}
		return false;
	}
	public static boolean containsLoad(ArrayList<EnergyConsumer> EnergyConsumerList, String targetValue) {
		for (int i=0; i<EnergyConsumerList.size(); i++){
			if (EnergyConsumerList.get(i).getEq_con_rdfID().contains(targetValue))
				return true;
		}
		return false;
	}
	public static boolean containsPT(ArrayList<PowerTransformer> PowerTransformerList, String targetValue) {
		for (int i=0; i<PowerTransformerList.size(); i++){
			if (PowerTransformerList.get(i).getEq_con_rdfID().contains(targetValue))
				return true;
		}
		return false;
	}
	
	rdfID: _64901aec-5a8a-4bcb-8ca7-a3ddbfcd0e6c; Name: BE-Busbar_1; Eq_con_rdfID: _469df5f7-058f-4451-a998-57a48e8a56fe
	rdfID: _ef45b632-3028-4afe-bc4c-a4fa323d83fe; Name: BE-Busbar_2; Eq_con_rdfID: _d0486169-2205-40b2-895e-b672ecb9e5fc
	rdfID: _5caf27ed-d2f8-458a-834a-6b3193a982e6; Name: BE-Busbar_3; Eq_con_rdfID: _b10b171b-3bc5-4849-bb1f-61ed9ea1ec7c
	rdfID: _fd649fe1-bdf5-4062-98ea-bbb66f50402d; Name: BE-Busbar_4; Eq_con_rdfID: _4ba71b59-ee2f-450b-9f7d-cc2f1cc5e386
	rdfID: _364c9ca2-0d1d-4363-8f46-e586f8f66a8c; Name: BE-Busbar_6; Eq_con_rdfID: _8bbd7e74-ae20-4dce-8780-c20f8e18c2e0
	rdfID: _63f25be7-7592-4cf1-8401-5772046ef2ae; Name: N1230992288; Eq_con_rdfID: _b10b171b-3bc5-4849-bb1f-61ed9ea1ec7c
	rdfID: _c8ce5e08-5ee3-42d9-aa44-5792db252d9f; Name: N1230992291; Eq_con_rdfID: _b10b171b-3bc5-4849-bb1f-61ed9ea1ec7c
	rdfID: _8da0ff82-2f23-4231-ac9b-28b9c9141432; Name: N1230992411; Eq_con_rdfID: _d0486169-2205-40b2-895e-b672ecb9e5fc
	rdfID: _d6986ea6-fadc-4113-806a-a8f95f62c216; Name: N1230992414; Eq_con_rdfID: _d0486169-2205-40b2-895e-b672ecb9e5fc
	
	//System.out.println(matrix_size);
	//DO YOU WANT TO PRINT TRAFO CONNECTIONS?
	int p1=0;//if p1=1 it prints...
	if (p1==1){
		//print ConnectedBus2TrafoList
		for (int i = 0; i <ConnectedBus2TrafoList.size(); i++) {								
			
			int busNum = ConnectedBus2TrafoList.get(i).getBusNum();	
			int trafoNum = ConnectedBus2TrafoList.get(i).getTrafoNum();
			int pp = ConnectedBus2TrafoList.size();
			
			System.out.println("busNum: " + busNum + "; trafoNum: " + trafoNum + "; size" + pp);
		}
	
		//print ConnectedBus2LineList
		for (int i = 0; i <ConnectedBus2LineList.size(); i++) {								
		
			int busNum = ConnectedBus2LineList.get(i).getBusNum();	
			int lineNum = ConnectedBus2LineList.get(i).getLineNum();
			int pp = ConnectedBus2LineList.size();
			
			String name = BusbarList.get(busNum).getName();
			String name2 = ACLineSegmentList.get(lineNum).getName();
			
			System.out.println("busNum: " + busNum + name + "; lineNum: " + lineNum + name2 + "; size" + pp);	
		}
	}

}
