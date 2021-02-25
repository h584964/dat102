package no.hvl.dat102.datakontrakt;



public class KlientDatakontrakt {

	public static void main(String[] args) {
		Datakontrakt data = new Datakontrakt(20);
		int valg;
		do {
			data.leggTilMedlem(Tekstgrensensitt.lesMedlem());
			System.out.println("Tast inn 1 for å legge til en medlemm, og 0 for å avslutte");
			valg = Tekstgrensensitt.TAST.nextInt();
			
			
		}
		// Kan bruke do-while
		while(valg == 1);
		Tekstgrensensitt.TAST.close();
		Tekstgrensensitt.skrivHobbyListe(data.getMedlemstab()[0]);
		Tekstgrensensitt.skrivParListe(data);
		
		

	}
	

}
