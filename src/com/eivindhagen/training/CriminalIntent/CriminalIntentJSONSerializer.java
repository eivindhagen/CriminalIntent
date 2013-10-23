package com.eivindhagen.training.CriminalIntent;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ehagen
 * Date: 10/23/13
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class CriminalIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException {
        // build JSON array
        JSONArray array = new JSONArray();
        for (Crime crime : crimes) {
            array.put(crime.toJSON());
        }

        // write to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
            // open and read
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // parse JSON
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            // build array
            for (int i = 0; i < array.length(); ++i) {
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // ingore
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return crimes;
    }
}
