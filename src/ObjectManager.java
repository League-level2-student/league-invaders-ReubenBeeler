
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	Rocketship rocketship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;
	}
	
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void update() {
		rocketship.update();
		for (Alien alien : aliens) {alien.update();}
		for (Projectile projectile : projectiles) {projectile.update();}
		
		checkCollision();
		purgeObjects();
		}

	
	void draw(Graphics g) {
		rocketship.draw(g);
		for (Alien alien : aliens) {alien.draw(g);}
		for (Projectile projectile : projectiles) {projectile.draw(g);}
	}
	
	void purgeObjects() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			if (!aliens.get(i).isActive) {aliens.remove(i);}
		}
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			if (!projectiles.get(i).isActive) {projectiles.remove(i);}
		}
	}
	
	void checkCollision() {
		for (Alien alien : aliens) {
			if (alien.collisionBox.intersects(rocketship.collisionBox)) {
				alien.isActive = false;
				rocketship.isActive = false;
			}
			for (Projectile projectile : projectiles) {
				if (alien.collisionBox.intersects(projectile.collisionBox)) {
					alien.isActive = false;
					projectile.isActive = false;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
	}
	
}