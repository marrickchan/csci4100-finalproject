package net.uoit.mcjb.csci4100_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 20/11/2016.
 */

public class ScoreAdapter extends ArrayAdapter<Score> {
    public static class ViewHolder {
        TextView name;
        TextView score;
    }

    public ScoreAdapter(Context context, ArrayList<Score> scores) {
        super(context, 0, scores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Score score = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

           // convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_high_score_list, parent, false);

         //   viewHolder.name = (TextView) convertView.findViewById(R.id.listItemName);
          //  viewHolder.score = (TextView) convertView.findViewById(R.id.listItemScore);

            convertView.setTag(viewHolder);
        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(score.getName());
        viewHolder.score.setText(String.valueOf(score.getScore()));


        return convertView;
    }
}
