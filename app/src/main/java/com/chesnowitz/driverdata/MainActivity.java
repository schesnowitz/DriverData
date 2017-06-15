package com.chesnowitz.driverdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseQuery<ParseObject> query = ParseQuery.getQuery("DriverStatus");

    /*
      Get the Data we want...
    */
    query.whereEqualTo("userEmail", "email1@example.com");
    query.setLimit(5);

    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if (e == null && objects != null) {
          Log.i("Data ->", "Items " + objects.size() + " objects");

          if (objects.size() > 0) {
            for (ParseObject object : objects) {
              Log.i("status", object.getString("status"));
              Log.i("timeInStatus", Double.toString(object.getDouble("timeInstatus")));
              Log.i("EMAIL", object.getString("userEmail"));
            }
          }
        } else {

        }
      }
    });
  }
}
