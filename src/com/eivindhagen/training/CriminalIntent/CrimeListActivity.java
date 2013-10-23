package com.eivindhagen.training.CriminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: ehagen
 * Date: 10/22/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("CrimeListActivity", "onCreate (before super)");
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onCreate (after super)");
    }

    @Override
    protected void onStart() {
        Log.i("CrimeListActivity", "onStart (before super)");
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onStart (after super)");
    }

    @Override
    protected void onResume() {
        Log.i("CrimeListActivity", "onResume (before super)");
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onResume (after super)");
    }

    @Override
    protected void onPause() {
        Log.i("CrimeListActivity", "onPause(before super)");
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onPause (after super)");
    }

    @Override
    protected void onStop() {
        Log.i("CrimeListActivity", "onStop (before super)");
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onStop (after super)");
    }

    @Override
    protected void onDestroy() {
        Log.i("CrimeListActivity", "onDestroy (before super)");
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("CrimeListActivity", "onDestroy (after super)");
    }

}
