package v4;

import jade.core.AID;  
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

// buyer get a book (arguments)
// seller

// book buyer with behaviour
public class BookBuyerAgent extends Agent{

	public String targetBookTitle;
	
	// nomi dei rivenditori 
	// questi sono all'interno del buyer 
	// sono dei campi della classe
	// conoscenze interne dell'agente (come se fosse belief)
	// array con i nomi dei venditori
	// vedremo più avanti che si può usare il DF
	public AID[] sellers = {
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
			
			addBehaviour(new TickerBehaviour(this, 6000){ // classe anonima (non ha un nome)
				protected void onTick() { // comportamento ripetuto ogni 60 secondi
					myAgent.addBehaviour(new RequestPerformed()); // viene aggiunto di un comportamento
				} // compito che deve essere svolto ogni 60 secondi
				// ogni 60 secondi viene fatta una richiesta ai seller (venditori)
				// chiedere se il libro è disponibile o no
			});
			
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
