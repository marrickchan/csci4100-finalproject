package net.uoit.mcjb.csci4100_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016-11-22.
 */

public class GameHelper {
    // Resources from Screen
    private RelativeLayout screen;
    private ImageView[] towers;
    private Context context;
    // Game Running
    private int STAGE_CODE;
    // private boolean gameRunning = true;
    // Set the timer to tick to run the game
    Timer t = new Timer();
    private long start;
    private int score = 0;
    private int lives = 10;
    private int gold = 150;

    // Monsters
    // Goblin Wave 1
    private Goblin gob1;
    private Goblin gob2;
    private Goblin gob3;
    private Goblin gob4;
    private Goblin gob5;

    // Towers
    // Goblin Wave 1
    private FireTower t1;

    // Wave Management
    private Map<Integer, Goblin> goblinsActive = new HashMap<Integer, Goblin>();
    private int[] goblinsAlive;
    private Map<Integer, FireTower> towersActive = new HashMap<Integer, FireTower>();
    private int wave = 0;
    private int waveEnemies = 0;

    public GameHelper(Context context, RelativeLayout screen, ImageView[] towers, int STAGE_CODE){
        this.screen = screen;
        this.towers = towers;
        this.context = context;
        this.STAGE_CODE = STAGE_CODE;

        // Initialize Information Bar
        TextView infoBar = (TextView)screen.findViewById(R.id.infoBar);
        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);

        // Create the goblins
        gob1 = new Goblin(context, screen, 0101, ++waveEnemies);
        goblinsActive.put(waveEnemies, gob1);
        gob2 = new Goblin(context, screen, 0101, ++waveEnemies);
        goblinsActive.put(waveEnemies, gob2);
        gob3 = new Goblin(context, screen, 0101, ++waveEnemies);
        goblinsActive.put(waveEnemies, gob3);
        gob4 = new Goblin(context, screen, 0101, ++waveEnemies);
        goblinsActive.put(waveEnemies, gob4);
        gob5 = new Goblin(context, screen, 0101, ++waveEnemies);
        goblinsActive.put(waveEnemies, gob5);

        goblinsAlive = new int[waveEnemies];
        for(int i = 0; i < waveEnemies; i++){
            goblinsAlive[i] = 1;
        }


        // Create tower
        t1 = new FireTower(towers[0].getX(), towers[0].getY(), towers[0], screen);

        // Start time of stage
        start = System.currentTimeMillis();

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runGame();
                for(int i = 1; i <= waveEnemies; i++){
                    System.out.println("Loop number: " + i);
                    // Check if goblin is alive
                    if(goblinsAlive[i-1] != 0) {
                        if (goblinsActive.get(i) != null) {
                            attack(t1, goblinsActive.get(i));
                        }
                    }

                }
            }
        },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                500);
    }

    private void runGame(){
        long currentTime = System.currentTimeMillis() - start;
        if(wave == 1) {
            if (currentTime > 2005 && currentTime < 2504) {
                gob1.start();
            } else if (currentTime > 5005 && currentTime < 5504) {
                gob2.start();
            } else if (currentTime > 8005 && currentTime < 8504) {
                gob3.start();
            } else if (currentTime > 11005 && currentTime < 11504) {
                gob4.start();
            } else if (currentTime > 14005 && currentTime < 14504) {
                gob5.start();
            }
        }
    }

    private void waveStart(){

    }

    private void waveEnd(){
        System.out.println("Wave is done");
        t.cancel();
    }

    public void kill(){
        t.cancel();
    }

    private boolean attack(FireTower tower, Goblin goblin){
        // Goblin Coordinates Center
        float gobX = (goblin.getX() + goblin.getWidth()) / 2;
        float gobY = (goblin.getY() + goblin.getHeight()) / 2;
        // Tower Location Center
        float centerX = (tower.returnX() + tower.getImageView().getWidth())/2;
        float centerY = (tower.returnY() + tower.getImageView().getHeight())/2;

        // Check Square Collision
        if ( // Check Horizontally
                (gobX > centerX - tower.returnRange() &&
                        gobX < centerX + tower.returnRange())
                        &&
                        // Check Vertically
                        (gobY > centerY - tower.returnRange() &&
                                gobY < centerY + tower.returnRange())) {


            if(goblin.setHP(goblin.getHP() - 2)){
                // Increase score
                // Remove goblin from active goblins
                goblinsActive.remove(goblin.getEnemyNumber());
                // 0 means goblin is dead
                goblinsAlive[goblin.getEnemyNumber()-1] = 0;
                // End Wave
                if(goblinsActive.size() == 0){
                    waveEnd();
                }
            }
        }
        return false;
    }
}
