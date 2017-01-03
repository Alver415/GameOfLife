
public class Test{

	public static void main(String[] a){
		new Test();
	}

	int[][] grid = new int[10][10];
	
	public Test(){
		grid[0][0] = 1;
		grid[1][0] = 1;
		grid[0][1] = 1;
		grid[1][1] = 1;

		grid[2][2] = 1;
		grid[3][2] = 1;
		grid[2][3] = 1;
		grid[3][3] = 1;

		grid[9][9] = 1;
		grid[9][8] = 1;
		grid[9][7] = 1;
		
		print();
		
		for (int i = 0; i < 5; i++){
			update();
			print();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {		}
		}

	}
	
	public int get(int x, int y){
		try{
			return grid[x % grid.length][y % grid.length];
		}catch(Exception e){
			return 0;
		}
	}
	public int up(int x, int y){
		try{
			return grid[x][y - 1];
		}catch(Exception e){
			return 0;
		}
	}
	public int down(int x, int y){
		try{
			return grid[x][y + 1];
		}catch(Exception e){
			return 0;
		}
	}
	public int left(int x, int y){
		try{
			return grid[x - 1][y];
		}catch(Exception e){
			return 0;
		}
	}
	public int right(int x, int y){
		try{
			return grid[x + 1][y];
		}catch(Exception e){
			return 0;
		}
	}	
	public int upLeft(int x, int y){
		try{
			return grid[x - 1][y - 1];
		}catch(Exception e){
			return 0;
		}
	}
	public int upRight(int x, int y){
		try{
			return grid[x + 1][y - 1];
		}catch(Exception e){
			return 0;
		}
	}
	public int downLeft(int x, int y){
		try{
			return grid[x - 1][y + 1];
		}catch(Exception e){
			return 0;
		}
	}
	public int downRight(int x, int y){
		try{
			return grid[x + 1][y + 1];
		}catch(Exception e){
			return 0;
		}
	}

	public void update(){
		int[][] newGrid = new int[grid.length][grid[0].length];
		
		for (int i = 0; i < grid[0].length; i++){
		for (int j = 0; j < grid.length;    j++){
			int n = get(j, i);
			int u = up(j, i);
			int d = down(j, i);
			int l = left(j, i);
			int r = right(j, i);
			int ul = upLeft(j, i);
			int ur = upRight(j, i);
			int dl = downLeft(j, i);
			int dr = downRight(j, i);
			
			int sum = u + d + l + r + ul + ur + dl + dr;
			
			if (n == 1){
				if 		(sum < 2)
					newGrid[j][i] = 0;
				else if (sum > 3)
					newGrid[j][i] = 0;
				else
					newGrid[j][i] = 1;
			}
			else if (sum == 3){
				newGrid[j][i] = 1;
			}
		}
		}
		
		grid = newGrid;
	}
	
	
	public void print(){
		StringBuilder s = new StringBuilder(150);
		s.append("\n");
		
		for (int i = 0; i < grid[0].length; i++){
		for (int j = 0; j < grid.length;    j++){
			s.append(grid[j][i] + " ");
		}	
		s.append("\n");
		}
		
		
		System.out.println(s);
		
	}
}
