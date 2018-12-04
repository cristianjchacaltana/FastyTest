package com.firebaseapp.fasty.fastytest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.adapters.TestAdapters;
import com.firebaseapp.fasty.fastytest.model.Career;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.Question;
import com.firebaseapp.fasty.fastytest.model.Questions;
import com.firebaseapp.fasty.fastytest.model.University;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class TestFragment extends Fragment {

    private static final String TAG = "TestFragment";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TestAdapters mTestAdapters;
    private Button mReviseButton;
    private Chronometer mTestChronometer;


    private ArrayList<Question> mDataArrayList;
    private University university;
    private Exam exam;
    private Career career;
    private IMainActivity mMainActivityInterface;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mMainActivityInterface = (MainActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null){
            university = bundle.getParcelable(MainActivity.ARGUMENT_UNIVERSITY);
            exam = bundle.getParcelable(MainActivity.ARGUMENT_EXAM);
            career = bundle.getParcelable(MainActivity.ARGUMENT_CAREER);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_test, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview_test);
        mReviseButton = root.findViewById(R.id.button_revise);
        mTestChronometer = root.findViewById(R.id.chronometer_test);

        initRecyclerView();
        initButton();
        initChronometer();

        return root;
    }


    private void initRecyclerView(){

        mLinearLayoutManager = new LinearLayoutManager(getContext(),
                                                    LinearLayoutManager.VERTICAL,
                                                    false);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();
        mTestAdapters = new TestAdapters(getContext(),
                                        mDataArrayList);

        mRecyclerView.setAdapter(mTestAdapters);
    }


    private void initData(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mDataArrayList = new ArrayList<>();


        db.collection("universidades")
            .document( university.getUid() )
            .collection("examenes")
            .document( exam.getUid() )
            .collection("preguntas")
            .whereEqualTo("uid", career.getArea())
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if (task.isSuccessful()){
                        Log.d(TAG, "onComplete: si se obtuvieron los datos");

                        for (QueryDocumentSnapshot document : task.getResult()){
                            Questions questions = document.toObject(Questions.class);

                            for (Question question : questions.getPreguntas()){
                                mDataArrayList.add(question);
                            }

                        }

                        mMainActivityInterface.sendQuestions(mDataArrayList);

                        mTestAdapters.notifyDataSetChanged();

                    } else {
                        Log.d(TAG, "onComplete: no se obtuvieron los datos");
                    }

                }
            });

    }


    private void initButton(){
        mReviseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityInterface.reviseTest(university, exam, career);
            }
        });
    }


    private void initChronometer(){

        mTestChronometer.setBase(SystemClock.elapsedRealtime());
        Log.d(TAG, "initChronometer: "+SystemClock.elapsedRealtime());

        mTestChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Log.d(TAG, "onChronometerTick: listener" + SystemClock.elapsedRealtime());

                long seconds;
                long minutes;
                long hours;

                seconds = (((SystemClock.elapsedRealtime() - mTestChronometer.getBase()) / 1000) % 60);
                minutes = (((SystemClock.elapsedRealtime() - mTestChronometer.getBase()) / 1000)  / 60) % 60;
                hours = (((SystemClock.elapsedRealtime() - mTestChronometer.getBase()) / 1000) / 3600);

                mTestChronometer.setText("hours:" + hours + " minutes:" + minutes + " seconds:" + seconds);
            }
        });

        mTestChronometer.start();

    }


    public void changeColorItemQuestion(int position){
        mTestAdapters.notifyItemChanged(position);
    }


}
