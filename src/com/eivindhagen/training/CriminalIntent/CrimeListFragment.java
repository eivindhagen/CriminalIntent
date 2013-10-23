package com.eivindhagen.training.CriminalIntent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ehagen
 * Date: 10/22/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";
    private boolean mSubtitleVisible;
    private static final int REQUEST_CRIME = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("CrimeListFragment", "onCreate (before super)");
        super.onCreate(savedInstanceState);
        Log.i("CrimeListFragment", "onCreate (before super)");

        setRetainInstance(true);
        mSubtitleVisible = false;

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.crimes_title);  // set Activity's title
        mCrimes = CrimeLab.get(getActivity()).getCrimes();  // get all the crimes for the list to display

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        return view;
    }

    @Override
    public void onStart() {
        Log.i("CrimeListFragment", "onStart (before super)");
        super.onPause();
        Log.i("CrimeListFragment", "onStart (after super)");
    }

    @Override
    public void onResume() {
        Log.i("CrimeListFragment", "onResume (before super)");
        super.onResume();
        Log.i("CrimeListFragment", "onResume (after super)");
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        Log.i("CrimeListFragment", "onPause (before super)");
        super.onPause();
        Log.i("CrimeListFragment", "onPause (after super)");
    }

    @Override
    public void onStop() {
        Log.i("CrimeListFragment", "onStop (before super)");
        super.onStop();
        Log.i("CrimeListFragment", "onStop (after super)");
    }

    @Override
    public void onDestroy() {
        Log.i("CrimeListFragment", "onDestroy (before super)");
        super.onDestroy();
        Log.i("CrimeListFragment", "onDestroy (after super)");
    }

    @Override
    public void onListItemClick(ListView l, View v, int listPosition, long rowId) {
        Crime crime = ((CrimeAdapter)getListAdapter()).getItem(listPosition);
        Log.d(TAG, crime.getTitle() + " was clicked (position=" + listPosition + " id=" + rowId + ")");

        // start activity for individual crime
//        Intent i = new Intent(getActivity(), CrimeActivity.class);
//        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
        i.putExtra(CrimeFragment.EXTRA_CRIME_LIST_INDEX, listPosition);
        startActivityForResult(i, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestId, int resultCode, Intent data) {
        if (REQUEST_CRIME == requestId) {
            // handle result here...
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible && showSubtitle != null) {
            showSubtitle.setTitle(R.string.hide_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
                intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
                startActivityForResult(intent, 0);
                return true;
            case R.id.menu_item_show_subtitle:
                if (getActivity().getActionBar().getSubtitle() == null) {
                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
                    mSubtitleVisible = true;
                    item.setTitle(R.string.hide_subtitle);
                } else {
                    getActivity().getActionBar().setSubtitle(null);
                    mSubtitleVisible = false;
                    item.setTitle(R.string.show_subtitle);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we didn't get a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            // set view properties from the model data
            Crime crime = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(crime.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(crime.isSolved());

            return convertView;
        }

    } // private class CrimeAdapter
}
