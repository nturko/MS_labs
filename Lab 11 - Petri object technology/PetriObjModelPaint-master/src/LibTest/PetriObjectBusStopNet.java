/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibTest;
import LibNet.NetLibrary;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriObjModel;
import PetriObj.PetriSim;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author nturk
 */
public class PetriObjectBusStopNet {
    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        double timeModeling = 1000.0;
        model.go(timeModeling);
        Iterator var6 = model.getListObj().iterator();
        
        System.out.println("Money that company had earn: " + ((PetriSim)model.getListObj().get(5)).getNet().getListP()[5].getMark()*20);
        System.out.println("Money that company had lost: " + ((PetriSim)model.getListObj().get(2)).getNet().getListP()[2].getMark()*20);
        while(var6.hasNext()) {
            PetriSim e = (PetriSim)var6.next();
            e.printMark();
        }

    }

    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> list = new ArrayList();
        list.add(new PetriSim(NetLibrary.CreateObjectIncomeGenerator("unif", 0.05, 0.02)));
        list.add(new PetriSim(NetLibrary.CreateObjectIncomeGenerator("unif", 0.05, 0.02)));
        list.add(new PetriSim(NetLibrary.CreateObjectDecisionMaking()));
        list.add(new PetriSim(NetLibrary.CreateObjectDecisionMaking()));
        list.add(new PetriSim(NetLibrary.CreateObjectBus(10, "unif", 20.0, 5.0, "unif", 5.0, 1.0)));
        list.add(new PetriSim(NetLibrary.CreateObjectBus(10, "unif", 30.0, 5.0, "unif", 5.0, 1.0)));

        
        int i;
        for(i=0; i<2; ++i){
            list.get(i).getNet().getListP()[1] = list.get(i+2).getNet().getListP()[0]; //generator --> DecisionMaking
        }
        
        int j;
        for(j=2; j<4; ++j){
            list.get(j).getNet().getListP()[1] = list.get(j+2).getNet().getListP()[0]; //DecisionMaking Queue --> General Queue
        }
        
        
        list.get(4).getNet().getListP()[4] = list.get(5).getNet().getListP()[3];
        list.get(5).getNet().getListP()[4] = list.get(4).getNet().getListP()[3];
        
        
        list.get(4).getNet().getListP()[5] = list.get(5).getNet().getListP()[5];


        PetriObjModel model = new PetriObjModel(list);
        return model;
    }
}
