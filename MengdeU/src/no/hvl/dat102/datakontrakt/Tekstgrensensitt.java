package no.hvl.dat102.datakontrakt;

import java.util.Scanner;

import no.hvl.dat102.hobby.Hobby;
import no.hvl.dat102.medlem.Medlem;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Tekstgrensensitt {
	public final static Scanner TAST = new Scanner(System.in);

	public static Medlem lesMedlem() {

//		int valg;
		System.out.println("Navn: ");
		String navn = TAST.next();
		navn += TAST.nextLine();

		MengdeADT<Hobby> hobbyer = lesHobby();

		Medlem medlem = new Medlem(navn, hobbyer);
		

		return medlem;

//			String hobbynavn = TAST.next();
//			hobbynavn += TAST.nextLine();
//
//			System.out.println("Trykk h for å gå ut");

	}

	public static void skrivHobbyListe(Medlem medlem) {
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	public static void skrivParListe(Datakontrakt arkiv) {
		int antallP = 0;
		Datakontrakt nyarkiv = arkiv;
		Medlem [] listen = nyarkiv.getMedlemstab();
		
		System.out.printf("%-30.30s  %-30.30s%n","PARNAVN:","HOBBYER:");
		

		for (int i = 0; i < nyarkiv.getAntall(); i++) {
			if (listen[i].getStatusIndeks() == -1) {//  && listen[i].getStatusIndeks() > i
				int indeks = listen[i].getStatusIndeks();
				// Finner navnet på dem hvis de er et par
				
//				System.out.format("%-20s", listen[i].getNavn() + " og " + listen[indeks].getNavn());
//				System.out.format(" %s%n", "<" + listen[i].getHobbyer().toString() + ">");
//				antallP++;
				String navn = listen[i].getNavn() + ", " + listen[indeks].getNavn();
				String hobby = listen[i].getHobbyer().toString();
				antallP++;
				
				
				nyarkiv.tilbakestillStausIndeks(listen[i].getNavn());
				

//				nyarkiv.tilbakestillStausIndeks(medlemstab[i].toString());
			
				System.out.printf("%-30.30s  %-30.30s%n",navn,hobby);
			}
			

		}
		System.out.println("-------------------------");
		System.out.println("Antall par som er funnet: " + antallP);

	}

	public static MengdeADT<Hobby> lesHobby() {
		MengdeADT<Hobby> hobbyer = new KjedetMengde<>();
		int valg;
		do {

			System.out.println("Skriv inn en hobby");
			String hobbynavn = TAST.next();
			hobbynavn += TAST.nextLine();

			System.out.println("Skriv 1 for å legge til flere hobbyer, og 0 for å avslutte");
			valg = TAST.nextInt();
			Hobby hobby = new Hobby(hobbynavn);
			hobbyer.leggTil(hobby);
		}

		while (valg == 1);

		return hobbyer;
	}

}
