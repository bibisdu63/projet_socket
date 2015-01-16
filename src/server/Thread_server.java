package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;

public class Thread_server implements Runnable {
	
	  private Thread _t; // contiendra le thread du client
	  private Socket _s; // recevra le socket liant au client
	  private PrintStream sortie; // pour gestion du flux de sortie
	  private Reader reader; // pour gestion du flux d'entrée
	  //private Basic_server serveur; // pour utilisation des méthodes de la classe principale
	  String line;
	  boolean boucle = true;
	  

	  Thread_server(Socket s) // le param s est donnée dans BlablaServ par ss.accept()
	  {
	      _s=s; // passage de local en global
	    _t = new Thread(this); // instanciation du thread
	    _t.start(); // demarrage du thread, la fonction run() est ici lancée
	  }
	  
	@Override
	public void run() {
		// TODO Auto-generated method stub

	    System.out.println("Un nouveau client s'est connecte -- Je suis dans le thread!!!");
	    try
	    {
	    	System.out.println("Connexion realise a "+_s.toString());
			reader = new InputStreamReader(_s.getInputStream()); 
			sortie = new PrintStream(_s.getOutputStream());
			BufferedReader keyboard = new BufferedReader(reader); 
			while (boucle){
				boucle = true; 
				line = keyboard.readLine(); 
				System.out.println("Vous avez saisi : "+line);
				
				if (line.equals("FIN")){
					boucle = false; 
					line = null; 
					_s.close();
				}else{
					//StringBuffer lineReversed = (new StringBuffer(line)).reverse();
					//sortie.println(lineReversed);
					sortie.println(calcul(line));
				}
			}
	    }
	    catch (Exception e){ }
	    finally // finally se produira le plus souvent lors de la deconnexion du client
	    {
	      try
	      {
	        System.out.println("Le client s'est deconnecte");
	        _s.close(); // fermeture du socket si il ne l'a pas déjà été (à cause de l'exception levée plus haut)
	      }
	      catch (IOException e){ }
	    }

	}
	public int calcul(String ligne){
		String [] tab = new String[3];
		tab= ligne.split(";");
		if(tab[0].equals("M"))
			return Integer.parseInt(tab[1])*Integer.parseInt(tab[2]);
		else if (tab[0].equals("D"))
			return Integer.parseInt(tab[1])/Integer.parseInt(tab[2]);
		
		
		return -1;
		
	}
}