package net.uoit.mcjb.csci4100_finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
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
    private Timer lifeTimer = new Timer();
    final Handler lifeHandler = new Handler();
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
    // Sound resources
    MediaPlayer fireballSound;
    MediaPlayer goblinSpawnSound;
    MediaPlayer goblinDeathSound;
    MediaPlayer trollSpawnSound;
    MediaPlayer trollDeathSound;

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

    // Wave Management
    private Map<Integer, Goblin> goblinsActive = new HashMap<Integer, Goblin>();
    private int[] goblinsAlive;
    private Map<Integer, Troll> trollsActive = new HashMap<Integer, Troll>();
    private int[] trollsAlive;
    private int wave = 0;
    private int waveEnemies = 0;
    // Postgame
    // After last wave
    private int lastWave = 4;
    private boolean stageComplete = false;

    public GameHelper(final Context context, final RelativeLayout screen, final ImageView[] towers, int STAGE_CODE){
        this.screen = screen;
        this.towers = towers;
        this.context = context;
        this.STAGE_CODE = STAGE_CODE;

        // Sound resources
        fireballSound = MediaPlayer.create(context, R.raw.fireball);
        // TODO:
        // Change these to the right sounds
        goblinSpawnSound = MediaPlayer.create(context, R.raw.fireball);
        goblinDeathSound = MediaPlayer.create(context, R.raw.fireball);
        trollSpawnSound = MediaPlayer.create(context, R.raw.fireball);
        trollDeathSound = MediaPlayer.create(context, R.raw.fireball);

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
                                                      attackSound();
                                                      return;
                                                  }
                                              } else if(goblinsActive.get(i) != null){
                                                  if (attack(fireTowers[0], goblinsActive.get(i))) {
                                                      attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[1], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[2], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[3], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[4], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[5], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[6], goblinsActive.get(i))) {
                                                    attackSound();
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
                                                    attackSound();
                                                    return;
                                                }
                                            } else if(goblinsActive.get(i) != null){
                                                if (attack(fireTowers[7], goblinsActive.get(i))) {
                                                    attackSound();
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

                // If no lives
                if(checkLives()){
                    endGame();
                }


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

    private void buildFireTower(final int towerNumber){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.buildFireTowerBody)
                .setTitle(R.string.buildFireTower);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
            public void  onClick(DialogInterface dialog, int id){
                if( gold >= 100 ) {
                    // Tower is built, start timer for the attack on that tower
                    gold -= 100;
                    towerBuilt[towerNumber] = true;
                }
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
            public void  onClick(DialogInterface dialog, int id){

            }
        });
        AlertDialog buildFireTowerDialog = builder.create();
    }

    private boolean checkLives(){
        if(lives <= 0){
            return true;
        }
        return false;
    }

    public boolean dead(){
        if(stageComplete){
            for(int i = 0; i < towerTimer.length; i++){
                towerTimer[i].cancel();
            }
            return true;
        }
        return false;
    }

    public int getFinalScore(){
        return score;
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

    private void enemyLifeCheck(){
        lifeHandler.post(lifeRunnable);
    }

    final Runnable lifeRunnable = new Runnable(){
        public void run() {
            int g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0;
            int g6 = 0, g7 = 0, g8 = 0, g9 = 0, g10 = 0, g11 = 0, g12 = 0, g13 = 0, g14 = 0, g15 = 0;
            int t1 = 0, t2 = 0, t3 = 0;
            if(gob1 != null)
                g1 = gob1.getDeath();
            if(gob2 != null)
                g2 = gob2.getDeath();
            if(gob3 != null)
                g3 = gob3.getDeath();
            if(gob4 != null)
                g4 = gob4.getDeath();
            if(gob5 != null)
                g5 = gob5.getDeath();
            if(gob6 != null)
                g6 = gob6.getDeath();
            if(gob7 != null)
                g7 = gob7.getDeath();
            if(gob8 != null)
                g8 = gob8.getDeath();
            if(gob9 != null)
                g9 = gob9.getDeath();
            if(gob10 != null)
                g10 = gob10.getDeath();
            if(gob11 != null)
                g11 = gob11.getDeath();
            if(gob12 != null)
                g12 = gob12.getDeath();
            if(gob13 != null)
                g13 = gob13.getDeath();
            if(gob14 != null)
                g14 = gob14.getDeath();
            if(gob15 != null)
                g15 = gob15.getDeath();
            if(troll1 != null)
                t1 = troll1.getDeath();
            if(troll2 != null)
                t2 = troll2.getDeath();
            if(troll3 != null)
                t3 = troll3.getDeath();


            lives = lives +
                    g1 + g2 + g3 + g4 + g5 +
                    g6 + g7 + g8 + g9 + g10 + g11 + g12 + g13 + g14 + g15 +
                    t1 + t2 + t3;

            if (g1 == -1) {
                killEnemy(gob1);
            } else if (g2 == -1){
                killEnemy(gob2);
            } else if (g3 == -1){
                killEnemy(gob3);
            } else if (g4 == -1){
                killEnemy(gob4);
            } else if (g5 == -1){
                killEnemy(gob5);
            } else if (g6 == -1){
                killEnemy(gob6);
            } else if (g7 == -1){
                killEnemy(gob7);
            } else if (g8 == -1){
                killEnemy(gob8);
            } else if (g9 == -1){
                killEnemy(gob9);
            } else if (g10 == -1){
                killEnemy(gob10);
            } else if (g11 == -1){
                killEnemy(gob11);
            } else if (g12 == -1){
                killEnemy(gob12);
            } else if (g13 == -1){
                killEnemy(gob13);
            } else if (g14 == -1){
                killEnemy(gob14);
            } else if (g15 == -1){
                killEnemy(gob15);
            } else if (t1 == -1){
                killEnemy(troll1);
            } else if (t2 == -1){
                killEnemy(troll2);
            } else if (t3 == -1){
                killEnemy(troll3);
            }
        }
    };

    private void killEnemy(Goblin gob){
        // Remove troll from active trolls
        goblinsActive.remove(gob.getEnemyNumber());
        // 0 means troll is dead, setting troll to dead
        goblinsAlive[gob.getEnemyNumber()-1] = 0;
        // End Wave if no more goblins or trolls alive
        if(goblinsActive.size() == 0 && trollsActive.size() == 0){
            waveEnd();
        }
    }
    private void killEnemy(Troll troll){
        // Remove troll from active trolls
        trollsActive.remove(troll.getEnemyNumber());
        // 0 means troll is dead, setting troll to dead
        trollsAlive[troll.getEnemyNumber()-1] = 0;
        // End Wave if no more goblins or trolls alive
        if(goblinsActive.size() == 0 && trollsActive.size() == 0){
            waveEnd();
        }
    }

    private void attackSound(){
        fireballSound.start();
        fireballSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer fireballSound) {
                fireballSound.release();
            }

        });
    }

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


        // Timer for checking lives
        lifeTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                enemyLifeCheck();
            }
        }, 0, 100);

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
        // If last wave, end game
        if(wave+1 == lastWave){
            endGame();
        }

        t = new Timer();
        // Prep for next Wave
        // Start Timer for next wave
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                wave++;
                waveStart();
            }
        },
            //Set how long before to start calling the TimerTask (in milliseconds)
            0,
            //Set the amount of time between each execution (in milliseconds)
            TIME_BETWEEN_WAVES);
    }

    private void endGame(){
        infoBarTimer.cancel();
        t.cancel();

        // Wave 1
        gob1.stop();
        gob2.stop();
        gob3.stop();
        gob4.stop();
        gob5.stop();
        gob1 = null;
        gob2 = null;
        gob3 = null;
        gob4 = null;
        gob5 = null;
        // Wave 2
        gob6.stop();
        gob7.stop();
        gob8.stop();
        gob9.stop();
        gob10.stop();
        gob11.stop();
        gob12.stop();
        gob13.stop();
        gob14.stop();
        gob15.stop();
        gob6 = null;
        gob7 = null;
        gob8 = null;
        gob9 = null;
        gob10 = null;
        gob11 = null;
        gob12 = null;
        gob13 = null;
        gob14 = null;
        gob15 = null;
        // Wave 3
        troll1.stop();
        troll2.stop();
        troll3.stop();
        troll1 = null;
        troll2 = null;
        troll3 = null;
        stageComplete = true;
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
        endGame();
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
            // goblin.setHP will return true if dead
            if(goblin.setHP(goblin.getHP() - 2)){
                // Increase score and gold
                gold+= 10;
                score+= 50;
                // Remove goblin from active goblins
                goblinsActive.remove(goblin.getEnemyNumber());
                // 0 means goblin is dead, setting goblin to dead
                goblinsAlive[goblin.getEnemyNumber()-1] = 0;
                // End Wave if no more goblins or trolls alive
                if(goblinsActive.size() == 0 && trollsActive.size() == 0){
                    waveEnd();
                }
            }
            return true;
        }
        return false;
    }

    private boolean attack(FireTower tower, Troll troll){
        // Troll Coordinates Center
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
            // troll.setHP will return true if dead
            if(troll.setHP(troll.getHP() - 2)){
                // Increase score and gold
                gold+= 25;
                score+= 70;
                // Remove troll from active trolls
                trollsActive.remove(troll.getEnemyNumber());
                // 0 means troll is dead, setting troll to dead
                trollsAlive[troll.getEnemyNumber()-1] = 0;
                // End Wave if no more goblins or trolls alive
                if(goblinsActive.size() == 0 && trollsActive.size() == 0){
                    waveEnd();
                }
            }
            return true;
        }
        return false;
    }
}
