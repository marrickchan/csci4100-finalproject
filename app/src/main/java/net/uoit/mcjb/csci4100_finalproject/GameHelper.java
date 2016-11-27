package net.uoit.mcjb.csci4100_finalproject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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
    private TextView infoBar;
    private Timer infoBarTimer = new Timer();
    final Handler myHandler = new Handler();
    // Game Running
    private int STAGE_CODE;
    // private boolean gameRunning = true;
    // Set the timer to tick to run the game
    private Timer t = new Timer();
    private long start;
    private int score = 0;
    private int lives = 10;
    private int gold = 250;

    // Monsters
    // Goblin Wave 1
    private Goblin gob1;
    private Goblin gob2;
    private Goblin gob3;
    private Goblin gob4;
    private Goblin gob5;

    // Towers
    private FireTower fireTowers[] = new FireTower[8];
    private boolean towerBuilt[] = new boolean[8];
    private Timer towerTimer[] = new Timer[8];
    // Helper For Listener
    private int currentTowerListenerHelper = 0;

    // Wave Management
    private Map<Integer, Goblin> goblinsActive = new HashMap<Integer, Goblin>();
    private int[] goblinsAlive;
    private int wave = 0;
    private int waveEnemies = 0;

    public GameHelper(final Context context, final RelativeLayout screen, final ImageView[] towers, int STAGE_CODE){
        this.screen = screen;
        this.towers = towers;
        this.context = context;
        this.STAGE_CODE = STAGE_CODE;

        // Initialize Information Bar
        infoBar = (TextView)screen.findViewById(R.id.infoBar);
        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
        // Towers all FALSE built
        for(int i = 0; i < towers.length; i++){
            towerBuilt[i] = false;
            towerTimer[i] = new Timer();
        }

        // Tower Slots
        towers[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[0]){
                    towerBuilt[0] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[0] = new FireTower(towers[0].getX(),
                                towers[0].getY(), towers[0], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);

                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[0].scheduleAtFixedRate(new TimerTask() {
                              @Override
                              public void run() {
                                  for (int i = 1; i <= waveEnemies; i++) {

                                      // Check if goblin is alive
                                      // Target first goblin system
                                      if (goblinsAlive[i - 1] != 0) {
                                          if (goblinsActive.get(i) != null) {

                                              // TO DO:
                                              // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS

                                              if(fireTowers[0] != null){
                                                  if(attack(fireTowers[0], goblinsActive.get(i))){
                                                      return;
                                                  }
                                              }


                                          }
                                      }
                                  }
                              }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[0].getAtkSpeed());

                    }
                }
            }
        });

        // Tower Slots
        towers[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[1]){
                    towerBuilt[1] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[1] = new FireTower(towers[1].getX(),
                                towers[1].getY(), towers[1], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[1].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[1] != null) {
                                                if(attack(fireTowers[1], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[1].getAtkSpeed());
                    }
                }
            }
        });

        // Tower Slots
        towers[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[2]){
                    towerBuilt[2] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[2] = new FireTower(towers[2].getX(),
                                towers[2].getY(), towers[2], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[2].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[2] != null) {
                                                if(attack(fireTowers[2], goblinsActive.get(i))){
                                                    return;
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[2].getAtkSpeed());
                    }
                }
            }
        });

        // Tower Slots
        towers[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[3]){
                    towerBuilt[3] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[3] = new FireTower(towers[3].getX(),
                                towers[3].getY(), towers[3], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        towerTimer[3].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[3] != null) {
                                                if(attack(fireTowers[3], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[3].getAtkSpeed());
                    }
                }
            }
        });

        // Tower Slots
        towers[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[4]){
                    towerBuilt[4] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[4] = new FireTower(towers[4].getX(),
                                towers[4].getY(), towers[4], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        towerTimer[4].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[4] != null) {
                                                if(attack(fireTowers[4], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[4].getAtkSpeed());

                    }
                }
            }
        });

        // Tower Slots
        towers[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[5]){
                    towerBuilt[5] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[5] = new FireTower(towers[5].getX(),
                                towers[5].getY(), towers[5], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        towerTimer[5].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[5] != null) {
                                                if(attack(fireTowers[5], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[5].getAtkSpeed());
                    }
                }
            }
        });

        // Tower Slots
        towers[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[6]){
                    towerBuilt[6] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[6] = new FireTower(towers[6].getX(),
                                towers[6].getY(), towers[6], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        towerTimer[6].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[6] != null) {
                                                if(attack(fireTowers[6], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[6].getAtkSpeed());
                    }
                }
            }
        });

        // Tower Slots
        towers[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!towerBuilt[7]){
                    towerBuilt[7] = true;
                    // Build the tower
                    if( gold >= 100 ){
                        // Tower is built, start timer for the attack on that tower
                        gold -= 100;
                        fireTowers[7] = new FireTower(towers[7].getX(),
                                towers[7].getY(), towers[7], screen);
                        infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
                        towerTimer[7].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0) {
                                        if (goblinsActive.get(i) != null) {
                                            // TO DO:
                                            // TO ADD, LOOP THROUGH TOWERS AND THEIR TARGET GOBLINS
                                            if (fireTowers[7] != null) {
                                                if(attack(fireTowers[7], goblinsActive.get(i))){
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            fireTowers[7].getAtkSpeed());
                    }
                }
            }
        });

        infoBarTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                updateInfoBar();
            }
        }, 0, 500);

        // Start Game Button
        final Button startRoundButton = (Button)screen.findViewById(R.id.startRoundw1s1);
        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoundButton.setVisibility(View.INVISIBLE);
                startRoundButton.setEnabled(false);
                startGame();
            }
        });
    }

    private void updateInfoBar(){
        myHandler.post(myRunnable);
    }
    final Runnable myRunnable = new Runnable() {
        public void run() {
            System.out.println("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
            infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score);
        }
    };

    private void startGame(){
        wave++;
        waveStart();
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
        // Start time of stage
        start = System.currentTimeMillis();

        if(wave == 1) {
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
            for (int i = 0; i < waveEnemies; i++) {
                goblinsAlive[i] = 1;
            }
        }

        // Start Timing Game
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runGame();
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            500);
    }

    private void waveEnd(){
        System.out.println("Wave is done");
        waveEnemies = 0;
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
                gold+= 10;
                score+= 50;
                System.out.println("Added Score and Gold");
                // Remove goblin from active goblins
                goblinsActive.remove(goblin.getEnemyNumber());
                // 0 means goblin is dead
                goblinsAlive[goblin.getEnemyNumber()-1] = 0;
                // End Wave
                if(goblinsActive.size() == 0){
                    waveEnd();
                }

            }
            return true;
        }
        return false;
    }
}
