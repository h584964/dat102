package no.hvl.dat102.tabell;

import MengdeTest.MengdeADTTest;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.tabell.TabellMengde;


public class TabellMengdeTest extends MengdeADTTest {
	
	protected MengdeADT<Integer> reset() {
		return new TabellMengde<Integer>();
	}

}
