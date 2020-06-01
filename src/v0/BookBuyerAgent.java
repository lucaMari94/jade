package v0;

import jade.core.AID; 
import jade.core.Agent;

// buyer get a book (arguments)
// seller

public class BookBuyerAgent extends Agent{

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
		
		if(args != null && args.length > 0) {
			
			targetBookTitle = (String)args[0];
			
			System.out.println("Trying to buy '" + targetBookTitle +"'"); // vuole acquistare il libro
			
		} else {
			
			System.out.println("No book title specified");
			
			doDelete();
			
		}
		
	}
	
	@Override
	protected void takeDown() {
		System.out.println("Bye");
	}
}
