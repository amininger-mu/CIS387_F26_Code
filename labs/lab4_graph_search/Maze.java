import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Maze {
	public record Coord(int row, int col) { 
		public Coord add(int dr, int dc) {
			return new Coord(row + dr, col + dc);
		}
	} 

	private enum Square {
		WALL, OPEN
	}
	
	private Coord start = null;
	private Coord goal = null;
	private Square[][] squares = null;
	private int width;
	private int height;

	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		generateMaze();
	}
	
	public Maze(File mazeFile) {
		readMazeFile(mazeFile);
	}

	public int width() { 
		return width;
	}

	public int height() {
		return height;
	}

	public int start() {
		return start.row * width + start.col;
	}

	public int goal() {
		return goal.row * width + goal.col;
	}

	public Coord getCoord(int v) {
		return new Coord(v / width, v % width);
	}

	public void populateGraph(Graph g) {
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (squares[r][c] == Square.WALL) continue;

				int v = r*width + c;

				if (r < height-1 && squares[r+1][c] == Square.OPEN) {
					g.addEdge(v, (r+1)*width + c);
				}
				if (c < width-1 && squares[r][c+1] == Square.OPEN) {
					g.addEdge(v, r*width + (c+1));
				}
			}
		}
	}
	
	private void generateMaze() {
		// Create array and fill with walls
		squares = new Square[height][width];
		for (int row = 0; row < height; row++) {
			Arrays.fill(squares[row], Square.WALL);
		}

		int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

        var stack = new LinkedList<Coord>();

		// Start at the top-left corner
		start = new Coord(1, 1);
		goal = null;
		stack.add(start);
		squares[1][1] = Square.OPEN;
 
        while (!stack.isEmpty()) {
			Coord coord = stack.peek();

			// check all 4 directions to find those that have not been visisted
			List<Integer> nextDirs = new ArrayList<Integer>();
			for (int i = 0; i < dirs.length; i++) {
				Coord next = coord.add(dirs[i][0]*2, dirs[i][1]*2);
				if (next.row > 0 && next.col > 0 && next.row < height && next.col < width 
					&& squares[next.row][next.col] == Square.WALL) {
					nextDirs.add(i);
				}
			}

			// If no open directions, then back up the search
            if (nextDirs.isEmpty()) {
				Coord end = stack.pop();
				if (goal == null && (end.row > height-3 || end.col > width-3)) {
					goal = end;
				}

                continue;
            }

			// Choose a random direction, then go to it
			int i = (int)(Math.random() * nextDirs.size());
			int[] dir = dirs[nextDirs.get(i)];
			squares[coord.row + dir[0]][coord.col + dir[1]] = Square.OPEN;  // mark the in-between square as well
			squares[coord.row + 2*dir[0]][coord.col + 2*dir[1]] = Square.OPEN;

			stack.push(coord.add(2*dir[0], 2*dir[1]));
        }

		// Add a few gaps to make the maze less linear
		for (int i = 0; i < width; i++) {
			while(true) {
				int row = (int)(Math.random() * height);
				int col = (int)(Math.random() * width);
				if (row > 0 && row < height-1 && col > 0 && col < width-1 
					&& ((row + col) % 2 == 1) && squares[row][col] == Square.WALL) {
					squares[row][col] = Square.OPEN;
					break;
				}
			}
		}
	}
	
	private void readMazeFile(File mazeFile) {
		ArrayList<String> rows = new ArrayList<String>();
		width = 0;

		try (Scanner mazeReader = new Scanner(mazeFile)) {
			while(mazeReader.hasNextLine()) {
				String line = mazeReader.nextLine();
				if (width == 0) {
					width = line.length();
				} else if (line.length() != width) {
					continue;
				}
				rows.add(line);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("mazeFile does not exist");
		}

		height = rows.size();
		if (height == 0) {
			throw new IllegalArgumentException("No maze data was read");
		}
		
		squares = new Square[height][width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				squares[r][c] = Square.OPEN;
				char letter = rows.get(r).charAt(c);
				if (letter == 'S') {
					start = new Coord(r, c); 
				} else if (letter == 'G') {
					goal = new Coord(r, c);
				} else if (letter == 'X') {
					squares[r][c] = Square.WALL;
				}
			}
		}
		if (start == null) {
			throw new IllegalArgumentException("No start position given on maze (character S)");
		}
		if (goal == null) {
			throw new IllegalArgumentException("No goal position given on maze (character G)");
		}
	}
	
	public void draw(Graphics2D g2, int sqSize) {
		// Draw each square
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				switch (squares[r][c]) {
					case WALL:  g2.setColor(Color.BLUE); break;
					case OPEN: g2.setColor(Color.WHITE); break;
				}
				g2.fillRect(c*sqSize, r*sqSize, sqSize, sqSize);
				g2.setColor(Color.BLACK);
				g2.drawRect(c*sqSize, r*sqSize, sqSize, sqSize);
			}
		}

		// Draw Start
		g2.setColor(Color.orange);
		g2.fillOval(start.col*sqSize, start.row*sqSize, sqSize, sqSize);

		// Draw Goal
		g2.setColor(Color.green);
		g2.fillOval(goal.col*sqSize, goal.row*sqSize, sqSize, sqSize);
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				switch (squares[r][c]) {
					case WALL: sb.append("X"); break;
					case OPEN: sb.append(" "); break;
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
