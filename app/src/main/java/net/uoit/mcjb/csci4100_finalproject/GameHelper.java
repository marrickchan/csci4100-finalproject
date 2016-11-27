package net.uoit.mcjb.csci4100_finalproject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    final private int TIME_BETWEEN_WAVES = 5000;
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
    // Goblin Wave 2
    private Goblin gob6;
    private Goblin gob7;
    private Goblin gob8;
    private Goblin gob9;
    private Goblin gob10;
    private Goblin gob11;
    private Goblin gob12;
    private Goblin gob13;
    private Goblin gob14;
    private Goblin gob15;
    // Troll Wave 3
    private Troll troll1;
    private Troll troll2;
    private Troll troll3;

    // Towers
    private FireTower fireTowers[] = new FireTower[8];
    private boolean towerBuilt[] = new boolean[8];
    private Timer towerTimer[] = new Timer[8];
    // Helper For Listener
    private int currentTowerListenerHelper = 0;

    // Wave Management
    private Map<Integer, Goblin> goblinsActive = new HashMap<Integer, Goblin>();
    private int[] goblinsAlive;
    private Map<Integer, Troll> trollsActive = new HashMap<Integer, Troll>();
    private int[] trollsAlive;
    private int wave = 0;
    private int waveEnemies = 0;

    public GameHelper(final Context context, final RelativeLayout screen, final ImageView[] towers, int STAGE_CODE){
        this.screen = screen;
        this.towers = towers;
        this.context = context;
        this.STAGE_CODE = STAGE_CODE;

        // Initialize Information Bar
        infoBar = (TextView)screen.findViewById(R.id.infoBar);
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

                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[0].scheduleAtFixedRate(new TimerTask() {
                              @Override
                              public void run() {
                                  for (int i = 1; i <= waveEnemies; i++) {
                                      // Check if goblin is alive
                                      // Target first goblin system
                                      if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                          if (fireTowers[0] != null) {
                                              if(trollsActive.get(i) != null) {
                                                  if (attack(fireTowers[0], trollsActive.get(i))) {
                                                      return;
                                                  }
                                              } else if(goblinsActive.get(i) != null){
                                                  if (attack(fireTowers[0], goblinsActive.get(i))) {
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
                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[1].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {
                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[1] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[1], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[1], goblinsActive.get(i))) {
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
                        // Attack Using Timer
                        // Start Timing Game
                        towerTimer[2].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {
                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[2] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[2], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[2], goblinsActive.get(i))) {
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
                        towerTimer[3].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {
                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[3] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[3], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[3], goblinsActive.get(i))) {
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
                        towerTimer[4].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[4] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[4], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[4], goblinsActive.get(i))) {
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
                        towerTimer[5].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[5] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[5], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[5], goblinsActive.get(i))) {
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
                        towerTimer[6].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[6] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[6], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[6], goblinsActive.get(i))) {
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
                        towerTimer[7].scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= waveEnemies; i++) {

                                    // Check if goblin is alive
                                    // Target first goblin system
                                    if (goblinsAlive[i - 1] != 0 || trollsAlive[i-1] != 0) {
                                        if (fireTowers[7] != null) {
                                            if(trollsActive.get(i) != null) {
                                                if (attack(fireTowers[7], trollsActive.get(i))) {
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[7], goblinsActive.get(i))) {
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
            System.out.println("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score + "  | Wave: " + wave);
            infoBar.setText("Lives: " + lives + "  | Gold: " + gold + "  | Score: " + score + "  | Wave: " + wave);
        }
    };

    private void startGame(){
        // Create all goblins
        // Wave 1
        gob1 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob2 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob3 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob4 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob5 = new Goblin(context, screen, 0101, ++waveEnemies);
        // Wave 2
        waveEnemies = 0;
        gob6 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob7 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob8 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob9 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob10 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob11 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob12 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob13 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob14 = new Goblin(context, screen, 0101, ++waveEnemies);
        gob15 = new Goblin(context, screen, 0101, ++waveEnemies);
        // Wave 3
        waveEnemies = 0;
        troll1 = new Troll(context, screen, STAGE_CODE, ++waveEnemies);
        troll2 = new Troll(context, screen, STAGE_CODE, ++waveEnemies);
        troll3 = new Troll(context, screen, STAGE_CODE, ++waveEnemies);


        // Start waves
        waveEnemies = 0;
        wave++;
        waveStart();
    }

    private void waveStart(){
        // Start time of stage
        start = System.currentTimeMillis();
        t.cancel();

        // STAGE 0101
        if(wave == 1 && STAGE_CODE == 0101) {
            // Set goblins for this wave
            goblinsActive.put(++waveEnemies, gob1);
            goblinsActive.put(++waveEnemies, gob2);
            goblinsActive.put(++waveEnemies, gob3);
            goblinsActive.put(++waveEnemies, gob4);
            goblinsActive.put(++waveEnemies, gob5);
            goblinsAlive = new int[waveEnemies];
            trollsAlive = new int[waveEnemies];
            for (int i = 0; i < waveEnemies; i++) {
                goblinsAlive[i] = 1;
                trollsAlive[i] = 0;
            }
            // Make sure trolls is empty
            trollsActive.put(1, troll1);
            trollsActive.remove(1);
        } else if(wave == 2 && STAGE_CODE == 0101){
            // Set goblins for this wave
            goblinsActive.put(++waveEnemies, gob6);
            goblinsActive.put(++waveEnemies, gob7);
            goblinsActive.put(++waveEnemies, gob8);
            goblinsActive.put(++waveEnemies, gob9);
            goblinsActive.put(++waveEnemies, gob10);
            goblinsActive.put(++waveEnemies, gob11);
            goblinsActive.put(++waveEnemies, gob12);
            goblinsActive.put(++waveEnemies, gob13);
            goblinsActive.put(++waveEnemies, gob14);
            goblinsActive.put(++waveEnemies, gob15);

            goblinsAlive = new int[waveEnemies];
            trollsAlive = new int[waveEnemies];
            for (int i = 0; i < waveEnemies; i++) {
                goblinsAlive[i] = 1;
                trollsAlive[i] = 0;
            }
            // Make sure trolls is empty
            trollsActive.put(1, troll1);
            trollsActive.remove(1);
        } else if(wave == 3 && STAGE_CODE == 0101){
            trollsActive.put(++waveEnemies, troll1);
            trollsActive.put(++waveEnemies, troll2);
            trollsActive.put(++waveEnemies, troll3);
            goblinsAlive = new int[waveEnemies];
            trollsAlive = new int[waveEnemies];
            for (int i = 0; i < waveEnemies; i++){
                trollsAlive[i] = 1;
                goblinsAlive[i] = 0;
            }
            // Make sure trolls is empty
            goblinsActive.put(1, gob1);
            goblinsActive.remove(1);
        }

        t = new Timer();
        // Start Timing Game
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runGame();
                System.out.println(System.currentTimeMillis() - start);
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            495);
    }

    private void waveEnd(){
        waveEnemies = 0;
        t.cancel();

        t = new Timer();
        // Prep for next Wave
        // Start Timer for next wave
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                wave++;
                System.out.println("Next Wave");
                waveStart();
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            TIME_BETWEEN_WAVES);
    }

    private void runGame(){
        long currentTime = System.currentTimeMillis() - start;
        if(wave == 1 && STAGE_CODE == 0101) {
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
        } else if(wave == 2 && STAGE_CODE == 0101){
            if (currentTime > 2005 && currentTime < 2504) {
                gob6.start();
            } else if (currentTime > 5005 && currentTime < 5504) {
                gob7.start();
            } else if (currentTime > 8005 && currentTime < 8504) {
                gob8.start();
            } else if (currentTime > 11005 && currentTime < 11504) {
                gob9.start();
            } else if (currentTime > 14005 && currentTime < 14504) {
                gob10.start();
            } else if (currentTime > 17005 && currentTime < 17504) {
                gob11.start();
            } else if (currentTime > 20005 && currentTime < 20504) {
                gob12.start();
            } else if (currentTime > 23005 && currentTime < 23504) {
                gob13.start();
            } else if (currentTime > 26005 && currentTime < 26504) {
                gob14.start();
            } else if (currentTime > 29005 && currentTime < 29504) {
                gob15.start();
            }
        } else if(wave == 3 && STAGE_CODE == 0101){
            if (currentTime > 2005 && currentTime < 2504) {
                troll1.start();
            } else if (currentTime > 8005 && currentTime < 8504) {
                troll2.start();
            } else if (currentTime > 13005 && currentTime < 13504) {
                troll3.start();
            }
        }
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
                System.out.println(goblinsActive.size());
                System.out.println(goblinsActive.size() == 0);
                System.out.println(trollsActive.size());
                System.out.println(trollsActive.size() == 0);
                if(goblinsActive.size() == 0 && trollsActive.size() == 0){
                    waveEnd();
                }
            }
            return true;
        }
        return false;
    }

    private boolean attack(FireTower tower, Troll troll){
        // Goblin Coordinates Center
        float trollX = (troll.getX() + troll.getWidth()) / 2;
        float trollY = (troll.getY() + troll.getHeight()) / 2;
        // Tower Location Center
        float centerX = (tower.returnX() + tower.getImageView().getWidth())/2;
        float centerY = (tower.returnY() + tower.getImageView().getHeight())/2;
        // Check Square Collision
        if ( // Check Horizontally
                (trollX > centerX - tower.returnRange() &&
                        trollX < centerX + tower.returnRange())
                        &&
                        // Check Vertically
                        (trollY > centerY - tower.returnRange() &&
                                trollY < centerY + tower.returnRange())) {
            System.out.println("HP: " + troll.getHP());
            if(troll.setHP(troll.getHP() - 2)){
                // Increase score
                gold+= 25;
                score+= 70;
                System.out.println("Added Score and Gold");
                // Remove goblin from active goblins
                trollsActive.remove(troll.getEnemyNumber());
                // 0 means goblin is dead
                trollsAlive[troll.getEnemyNumber()-1] = 0;
                // End Wave
                System.out.println(goblinsActive.size());
                System.out.println(goblinsActive.size() == 0);
                System.out.println(trollsActive.size());
                System.out.println(trollsActive.size() == 0);
                if(goblinsActive.size() == 0 && trollsActive.size() == 0){
                    waveEnd();
                }
            }
            return true;
        }
        return false;
    }
}
