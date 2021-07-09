import java.util.Iterator;

import DAO.*;
import POJO.*;

public class Program {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setEmailAddress("tintin@gmail.com");
		p1 = p1.getOne();
		Manager m = (Manager) p1;
		m.setPassword("195086985");
		m = m.getOne();
		if(m==null) System.out.println("Mot de passe incorrect");
		else System.out.println(m.toString());
		
		System.out.println("***********************************************************************************************************************");
		
		Person p2 = new Person();
		p2.setEmailAddress("haddock@gmail.com");
		p2 = p2.getOne();
		Organizer o = (Organizer) p2;
		o.setPassword("1624974620");
		o = o.getOne();
		if(o==null) System.out.println("Mot de passe incorrect");
		else System.out.println(o.toString());

		System.out.println("***********************************************************************************************************************");
		
		Person p3 = new Person();
		p3.setEmailAddress("dupond@gmail.com");
		p3 = p3.getOne();
		Spectator s = (Spectator) p3;
		s.setPassword("1292710371");
		s = s.getOne();
		if(s==null) System.out.println("Mot de passe incorrect");
		else System.out.println(s.toString());

		System.out.println("***********************************************************************************************************************");
		
		Person p4 = new Person();
		p4.setEmailAddress("fakir@gmail.com");
		p4 = p4.getOne();
		Artist a = (Artist) p4;
		a = a.getOne();
		if(a==null) System.out.println("Artiste incorrect");
		else System.out.println(a.toString());

		System.out.println("***********************************************************************************************************************");
		
		Artist artist = new Artist();
		Iterator<Artist> iter = artist.getAll().iterator();
		while(iter.hasNext()) {
			Artist ar = iter.next();
			System.out.println(ar.toString());
		}
	}

}
