package v3;

import java.util.Hashtable;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

// UNA GUI PER OGNI SELLER (catalogo)
public class BookSellerAgent extends Agent {
	
	public Hashtable catalogue; // catalogo dell'agente -> insieme dei libri che ha a disposizione
	private BookSellerGui myGui; // gui swing in cui è possibile inizializzare i libri e aggiungerli al catalogo
	
	public void setup() {
		catalogue = new Hashtable();
		myGui = new BookSellerGui(this);
		myGui.showGui();
		
		// 2 comportamenti 
		// OfferRequestsServer: il seller risponde alle richieste di prezzo
		addBehaviour(new OfferRequestsServer());
		
		// PurchaseOrdersServer: il seller si occupa dell'ordine di acquisto
		addBehaviour(new PurchaseOrdersServer()); // ordine di acquisto
		
	}
	
	public void takeDown() {
		myGui.dispose();
		System.out.println("Seller agent '"+ getAID().getLocalName() +" terminating'");
	}
	
	 // USATA NELL'INTERFACCIA PER AGGIORNARE L'HASHTABLE
	public void updateCatalogue(final String title, final int price) {
		addBehaviour( new OneShotBehaviour() {
			public void action() {
				catalogue.put(title, price);
			}
		});
	}
	
}
