package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.University;

import java.util.ArrayList;

public class UniversitiesAdapters extends RecyclerView.Adapter<UniversitiesAdapters.ViewHolder>{

    private Context mContext;
    private ArrayList<University> mDataArrayList;
    private IMainActivity mMainActivityInterface;


    public UniversitiesAdapters(Context mContext, ArrayList<University> mDataArrayList){
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_university, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.mNameTextView.setText( mDataArrayList.get(position).getNombre() );
        Glide.with(mContext)
            .load( Uri.parse(mDataArrayList.get(position).getImagen()) )
            .into(holder.mBackgroundImageView);

        holder.mUniversitiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityInterface.openExamsFragment( mDataArrayList.get(position) );
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

        CardView mUniversitiesCardView;
        ImageView mBackgroundImageView;
        TextView mNameTextView;

        public ViewHolder(View itemView){
            super(itemView);

            mUniversitiesCardView = itemView.findViewById(R.id.cardview_universities);
            mBackgroundImageView = itemView.findViewById(R.id.image_universities_background);
            mNameTextView = itemView.findViewById(R.id.text_universities_name);

        }
    }

}


