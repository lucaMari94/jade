package example;

import jade.core.Agent;
import java.util.Iterator;

public class HelloWorldAgent extends Agent {
	
	@Override
	protected void setup() {
		System.out.println("Hello World! I'm Agent");
		
		System.out.println("My local name is " + getAID().getLocalName()); // getAID() method of class Agent
		
		System.out.println("My GUID is " + getAID().getName());
		
		System.out.println("My address are:");
		Iterator it = getAID().getAllAddresses();
		while(it.hasNext()) {
			System.out.println("- " + it.next());
		}
		
	}
	
}
