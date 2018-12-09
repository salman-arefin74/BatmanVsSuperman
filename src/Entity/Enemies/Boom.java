package Entity.Enemies;

import Entity.*;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Boom extends Enemy {
	
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	
	
	public Boom(TileMap tm) {
		
		super(tm);
		
		
		
		moveSpeed = 0.3;
                maxSpeed = 0.3;
		/*if(right)dx = moveSpeed;
		else dx = -moveSpeed;*/
		
		width = 370;
		height = 21;
		cwidth = 190;
		cheight = 14;
		
                damage = 1;
		//Loading sprites
		try {
                    
                    sprites = new BufferedImage[11];
			
                    
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Resources/Sprites/Enemies/laser3.gif"
				)
			);
                        
			
			for(int i = 0; i < sprites.length; i++) {
                           
				sprites[i] = spritesheet.getSubimage(
					 20,
					i * height,
					330,
					height
				);
                        System.out.print("Visible");
                        
                            		
                    }
                        
                animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(240);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
                
                
		
	}

   
	
	public void setHit() {
		if(hit) return;
		hit = true;
		
		animation.setDelay(240);
		dx = 0;
                
	}
	
	public boolean shouldRemove() { return remove; }
        
        private void getNextPosition() {
		
		// movement
		//if(left) {
			//dx -= moveSpeed;
			//if(dx < -(maxSpeed+10)) {
                          //  dx = maxSpeed-20;
		//}
		//}
		/*else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = 1+maxSpeed;
			}
		}*/
		
		
		
	}
	
	public void update() {
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
                getNextPosition();
                
                /*if(right && dx == 0) {
			right = false;
			left = false;
			facingRight = false;
		}*/
		
		if(dx == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}


















