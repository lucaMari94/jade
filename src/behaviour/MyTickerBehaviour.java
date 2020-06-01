package behaviour;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class MyTickerBehaviour extends TickerBehaviour {
	
	public MyTickerBehaviour(Agent agent, int time) {
		super(agent,time);
	}
	
	@Override
	protected void onTick() {
		System.out.println("XXX");
		
	}

}
