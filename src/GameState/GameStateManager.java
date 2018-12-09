package GameState;
import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;

public class GameStateManager {
	
	private GameState[] gameStates;
	public int currentState;
	
	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
        public static final int DEAD = 2;
        //public static final int SUPERMANDEAD = 4;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
            Timer timer = new Timer();
		if(state == MENUSTATE){
			gameStates[state] = new MenuState(this);
                       // if(state!=LEVEL1STATE)
                //ggMusic = new SoundEffects("/Resources/Music/level1-1.mp3");
                //ggMusic.play(  );
 
                }
                        if(state == LEVEL1STATE){
			gameStates[state] = new Level1State(this);
                        //ggMusic.close();
                        if(state!=MENUSTATE||state!=DEAD)
               // ggMusic = new SoundEffects("/Resources/Music/level1.mp3");
               // ggMusic.play(  );
                
                        timer.cancel();
                        }
                        
                 if(state == DEAD){
			gameStates[state] = new Dead(this);
                    //    ggMusic.close();
                   //    if(state!=LEVEL1STATE||state!=MENUSTATE){
               // ggMusic = new SoundEffects("/Resources/Music/dead1.mp3");
                //ggMusic.play(  );
                      // } 
                 }
                 
                
        }
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	
}









