package com.firebaseapp.fasty.fastytest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Career;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.Careers;
import com.firebaseapp.fasty.fastytest.model.University;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class IndicationsFragment extends Fragment {

    private static final String TAG = "IndicationsFragment";


    private Spinner mSpinner;
    private ArrayAdapter<Career> mCareersAdapters;
    private Button mStarExamButton;
    private Button mCancelButton;

    private ArrayList<Career> mDataArrayList;
    private University university;
    private Exam exam;
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

        if (bundle != null) {
            university = bundle.getParcelable(MainActivity.ARGUMENT_UNIVERSITY);
            exam = bundle.getParcelable(MainActivity.ARGUMENT_EXAM);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_indications, container, false);

        mSpinner = root.findViewById(R.id.spinner_indications_careers);
        mStarExamButton = root.findViewById(R.id.button_indications_star_exam);
        mCancelButton = root.findViewById(R.id.button_indications_cancel);

        initSpinner();
        initButton();

        return root;
    }



    private void initSpinner(){

        initData();


        mCareersAdapters = new ArrayAdapter<>(getContext(),
                                            android.R.layout.simple_spinner_item,
                                            mDataArrayList);
        mCareersAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mCareersAdapters);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStarExamButton.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }




    private void initButton(){

        mStarExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Career career = (Career) mSpinner.getSelectedItem();
                mMainActivityInterface.openTestFragment(university, exam, career);


            }
        });

    }




    private void initData(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mDataArrayList = new ArrayList<Career>();


        CollectionReference collectionReference = db.collection("universidades")
                                                    .document( university.getUid() )
                                                    .collection("examenes")
                                                    .document( exam.getUid() )
                                                    .collection("carreras");



        collectionReference.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()){
                                    Log.d(TAG, "onComplete: si se obtuvieron los datos" + task.getResult().size());

                                    for (QueryDocumentSnapshot document : task.getResult()){
                                        Careers careers = document.toObject(Careers.class);

                                        for (Career career : careers.getCarreras()){
                                            mDataArrayList.add(career);
                                        }

                                    }

                                    mCareersAdapters.notifyDataSetChanged();

                                } else {
                                    Log.d(TAG, "onComplete: no se obtuvieron los datos");
                                }

                            }
                        });


    }




}
