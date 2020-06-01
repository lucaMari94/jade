package v3;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

// CyclicBehavour viene ripetuto 
// il seller risponde alle richieste di prezzo
public class OfferRequestsServer extends CyclicBehaviour{
	public void action() {
		
		// ricezione del messaggio da parte del buyer
		// il buyer chiede il prezzo di un libro
		
		
		
		// ATTENZIONE: LA RECEIVE NON È BLOCCANTE 
		// VA GESTITO MANUALMENTE
		// SE NON CI SONO MESSAGGI IN CODA
		// BISOGNA BLOCCARE L'AGENTE IN ATTESA DI MESSAGGI!
		// --> IF(MSG != NULL) { ... } ELSE { BLOCK(); }
		// BLOCK()
		// IL COMPORTAMENTO NON VIENE MESSO NELLA CODA DEI COMPORTAMENTI ATTIVI
		// DALLO SCHEDULER FINTANTO CHE LA CONDIZIONE "UN MESSAGGIO È ARRIVATO"
		// NON DIVENTA VERA
		// QUANDO ARRIVA UN MESSAGGIO IL COMPORTAMENTO VIENE MESSO NELLA CODA DEI COMPORTAMENTI ATTIVI
		
		
		
		ACLMessage msg = myAgent.receive();
		System.out.println("Checking message...");
		if(msg != null) {
			/*String title = msg.getContent(); // contenuto del messaggio
			ACLMessage reply = msg.createReply(); // creazione della risposta
			Integer price = (Integer) ((BookSellerAgent)myAgent).catalogue.get(title);
			if(price != null) {
				reply.setPerformative(ACLMessage.PROPOSE);
				reply.setContent(String.valueOf(price.intValue()));
			} else {
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent("not-available");
			}
			myAgent.send(reply);*/
		} else {
			System.out.println("Nessun messaggio trovato in coda"); 
			block(); // COMPORTAMENTO NON ESEGUITO DALLO SCHEDULER
		}
				
		
	}
}
