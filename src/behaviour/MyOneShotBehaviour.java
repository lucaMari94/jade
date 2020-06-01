package behaviour;

import jade.core.behaviours.OneShotBehaviour;

public class MyOneShotBehaviour extends OneShotBehaviour{
	
	public void action() {
		System.out.println("Operation X");
	}
	
	// il metodo done restitusce di default "true" -> è ereditato dalla classe OneShotBehaviour
}