package pdd.proxy2.entity;

public interface ICommunication {

	public abstract void add(String s);//Fin add

	public abstract void add(String friend, String message);

	public abstract void poke(String who, String friend);

}