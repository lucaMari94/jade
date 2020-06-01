package v1;

import jade.core.behaviours.OneShotBehaviour;

//ogni 60 secondi viene fatta una richiesta ai seller (venditori)
// chiedere se il libro è disponibile o no
public class RequestPerformed extends OneShotBehaviour{
	public void action() {
		System.out.println("Operation X"); // per stampa solo una stringa
	}
}
