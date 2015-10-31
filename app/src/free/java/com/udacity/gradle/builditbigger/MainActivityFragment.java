package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.AsyncTask;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import builditbigger.gradle.udacity.com.jokeandroidlib.AndLibActivity;

public class MainActivityFragment extends Fragment implements TestTask{

    private EndpointsAsyncTask mAsyncTask;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        ( (Button)root.findViewById(R.id.button1) ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(MainActivityFragment.this).execute();
                Toast.makeText(getActivity(), getResources().getString(R.string.pls_wait)
                        , Toast.LENGTH_LONG).show();
            }
        });
        setRetainInstance(true);
        return root;
    }

    @Override
    public void onDone(String jokeFromServer) {
        startActivity(new Intent(getActivity(), AndLibActivity.class).putExtra("joke", jokeFromServer));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAsyncTask != null && mAsyncTask.getStatus()!= AsyncTask.Status.FINISHED)
            mAsyncTask.cancel(true);
    }

}
