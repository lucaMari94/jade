package v4;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

//ogni 60 secondi viene fatta una richiesta ai seller (venditori)
// chiedere se il libro è disponibile o no
public class RequestPerformed extends OneShotBehaviour{
	public void action() {
		
		myAgent.doWait(20000); // doWait -> tempo di aprire l'interfaccia per lo sniffing
		
		/**
		 * invio di un messaggio a tutti i seller
		 * performativa CFP
		 * richiesta per proposte
		 * i venditori interessati dicono la quotazione 
		 * (vendono il libro ad un prezzo specifico)
		 */
		ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
		for(int i = 0; i < ((BookBuyerAgent)myAgent).sellers.length; i++) {
			cfp.addReceiver(((BookBuyerAgent)myAgent).sellers[i]);
		}
		cfp.setContent(((BookBuyerAgent)myAgent).targetBookTitle);
		System.out.println("Sending message... " + cfp);
		myAgent.send(cfp); // invio del messaggio a tutti i sellers
	}
}
