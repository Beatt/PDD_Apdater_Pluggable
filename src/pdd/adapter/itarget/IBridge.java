package pdd.adapter.itarget;

/*
 *	Objetivo: Actua como puente.
 */
public interface IBridge {

	void add(String message);
	void add(String friend, String message);
	void poke(String who);
	
}//Fin interface IBridge
