package v5;

import jade.core.AID;  
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
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
	/*public AID[] sellers = {
			new AID("seller1", AID.ISLOCALNAME),
			new AID("seller2", AID.ISLOCALNAME)
	};*/
	public AID[] sellers;
	
	@Override
	protected void setup() {
		
		System.out.println("Hello World! I'm Agent");
		
		
		Object [] args = getArguments(); // method of class Agent
		
		if(args != null && args.length > 0) {
			
			targetBookTitle = (String)args[0];
						
			addBehaviour(new TickerBehaviour(this, 60000){ // classe anonima (non ha un nome)
				protected void onTick() { // comportamento ripetuto ogni 60 secondi
					
					System.out.println("Trying to buy "+targetBookTitle); // vuole acquistare il libro
					// ricerca dei sellers nelle pagine gialle
					DFAgentDescription template = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("book-selling");
					template.addServices(sd);
					try {
						DFAgentDescription[] results = DFService.search(myAgent, template); // array di descrittori
						sellers = new AID[results.length];
						for(int i = 0; i < results.length; i++) {
							sellers[i] = results[i].getName();
						}
						
					} catch (FIPAException fe) {
						fe.printStackTrace();
					}
					
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
