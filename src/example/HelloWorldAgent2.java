package example;

import jade.core.Agent;

public class HelloWorldAgent2 extends Agent {
	
	@Override
	protected void setup() {
		System.out.println("Hello World! I'm Agent");
		
		Object [] args = getArguments(); // method of class Agent
		
		if(args != null) {
			System.out.println("My arguments are:");
			for(int i = 0; i <args.length; i++) {
				System.out.println(args[i]);
			}
		}
		
	}
	
}
