package v5;

import java.util.Hashtable; 

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

// UNA GUI PER OGNI SELLER (catalogo)
public class BookSellerAgent extends Agent {
	
	public Hashtable catalogue; // catalogo dell'agente -> insieme dei libri che ha a disposizione
	private BookSellerGui myGui; // gui swing in cui è possibile inizializzare i libri e aggiungerli al catalogo
	
	public void setup() {
		// registrazione DELL'AGENTE nelle pagine gialle (DF)
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("book-selling");
		sd.setName("JADE-book-trading");
		dfd.addServices(sd);
		try {
			DFService.register(this,dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
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
		// DE-REGISTRAZIONE DELL'AGENTE DALLE PAGINE GIALLE
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		myGui.dispose();
		System.out.println("Seller agent '"+ getAID().getLocalName() +" terminating'");
	}
	
	 // USATA NELL'INTERFACCIA PER AGGIORNARE L'HASHTABLE
	public void updateCatalogue(final String title, final int price) {
		addBehaviour( new OneShotBehaviour() {
			public void action() {
				catalogue.put(title, price);
				System.out.println(title+" inserted into catalogue. Price = "+price);
			}
		});
	}
	
}
