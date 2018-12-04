package com.firebaseapp.fasty.fastytest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Question;
import com.google.api.LogDescriptor;

import java.util.ArrayList;

public class TestAdapters extends RecyclerView.Adapter<TestAdapters.ViewHolder>{

    private Context mContext;
    private ArrayList<Question> mDataArrayList;
    private IMainActivity mMainActivityInterface;


    public TestAdapters(Context mContext, ArrayList<Question> mDataArrayList){
        this.mContext = mContext;
        this.mDataArrayList = mDataArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_question, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.mNumberTextView.setText( String.valueOf( mDataArrayList.get(position).getUid() ) );
        holder.mQuestionTextView.setText( "pregunta " + mDataArrayList.get(position).getUid() );
        holder.mQuestionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityInterface.openQuestionFragment( mDataArrayList.get(position) );
            }
        });

//        reseteamos el color de background pq van al recyclerview pool
        holder.mQuestionsCardView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primaryBackgroundColor));

//        cambiamos el color a el item question
        if (mMainActivityInterface.getAnswer(position).getUid()!= null &&
            mMainActivityInterface.getAnswer(position).getRespuesta()!= null ){
            holder.mQuestionsCardView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primaryColor));
        }

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

        CardView mQuestionsCardView;
        TextView mNumberTextView;
        TextView mQuestionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mQuestionsCardView = itemView.findViewById(R.id.cardview_questions);
            mNumberTextView = itemView.findViewById(R.id.text_questions_number);
            mQuestionTextView = itemView.findViewById(R.id.text_questions_question);
        }

    }


}
