package com.firebaseapp.fasty.fastytest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.adapters.PositionsAdapters;
import com.firebaseapp.fasty.fastytest.model.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PositionsFragment extends Fragment {

    private static final String TAG = "PositionsFragment";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private PositionsAdapters mPositionsAdapter;

    private ArrayList<Position> mDataArrayList;
    private String pathExam;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null){
            pathExam = bundle.getString(MainActivity.ARGUMENT_PATH_EXAM);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_positions, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview_positions);

        initRecyclerView();

        return root;

    }


    private void initRecyclerView(){

        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mPositionsAdapter = new PositionsAdapters(getContext(), mDataArrayList);
        mRecyclerView.setAdapter(mPositionsAdapter);

    }


    private void initData(){
        mDataArrayList = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        db.child("positions")
            .child(pathExam)
            .orderByKey()
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        Position position = child.getValue(Position.class);
                        mDataArrayList.add(position);
                    }

                    mPositionsAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

}
