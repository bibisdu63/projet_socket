package server;

import java.net.ServerSocket;

public class Basic_server {
	private static int port; 
	public static ServerSocket s;
	
	public static void main (String[] args) throws Exception
	{ 
		Basic_server serv = new Basic_server();
		
		if (args.length !=1){
			port = new Integer ("18241");
		}
		else port = new Integer(args[0]).intValue();
		affichage(port);
		
		new Commandes(serv);
		
		s = new ServerSocket(port); 
		
		while (true && !s.isClosed()){
			
			new Thread_server(s.accept());
					
			
		}
		
	}
	
	static private void affichage(int port){
		System.out.println(" --------------------");
		System.out.println(" Projet Sockets - Serveur et client ");
		System.out.println(" mini calculatrice");
		System.out.println(" Jordan JALLY & Bastien BISSON");
		System.out.println(" Copyright : 2015");
		System.out.println(" version : 1.0");
		System.out.println(" --------------------");
		System.out.println("Demarre sur le port : "+port);
		System.out.println(" --------------------");
		System.out.println("Quitter : taper\"quit\"");
		System.out.println(" --------------------");
	}
}
