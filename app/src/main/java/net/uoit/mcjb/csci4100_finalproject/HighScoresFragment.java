package net.uoit.mcjb.csci4100_finalproject;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HighScoresFragment extends ListFragment {
    private ArrayList<Score> scores;
    private ScoreAdapter scoreAdapter;
    ScoreDBHelper db;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new ScoreDBHelper(this.getActivity());
        scores = db.getAllScores();

        // TODO: Need to change to pull from db once scores added after game
        for (int i = 0; i < 1; i++) {
            //name is currently primary key
            scores.add(new Score("Empty", 0));
            db.addScore(scores.get(i));
        }

        scoreAdapter = new ScoreAdapter(getActivity(), scores);
        setListAdapter(scoreAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        scores = db.getAllScores();
        scoreAdapter = new ScoreAdapter(getActivity(), scores);
        setListAdapter(scoreAdapter);
    }
}
