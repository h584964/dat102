package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {// Kladdeoppgave 3

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (start.getElement().equals(element)) { // sletter foran
			// henter ut elementet
			resultat = start.getElement();
			// oppsatere start
			start = start.getNeste();
			// minker antall
			antall--;
			// hvis det ikke er tilfelle så må vi gå videre
		} else {// gjennomgår den kjedet struturen
			forgjenger = start;
			aktuell = start.getNeste();
			// bruker en loop
			for (int sok = 2; sok <= antall && !funnet; sok++) {
				// begynne å sammenligne
				if (aktuell.getElement().equals(element))
					funnet = true;

				else {
					forgjenger = aktuell.getNeste();

				}

			}
			if (funnet) {
				resultat = aktuell.getElement();
				// Da skal vi slette midt inn eller bak
				forgjenger.setNeste(aktuell.getNeste());
				// Må få forrge.setNeste til å peke på aktuell.getNeste
				// Så må vi minke igjen, fordi vi sletter
				antall--;
			}
		}
		return resultat;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(Object m2) {
		KjedetMengde<T> mengde = (KjedetMengde<T>) m2;
		boolean likeMengder = mengde.antall() == antall;
		T element;
		Iterator<T> mengdeI = mengde.oppramser();
		while (mengdeI.hasNext()) {
			if (!inneholder(mengdeI.next())) {
				likeMengder = false;
			}
		}
//		MengdeADT<T> mengde = (MengdeADT<T>) m2;
//		if (antall == mengde.antall()) {
//			Iterator<T> nyIt = mengde.oppramser();
//			while (nyIt.hasNext() && likeMengder) {
//				if (!this.inneholder(nyIt.next())) {
//					likeMengder = false;
//				}
//			}
//
//		} else {
//			likeMengder = false;
//		}

//		if (m2 instanceof MengdeADT) {
//			Iterator<T> nyIt = ((MengdeADT<T>) m2).oppramser();
//			if (this.antall() == ((MengdeADT<T>) m2).antall()) {
//				likeMengder = true;
//				while (nyIt.hasNext()) {
//					element = nyIt.next();
//					if (!this.inneholder(element)) {
//						likeMengder = false;
//					}
//				}
//
//			}
//		}
		return likeMengder;

	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {//
		MengdeADT<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		T element = null;

		while (aktuell != null) {// ubetinget innettning1
			element = aktuell.getElement();
			((KjedetMengde<T>) begge).settInn(element);
			// Bruker objekt referanse -- KjedetMengde
			aktuell = aktuell.getNeste();

		}
//		KjedetMengde ny = (KjedetMengde)m2;
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {// At vi ikke får flere elemnter inn hele tiden, at det ikke blir hull
			element = teller.next(); // Oppdaterer
			if (!this.inneholder(element)) {

				((KjedetMengde<T>) begge).settInn(element);

			}
		}

		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();

		T element;
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (this.inneholder(element)) {
				((KjedetMengde<T>) snittM).settInn(element);
			}
		}
		/*
		 * Fyll ut...
		 * 
		 * if (this.inneholder(element)) ((KjedetMengde<T>) snittM).settInn(element);
		 */
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element;

		Iterator<T> teller = this.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!m2.inneholder(element)) {

				((KjedetMengde<T>) differensM).settInn(element);
			}

		}

		Iterator<T> teller2 = m2.oppramser();
		while (teller2.hasNext()) {
			element = teller2.next();
			if (!this.inneholder(element)) {
				((KjedetMengde<T>) differensM).settInn(element);

			}

		}

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

	public String toString() {
		// For klassen KjedetMengde
		String resultat = "";
		LinearNode<T> aktuell = start;
		while (aktuell != null) {
			resultat += aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste();
		}
		return resultat;
	}

}// class
