import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	
	JFrame frame;
	private GamePanel gamePanel;
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 500;
	
	LeagueInvaders(JFrame frame, GamePanel gamePanel) {
		this.frame = frame;
		this.gamePanel = gamePanel;
	}
	
	public static void main(String args[]) {
		new LeagueInvaders(new JFrame(), new GamePanel()).setup();
	}
	
	void setup() {
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}