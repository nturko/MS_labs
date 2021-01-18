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
public class PetriObjectRobotNet {
    public static void main(String []args)throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        double timeModeling = 100000.0;
        model.go(timeModeling);
        System.out.println(" --- MEAN VALUE OF QUEUE BEFORE MACHINES ---");

        int j;
        for(j = 2; j<5; j+=2) {
            System.out.println(" --- MEAN VALUE OF NOT WORKING MACHINES ---");

        }
        
        System.out.println(" --- MEAN VALUE OF NOT WORKING MACHINES ---");
        for(j = 2; j<5; j+=2) {
            System.out.println(((PetriSim)model.getListObj().get(j)).getNet().getListP()[3].getMean());
        }

        
        Iterator var7 = model.getListObj().iterator();

        while(var7.hasNext()) {
            PetriSim e = (PetriSim)var7.next();
            e.printMark();
        
        }
    }
    
    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> list = new ArrayList();
        list.add(new PetriSim(NetLibrary.CreateObjectIncomeGenerator("exp", 40.0, 0.0)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobot()));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("unif", 60.0, 10.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobot()));
        list.add(new PetriSim(NetLibrary.CreateObjectMachine("exp", 100.0, 0.0, 3)));
        list.add(new PetriSim(NetLibrary.CreateObjectRobot()));
        list.add(new PetriSim(NetLibrary.CreateObjectStorage()));
       
       
        list.get(0).getNet().getListP()[1] = list.get(1).getNet().getListP()[0]; 
        
        int i;
        for(i=1; i<6; ++i){
            list.get(i).getNet().getListP()[2] = list.get(i+1).getNet().getListP()[0];
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
