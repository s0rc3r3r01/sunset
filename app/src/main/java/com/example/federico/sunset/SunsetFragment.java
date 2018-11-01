package com.example.federico.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by federico on 13/01/2017.
 */

public class SunsetFragment extends Fragment{

    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private int mBlueSkyColor;
    private int mSunsetColor;
    private int mNightSKyColor;


    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);


        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetColor = resources.getColor(R.color.sunset_sky);
        mNightSKyColor = resources.getColor(R.color.night_sky);
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);
        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });
        return view;
    }

    private void  startAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView,"y",sunYStart,sunYEnd).setDuration(3000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mBlueSkyColor,mSunsetColor).setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

        ObjectAnimator nigthSkyAnimator = ObjectAnimator.ofInt(mSkyView,"backgroundColor", mSunsetColor,mNightSKyColor).setDuration(1500);
        nigthSkyAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(sunsetSkyAnimator).before(nigthSkyAnimator);
        animatorSet.start();

    }


}
