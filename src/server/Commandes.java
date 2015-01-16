package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commandes implements Runnable{
	
	BufferedReader in;
	Thread th; 
	Basic_server serv; 
	String comm="";

	public Commandes(Basic_server serv) {
		
		this.serv = serv;
		in = new BufferedReader(new InputStreamReader(System.in));
		th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		
		try {
			while ((comm=in.readLine()) != null)
			{
				if (comm.equalsIgnoreCase("quit")){
					Basic_server.s.close();
					System.exit(0);
				} else {
					System.out.println("cette commande n'est pas supporté!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
