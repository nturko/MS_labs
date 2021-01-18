package LibNet;

import PetriObj.ArcIn;
import PetriObj.ArcOut;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriNet;
import PetriObj.PetriP;
import PetriObj.PetriT;
import java.util.ArrayList;
import java.util.Random;
public class NetLibrary {
    
/**
     * Creates Petri net that describes the dynamics of system of the mass
     * service (with unlimited queue)
     *
     * @param numChannel the quantity of devices
     * @param timeMean the mean value of service time of unit
     * @param name the individual name of SMO
     * @throws ExceptionInvalidTimeDelay if one of net's transitions has no input position.
     * @return Petri net dynamics of which corresponds to system of mass service with given parameters
     * @throws PetriObj.ExceptionInvalidNetStructure
     */
public static PetriNet CreateNetSMOwithoutQueue(int numChannel, double timeMean, String name) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure{
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",0));
	d_P.add(new PetriP("P2",numChannel));
	d_P.add(new PetriP("P3",0));
	d_T.add(new PetriT("T1",timeMean,Double.MAX_VALUE));
	d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
	d_T.get(0).setParamDeviation(0.0);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
	PetriNet d_Net = new PetriNet("SMOwithoutQueue"+name,d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

 /**
     * Creates Petri net that describes the dynamics of arrivals of demands for
     * service
     *
     * @param timeMean mean value of interval between arrivals
     * @return Petri net dynamics of which corresponds to generator
     * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
     * @throws PetriObj.ExceptionInvalidNetStructure
     */
public static PetriNet CreateNetGenerator(double timeMean) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure{
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_T.add(new PetriT("T1", timeMean,Double.MAX_VALUE));
	d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
	d_T.get(0).setParamDeviation(0.0);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
	PetriNet d_Net = new PetriNet("Generator",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
 /**
     * Creates Petri net that describes the route choice with given
     * probabilities
     *
     * @param p1 the probability of choosing the first route
     * @param p2 the probability of choosing the second route
     * @param p3 the probability of choosing the third route
     * @return Petri net dynamics of which corresponds to fork of routs
     * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
     * @throws PetriObj.ExceptionInvalidNetStructure
     */
public static PetriNet CreateNetFork(double p1, double p2, double p3) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure{
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",0));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_T.add(new PetriT("T1",0.0,Double.MAX_VALUE));
	d_T.get(0).setProbability(p1);
	d_T.add(new PetriT("T2",0.0,Double.MAX_VALUE));
	d_T.get(1).setProbability(p2);
	d_T.add(new PetriT("T3",0.0,Double.MAX_VALUE));
	d_T.get(2).setProbability(p3);
	d_T.add(new PetriT("T4",0.0,Double.MAX_VALUE));
	d_T.get(3).setProbability(1-p1-p2-p3);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(3),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(4),1));
	PetriNet d_Net = new PetriNet("Fork",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
   /**
     * Creates Petri net that describes the route choice with given
     * probabilities
     *
     * @param numOfWay quantity of possibilities in choice ("ways")
     * @param probabilities set of values probabilities for each "way"
     * @return Petri net dynamics of which corresponds to fork of routs
     * @throws PetriObj.ExceptionInvalidTimeDelay if Petri net has invalid structure
     * @throws PetriObj.ExceptionInvalidNetStructure
     */
    public static PetriNet CreateNetFork(int numOfWay, double[] probabilities) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {

        ArrayList<PetriP> d_P = new ArrayList<>();
        ArrayList<PetriT> d_T = new ArrayList<>();
        ArrayList<ArcIn> d_In = new ArrayList<>();
        ArrayList<ArcOut> d_Out = new ArrayList<>();

        d_P.add(new PetriP("P0", 0));
        for (int j = 0; j < numOfWay; j++) {
            d_P.add(new PetriP("P" + (j + 1), 0));
        }

        for (int j = 0; j < numOfWay; j++) {
            d_T.add(new PetriT("вибір маршруту " + (j + 1), 0));
        }
        for (int j = 0; j < numOfWay; j++) {
            d_T.get(j).setProbability(probabilities[j]);
        }

        for (int j = 0; j < numOfWay; j++) {
            d_In.add(new ArcIn(d_P.get(0), d_T.get(j), 1));
        }

        for (int j = 0; j < numOfWay; j++) {
            d_Out.add(new ArcOut(d_T.get(j), d_P.get(j + 1), 1));
        }

        PetriNet d_Net = new PetriNet("Fork ", d_P, d_T, d_In, d_Out);

        PetriP.initNext();
        PetriT.initNext();
        ArcIn.initNext();
        ArcOut.initNext();

        return d_Net;
    }    

public static PetriNet CreateNetMalware() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("NewPacket",0));
	d_P.add(new PetriP("Firewall",0));//p1
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("WebServer",0));//p3
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("FileServer",0));//p6
	d_P.add(new PetriP("P7",0));
	d_P.add(new PetriP("OS",0));//p8
	d_P.add(new PetriP("P9",0));
	d_P.add(new PetriP("VulnOfWall",1));
	d_P.add(new PetriP("VulnOfWebServ",1));
	d_P.add(new PetriP("VulnOfFileServ",1));
	d_P.add(new PetriP("VulnOfOS",1));
	d_P.add(new PetriP("VulnOfAuthorization",1));
	d_P.add(new PetriP("P15",0));
	d_P.add(new PetriP("P16",0));
	d_P.add(new PetriP("Success",0));  //p17
	d_P.add(new PetriP("Alarm",0));
	d_T.add(new PetriT("NetworkAccessControl",1.0));
	d_T.add(new PetriT("Authorization",0.0));
	d_T.get(1).setPriority(1);
	d_T.get(1).setProbability(0.5);
	d_T.add(new PetriT("RunWS",1.0));
	d_T.get(2).setPriority(1);
	d_T.get(2).setProbability(0.5);
	d_T.add(new PetriT("RunFS",1.0));
	d_T.get(3).setPriority(1);
	d_T.get(3).setProbability(0.5);
	d_T.add(new PetriT("RunOS",1.0));
	d_T.get(4).setPriority(1);
	d_T.get(4).setProbability(0.5);
	d_T.add(new PetriT("T1",0.0));
	d_T.add(new PetriT("T2",0.0));
	d_T.add(new PetriT("T3",0.0));
	d_T.add(new PetriT("T4",0.0));
	d_T.get(8).setPriority(1);
	d_T.get(8).setProbability(0.5);
	d_T.add(new PetriT("T5",0.0));
	d_T.get(9).setPriority(1);
	d_T.get(9).setProbability(0.5);
	d_T.add(new PetriT("T6",0.0));
	d_T.get(10).setPriority(1);
	d_T.get(10).setProbability(0.5);
	d_T.add(new PetriT("T7",0.0));
	d_T.get(11).setPriority(1);
	d_T.get(11).setProbability(0.5);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(6),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(8),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(10),d_T.get(0),1));
	d_In.get(10).setInf(true);
	d_In.add(new ArcIn(d_P.get(12),d_T.get(3),1));
	d_In.get(11).setInf(true);
	d_In.add(new ArcIn(d_P.get(14),d_T.get(1),1));
	d_In.get(12).setInf(true);
	d_In.add(new ArcIn(d_P.get(11),d_T.get(2),1));
	d_In.get(13).setInf(true);
	d_In.add(new ArcIn(d_P.get(13),d_T.get(4),1));
	d_In.get(14).setInf(true);
	d_In.add(new ArcIn(d_P.get(8),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(9),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(15),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(16),d_T.get(7),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(8),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(11),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(10),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(9),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(8),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(9),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(15),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(16),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(17),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(18),1));
	d_Out.add(new ArcOut(d_T.get(11),d_P.get(18),1));
	d_Out.add(new ArcOut(d_T.get(10),d_P.get(18),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(18),1));
	PetriNet d_Net = new PetriNet("malware",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
public static PetriNet CreateNetAdmin() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("User",1));
	d_P.add(new PetriP("P1",0));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("Damaged",0));
	d_P.add(new PetriP("Harmless",0));
	d_P.add(new PetriP("Executed",0));
	d_P.add(new PetriP("NewPacket",0));
	d_P.add(new PetriP("Admin",1));
	d_P.add(new PetriP("FileServer",0));
	d_P.add(new PetriP("Alarm",0));
	d_P.add(new PetriP("P10",0));
	d_T.add(new PetriT("DoTest",1.0));
	d_T.add(new PetriT("ControlTime",10.0));
	d_T.add(new PetriT("T3",0.0));
	d_T.add(new PetriT("T4",0.0));
	d_T.add(new PetriT("RepairResources",0.0));
	d_T.add(new PetriT("T2",0.0));
	d_T.add(new PetriT("T3",1.0));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(9),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(10),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(6),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(8),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(10),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(0),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(10),1));
	PetriNet d_Net = new PetriNet("admin",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
public static PetriNet CreateNetTest3() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",100));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P3",0));
	d_T.add(new PetriT("T1",0.0));
	d_T.add(new PetriT("T2",0.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	PetriNet d_Net = new PetriNet("test3",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetGenerator2(double d) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_T.add(new PetriT("T1",d));
	d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
	d_T.get(0).setParamDeviation(0.0);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
	PetriNet d_Net = new PetriNet("Generator",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetThread3() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("bow{",0));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("lockA",1));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("P6",0));
	d_P.add(new PetriP("failure++",0));
	d_P.add(new PetriP("lockB",1));
	d_P.add(new PetriP("bowA++",0));
	d_P.add(new PetriP("P10",0));
	d_P.add(new PetriP("bowB++",0));
	d_P.add(new PetriP("P15",0));
	d_P.add(new PetriP("bowLoop{",100));
	d_P.add(new PetriP("bow",0));
	d_P.add(new PetriP("Core",1));
	d_T.add(new PetriT("imp{",0.1));
	d_T.add(new PetriT("tryLockA",0.0));
	d_T.add(new PetriT("0&?",0.0));
	d_T.add(new PetriT("tryLockB",0.0));
	d_T.add(new PetriT("bowBack{",0.1));
	d_T.add(new PetriT("unlockA",0.0));
	d_T.add(new PetriT("0&1",0.0));
	d_T.add(new PetriT("failure",0.0));
	d_T.add(new PetriT("unlockAB",0.1));
	d_T.add(new PetriT("unlockB",0.1));
	d_T.add(new PetriT("for{",0.0));
	d_T.add(new PetriT("for",0.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(7),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(9),d_T.get(8),1));
	d_In.add(new ArcIn(d_P.get(11),d_T.get(9),1));
	d_In.add(new ArcIn(d_P.get(12),d_T.get(10),1));
	d_In.add(new ArcIn(d_P.get(13),d_T.get(11),1));
	d_In.add(new ArcIn(d_P.get(14),d_T.get(10),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(9),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(8),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(10),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(11),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(10),d_P.get(0),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(11),d_P.get(14),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(6),1));
        
	PetriNet d_Net = new PetriNet("friendThread",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetConsistency(double forDelay, double readDelay, double modifyDelay, double writeDelay) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1000000));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",1));
        d_P.add(new PetriP("readPermission",1));
	d_P.add(new PetriP("writePermission",1));
        d_P.add(new PetriP("Cores",2));
	d_T.add(new PetriT("for",forDelay));
        d_T.get(0).setDistribution("norm", d_T.get(0).getTimeServ());
        d_T.get(0).setParamDeviation(0.1);
	d_T.add(new PetriT("read",readDelay));
	d_T.add(new PetriT("modify",modifyDelay));
	d_T.add(new PetriT("write",writeDelay));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(6),d_T.get(3),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(6),1));
        
        
	PetriNet d_Net = new PetriNet("Consistency",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetFriend() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	double delay = 100.0;
        double x=0.00000001;
    ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
        Random r = new Random();
	d_P.add(new PetriP("bow[",0));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("lock",1));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("P6",0));
	d_P.add(new PetriP("failure++",0));
	d_P.add(new PetriP("lockOther",1));
	d_P.add(new PetriP("bowA++",0));
	d_P.add(new PetriP("P10",0));
	d_P.add(new PetriP("bowB++",0));
	d_P.add(new PetriP("P15",0));
	d_P.add(new PetriP("bowLoop[",10));
	d_P.add(new PetriP("bow]",0));
	d_P.add(new PetriP("Core",1));
	d_T.add(new PetriT("imp[",delay*x));// was delay
        d_T.get(0).setDistribution("norm", d_T.get(0).getTimeServ()*0.1);
	d_T.add(new PetriT("tryLockA",delay*x,1));//priority = 1
	d_T.get(1).setDistribution("norm", d_T.get(1).getTimeServ()*0.1);
        d_T.add(new PetriT("0&?",0.0));
	d_T.add(new PetriT("tryLockB",delay*x,1));//priority = 1
	d_T.get(3).setDistribution("norm", d_T.get(3).getTimeServ()*0.1);
        d_T.add(new PetriT("bowBack[]",delay)); //delay*x
	d_T.get(4).setDistribution("norm", d_T.get(4).getTimeServ()*0.1);
        d_T.add(new PetriT("unlockA",0.0));
	d_T.add(new PetriT("0&1",0.0,1)); //priority = 1
	d_T.add(new PetriT("failure",0.0));
	d_T.add(new PetriT("unlockAB",0.0));
	d_T.add(new PetriT("unlockB",0.0));
	d_T.add(new PetriT("for[",delay)); //sleep
	d_T.get(10).setDistribution("norm", d_T.get(10).getTimeServ()*0.1);
        d_T.add(new PetriT("for]",0.0+r.nextDouble()));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(7),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(9),d_T.get(8),1));
	d_In.add(new ArcIn(d_P.get(11),d_T.get(9),1));
	d_In.add(new ArcIn(d_P.get(12),d_T.get(10),1));
	d_In.add(new ArcIn(d_P.get(13),d_T.get(11),1));
	d_In.add(new ArcIn(d_P.get(14),d_T.get(10),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(9),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(8),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(10),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(11),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(10),d_P.get(0),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(11),d_P.get(14),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(6),1));
	PetriNet d_Net = new PetriNet("Friend ",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetFriendUsingCores() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	double delay = 100.0;
        double x=0.8;
        ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
        Random r = new Random();
	d_P.add(new PetriP("bow[",0));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("lock",1));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("P6",0));
	d_P.add(new PetriP("failure++",0));
	d_P.add(new PetriP("lockOther",1));
	d_P.add(new PetriP("bowA++",0));
	d_P.add(new PetriP("P10",0));
	d_P.add(new PetriP("bowB++",0));
	d_P.add(new PetriP("P15",0));
	d_P.add(new PetriP("bowLoop[",10));
	d_P.add(new PetriP("bow]",0));
	d_P.add(new PetriP("Core",10));
	d_T.add(new PetriT("imp[",delay*x));// was delay
        d_T.get(0).setDistribution("norm", d_T.get(0).getTimeServ()*0.1);
	d_T.add(new PetriT("tryLockA",delay*x,1));//priority = 1
	d_T.get(1).setDistribution("norm", d_T.get(1).getTimeServ()*0.1);
        d_T.add(new PetriT("0&?",0.0));
	d_T.add(new PetriT("tryLockB",delay*x,1));//priority = 1
	d_T.get(3).setDistribution("norm", d_T.get(3).getTimeServ()*0.1);
        d_T.add(new PetriT("bowBack[]",delay)); //delay*x
	d_T.get(4).setDistribution("norm", d_T.get(4).getTimeServ()*0.1);
        d_T.add(new PetriT("unlockA",0.0));
	d_T.add(new PetriT("0&1",0.0,1)); //priority = 1
	d_T.add(new PetriT("failure",0.0));
	d_T.add(new PetriT("unlockAB",0.0));
	d_T.add(new PetriT("unlockB",0.0));
	d_T.add(new PetriT("for[",delay)); //sleep
	d_T.get(10).setDistribution("norm", d_T.get(10).getTimeServ()*0.1);
        d_T.add(new PetriT("for]",0.0+r.nextDouble()));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(5),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(7),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(6),1));
	d_In.add(new ArcIn(d_P.get(9),d_T.get(8),1));
	d_In.add(new ArcIn(d_P.get(11),d_T.get(9),1));
	d_In.add(new ArcIn(d_P.get(12),d_T.get(10),1));
	d_In.add(new ArcIn(d_P.get(13),d_T.get(11),1));
	d_In.add(new ArcIn(d_P.get(14),d_T.get(10),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(9),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(8),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(10),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(6),d_P.get(11),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(10),d_P.get(0),1));
	d_Out.add(new ArcOut(d_T.get(8),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(11),d_P.get(14),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(7),d_P.get(13),1));
	d_Out.add(new ArcOut(d_T.get(9),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(6),1));

	PetriNet d_Net = new PetriNet("Friend ",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}


