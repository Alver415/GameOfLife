package Application;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Application extends JFrame implements MouseListener, KeyListener{
	
	public static void main(String[] args){
		new Application(50, 50);	
	}
	
	public static boolean mouseDown = false;
	
	private Timer timer;
	private Grid grid;

	public Application(int rows, int columns){
		int width  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();	
		int w = 20 * 50;
		int h = 20 * 50;
		getContentPane().setPreferredSize(new Dimension(w + 200, h));
		setLocation((width - w) / 2, (height - h) / 2);
		setResizable(true);
		
		JPanel option_panel = new JPanel();
		option_panel.setLocation(w,0);
		option_panel.setSize(200,h);
		option_panel.setLayout(null);
		option_panel.setBackground(new Color(150,150,175));
		
		JButton b_clear = new JButton("Clear");
		b_clear.setLocation(25,50);
		b_clear.setSize(150,50);
		b_clear.setFocusable(false);
		b_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.clear();
				repaint();
			}
		});
		option_panel.add(b_clear);
		
		JButton b_pause = new JButton("Pause"){
			@Override
			public void paintComponent(Graphics g){
				if (timer.isRunning()){
					setText("Pause");
				}
				else {
					setText("Play");
				}
				super.paintComponent(g);
			}
		};
		b_pause.setLocation(25,150);
		b_pause.setSize(150,50);
		b_pause.setFocusable(false);
		b_pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()){
					b_pause.setText("Play");
					timer.stop();
				}
				else {
					timer.start();
					b_pause.setText("Pause");
				}
				repaint();
			}
		});
		option_panel.add(b_pause);
		
		JButton b_next = new JButton("Next");
		b_next.setLocation(25,250);
		b_next.setSize(150,50);
		b_next.setFocusable(false);
		b_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.update();
				repaint();
			}
		});
		option_panel.add(b_next);
		
		
		JSlider s_rate = new JSlider(JSlider.VERTICAL, 0, 1000, 100){
			@Override
			public void paintComponent(Graphics g){
				setValue(timer.getDelay());
				super.paintComponent(g);
			}
		};
		s_rate.setLocation(50, 400);
		s_rate.setSize(100, 500);
		s_rate.setMajorTickSpacing(100);
		s_rate.setMinorTickSpacing(10);
		s_rate.setPaintTicks(true);
		s_rate.setPaintLabels(true);
		s_rate.setFocusable(false);
		s_rate.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				timer.setDelay(s_rate.getValue());
			}
		});
		option_panel.add(s_rate);
		
		add(option_panel);
		
		grid = new Grid(columns, rows);
		grid.set(3,3, Cell.ON);
		grid.set(3,4, Cell.ON);
		grid.set(3,5, Cell.ON);
		grid.set(2,5, Cell.ON);
		grid.set(1,4, Cell.ON);
		
		
		getContentPane().add(grid);

		addKeyListener(this);	
		
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.update();
				repaint();
			}
		});
		timer.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("John Conway's Game of Life");
		pack();
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case (KeyEvent.VK_SPACE):
			if (timer.isRunning())
				timer.stop();
			else 
				timer.start();
			break;

		case (KeyEvent.VK_LEFT):
			timer.setDelay(timer.getDelay() + 10);
			break;
		case (KeyEvent.VK_RIGHT):
			timer.setDelay(Math.max(0, timer.getDelay() - 10));
			break;
		case (KeyEvent.VK_ESCAPE):
			System.exit(0);
			break;

		case (KeyEvent.VK_BACK_SPACE):
			grid.clear();
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}