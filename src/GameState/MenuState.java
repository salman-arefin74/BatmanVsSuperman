package GameState;
import java.util.Timer;
import java.util.TimerTask;

import TileMap.Background;
import java.applet.AudioClip;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MenuState extends GameState {
	int i=0;
	private Background bg;
	
	public int currentChoice = 0;
        //private SoundEffects ggMusic;
        
	private String[] options = {
		"Start",
		"Controls",
		"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
               
		bg = new Background("/Resources/Backgrounds/new/tmp-"+i+".gif", 1);
               
		try {
                     Timer timer = new Timer();
                     TimerTask task = new TimerTask() {
                        @Override
                     public void run() {
                          i++;
                          if(i==67){
                                i=0;
                                i++;
       
                    }
                    bg = new Background("/Resources/Backgrounds/new/tmp-"+i+".gif", 1);
    
             }
         };

  
                    timer.schedule(task, 1,60);
                    //bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Jokerman",
					Font.PLAIN,
					28);
			
			font = new Font("Impact", Font.TRUETYPE_FONT, 40);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		//g.drawString("Batman VS SuperMan", 330, 170);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 480, 250 + i * 62);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
                        
                        
		}
		if(currentChoice == 1) {
			// help
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}