public static PetriNet CreateNetNewPool() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("tasksQueue",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("end",0));
	d_P.add(new PetriP("P7",0));
	d_P.add(new PetriP("numWorkers",0));
	d_T.add(new PetriT("T1",0.1));
	d_T.add(new PetriT("newRunnable",0.1));
	d_T.add(new PetriT("invoke",0.1));
	d_T.add(new PetriT("run",0.2));
	d_T.add(new PetriT("T5",0.1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(4),5));
	d_In.add(new ArcIn(d_P.get(6),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(6),2));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(7),5));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(5),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(1),1));
	PetriNet d_Net = new PetriNet("NewPool",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetThreadStartAndEnd() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",0));
	d_P.add(new PetriP("P6",0));
	d_P.add(new PetriP("P7",0));
	d_P.add(new PetriP("P8",0));
	d_P.add(new PetriP("P9",0));
	d_T.add(new PetriT("new",0.1));
	d_T.add(new PetriT("start",0.1));
	d_T.add(new PetriT("runStart",0.1));
	d_T.add(new PetriT("runFinish",0.1));
	d_T.add(new PetriT("end",0.1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(6),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(4),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(8),1));
	PetriNet d_Net = new PetriNet("ThreadStartAndEnd",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateNetTestInfArc() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",1));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("P4",0));
	d_T.add(new PetriT("T1",1.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(0),1));
	d_In.get(1).setInf(true);
	d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(3),1));
	PetriNet d_Net = new PetriNet("TestInfArc",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
public static PetriNet CreateNetTestStatistics() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",100));
	d_P.add(new PetriP("P2",1));
	d_P.add(new PetriP("P3",0));
	d_P.add(new PetriP("P4",0));
	d_P.add(new PetriP("P5",1));
	d_T.add(new PetriT("T1",10.0));
	d_T.add(new PetriT("T2",5.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(4),1));
	PetriNet d_Net = new PetriNet("TestStatistics",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
public static PetriNet CreateNetTask(double a) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1000));
	d_P.add(new PetriP("P2",0));
        d_P.add(new PetriP("Resource",0));
	d_T.add(new PetriT("T1",a));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
        for(PetriT tr: d_T){
            d_In.add(new ArcIn(d_P.get(2),tr,1));
            d_Out.add(new ArcOut(tr,d_P.get(2),1));
            
        }
	PetriNet d_Net = new PetriNet("Task",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}


