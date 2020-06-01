package behaviour;

import jade.core.behaviours.CyclicBehaviour;

public class MyCyclicBehaviour  extends CyclicBehaviour{
	
	public void action() {
		System.out.println("Operation Y");
	}
	
	// il metodo done restitusce di default "false" -> è ereditato dalla classe OneShotBehaviour{

}
