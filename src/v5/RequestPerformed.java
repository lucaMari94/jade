package v5;

import jade.core.AID;
import jade.core.behaviours.Behaviour; 
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

//ogni 60 secondi viene fatta una richiesta ai seller (venditori)
// chiedere se il libro è disponibile o no
public class RequestPerformed extends Behaviour{
	
	private MessageTemplate mt;
	private int step = 0;
	
	private AID bestSeller = null;
	private int bestPrice;
	private int repliesCnt = 0;
	
	public void action() {
		
		switch(step) {
			case 0:
				
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
				
				// conversation ID per riconoscere la conversazione
				// perché l'INBOX è unico e ci sono più messaggi
				// si vuole prendere il messaggio di interesse --> si utilizza il TEMPLATE
				cfp.setConversationId("book-trade");
				cfp.setReplyWith("cfp" + System.currentTimeMillis());
				
				System.out.println("Sending message... " + cfp);
				myAgent.send(cfp); // invio del messaggio a tutti i sellers
				
				step = 1;
				
				// template -> match con il conversationID e InReplyTo --> match con la risposta! 
				// messaggi che interessano
				mt = MessageTemplate.and(
						MessageTemplate.MatchConversationId("book-trade"), 
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				break;
				
			case 1:
				
				ACLMessage reply = myAgent.receive(mt); // mt template di prima
				if(reply != null) { // se il messaggio con il template c'è nella coda
					if(reply.getPerformative() == ACLMessage.PROPOSE) { // se è stata ricevuta una proposta
						int price = Integer.parseInt(reply.getContent());
						if(bestSeller == null || price < bestPrice) { // controllo: prezzo più basso
							bestPrice = price;
							bestSeller = reply.getSender();
						}
					}
					repliesCnt++; // numero di risposte ricevute = il buyer si aspetta una risposta (propose/refuse( da tutti
					if(repliesCnt >= ((BookBuyerAgent)myAgent).sellers.length) {
						step = 2;
					}
				} else { // altrimenti il comportamento si blocca in attesa di una risposta da parte di un seller
					block();
				}
				break;
				
			case 2:
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(bestSeller);
				order.setContent(((BookBuyerAgent)myAgent).targetBookTitle);
				order.setConversationId("book-trade");
				order.setReplyWith("cfp" + System.currentTimeMillis());
				myAgent.send(order);
				
				mt = MessageTemplate.and(
						MessageTemplate.MatchConversationId("book-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				
				step = 3;
				
				break;
			
			case 3:
				reply = myAgent.receive(mt);
				if(reply != null) {
					if(reply.getPerformative() == ACLMessage.INFORM) { // se è stata ricevuta una proposta
						System.out.println(((BookBuyerAgent)myAgent).targetBookTitle);
						System.out.println("Price = " + bestPrice);
						myAgent.doDelete();
					}
					step = 4;
				} else {
					block();
				}
				break;
		}
		
		
	}
	public boolean done() {
		return (
				(step==2 && bestSeller == null) // step = 2 e non ci sono venditori con la miglior offerta (es. nessuno ha il libro)
				|| step == 4
				);
	}
}
