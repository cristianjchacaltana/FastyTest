package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.University;

import java.util.ArrayList;

public class ExamsAdapters extends RecyclerView.Adapter<ExamsAdapters.ViewHolder>{

    private Context mContext;
    private ArrayList<Exam> mDataArrayList;
    private IMainActivity mMainActivityInterface;
    private University university;


    public ExamsAdapters(Context mContext, ArrayList<Exam> mDataArrayList, University university){
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
        this.university = university;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_exam, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.mNameTextView.setText( mDataArrayList.get(position).getUid() );

        holder.mExamsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityInterface.openIndicationsFragment(university,
                                                            mDataArrayList.get(position));
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

        private CardView mExamsCardView;
        private ImageView mBackgroundImageView;
        private TextView mNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mExamsCardView = itemView.findViewById(R.id.cardview_exams);
            mBackgroundImageView = itemView.findViewById(R.id.image_exams_background);
            mNameTextView = itemView.findViewById(R.id.text_exams_name);

        }


    }

}
