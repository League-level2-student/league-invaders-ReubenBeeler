import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font descriptFont = new Font("Times New Roman", Font.PLAIN, 20);
	
	Timer frameDraw;
	
	Rocketship myRocketship = new Rocketship(225, 700, 50, 50);
	
	GamePanel() {
		frameDraw  = new Timer(1000/60, this);
		frameDraw.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

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
		
		myRocketship.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setColor(Color.WHITE);
		
		g.setFont(titleFont);
		g.drawString("GAME OVER", 95, 100);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP && myRocketship.y >= 0) {
				myRocketship.up();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && myRocketship.y <= (HEIGHT - myRocketship.height)) {
				myRocketship.down();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && myRocketship.x >= 0) {
				myRocketship.left();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && myRocketship.x <= (WIDTH - myRocketship.width)) {
				myRocketship.right();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
}