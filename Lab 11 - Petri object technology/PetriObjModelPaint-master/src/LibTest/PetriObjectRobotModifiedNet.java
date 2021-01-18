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


import java.util.ArrayList;
/**
 *
 * @author nturko
 */
public class PetriObjectRobotModifiedNet {
    public static void main(String []args)throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        double timeModeling = 100000.0;
        model.go(timeModeling);
        System.out.println(" --- MEAN VALUE OF QUEUE BEFORE MACHINES ---");

        int j;
        for(j = 2; j<9; j+=2) {
            System.out.println(((PetriSim)model.getListObj().get(j)).getNet().getListP()[0].getMean());
        }
        
        System.out.println(" --- MEAN VALUE OF NOT WORKING MACHINES ---");
        for(j = 2; j<9; j+=2) {
            System.out.println(((PetriSim)model.getListObj().get(j)).getNet().getListP()[3].getMean());
        }

        Iterator var12 = model.getListObj().iterator();

        while(var12.hasNext()) {
            PetriSim e = (PetriSim)var12.next();
            e.printMark();
        
        }
    }
    
    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> list = new ArrayList();
        list.add(new PetriSim(NetLibrary.CreateObjectIncomeGenerator("exp", 60.0, 0.0)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotPull(8.0, 10.0, 2.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("unif", 60.0, 10.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotPull(8.0, 10.0, 2.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("unif", 60.0, 10.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotPull(8.0, 10.0, 2.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("exp", 100.0, 0.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotPull(8.0, 10.0, 2.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("exp", 100.0, 0.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotPull(8.0, 10.0, 2.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectStorage()));
        list.add(new PetriSim(NetLibrary.CreateObjectRobotResource(3)));
        
        int i;
        for(i=1; i<10; i+=2){
            list.get(11).getNet().getListP()[0] = list.get(i).getNet().getListP()[3];
        }
       
        
        list.get(0).getNet().getListP()[1] = list.get(1).getNet().getListP()[0];
        
        int j;
        for(j=1; j<10; ++j){
            list.get(j).getNet().getListP()[2] = list.get(j+1).getNet().getListP()[0];
        }
        /*
        list.get(1).getNet().getListP()[2] = list.get(2).getNet().getListP()[0];
        list.get(2).getNet().getListP()[2] = list.get(3).getNet().getListP()[0];
        list.get(3).getNet().getListP()[2] = list.get(4).getNet().getListP()[0];
        list.get(4).getNet().getListP()[2] = list.get(5).getNet().getListP()[0];
        list.get(5).getNet().getListP()[2] = list.get(6).getNet().getListP()[0];
        */
     
        PetriObjModel model = new PetriObjModel(list);
        return model;
    }
}
