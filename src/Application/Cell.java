package Application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Cell extends JPanel{

	public static final boolean ON 	  = true;
	public static final boolean OFF   = false;
	
	private Color ON_COLOR  = Color.BLACK;
	private Color OFF_COLOR = Color.WHITE;
	
	private static Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.YELLOW);
	private static Border DEFAULT_BORDER  = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	private boolean state = false;
	private boolean next  = false;
	
	private static boolean draw = false;
	
	public Cell(){

		setBackground(OFF_COLOR);
		setBorder(DEFAULT_BORDER);
		addMouseListener(new MouseListener() {				

			@Override
			public void mouseReleased(MouseEvent e) {
				Application.mouseDown = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Application.mouseDown = true;
				draw = !state;
				toggle();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(DEFAULT_BORDER);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Application.mouseDown && draw != state)
					mousePressed(e);
				setBorder(SELECTED_BORDER);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		}); 
	}
	
	
	public void set(boolean state){
		if (state)
			on();
		else
			off();
	}
	public void setNext(boolean state){
		if (state)
			next = ON;
		else
			next = OFF;
	}
	
	public boolean getNext(){
		return next;
	}
	
	public void on(){
		state = ON;
	}
	public void off(){ 
		state = OFF;
	}

	public void toggle(){
		if (isOn())
			off();
		else
			on();
		repaint();
	}
	public boolean isOn(){
		return state;
	}
	public int toInt(){
		if (isOn())
			return 1;
		else
			return 0;
	}
	public String toString(){
		if (isOn())
			return "1";
		else
			return "0";
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (isOn())
			setBackground(ON_COLOR);
		else
			setBackground(OFF_COLOR);
		super.paintComponent(g);
	}
	
}
