package clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;

public class client
{
	private static int port;
	public static void main (String [] args) throws Exception
	{
		String adresse,line,lineReversed;
		Reader readSoc;
		PrintStream sortie = null;

		if(args.length != 2 ) {
			System.out.println("usage : java client nom_serveur port");
			System.exit(0);}
		adresse = args[0];
		port = new Integer(args[1]).intValue();

		Socket socket = new Socket (adresse,port);

		Reader reader = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(reader);
		
		sortie = new PrintStream(socket.getOutputStream());
		readSoc = new InputStreamReader(socket.getInputStream());
		BufferedReader keyboardSoc = new BufferedReader(readSoc);
		
		while(true){
				System.out.println("Taper M pour mult et D pour div");
				line = keyboard.readLine();
				System.out.println("Entrer premier nombre");
				line += ";"+ keyboard.readLine();
				System.out.println("Entrer deuxieme nombre");
				line += ";"+ keyboard.readLine();
				sortie.println(line);
				
				if(line.equals("FIN"))break;
				
				lineReversed = keyboardSoc.readLine();
				System.out.println("Recu : "+lineReversed);}
		
		socket.close();
	}
}
