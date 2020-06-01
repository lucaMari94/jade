package v5;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

// seller si occupa dell'ordine di acquisto
public class PurchaseOrdersServer extends CyclicBehaviour{
	public void action() {
		//System.out.println("Operation X");
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL); 
		ACLMessage msg = myAgent.receive(mt); // aspetta una accept_proposal
		if (msg != null) {
			// ACCEPT_PROPOSAL Message received. Process it
			String title = msg.getContent();
			ACLMessage reply = msg.createReply();

			Integer price = (Integer) ((BookSellerAgent)myAgent).catalogue.remove(title);
			if (price != null) {
				reply.setPerformative(ACLMessage.INFORM); // invia INFORM DELL'AVVENUTO INVIO DEL LIBRO
				System.out.println(title+" sold to agent "+msg.getSender().getName());
			}
			else {
				// The requested book has been sold to another buyer in the meanwhile .
				reply.setPerformative(ACLMessage.FAILURE);
				reply.setContent("not-available");
			}
			myAgent.send(reply);
		}
		else {
			block();
		}
	}
}
