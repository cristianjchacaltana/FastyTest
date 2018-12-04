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
import com.firebaseapp.fasty.fastytest.RealtimeConstans;
import com.firebaseapp.fasty.fastytest.adapters.ResultsAdapters;
import com.firebaseapp.fasty.fastytest.model.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ResultsFragment extends Fragment {

    private static final String TAG = "ResultsFragment";

    private RecyclerView mResultsRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ResultsAdapters mResultsAdapters;

    private ArrayList<Result> mDataArrayList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_results, container, false);

        mResultsRecyclerView = root.findViewById(R.id.recyclerview_results);

        initRecyclerView();

        return root;

    }


    private void initRecyclerView(){

        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);
        mResultsRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mResultsAdapters = new ResultsAdapters(getContext(),
                                            mDataArrayList);
        mResultsRecyclerView.setAdapter(mResultsAdapters);

    }


    private void initData(){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        mDataArrayList = new ArrayList<>();

        db.child(RealtimeConstans.EXAMS)
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .orderByKey()
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        Result result = child.getValue(Result.class);
                        mDataArrayList.add(result);
                    }

                    mResultsAdapters.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

    }


}
