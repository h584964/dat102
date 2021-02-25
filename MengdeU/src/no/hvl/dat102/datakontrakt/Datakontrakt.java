package no.hvl.dat102.datakontrakt;

import no.hvl.dat102.medlem.Medlem;

public class Datakontrakt {
	private Medlem[] medlemstab;
	private int antall;

	private final static int STDK = 100;

	public Datakontrakt(int n) {
		medlemstab = new Medlem[n];
		antall = 0;
	}

	public Datakontrakt() {
		this(STDK);
	}

	public int getAntall() {
		return antall;
	}

	public Medlem[] getMedlemstab() {
		return medlemstab;
	}

	public void setMedlemstab(Medlem[] medlemstab) {
		this.medlemstab = medlemstab;
	}

	// Legger til et nytt medlem i medlemstabellen
	public void leggTilMedlem(Medlem medlem) {
		// sjekker om tabellen er full
		if (antall >= medlemstab.length)
			utvidKapasistet();

		medlemstab[antall] = medlem;
		antall++;

		finnPartnernFor(medlem.getNavn());
//		

	}

	//
	public int finnMedlemsIndeks(String medlemsnavn) {
//		int index;
//		boolean funnet = false;
//		
//		for(index = 0; (index < antall && funnet); index++) {
//			if(medlemstab[index].getNavn() == medlemsnavn) {
//				funnet = true;
//				return index;
//			}else {
//				index = -1;
//			}
//			
//		}
//		
//		return index;

		for (int i = 0; i < antall; i++) {
			if (medlemstab[i].getNavn().equals(medlemsnavn)) {
				return i;
			}
		}
		return -1;

	}

	public int finnPartnernFor(String medlemsnavn) {
//		int sok = finnMedlemsIndeks(medlemsnavn);
//		Medlem medlem2 = medlemstab[sok];
//
//		for (int i = 0; i < antall; i++) {
//			if (medlem2.passerTil(medlemstab[i])) {//i != sok &&
//				medlem2.setStatusIndeks(i);
//				medlemstab[i].setStatusIndeks(sok);
//				return i;
//			}
//		}
//		return -1;
//		boolean funnet = false;
//
//		int m1 = finnMedlemsIndeks(medlemsnavn);
//		int m2 = -1;
//
//		int indeks = -1;
//
////		if(!erKoblet(medlemsNavn)){
//		for (int i = 0; i < antall && !funnet; i++) {
//			m2 = i;
//			if ((medlemstab[m2].passerTil(medlemstab[m1]))
////			 Sjekker at indeksen ikke allerede er lik så vi slipper duplikater
//					&& medlemstab[m2].getStatusIndeks() == -1 && medlemstab[m1].getStatusIndeks() == -1
//					) {
//				medlemstab[m2].setStatusIndeks(m1);
//				medlemstab[m1].setStatusIndeks(m2);
//				indeks = m2;
//				funnet = true;
//			}
//		}
//		
//		return indeks;

//		boolean funnet = false;
//		int index;
//		
//		for(index = 0; (index < antall && !funnet) ; index++) {
//			Medlem medlem2 = medlemstab[index];
//			if(medlem2.passerTil(medlemstab[index])) {
//				funnet = true;
//				
//			}else {
//				index = -1;
//			}
//		}
//		
//		return index;

////		boolean funnet = false;
		int indeks = finnMedlemsIndeks(medlemsnavn);
		int imat = -1;

		Medlem medlem2 = medlemstab[indeks];

		for (imat = 0; imat < antall; imat++) {
			if (imat == indeks && medlem2.passerTil(medlemstab[imat])) {
				medlemstab[imat].setStatusIndeks(indeks);
				medlemstab[indeks].setStatusIndeks(imat);
				return imat;
			}

		}
		return -1;
	}

	// Oppdadere medlemstballen slik at meldem blir idenfiseres ved medlemsnavn
	public void tilbakestillStausIndeks(String medlemsnavn) {
		int sok = finnMedlemsIndeks(medlemsnavn);
		int indeks = medlemstab[sok].getStatusIndeks();

		if (indeks != -1) {
			medlemstab[sok].setStatusIndeks(-1);
			medlemstab[indeks].setStatusIndeks(-1);
		}

	}

	// Hjelpemetode for meldemstabellen
	private void utvidKapasistet() {
		Medlem[] hjelpetabell = new Medlem[(int) Math.ceil(1.1 * medlemstab.length)];
		for (int i = 0; i < medlemstab.length; i++) {
			hjelpetabell[i] = medlemstab[i];
		}
		medlemstab = hjelpetabell;
	}

}
