package Entity.Enemies;

import Entity.*;
import TileMap.TileMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class Resturant extends Shop {
	
	private BufferedImage[] sprites;
	
	public Resturant(TileMap tm) {
		
		super(tm);
		
		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
                
                width = 100;
		height = 130;
		cwidth = 40;
		cheight = 57;
                
                health2 = maxHealth = 1;
		damage = 1;
                
                //Load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Resources/Sprites/Enemies/shop.gif"
				)
			);
			
			sprites = new BufferedImage[1];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * (width),
					0,
					width,
					120
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
                animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(350);
		
		right = true;
		facingRight = true;
        }

    
        
        private void getNextPosition() {
		
		/*// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = 1+maxSpeed;
			}
		}*/
		
		// falling
		/*if(falling) {
			dy += fallSpeed;
		}*/
		
	}
	
        public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
		/*// if it hits a wall, go other direction
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}*/
		
		// update animation
		animation.update();
		
	}
        
        public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
		
	}
}