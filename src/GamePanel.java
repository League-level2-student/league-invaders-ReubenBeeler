
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = END;
	
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font descriptFont = new Font("Times New Roman", Font.PLAIN, 20);

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		
	}
	
	void updateEndState() {
		
	}
	
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		
		g.setColor(Color.WHITE);
		
		g.setFont(titleFont);
		g.drawString("LEAGUE INVADERS", 20, 100);
		
		g.setFont(descriptFont);
		g.drawString("Press ENTER to start!", 150, 380);
		g.drawString("Press SPACE for instructions.", 120, 530);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setColor(Color.WHITE);
		
		g.setFont(titleFont);
		g.drawString("GAME OVER", 95, 100);
	}
}
