package com.firebaseapp.fasty.fastytest.model;

import android.support.v4.app.Fragment;

public class FragmentTag {

    private Fragment fragment;
    private String tag;



    public FragmentTag(){

    }

    public FragmentTag(Fragment fragment, String tag){
        this.fragment = fragment;
        this.tag = tag;
    }



    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
