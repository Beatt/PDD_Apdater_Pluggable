package pdd.apdater.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pdd.adapter.dto.MyOpenBook;

/*
 * PDD PLUGGABLE ADAPTERS. 
 * Objetivo: Adaptar el nuevo sistema llamado
 * 		MyCoolBook, para que trabaje con los actiguos
 * 			sistemas que se crearon para que trabajaran
 * 			con SpaceBook.
 *  ADAPTER = MyCoolBook
 *  ADAPTEE = MyOPenBook
 */
public class MyCoolBook extends MyOpenBook {
	
	private GUIInteract ventana;
	private static Map<String, MyCoolBook>  community
		= new HashMap<String, MyCoolBook>();
	private String name;
	
	public MyCoolBook(String name) {
		super(name); 
		this.name = name;
		ventana = new GUIInteract("CoolBook", name);
		
		// Establecemos que está instancia pertenece a la llave
		// 		que lleva por nombre(name).
		community.put(name, this); 
	}
	
	/*
	 * Objetivo: Permite colocar mensajes en la
	 * 		ventana.
	 */
	private void mensaje(String mensaje) {
		ventana.getPnlMensajes().add(new JLabel(mensaje));			
	}
	
	@Override
	public void poke(String who) {		
		mensaje("");		
		
		if(community.containsKey(who)) {
			community.get(who).add(name, " Poke you");
		} 
		else {
			mensaje("Amigo " + who + " no forma parte de la comunidad");
		}
	}
	
	@Override
	public void add(String friend, String message) {
		
		if(community.containsKey(friend)) {
			community.get(friend).mensaje(name + " : " + message);
		}
		else {
			mensaje("Tu amigo " + friend + " no es parte de la comunidad?");
		}		
	}
	
	/*
	 * Objetivo: GUI para permitir realizar poke sobre una persona.
	 */
	private class GUIInteract extends JFrame implements Runnable {

		private static final long serialVersionUID = 1L;
		private Thread thread;		
		private JPanel pnlMensajes;
		
		public GUIInteract(String title, String name) {

			super(title);
			setLayout(new BorderLayout());
			JPanel pnlPrincipal = new JPanel(new BorderLayout(20, 30));
			setPnlMensajes(new JPanel(new FlowLayout(FlowLayout.CENTER)));
			
			Controlador control = new Controlador();
			JButton btnEnviar = new JButton("Poke " + name);
			btnEnviar.addActionListener(control);
			
			pnlPrincipal.add(btnEnviar, BorderLayout.NORTH);
			pnlPrincipal.add(new JLabel("Bienvenido a Coolbook " + name), BorderLayout.CENTER);

			Container contenedor = getContentPane();
			contenedor.add(pnlPrincipal, BorderLayout.CENTER);
			contenedor.add(getPnlMensajes(), BorderLayout.SOUTH);
			
			setSize(300, 300);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setThread(new Thread(this));
			getThread().setName(name);
			getThread().start();
		}

		@Override
		public void run() {
			
			while(thread.isAlive()) {
				validate();
			}
			
		}// Fin Run

		public Thread getThread() {
			return thread;
		}

		public void setThread(Thread thread) {
			this.thread = thread;
		}

		public JPanel getPnlMensajes() {
			return pnlMensajes;
		}

		public void setPnlMensajes(JPanel pnlMensajes) {
			this.pnlMensajes = pnlMensajes;
		}
		
		private class Controlador implements ActionListener {
			
			// FALTO PULIR ESTA PARTE, PARA QUE SE PUEDA
			//		INTERACTUAR CON N... PERSONAS.
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("Poke Juan")) {
					community.get("Juan").poke("Pepe");
				}
				else if(e.getActionCommand().equals("Poke Pepe")) {
					community.get("Pepe").poke("Juan");
				}
				
			}
			
		}//Fin class Controlador
		
	}//Fin class GUIInteract

	public static void main(String[] args) {
		
		// Se trabaja con la nueva versión, 
		// 		la cual está adapta con la versiones antiguas.
		MyCoolBook juan = new MyCoolBook("Juan");
		MyCoolBook pepe = new MyCoolBook("Pepe");
		((MyOpenBook) juan).add("En en el antiguo sistema!!");		
		((MyOpenBook) juan).add("Que showww morros!!");	
		((MyOpenBook) pepe).add("Dando el rol en el antiguo sistema");
		((MyOpenBook) juan).add("Pepe", "Que show pepito !!!");	
		//((MyOpenBook) pepe).add("Juan", "Que pedo juan por que andas en el sistema viejo :D");
		
		// Se está trabajando con una versión antigua de MyOpenBook
		MyOpenBook rocio = new MyOpenBook("Rocio");
		MyOpenBook ronaldo = new MyOpenBook("Ronaldo");		
		rocio.add("Hola");
		rocio.poke("Ronaldo-4");
		rocio.add("Ronaldo-4", "Que pedo ronaldo, arma la reta morro!!");
		ronaldo.add("Esto está chevere!!");
						
	}

}// Fin class GUIInteract
