package net.uoit.mcjb.csci4100_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by user on 2016-11-22.
 */

public class GameHelper {
    private RelativeLayout screen;

    public GameHelper(Context context, RelativeLayout screen, ImageView[] towers){
        this.screen = screen;
        // Goblin Wave 1
        Goblin gob1 = new Goblin(context, screen, 0101);
        gob1.start();
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
