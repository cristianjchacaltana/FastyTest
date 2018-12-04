package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.model.Position;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PositionsAdapters extends RecyclerView.Adapter<PositionsAdapters.ViewHolder>{


    private Context mContext;

    private ArrayList<Position> mDataArrayList;


    public PositionsAdapters(Context mContext, ArrayList<Position> mDataArrayList){
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_position, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCareerTextView.setText( mDataArrayList.get(position).getCarrera() );
        holder.mConditionTextView.setText( mDataArrayList.get(position).getCondicion() );
        holder.mMeritTextView.setText( String.valueOf(mDataArrayList.get(position).getMerito()) );
        holder.mPositionTextView.setText( mDataArrayList.get(position).getPosicion() );
        holder.mScoreTextView.setText( String.valueOf(mDataArrayList.get(position).getPuntaje()) );
    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mCareerTextView;
        TextView mConditionTextView;
        TextView mMeritTextView;
        TextView mPositionTextView;
        TextView mScoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mCareerTextView = itemView.findViewById(R.id.text_position_career);
            mConditionTextView = itemView.findViewById(R.id.text_position_condition);
            mMeritTextView = itemView.findViewById(R.id.text_position_merit);
            mPositionTextView = itemView.findViewById(R.id.text_position_position);
            mScoreTextView = itemView.findViewById(R.id.text_position_score);
        }
    }

}
