package GameState;
import Entity.*;
import Main.GamePanel;
import TileMap.*;
import Entity.Enemies.*;
//import Entity.Coins.*;
import Entity.Player;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.*;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	private HUD hud;
        
        private Boom laser;
        public int dub =0;
	private Player player;
	private ArrayList<Enemy> enemies;
        private ArrayList<Score> scoring;
        private ArrayList<Bank> banking;
        private ArrayList<Shop> shopping;
        private ArrayList<Explosion> explosions;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
                
              
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Resources/Tilesets/tiles.gif");
		tileMap.loadMap("/Resources/Maps/Map2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Resources/Backgrounds/back.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
                
                explosions = new ArrayList<Explosion>();
               // populateCoins();
                populateEnemies();
                //populateCoins();
                
                /*//test
                enemies = new ArrayList <Enemy>();
                Slugger s;
                s = new Slugger(tileMap);
                s.setPosition(100, 100);
                enemies.add(s);*/
                
                hud = new HUD(player);
                //laser = new Boom(tileMap);
		
	}
        /*private void populateCoins(){
            scoring = new ArrayList<Score>();
            Coins c;
            
            Point[] points5 = new Point[] {
			new Point(1000, 200),
			//new Point(7500, 440),
			//new Point(9300, 385),
			//new Point(1680, 200),
			//new Point(1800, 200)
		};
                
                
		for(int i = 0; i < points5.length; i++) {
			c = new Coins(tileMap);
			c.setPosition(points5[i].x, points5[i].y);
			scoring.add(c);
                }
        }*/
	
        private void populateEnemies() {
		
		//enemies = new ArrayList<Enemy>();
                scoring = new ArrayList<Score>();
		banking = new ArrayList<Bank>();
		shopping = new ArrayList<Shop>();
                
		//Slugger s;
                //Joker j;
                //Boom b;
                //Superman p;
                Coins c;
                House h;
                Resturant r;
                
                
		/*Point[] points = new Point[] {
                        new Point(320, 200),
			new Point(1700, 200),
			new Point(2400, 200),
                        new Point(4200, 200),
			new Point(4800, 200),
                        //new Point(5500, 200),
                        new Point(6100, 325),
                        new Point(6600, 270),
                        new Point(7340, 100),
		};
		for(int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}*/
                
                Point[] points5 = new Point[] {
			new Point(1000, 300),
			new Point(1800, 340),
                        new Point(2500, 300),
			new Point(3300, 365),
                        new Point(3900, 400),
			new Point(4750, 300),
			new Point(5700, 330),
			new Point(6800, 250),
			new Point(7700, 180),
                        new Point(8150, 230)
		};
                
                
		for(int i = 0; i < points5.length; i++) {
			c = new Coins(tileMap);
			c.setPosition(points5[i].x, points5[i].y);
			scoring.add(c);
                }
                
                Point[] points6 = new Point[] {
			new Point(4400,400),
			new Point(6600, 272),
                        new Point(8500, 368),
			//new Point(1000,400),
		};
                
                
		for(int i = 0; i < points6.length; i++) {
			h = new House(tileMap);
			h.setPosition(points6[i].x, points6[i].y);
			banking.add(h);
                }
                
                /*Point[] points2 = new Point[] {
			//new Point(3000, 300),
			
			//new Point(1680, 200),
			//new Point(1800, 200)
		};
		for(int i = 0; i < points2.length; i++) {
			j = new Joker(tileMap);
			j.setPosition(points2[i].x, points2[i].y);
			enemies.add(j);
		}*/
                
                /*Point[] points3 = new Point[] {
			new Point(8865, 360),
			//new Point(7500, 440),
			//new Point(9300, 385),
			//new Point(1680, 200),
			//new Point(1800, 200)
		};
                
                
		for(int i = 0; i < points3.length; i++) {
			b = new Boom(tileMap);
			b.setPosition(points3[i].x, points3[i].y);
			enemies.add(b);
		}*/
                
                /*Point[] points4 = new Point[] {
			new Point(9090, 370),
                        //new Point(1000, 300),
			//new Point(9300, 385),
			//new Point(1680, 200),
			//new Point(1800, 200)
		};
                
                
		for(int i = 0; i < points4.length; i++) {
			p = new Superman(tileMap);
			p.setPosition(points4[i].x, points4[i].y);
			enemies.add(p);
                }*/
                
                Point[] points7 = new Point[] {
			new Point(7450,185),
			//new Point(6600, 272),
                        //new Point(8500, 368),
			//new Point(1000,400),
		};
                
                
		for(int i = 0; i < points7.length; i++) {
			r = new Resturant(tileMap);
			r.setPosition(points7[i].x, points7[i].y);
			shopping.add(r);
                }
                
                
                
                
                
	}
	
	
	public void update() {
		if(player.getLives() == 1) {
				gsm.setState(GameStateManager.DEAD);
			}
		// update player
		player.update();
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);
                // set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
                
                // attack enemies
		//player.checkAttack(enemies);
                
                //get coins
                player.checkPoints(scoring);
                
                //get bank
                player.checkBank(banking);
                
                //get shop
                player.checkShopping(shopping);
               
             /*   // update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
                        if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
                                        new Explosion(e.getx(), e.gety()));
			}
                        
			
		}*/
                
                // update scoring
		for(int i = 0; i < scoring.size(); i++) {
			Score q = scoring.get(i);
			q.update();
                        //scoring.get(i).update();
                        if(q.isDead()) {
				scoring.remove(i);
				i--;
				//explosions.add(
                                   //     new Explosion(q.getx(), q.gety()));
			}
			
		}
                
                // update banking
		for(int i = 0; i < banking.size(); i++) {
			Bank w = banking.get(i);
			w.update();
                        //scoring.get(i).update();
                        if(w.isDead()) {
				banking.remove(i);
				i--;
				//explosions.add(
                                   //     new Explosion(q.getx(), q.gety()));
			}
			
		}
                
                // update shopping
		for(int i = 0; i < shopping.size(); i++) {
			Shop t = shopping.get(i);
			t.update();
                        //scoring.get(i).update();
                        if(t.isDead()) {
				shopping.remove(i);
				i--;
				//explosions.add(
                                   //     new Explosion(q.getx(), q.gety()));
			}
			
		}
                
                // update explosions
	/*	for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}*/
                
                
                   
                
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
                // draw enemies
		/*for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}*/
                
                // draw explosions
		/*for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}*/
                
                //draw hud
                hud.draw(g);
                
                //Draw coins
                for(int i = 0; i < scoring.size(); i++) {
                    scoring.get(i).draw(g);
		}
                    
                //Draw banks
                for(int i = 0; i < banking.size(); i++) {
                    banking.get(i).draw(g);
		}
                
                //Draw shops
                for(int i = 0; i < shopping.size(); i++) {
                    shopping.get(i).draw(g);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}
	
}












