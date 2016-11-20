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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scores = new ArrayList<>();
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scores.add(new Score("John Smith", 3434));
        scoreAdapter = new ScoreAdapter(getActivity(), scores);
        setListAdapter(scoreAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
