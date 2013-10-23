package com.eivindhagen.training.CriminalIntent;

import android.support.v4.app.Fragment;

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
}