public static PetriNet CreateNetGeneratorInf() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_P.add(new PetriP("P1",0));
	d_T.add(new PetriT("T1",2.0));
	d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
	d_T.get(0).setParamDeviation(0.0);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(0),1));
	d_In.get(1).setInf(true);
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
	PetriNet d_Net = new PetriNet("Generator",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
public static PetriNet CreateNetUntitled() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",100));
	d_P.add(new PetriP("P2",0));
	d_T.add(new PetriT("T1",2.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	PetriNet d_Net = new PetriNet("Untitled",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

/*
Some code for LW #11
----------------------------------
LW #11. Task 3
*/
public static PetriNet CreateObjectIncomeGenerator(String distribution, double timeDelay, double deviation) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("P1",1));
	d_P.add(new PetriP("P2",0));
	d_T.add(new PetriT("Income",timeDelay));

	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
	PetriNet d_Net = new PetriNet("ObjectIncomeGenerator",d_P,d_T,d_In,d_Out);
      
        if (distribution == "exp") {
            ((PetriT)d_T.get(0)).setDistribution(distribution, ((PetriT)d_T.get(0)).getTimeServ());
            
        } else {
            ((PetriT)d_T.get(0)).setDistribution(distribution, ((PetriT)d_T.get(0)).getTimeServ());
            ((PetriT)d_T.get(0)).setParamDeviation(deviation);
        }
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}


public static PetriNet CreateObjectRobot() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue of details",0));
	d_P.add(new PetriP("In motion",0));
	d_P.add(new PetriP("Point of arrival",0));
	d_P.add(new PetriP("Available robot",1));
	d_T.add(new PetriT("Get the detail",8.0));
	d_T.get(0).setDistribution("unif", d_T.get(0).getTimeServ());
	d_T.get(0).setParamDeviation(1.0);
	d_T.add(new PetriT("Take off the detail",10.0));
	d_T.get(1).setDistribution("unif", d_T.get(1).getTimeServ());
	d_T.get(1).setParamDeviation(2.0);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	PetriNet d_Net = new PetriNet("ObjectRobot",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateObjectMachine(String distribution, double timeDelay, double deviation, int availableMachines) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue of details",0));
	d_P.add(new PetriP("In processing",0));
	d_P.add(new PetriP("Release",0));
	d_P.add(new PetriP("Available machines", availableMachines));
	d_T.add(new PetriT("Start manufacture",timeDelay));
	d_T.add(new PetriT("End manufacture",0.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	PetriNet d_Net = new PetriNet("ObjectMachine",d_P,d_T,d_In,d_Out);
        if (distribution == "exp") {
            ((PetriT)d_T.get(0)).setDistribution(distribution, ((PetriT)d_T.get(0)).getTimeServ());
            
        } else {
            ((PetriT)d_T.get(0)).setDistribution(distribution, ((PetriT)d_T.get(0)).getTimeServ());
            ((PetriT)d_T.get(0)).setParamDeviation(deviation);
        }
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateObjectStorage(){
        ArrayList<PetriP> d_P = new ArrayList<>();
        d_P.add(new PetriP("Full processed details",0));
        PetriNet d_Net = new PetriNet("ObjectStorage",d_P);
        PetriP.initNext();
        return d_Net;
}

//modified robot pull with special room
public static PetriNet CreateObjectRobotPull(double timeDelay1, double timeDelay2, double deviation, int robots) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue of details",0));
	d_P.add(new PetriP("In motion",0));
	d_P.add(new PetriP("Point of arrival",0));
	d_P.add(new PetriP("Available robots",robots));
	d_T.add(new PetriT("Get the detail",timeDelay1));
	d_T.get(0).setDistribution("exp", d_T.get(0).getTimeServ());
	d_T.add(new PetriT("Take off the detail",timeDelay2));
	d_T.get(1).setDistribution("unif", d_T.get(1).getTimeServ());
	d_T.get(1).setParamDeviation(deviation);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	PetriNet d_Net = new PetriNet("RobotPull",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateObjectRobotResource(int sizePull){
        ArrayList<PetriP> d_P = new ArrayList<>();
        d_P.add(new PetriP("Available robots from pull",sizePull));
        PetriNet d_Net = new PetriNet("ObjectRobotsPull",d_P);
        PetriP.initNext();
        return d_Net;
}

//LW #11. Task 4

public static PetriNet CreateObjectDecisionMaking() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue of incomed",0));
	d_P.add(new PetriP("General queue",0));
	d_P.add(new PetriP("Lost clients",0));
	d_T.add(new PetriT("Get in general queue",0.0));
	d_T.add(new PetriT("Leave station",0.0));
	d_T.get(1).setPriority(2);
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),40));
	d_In.get(2).setInf(true);
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
	PetriNet d_Net = new PetriNet("ObjectDecisionMaking",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}

public static PetriNet CreateObjectBus(int busInt,String distribution1, double timeDelay1, double deviation1,
        String distribution2, double timeDelay2, double deviation2) 
        throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue",0));
	d_P.add(new PetriP("Available busses in X",busInt));
	d_P.add(new PetriP("Ready to go",0));
        d_P.add(new PetriP("Bus in X",0));
        d_P.add(new PetriP("Bus in Y",0));
	d_P.add(new PetriP("Wealth",0));
	d_T.add(new PetriT("Road", timeDelay1));
	d_T.add(new PetriT("Arrival in X",timeDelay2));
	d_T.add(new PetriT("Take places in bus",0.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(2),35));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(4),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(5),35));
	PetriNet d_Net = new PetriNet("BusMovement",d_P,d_T,d_In,d_Out);
        if (distribution1 == "exp") {
            ((PetriT)d_T.get(0)).setDistribution(distribution1, ((PetriT)d_T.get(0)).getTimeServ());
            
        } else {
            ((PetriT)d_T.get(0)).setDistribution(distribution1, ((PetriT)d_T.get(0)).getTimeServ());
            ((PetriT)d_T.get(0)).setParamDeviation(deviation1);
        }
        if (distribution2 == "exp") {
            ((PetriT)d_T.get(1)).setDistribution(distribution2, ((PetriT)d_T.get(1)).getTimeServ());
            
        } else {
            ((PetriT)d_T.get(1)).setDistribution(distribution2, ((PetriT)d_T.get(1)).getTimeServ());
            ((PetriT)d_T.get(1)).setParamDeviation(deviation2);
        }
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}


    public static PetriNet CreateObjectBusMovement(int places, double timeDelay1, double deviation1, double timeDelay2, double deviation2, int priority) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
        ArrayList<PetriP> d_P = new ArrayList();
        ArrayList<PetriT> d_T = new ArrayList();
        ArrayList<ArcIn> d_In = new ArrayList();
        ArrayList<ArcOut> d_Out = new ArrayList();
        d_P.add(new PetriP("Queue for A", 0));
        d_P.add(new PetriP("Take places in bus", 0));
        d_P.add(new PetriP("In road", 0));
        d_P.add(new PetriP("Bus in B", 0));
        d_P.add(new PetriP("Queue for B", 0));
        d_P.add(new PetriP("Take places in bus", 0));
        d_P.add(new PetriP("In road", 0));
        d_P.add(new PetriP("Available busses in А", 1));
        d_P.add(new PetriP("Wealth", 0));
        d_T.add(new PetriT("To bus", 0.0, priority));
        d_T.add(new PetriT("Move from", 0.0));
        d_T.add(new PetriT("Take off", 0.0));
        d_T.add(new PetriT("To bus", 0.0, priority));
        d_T.add(new PetriT("Move from", 0.0));
        d_T.add(new PetriT("Take off", 0.0));
        d_In.add(new ArcIn((PetriP)d_P.get(0), (PetriT)d_T.get(0), places));
        d_In.add(new ArcIn((PetriP)d_P.get(7), (PetriT)d_T.get(0), 1));
        d_In.add(new ArcIn((PetriP)d_P.get(1), (PetriT)d_T.get(1), 1));
        d_In.add(new ArcIn((PetriP)d_P.get(2), (PetriT)d_T.get(2), 1));
        d_In.add(new ArcIn((PetriP)d_P.get(3), (PetriT)d_T.get(3), 1));
        d_In.add(new ArcIn((PetriP)d_P.get(4), (PetriT)d_T.get(3), places));
        d_In.add(new ArcIn((PetriP)d_P.get(5), (PetriT)d_T.get(4), 1));
        d_In.add(new ArcIn((PetriP)d_P.get(6), (PetriT)d_T.get(5), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(0), (PetriP)d_P.get(1), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(1), (PetriP)d_P.get(2), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(1), (PetriP)d_P.get(8), 20 * places));
        d_Out.add(new ArcOut((PetriT)d_T.get(2), (PetriP)d_P.get(3), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(3), (PetriP)d_P.get(5), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(4), (PetriP)d_P.get(6), 1));
        d_Out.add(new ArcOut((PetriT)d_T.get(4), (PetriP)d_P.get(8), 20 * places));
        d_Out.add(new ArcOut((PetriT)d_T.get(5), (PetriP)d_P.get(7), 1));
        ((PetriT)d_T.get(1)).setDistribution("unif", timeDelay1);
        ((PetriT)d_T.get(1)).setParamDeviation(deviation1);
        ((PetriT)d_T.get(2)).setDistribution("unif", timeDelay2);
        ((PetriT)d_T.get(2)).setParamDeviation(deviation2);
        ((PetriT)d_T.get(4)).setDistribution("unif", timeDelay1);
        ((PetriT)d_T.get(4)).setParamDeviation(deviation1);
        ((PetriT)d_T.get(5)).setDistribution("unif", timeDelay2);
        ((PetriT)d_T.get(5)).setParamDeviation(deviation2);
        PetriNet d_Net = new PetriNet("Bus", d_P, d_T, d_In, d_Out);
        PetriP.initNext();
        PetriT.initNext();
        ArcIn.initNext();
        ArcOut.initNext();
        return d_Net;
    }
    

