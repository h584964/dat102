package MengdeTest;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.mengde.adt.MengdeADT;



public abstract class MengdeADTTest {

	// Referer til mengde

	private MengdeADT<Integer> begge;
	private MengdeADT<Integer> m1;
	private MengdeADT<Integer> m2;
	private MengdeADT<Integer> fasit;

	// Test data

	protected abstract MengdeADT<Integer> reset();

	/**
	 * Hent en ny mengde for hver test
	 * 
	 */
	@BeforeEach
	public void setup() {
		begge = reset();
		m1 = reset();
		m2 = reset();
		fasit = reset();
	}
	
	
	

	@Test
	public void UnionTest() {


		m1.leggTil(1);
		m1.leggTil(2);
		m1.leggTil(3);
		m1.leggTil(4);
		m1.leggTil(5);

		m2.leggTil(6);
		m2.leggTil(7);
		m2.leggTil(8);
		m2.leggTil(9);
		m2.leggTil(10);

		fasit.leggTil(1);
		fasit.leggTil(2);
		fasit.leggTil(3);
		fasit.leggTil(4);
		fasit.leggTil(5);
		fasit.leggTil(6);
		fasit.leggTil(7);
		fasit.leggTil(8);
		fasit.leggTil(9);
		fasit.leggTil(10);


		begge = m1.union(m2);
		assertTrue(begge.equals(fasit));
	}

	@Test
	public void SnittTest() {

		m1.leggTil(1);
		m1.leggTil(2);
		m1.leggTil(3);
		m1.leggTil(4);
		m1.leggTil(5);

		m2.leggTil(1);
		m2.leggTil(2);
		m2.leggTil(8);
		m2.leggTil(9);
		m2.leggTil(10);
		
		fasit.leggTil(1);
		fasit.leggTil(2);
		
		
		
		begge = m1.snitt(m2);
		assertTrue(begge.equals(fasit));

	}

	@Test
	public void DifferensTest() {
	
		m1.leggTil(1);
		m1.leggTil(2);
		m1.leggTil(3);
		m1.leggTil(4);
		m1.leggTil(5);
		
		
		m2.leggTil(1);
		m2.leggTil(2);
		m2.leggTil(3);
		m2.leggTil(4);
		m2.leggTil(10);
		
		fasit.leggTil(5);
		fasit.leggTil(10);
		
	
		
		begge = m1.differens(m2);
		assertTrue(begge.equals(fasit));
	}

}
