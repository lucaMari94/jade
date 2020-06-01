package example;

import jade.core.Agent; 

// agent termination 

public class HelloWorldAgent3 extends Agent {
	
	@Override
	protected void setup() {
		System.out.println("Hello World! I'm Agent");
		
		doDelete(); // call takeDown
	}
	
	@Override
	protected void takeDown() {
		System.out.println("Bye");
	}
	
}