public static PetriNet CreateNetUntitledd() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Queue",0));
	d_P.add(new PetriP("Available busses in A",0));
	d_P.add(new PetriP("Ready to go",0));
	d_P.add(new PetriP("Bus in B",0));
	d_P.add(new PetriP("Queue",0));
	d_P.add(new PetriP("Available busses in B",0));
	d_P.add(new PetriP("Ready to go",0));
	d_P.add(new PetriP("Bus in A",0));
	d_T.add(new PetriT("Take places in bus",0.0));
	d_T.add(new PetriT("Arrival in A",0.0));
	d_T.add(new PetriT("Arrival in B",0.0));
	d_T.add(new PetriT("Road",0.0));
	d_T.add(new PetriT("Take places in bus",0.0));
	d_T.add(new PetriT("T6",0.0));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(0),25));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(2),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(3),1));
	d_In.add(new ArcIn(d_P.get(4),d_T.get(4),25));
	d_In.add(new ArcIn(d_P.get(5),d_T.get(4),1));
	d_In.add(new ArcIn(d_P.get(7),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(6),d_T.get(5),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(3),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(5),d_P.get(7),1));
	d_Out.add(new ArcOut(d_T.get(4),d_P.get(6),1));
	d_Out.add(new ArcOut(d_T.get(2),d_P.get(5),1));
	PetriNet d_Net = new PetriNet("Untitled",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}


        public static PetriNet CreateСonveyorSystem(double servC1, double transpC1C2, double servC2) throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
                ArrayList<PetriP> d_P = new ArrayList<>();
                ArrayList<PetriT> d_T = new ArrayList<>();
                ArrayList<ArcIn> d_In = new ArrayList<>();
                ArrayList<ArcOut> d_Out = new ArrayList<>();
                d_P.add(new PetriP("P1",1));
                d_P.add(new PetriP("Черга п1",0));
                d_P.add(new PetriP("Вільний п1",1));
                d_P.add(new PetriP("Кількість оброблених",0));
                d_P.add(new PetriP("Черга п2",0));
                d_P.add(new PetriP("Вільний п2",1));
                d_P.add(new PetriP("Черга п3",0));
                d_P.add(new PetriP("Вільний п3",1));
                d_P.add(new PetriP("Черга п4",0));
                d_P.add(new PetriP("Черга п5",0));
                d_P.add(new PetriP("Вільний п4",1));
                d_P.add(new PetriP("Вільний п5",1));
                d_T.add(new PetriT("Надходження",1.0));
                d_T.add(new PetriT("Обслуговування п1",1.0));
                d_T.get(1).setDistribution("exp", servC1);
                d_T.get(1).setParamDeviation(0.0);
                d_T.get(1).setPriority(1);
                d_T.add(new PetriT("Перехід п1-п2",transpC1C2));
                d_T.add(new PetriT("Обслуговування п2",1.0));
                d_T.get(3).setDistribution("exp", servC2);
                d_T.get(3).setParamDeviation(0.0);
                d_T.get(3).setPriority(1);
                d_T.add(new PetriT("Перехід п2-п3",1.0));
                d_T.add(new PetriT("Обслуговування п3",1.0));
                d_T.get(5).setDistribution("exp", 1.0);
                d_T.get(5).setParamDeviation(0.0);
                d_T.get(5).setPriority(1);
                d_T.add(new PetriT("Перехід п3-п4",1.0));
                d_T.add(new PetriT("Перехід п4-п5",1.0));
                d_T.add(new PetriT("Перехід п5-п1",5.0));
                d_T.add(new PetriT("Обслуговування п4",1.0));
                d_T.get(9).setDistribution("exp", 1.0);
                d_T.get(9).setParamDeviation(0.0);
                d_T.add(new PetriT("Обслуговування п5",1.0));
                d_T.get(10).setDistribution("exp", 1.0);
                d_T.get(10).setParamDeviation(0.0);
                d_In.add(new ArcIn(d_P.get(0),d_T.get(0),1));
                d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
                d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
                d_In.add(new ArcIn(d_P.get(1),d_T.get(2),1));
                d_In.add(new ArcIn(d_P.get(5),d_T.get(3),1));
                d_In.add(new ArcIn(d_P.get(4),d_T.get(3),1));
                d_In.add(new ArcIn(d_P.get(4),d_T.get(4),1));
                d_In.add(new ArcIn(d_P.get(7),d_T.get(5),1));
                d_In.add(new ArcIn(d_P.get(6),d_T.get(5),1));
                d_In.add(new ArcIn(d_P.get(6),d_T.get(6),1));
                d_In.add(new ArcIn(d_P.get(8),d_T.get(7),1));
                d_In.add(new ArcIn(d_P.get(9),d_T.get(8),1));
                d_In.add(new ArcIn(d_P.get(10),d_T.get(9),1));
                d_In.add(new ArcIn(d_P.get(8),d_T.get(9),1));
                d_In.add(new ArcIn(d_P.get(9),d_T.get(10),1));
                d_In.add(new ArcIn(d_P.get(11),d_T.get(10),1));
                d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
                d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),4));
                d_Out.add(new ArcOut(d_T.get(1),d_P.get(2),1));
                d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
                d_Out.add(new ArcOut(d_T.get(2),d_P.get(4),1));
                d_Out.add(new ArcOut(d_T.get(3),d_P.get(5),1));
                d_Out.add(new ArcOut(d_T.get(3),d_P.get(3),1));
                d_Out.add(new ArcOut(d_T.get(5),d_P.get(7),1));
                d_Out.add(new ArcOut(d_T.get(4),d_P.get(6),1));
                d_Out.add(new ArcOut(d_T.get(5),d_P.get(3),1));
                d_Out.add(new ArcOut(d_T.get(6),d_P.get(8),1));
                d_Out.add(new ArcOut(d_T.get(7),d_P.get(9),1));
                d_Out.add(new ArcOut(d_T.get(8),d_P.get(1),1));
                d_Out.add(new ArcOut(d_T.get(9),d_P.get(10),1));
                d_Out.add(new ArcOut(d_T.get(9),d_P.get(3),1));
                d_Out.add(new ArcOut(d_T.get(10),d_P.get(11),1));
                d_Out.add(new ArcOut(d_T.get(10),d_P.get(3),1));
                PetriNet d_Net = new PetriNet("СonveyorSystem",d_P,d_T,d_In,d_Out);
                PetriP.initNext();
                PetriT.initNext();
                ArcIn.initNext();
                ArcOut.initNext();

                return d_Net;
        }
                
