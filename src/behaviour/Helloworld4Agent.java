package behaviour;

import jade.core.AID;  
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

// buyer get a book (arguments)
// seller

// comportamenti eseguiti uno alla volta
// un singolo scheduler

// se si desidera fare interleaving dei comportamenti 
// deve essere fatto a mano

// tipi di comportamenti:
// One Shot --> metodo done() restituisce subito "true"
// la action è eseguita solo una volta

// Cyclic --> metodo done() restituisce sempre "false"
// la action viene eseguita ogni volta 
// il comportamento viene inserito nel pool di comportamenti attivi ogni volta

public class Helloworld4Agent extends Agent{

	private String targetBookTitle;
	
	// nomi dei rivenditori 
	// questi sono all'interno del buyer 
	// sono dei campi della classe
	// conoscenze interne dell'agente (come se fosse belief)
	// array con i nomi dei venditori
	// vedremo più avanti che si può usare il DF
	private AID[] sellers = {
			new AID("seller1", AID.ISLOCALNAME),
			new AID("seller2", AID.ISLOCALNAME)
	};
	
	@Override
	protected void setup() {
		System.out.println("Hello World! I'm Agent");
		
		Object [] args = getArguments(); // method of class Agent
		
		if(args != null) {
			System.out.println("My arguments are:");
			for(int i = 0; i <args.length; i++) {
				System.out.println(args[i]);
			}
		}
		
		//addBehaviour(new MyOneShotBehaviour());
		//addBehaviour(new MyCyclicBehaviour());
		//addBehaviour(new MyThreeStepBehaviour());
		//addBehaviour(new MyOneShotBehaviour()); 
		// insieme a MyThreeStepBehaviour --> cooperazione
		// lo schedule prende i comportamenti e li esegue
		
		// WakerBehaviour
		// comportamento risvegliato dopo un tot di tempo
		addBehaviour(new WakerBehaviour(this, 10000) { // classe anonima
			public void handleElapsedTimeout() { // handleElapsedTimeout
				System.out.println("X");
			}
		});
		
		// TickerBehaviour
		// comportamento ripetuto ogni tot tempo
		addBehaviour(new TickerBehaviour(this, 10000) { // classe anonima
			public void onTick() { // onTick
				System.out.println("Y");
			}
		});
		
		addBehaviour(new MyTickerBehaviour(this,10000));
	}
	
	@Override
	protected void takeDown() {
		System.out.println("Bye");
	}
}
