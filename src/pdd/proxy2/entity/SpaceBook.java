package pdd.proxy2.entity;

import java.util.HashMap;
import java.util.Map;

public class SpaceBook implements ICommunication {
	
	private static Map<String, SpaceBook>  community
		= new HashMap<String, SpaceBook>();
	
	private String pages = "";
	private String name;
	private String gap = "\n\t\t\t\t";
	
	public static boolean IsUnique(String name) {		
		return community.containsKey(name);
	}//Fin IsUnique
	
	public SpaceBook(String n) {
		
		name = n; // Nombre de dueño de la página.
		community.put(n, this);
		
	}//Fin SpaceBook
	
	/* 
	 * Objetivo: Agrega mensaje a su página del usuario.
	 * @see pdd.proxy2.entity.ICommunication#add(java.lang.String)
	 */
	@Override
	public void add(String s) {
		
		pages += gap + s;
		System.out.println(gap+"=====================" + name + "'s SpaceBook ==========");
		System.out.println(pages);
		System.out.println(gap+"=================================");
		
	}//Fin add
	
	/* 
	 * Objetivo: Agrega mensaje a una página de otro usuario.
	 * @see pdd.proxy2.entity.ICommunication#add(java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String friend, String message) {
		
		community.put(friend, this).add(message);
		
	}
	
	/*
	 * Objetivo: Permite ingresar mensaje en la página de
	 * 	un usuario existente en el sistema SpaceBook
	 * @see pdd.proxy2.entity.ICommunication#poke(java.lang.String, java.lang.String)
	 */
	@Override
	public void poke(String who, String friend) {
		
		community.get(who).pages += gap + friend + " poked you";
		
	}
	
}//Fin class SpaceBook