public static PetriNet CreateObjectChopsticks(){
        ArrayList<PetriP> d_P = new ArrayList<>();
        d_P.add(new PetriP("Chopstick",1));
        PetriNet d_Net = new PetriNet("ObjectChopstick",d_P);
        PetriP.initNext();
        return d_Net;
}

public static PetriNet CreateNetPhilosophers_task() throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
	ArrayList<PetriP> d_P = new ArrayList<>();
	ArrayList<PetriT> d_T = new ArrayList<>();
	ArrayList<ArcIn> d_In = new ArrayList<>();
	ArrayList<ArcOut> d_Out = new ArrayList<>();
	d_P.add(new PetriP("Think",1));
	d_P.add(new PetriP("First",1));
	d_P.add(new PetriP("Second",1));
	d_P.add(new PetriP("Eat",0));
	d_T.add(new PetriT("Put",1.0));
	d_T.add(new PetriT("Take",0.0));
	d_In.add(new ArcIn(d_P.get(3),d_T.get(0),1));
	d_In.add(new ArcIn(d_P.get(0),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(1),d_T.get(1),1));
	d_In.add(new ArcIn(d_P.get(2),d_T.get(1),1));
	d_Out.add(new ArcOut(d_T.get(1),d_P.get(3),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(0),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(1),1));
	d_Out.add(new ArcOut(d_T.get(0),d_P.get(2),1));
	PetriNet d_Net = new PetriNet("Philosophers_task",d_P,d_T,d_In,d_Out);
	PetriP.initNext();
	PetriT.initNext();
	ArcIn.initNext();
	ArcOut.initNext();

	return d_Net;
}
}