package no.hvl.dat102.medlem;

import no.hvl.dat102.hobby.Hobby;
import no.hvl.dat102.mengde.adt.MengdeADT;

public class Medlem {

	private String navn;
	private MengdeADT<Hobby> hobbyer;
	private int statusIndeks;

	public Medlem(String navn, MengdeADT<Hobby> hobbyer) {
		this.navn = navn;
		this.hobbyer = hobbyer;
		statusIndeks = -1;

	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(MengdeADT<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	public boolean passerTil(Medlem medlem2) {
//		boolean passerTil = false;
//		
//		if(hobbyer.equals(medlem2.hobbyer)) {
//			passerTil = true;
//		}
		return hobbyer.equals(medlem2.getHobbyer());
//		
//		return passerTil;
//	}

	}
}
