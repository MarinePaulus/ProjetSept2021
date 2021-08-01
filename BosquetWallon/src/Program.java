
import java.util.Iterator;

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
		else {
			System.out.println(o.toString());
			Iterator<Booking> iter3 = o.getBookingList().iterator();
			while(iter3.hasNext()) {
				Booking boo = iter3.next();
				System.out.println(boo.toString());
			}
		}
		
		System.out.println("***********************************************************************************************************************");
				
		Person p3 = new Person();
		p3.setEmailAddress("dupond@gmail.com");
		p3 = p3.getOne();
		Spectator s = (Spectator) p3;
		s.setPassword("1292710371");
		s = s.getOne();
		if(s==null) System.out.println("Mot de passe incorrect");
		else {
			System.out.println(s.toString());
			Iterator<Order> iter4 = s.getOrderList().iterator();
			while(iter4.hasNext()) {
				Order or = iter4.next();
				System.out.println(or.toString());
				Iterator<Ticket> iter5 = or.getTicketList().iterator();
				while(iter5.hasNext()) {
					Ticket t = iter5.next();
					System.out.println(t.toString());
					
				}
			}
		}

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

		System.out.println("***********************************************************************************************************************");
/*	
		Planning pla = new Planning();
		pla.setBeginDate(new Date());
		pla.setEndDate(new Date());
		//if(pla.create()) {
			pla = pla.getOneNoID();
			if(pla==null) System.out.println("Planning incorrect");
			else System.out.println(pla.toString());
			pla = pla.getOne();
			if(pla==null) System.out.println("Planning incorrect");
			else System.out.println(pla.toString());
		//}
*/		
		System.out.println("***********************************************************************************************************************");
		
		Planning pla2 = new Planning();
		Iterator<Planning> iter2 = pla2.getAll().iterator();
		while(iter2.hasNext()) {
			Planning pl = iter2.next();
			System.out.println(pl.toString());
			Iterator<Show> iter5 = pl.getShowList().iterator();
			while(iter5.hasNext()) {
				Show sh = iter5.next();
				System.out.println(sh.toString());
				Iterator<Artist> iter6 = sh.getArtistList().iterator();
				while(iter5.hasNext()) {
					Artist ar = iter6.next();
					System.out.println(ar.toString());
				}
				Iterator<Representation> iter7 = sh.getRepresentationList().iterator();
				while(iter7.hasNext()) {
					Representation re = iter7.next();
					System.out.println(re.toString());
				}
			}
		}
		
		System.out.println("***********************************************************************************************************************");
		
		Configuration conf = new Configuration();
		conf.setId(2);
		conf = conf.getOne();
		conf.setCategoryList();
		conf.toString();
		Iterator<Category> iter8 = conf.getCategoryList().iterator();
		while(iter8.hasNext()) {
			Category cat = iter8.next();
			System.out.println(cat.toString());
		}
		
		
//		Organizer o = new Organizer();
//		o.setId(2);
//		o.setPassword("1624974620");
//		o = o.getOne();
//		Planning p = new Planning();
//		p.setId(23);
//		p = p.getOne();
//		System.out.println(p.toString());
//		Booking b = new Booking();
//		b.setPlanning(p);
//		b.create();
//		b = b.getOneNoID();
//		System.out.println(b.toString());
//		o.addBooking(b);
//		o.recBookings();
//		System.out.println(o.toString());
//		Iterator<Booking> iter3 = o.getBookingList().iterator();
//		while(iter3.hasNext()) {
//			Booking boo = iter3.next();
//			System.out.println(boo.toString());
//		}
	}
}
