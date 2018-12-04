package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Result;

import java.util.ArrayList;



public class ResultsAdapters extends RecyclerView.Adapter<ResultsAdapters.ViewHolder>{

    private Context mContext;

    private ArrayList<Result> mDataArrayList;
    private IMainActivity mMainActivityInterface;


    public ResultsAdapters(Context mContext, ArrayList<Result> mDataArrayList) {
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_result, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.mNameTextView.setText( mDataArrayList.get(position).getUniversidad() );

        holder.mResultsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityInterface.openStatisticsFragment( mDataArrayList.get(position) );
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }




    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mMainActivityInterface = (MainActivity) mContext;

    }




    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView mResultsCardView;
        ImageView mBackgroundImageView;
        TextView mNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mResultsCardView = itemView.findViewById(R.id.cardview_results);
            mBackgroundImageView = itemView.findViewById(R.id.image_results_background);
            mNameTextView = itemView.findViewById(R.id.text_results_name);
        }
    }


}
