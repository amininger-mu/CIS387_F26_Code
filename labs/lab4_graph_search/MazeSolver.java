import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MazeSolver extends JFrame {
	protected int SQUARE_SIZE = 50;
	
	protected Maze maze;
	protected Graph G;
	protected PathSearch pathSearch;
	
	protected Font uiFont; // the Font to use for UI elements
	private JPanel menu;
	private JLabel pathLengthLabel;
	private JLabel numVisitedLabel;
	
	public MazeSolver(File mazeFile) {
		if (mazeFile != null) {
			maze = new Maze(mazeFile);
			initGraph();
		} else {
			randomizeMaze();
		}

		this.add(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw((Graphics2D)g);
			}
		}, BorderLayout.CENTER);

		uiFont = new Font("Source Sans 3", Font.BOLD, 24);
		createMenu();

		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGraph() {
		G = new AdjacencyList(maze.width() * maze.height());
		maze.populateGraph(this.G);
		pathSearch = null;
		this.SQUARE_SIZE = 1000 / maze.width();
	}
	
	protected void createMenu() {
		menu = new JPanel();

		JButton rand = new JButton("Randomize");
		rand.setFont(uiFont);
		rand.addActionListener(e -> randomizeMaze());
		this.menu.add(rand);
		
		JButton bfs = new JButton("BFS");
		bfs.setFont(uiFont);
		bfs.addActionListener(e -> search(new BreadthFirstSearch(G)));
		this.menu.add(bfs);

		JButton dfs = new JButton("DFS");
		dfs.setFont(uiFont);
		dfs.addActionListener(e -> search(new DepthFirstSearch(G)));
		this.menu.add(dfs);

		// Create a label to show path length information
		this.pathLengthLabel = new JLabel("Path Length:");
		this.pathLengthLabel.setFont(uiFont);
		this.menu.add(this.pathLengthLabel);

		// Create a label to show score information
		this.numVisitedLabel = new JLabel("# Visited:");
		this.numVisitedLabel.setFont(uiFont);
		this.menu.add(this.numVisitedLabel);

		((FlowLayout)this.menu.getLayout()).setHgap(30);

		this.add(menu, BorderLayout.PAGE_START);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SQUARE_SIZE * maze.width(), 
							SQUARE_SIZE * maze.height() + 100);
	}

	private void randomizeMaze() {
		this.maze = new Maze(51, 41);
		initGraph();
		this.repaint();
	}
	
	protected void search(PathSearch searchAlg) {
		System.out.printf("===== SEARCH: %s ======\n", searchAlg.getClass());
		this.pathSearch = searchAlg;

		long start = System.nanoTime();
		pathSearch.search(maze.start(), maze.goal());
		long elapsed = (System.nanoTime() - start)/1000;
		System.out.println("> Time to search: " + elapsed + "μs");

		this.repaint();
	}

	protected void draw(Graphics2D g2) {
		maze.draw(g2, SQUARE_SIZE);
		if (pathSearch != null) {
			boolean[] visited = pathSearch.wasVisited();
			drawVisited(g2, visited);

			var path = pathSearch.getPath(maze.goal());
			if (path != null) {
				drawPath(g2, path);
			} else {
				this.pathLengthLabel.setText("No Path Found");
				System.out.println("> No Path Found");
			}
		}
	}

	protected void drawVisited(Graphics2D g2, boolean[] visited) {
		// Update Label with # vertices visited
		int nVisited = 0;
		for (boolean b : pathSearch.wasVisited()) if(b) nVisited++;
		System.out.println("> Number of vertices visited: " + nVisited);
		numVisitedLabel.setText("# Visited: " + nVisited);

		// Draw a yellow square at each visited location
		g2.setColor(new Color(255, 255, 0, 150));
		for (int row = 0; row < maze.height(); row++) {
			for (int col = 0; col < maze.width(); col++) {
				int v = row*maze.width() + col;
				if (visited[v]) {
					g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
		}
	}
	
	protected void drawPath(Graphics2D g2, List<Integer> path) {
		// Update the label with the path length
		this.pathLengthLabel.setText("Path Length: " + path.size());
		System.out.println("> Path Length: " + path.size());

		// Draw the path as a series of line segments
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.red);
		int offX = SQUARE_SIZE / 2;
		int offY = SQUARE_SIZE / 2;
		for (int i = 1; i < path.size(); i++) {
			int v1 = path.get(i-1);
			int v2 = path.get(i);
			Maze.Coord begin = maze.getCoord(v1);
			Maze.Coord end = maze.getCoord(v2);
			g2.drawLine(begin.col()*SQUARE_SIZE + offX, begin.row()*SQUARE_SIZE + offY, 
						end.col()*SQUARE_SIZE + offX, end.row()*SQUARE_SIZE + offY);
		}
	}
	
	public static void main(String[] args) {
		File mazeFile = null;
		if (args.length > 0) {
			mazeFile = new File(args[0]);
		}
		MazeSolver solver = new MazeSolver(mazeFile);
	}
}
