package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.model.Notification;

import java.util.ArrayList;

public class NotificationsAdapters extends RecyclerView.Adapter<NotificationsAdapters.ViewHolder> {

    private Context mContext;

    private ArrayList<Notification> mDataArrayList;



    public NotificationsAdapters(Context mContext, ArrayList<Notification> mDataArrayList){
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_notification, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        /*Glide.with(mContext)
            .load(Uri.parse( mDataArrayList.get(position).getImage() ))
            .into(holder.mCategoryImageView);*/

        holder.mInformationTextView.setText( mDataArrayList.get(position).getText() );
        holder.mDateTextView.setText( mDataArrayList.get(position).getTimestamp() );

    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mCategoryImageView;
        TextView mInformationTextView;
        TextView mDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mCategoryImageView = itemView.findViewById(R.id.imageview_category);
            mInformationTextView = itemView.findViewById(R.id.text_information);
            mDateTextView = itemView.findViewById(R.id.text_date);
        }
    }


}
