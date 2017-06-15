package com.chesnowitz.driverdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
/*
    final ParseObject driverData = new ParseObject("DriverStatus");
    driverData.put("userEmail", "email@email.com");
    driverData.put("status", "onDuty");
    driverData.put("timeInStatus", 1.75);
    driverData.saveEventually(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          Log.i("Driver -->", driverData.toString());
        } else {
          Log.i("Driver -->", e.toString());
        }
      }
    });
*/

    ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("DriverStatus");

    // gets item by the id -- id is auto created
    parseQuery.getInBackground("cTqg4JRCy1", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if (e == null && object != null) {
          Log.i("Email Value --> ", object.getString("userEmail"));
          Log.i("Status Value --> ", object.getString("status"));
          Log.i("Time in status --> ", Double.toString(object.getDouble("timeInStatus")));
        } else {

        }
      }
    });
  }
}
