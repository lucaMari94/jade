package behaviour;

import jade.core.behaviours.Behaviour;

// comportamento personalizzato 
// si specifica sia il metodo action() che il metodo done()
// si spefica il termine nel metodo done()
// interleaving cooperativo

public class MyThreeStepBehaviour extends Behaviour {

	private int step = 0;
	
	// modo tipico di programmare
	// switch con 3 parti di un codice che deve essere eseuito
	// ad ogni esecuzione si vuole un diverso comportamento
	// lo schedule esegue il comportamento fino a quando la condizione booleana in done non diventa vera
	public void action() {
		switch(step) {
			case 0:
				System.out.println("X");
				step++; //X
				break;
			case 1:
				System.out.println("Y");
				step++; //Y
				break;
			case 2:
				System.out.println("Z");
				step++; //Z
				break;
		}
	}
	
	public boolean done() {
		return step == 3;
	}
}
