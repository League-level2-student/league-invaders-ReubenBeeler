import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font descriptFont = new Font("Times New Roman", Font.PLAIN, 20);
	
	Timer frameDraw;
	Timer alienSpawn;
	
	Rocketship myRocketship = new Rocketship(225, 700, 50, 50);
	
	ObjectManager objectManager = new ObjectManager(myRocketship);
	
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	GamePanel() {
		frameDraw  = new Timer(10, this);
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
		objectManager.update();
		if (myRocketship.isActive == false) {
			alienSpawn.stop();
			currentState = END;
		}
		if(up && myRocketship.y >= 50) {myRocketship.up();}
		if(down && myRocketship.y <= (760 - myRocketship.height)) {myRocketship.down();}
		if(left && myRocketship.x >= 0) {myRocketship.left();}
		if(right && myRocketship.x <= (500 - myRocketship.width)) {myRocketship.right();}
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
		try {
			g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("space.png")), 0, 0, 500, 800, null);
		} catch (IOException e) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		objectManager.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setColor(Color.WHITE);
		
		g.setFont(titleFont);
		g.drawString("GAME OVER", 95, 100);
		
		g.setFont(descriptFont);
		g.drawString("Score: " + objectManager.score, 200, 200);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    if (currentState == END) {currentState = MENU;}
		    else {
		        currentState++;
		        if (currentState == GAME) {startGame();}
		        else {alienSpawn.stop();}
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {up = true;}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {down = true;}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {left = true;}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {right = true;}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == GAME) {objectManager.addProjectile(myRocketship.getProjectile());}
			else if (currentState == MENU) {JOptionPane.showMessageDialog(this, "Arrow keys to move, press spacebar to shoot a projectile. If your spaceship hits an alien, you lose.");}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {up = false;}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {down = false;}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {left = false;}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {right = false;}
		}
	}
	
	public void startGame() {
		alienSpawn = new Timer(500, objectManager);
		alienSpawn.start();
	}
	
}