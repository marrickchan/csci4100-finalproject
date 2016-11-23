package net.uoit.mcjb.csci4100_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    private boolean gameRunning = true;
    private long start;

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

    public GameHelper(Context context, RelativeLayout screen, ImageView[] towers, int STAGE_CODE){
        this.screen = screen;
        this.towers = towers;
        this.context = context;
        this.STAGE_CODE = STAGE_CODE;

        // Create the goblins
        gob1 = new Goblin(context, screen, 0101);
        gob2 = new Goblin(context, screen, 0101);
        gob3 = new Goblin(context, screen, 0101);
        gob4 = new Goblin(context, screen, 0101);
        gob5 = new Goblin(context, screen, 0101);

        // Create tower
        t1 = new FireTower(towers[0].getX(), towers[0].getY(), towers[0], screen);

        // Start time of stage
        start = System.currentTimeMillis();

        // Set the timer to tick to run the game
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                startLevel();
            }
        },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                500);
    }

    public void startLevel(){
        long currentTime = System.currentTimeMillis() - start;
        if(currentTime > 2005 && currentTime < 2504){
            gob1.start();
        } else if(currentTime > 5005 && currentTime < 5504) {
            gob2.start();
        } else if(currentTime > 8005 && currentTime < 8504) {
            gob3.start();
        } else if(currentTime > 11005 && currentTime < 11504) {
            gob4.start();
        } else if(currentTime > 14005 && currentTime < 14504) {
            gob5.start();
        }
    }

    public static boolean attack(FireTower tower, Goblin goblin){
        // Check collision
        // If collided, attack
        // Find center of the tower
        float centerX = (tower.returnX() + tower.getImageView().getWidth())/2;
        float centerY = (tower.returnY() + tower.getImageView().getHeight())/2;

        // Check all the items on the screen and detect closeness
        for (int i = 0; i < tower.returnRL().getChildCount(); i++) {
            View child = tower.returnRL().getChildAt(i);
            // Only compare objects that are now the same id as tower
            // TODO:
            // If time, detect all towers
            if (child.getId() != tower.getImageView().getId()){
                // Check Collision (CURRENTLY ONLY CHECKS BY SQUARE)
                // (ALSO ONLY CHECKS IF CENTER OF CHILD IS IN RANGE)

                // Child Center
                float childX = (child.getX() + child.getWidth())/2;
                float childY = (child.getY() + child.getHeight())/2;

                // Check Square Collision
                if ( // Check Horizontally
                        (childX > centerX - tower.returnRange() &&
                                childX < centerX + tower.returnRange())
                                &&
                                // Check Vertically
                                (childY > centerY - tower.returnRange() &&
                                        childY < centerY + tower.returnRange())){
                    goblin.setHP(goblin.getHP() - 2);
                }
            }
        }
        return false;
    }
}
