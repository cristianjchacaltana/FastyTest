package com.firebaseapp.fasty.fastytest.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.adapters.NotificationsAdapters;
import com.firebaseapp.fasty.fastytest.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    private static final String TAG = "NotificationsFragment";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NotificationsAdapters mNotificationsAdapters;

    private ArrayList<Notification> mDataArrayList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview_notifications);

        initRecyclerView();

        return root;
    }


    private void initRecyclerView(){

        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mNotificationsAdapters = new NotificationsAdapters(getContext(),
                                                        mDataArrayList);
        mRecyclerView.setAdapter(mNotificationsAdapters);

    }


    private void initData(){

        mDataArrayList = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        db.child("notifications")
            .child("uidUser")
            .orderByKey()
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    mDataArrayList.clear();

                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        Notification notification = child.getValue(Notification.class);
                        mDataArrayList.add(notification);
                    }

                    mNotificationsAdapters.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });


    }


}
