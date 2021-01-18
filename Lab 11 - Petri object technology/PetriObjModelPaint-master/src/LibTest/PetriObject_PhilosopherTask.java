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
public class PetriObject_PhilosopherTask {
     public static void main(String []args)throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        PetriObjModel model = getModel();
        model.setIsProtokol(true);
        double timeModeling = 100.0;
        model.go(timeModeling);
        
        System.out.println(" --- MEAN VALUE OF PHILOSOPHER THINKING ---");
        for(int j =0; j<5; j++) {
            System.out.println(((PetriSim)model.getListObj().get(j)).getNet().getListP()[0].getMean());
        }
              
     
        
    }
    
    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> list = new ArrayList();
        

        for(int i=0; i<5; i++){
            list.add(new PetriSim(NetLibrary.CreateNetPhilosophers_task()));
        } 
        
        for(int k=0; k<5; k++){
            if (k!=4){
                list.get(k).getNet().getListP()[2] = list.get(k+1).getNet().getListP()[1];
            }
            else{
                list.get(k).getNet().getListP()[2] = list.get(0).getNet().getListP()[1];
                //list.get(k).getNet().getListP()[2] = list.get(4).getNet().getListP()[3];
            }
        }
        
        PetriObjModel model = new PetriObjModel(list);
        return model;
    }
}
