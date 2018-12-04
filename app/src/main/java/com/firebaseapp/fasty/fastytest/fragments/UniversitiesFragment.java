package com.firebaseapp.fasty.fastytest.fragments;


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

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.adapters.UniversitiesAdapters;
import com.firebaseapp.fasty.fastytest.model.University;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class UniversitiesFragment extends Fragment {

    private static final String TAG = "UniversitiesFragment";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private UniversitiesAdapters mUniversitiesAdapters;

    private ArrayList<University> mDataArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_universities, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview_universities);

        initRecyclerView();

        return root;
    }


    private void initRecyclerView(){
        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mUniversitiesAdapters = new UniversitiesAdapters(getContext(), mDataArrayList);
        mRecyclerView.setAdapter(mUniversitiesAdapters);
    }



    private void initData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("universidades");
        mDataArrayList = new ArrayList<>();

        collectionReference.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if ( task.isSuccessful() ){
                                    Log.d(TAG, "onComplete: si se obtuvieron los datos");

                                    for (QueryDocumentSnapshot document : task.getResult()){
                                        University university = document.toObject(University.class);
                                        mDataArrayList.add(university);
                                    }

                                    mUniversitiesAdapters.notifyDataSetChanged();

                                } else {
                                    Log.d(TAG, "onComplete: no se obtuvieron los datos");
                                }

                            }
                        });

    }

}
