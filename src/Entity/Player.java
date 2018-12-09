package Entity;

import GameState.GameState;
import GameState.GameStateManager;
import  GameState.MenuState;
import TileMap.*;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import GameState.Level1State;
import java.util.TimerTask;
import TileMap.*;

public class Player extends MapObject {
  public int getLives() { 
      return playerdie; 
  }
	private Background bm;
        private BufferedImage image;
         public int playerdie=5;
	// player stuff
	private int health;
	private int maxHealth;
	private int fire;
        
	private int maxFire;
       // private SoundEffects ggMusic;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	// fireball
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;
	
	// scratch
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	
	// gliding
	private boolean gliding;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		3,7,1,1,1,4,7
	};
	
	// animation actions
	public static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
        
        // Scoring
        private int points;
        
	
	public Player(TileMap tm) {
		
		super(tm);
		
		width = 80;
		height = 120;
		cwidth = 40;
		cheight = 57;
		
		moveSpeed = 1.6;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 10;
		fire = maxFire = 2500;
		
		fireCost = 200;
		fireBallDamage = 1;
		fireBalls = new ArrayList<FireBall>();
		
		scratchDamage = 8;
		scratchRange = 40;
                points = 0;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Resources/Sprites/Player/playersprites2.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
                            
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i == 0) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height+9,
								width,
								height-10
						);
					}
                                        else if(i == 1) {
						bi[j] = spritesheet.getSubimage(
								j * (width+20),
								i * height,
								(width-45)*2,
								height
						);
					}
                                        else if(i == 2) {
						bi[j] = spritesheet.getSubimage(
								j * (width),
								i * height,
								(width),
								height
						);
					}
                                        else if(i == 3) {
						bi[j] = spritesheet.getSubimage(
								j * (width),
								i * height+10,
								(width),
								height
						);
					}
                                         else if(i == 4) {
						bi[j] = spritesheet.getSubimage(
								j * (width),
								i * (height+6),
								(width)*2,
								height-20
						);
					}
                                         else if(i == 5) {
                                             
                                                 
                                             
						bi[j] = spritesheet.getSubimage(
								j * (width+5),
								i * height,
								(width),
								height
						);
					}
                                         else if(i == 6) {
                                             
						bi[j] = spritesheet.getSubimage(
								j * (width+5),
								i * height,
								(width),
								height
						);
					}
					else {
                                             
						bi[j] = spritesheet.getSubimage(
								j * (width+15) ,
								i * height,
								width,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
                
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
        public int getPoints() { return points; }
	public int getFire() { return fire; }
	public int getMaxFire() { return maxFire; }
        //public int getScore(){ return score; }
	
	public void setFiring() { 
		firing = true;
               // ggMusic = new SoundEffects("/Resources/Music/bullet.mp3");
                                                 //ggMusic.play(  );
                                                 
	}
	public void setScratching() {
		scratching = true;
	//ggMusic = new SoundEffects("/Resources/Music/kick.mp3");
                                                 //ggMusic.play(  );
                                                 
        }
	public void setGliding(boolean b) { 
		gliding = b;
                
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed+1;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		// cannot move while attacking, except in air
		if(
		(currentAction == SCRATCHING || currentAction == FIREBALL) &&
		!(jumping || falling)) {
			dx = 0;
		}
		
		// jumping
		if(jumping && !falling) {
			dy = jumpStart;
                        
                                                 
			falling = true;	
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping){ dy += stopJumpSpeed;}
			if(dy > maxFallSpeed){ dy = maxFallSpeed;}
                                               
                        
		}
		
	}
       
        public void checkAttack(ArrayList<Enemy> enemies) {
		
		// loop through enemies
		for(int i = 0; i < enemies.size(); i++) {
			
			Enemy e = enemies.get(i);
			
			// scratch attack
			if(scratching) {
				if(facingRight) {
					if(
						e.getx() > x &&
						e.getx() < x + scratchRange && 
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
                                                //ggMusic = new SoundEffects("/Resources/Music/hela.mp3");
                                                // ggMusic.play(  );
					}
				}
				else {
					if(
						e.getx() < x &&
						e.getx() > x - scratchRange &&
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
			}
			
			// fireballs
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
                                        hit2();
                                        
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision
			if(intersects(e)) {
				hit(e.getDamage());
                                
			}
			
		}
		
	}
        
        public void checkPoints(ArrayList<Score> scoring) {
		
		// loop through coins
		for(int i = 0; i < scoring.size(); i++) {
			
			Score z = scoring.get(i);
			
			
			
			// check coin collision
			if(intersects(z)) {
				hit2();
                                System.out.println("SHOSH");
                                z.hit(z.getDamage());
			}
			
		}
		
	}
        
        public void checkBank(ArrayList<Bank> banking) {
		
		// loop through coins
		for(int i = 0; i < banking.size(); i++) {
			
			Bank w = banking.get(i);
			
			
			
			// check coin collision
			if(intersects(w)) {
				hit3(w.getDamage());
                                //hit(w.getDamage());
                                System.out.println("BANK SHOSH");
                                //z.hit(z.getDamage());
			}
			
		}
		
	}
        
        public void checkShopping(ArrayList<Shop> shopping) {
		
		// loop through coins
		for(int i = 0; i < shopping.size(); i++) {
			
			Shop t = shopping.get(i);
			
			
			
			// check coin collision
			if(intersects(t)) {
				//hit3(w.getDamage());
                                //hit(w.getDamage());
                                System.out.println("SHOP SHOSH");
                                //z.hit(z.getDamage());
                                hit4();
			}
			
		}
		
	}
        
        public void hit4() {
		if(flinching) return;
		points = points -20;
                health++;
                if(health > 10)
                    health = 10;
                
      
                
		flinching = true;
		flinchTimer = System.nanoTime();
	}
        
        public void hit2() {
		//if(dead || flinching) return;
		points = points +5;
      
                
		flinching = true;
		flinchTimer = System.nanoTime();
	}
        
        public void hit3(int damage) {
		if(dead || flinching) return;
		points = points +20;
                health -= damage;
		if(health < 0) health = 0;
		if(health == 0) {
                  
                 dead = true;
                }
                
                if(dead== true){
           //         setDead();
                    System.out.print("ok");
                    playerdie=1;
                    	//bm= new Background("/Resources/Backgrounds/back.gif", 1);
                        try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/Resources/HUD/end.gif"
				)
			);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
               
		
                  
  
                }
                
		flinching = true;
		flinchTimer = System.nanoTime();
	}
        
        public void hit(int damage) {
		if(flinching) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) {
                  
                 dead = true;
                }
                
                if(dead== true){
           //         setDead();
                    System.out.print("ok");
                    playerdie=1;
                    	//bm= new Background("/Resources/Backgrounds/back.gif", 1);
                        try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/Resources/HUD/end.gif"
				)
			);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
               
		
                  
  
                }
		flinching = true;
		flinchTimer = System.nanoTime();
	}
        
        
        
       
	
       
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
                
                // check attack has stopped
		if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) scratching = false;
		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()){ firing = false;}
                        }
                
                // fireball attack
		fire += 1;
		if(fire > maxFire) fire = maxFire;
		if(firing && currentAction != FIREBALL) {
			if(fire > fireCost) {
				fire -= fireCost;
				FireBall fb = new FireBall(tileMap, facingRight);
				fb.setPosition(x, y-20);
				fireBalls.add(fb);
			}
                }
                // update fireballs
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
                
                
		// check done flinching
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}
                
                
		
		// set animation
		if(scratching) {
			if(currentAction != SCRATCHING) {
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(firing) {
			if(currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(2000);
				width = 30;
                                
                               
                                
                                
                                
                                }
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(120);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(180);
				width = 40;
			}
		}
		
		animation.update();
		
                
                
                if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) scratching = false;
		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()) firing = false;
		}
                
		// set direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
                                   
                // draw fireballs
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
                }	
		// draw player
                
                if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2 + width),
				(int)(y + ymap - height / 2),
				-width-40,
				height,
				null
			);
			
		}
                
                
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
                        
		}
		
		
                
		
	}

    
	
}

















