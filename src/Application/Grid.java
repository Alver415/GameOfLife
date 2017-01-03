package Application;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Grid extends JPanel{
	
	private final int rows;
	private final int cols;

	private final Cell[][] cells;
	
	public Grid(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.cells = new Cell[rows][cols];
		
		for (int i = 0; i < rows ; i++){
		for (int j = 0; j < cols; j++){
			add(cells[i][j] = new Cell());
		}
		}
		resize(20);
		setLocation(5, 5);
		setSize(20 * rows + 5, 20 * cols + 5);
		setLayout(null);
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return cols;
	}
	
	public int getDimensions(){
		return rows * cols;
	}
	
	public Cell get(int x, int y){
		if (x < 0)
			x += rows;
		if (y < 0)
			y += cols;
		return cells[x % rows][y % cols];
	}
	public void set(int x, int y, boolean state){
		get(x, y).set(state);
	}
	
	public void resize(int size){
		for (int i = 0; i < rows; i++){
		for (int j = 0; j < cols;  j++){
			Cell c = cells[i][j];
			c.setLocation(size * i, size * j);
			c.setSize(new Dimension(size, size));
		}
		}	
		
	}
	
	public void update(){
		for (int i = 0; i < cols; i++){
		for (int j = 0; j < rows;  j++){
	
			int n  = get(j, i).toInt();
			int u  = get(j, i - 1).toInt();
			int d  = get(j, i + 1).toInt();
			int l  = get(j - 1, i).toInt();
			int r  = get(j + 1, i).toInt();
			int ul = get(j - 1, i - 1).toInt();
			int ur = get(j + 1, i - 1).toInt();
			int dl = get(j - 1, i + 1).toInt();
			int dr = get(j + 1, i + 1).toInt();
			
			int sum = u + d + l + r + ul + ur + dl + dr;

			if (n == 1){
				if 		(sum < 2)
					cells[j][i].setNext(Cell.OFF);
				else if (sum > 3)
					cells[j][i].setNext(Cell.OFF);
				else
					cells[j][i].setNext(Cell.ON);
			}
			else if (sum == 3){
				cells[j][i].setNext(Cell.ON);
			}
			else{
				cells[j][i].setNext(Cell.OFF);
			}
		}
		}
		for (int i = 0; i < cols; i++){
		for (int j = 0; j < rows;  j++){
			cells[j][i].set(cells[j][i].getNext());
		}
		}
	}
	
	public void clear(){
		for (int i = 0; i < cols; i++){
		for (int j = 0; j < rows;  j++){
			cells[j][i].set(Cell.OFF);
		}
		}
	}
	
	public void print(){
		StringBuilder s = new StringBuilder(150);
		s.append("\n");
		
		for (int i = 0; i < cols; i++){
		for (int j = 0; j < rows;  j++){
			s.append(get(j, i) + " ");
		}	
		s.append("\n");
		}
		
		
		System.out.println(s);
		
	}

}
