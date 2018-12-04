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

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.adapters.ExamsAdapters;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.University;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class ExamsFragment extends Fragment {

    private static final String TAG = "ExamsFragment";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ExamsAdapters mExamsAdapters;


    private ArrayList<Exam> mDataArrayList;
    private University university;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();

        if ( args != null ){
            university = args.getParcelable( MainActivity.ARGUMENT_UNIVERSITY );
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_exams, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview_exams);

        initRecyclerView();

        return root;
    }


    private void initRecyclerView(){

        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mExamsAdapters = new ExamsAdapters(getContext(),
                                        mDataArrayList,
                                        university);
        mRecyclerView.setAdapter(mExamsAdapters);


    }


    private void initData(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mDataArrayList = new ArrayList<>();


        db.collection("universidades")
            .document( university.getUid() )
            .collection("examenes")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if ( task.isSuccessful() ){

                        Log.d(TAG, "onComplete: si se obtuvieron los datos");

                        for ( QueryDocumentSnapshot document : task.getResult() ){
                            Exam exam = document.toObject(Exam.class);
                            mDataArrayList.add(exam);
                        }
                        mExamsAdapters.notifyDataSetChanged();

                    } else {
                        Log.d(TAG, "onComplete: no se obtuvieron los datos");
                    }

                }
            });

    }

}
