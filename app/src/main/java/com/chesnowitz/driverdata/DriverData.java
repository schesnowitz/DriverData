package com.chesnowitz.driverdata;

/**
 * Created by steve on 6/15/2017.
 */

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class DriverData extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("vYJ3YcZoCUDjT2nbBugynzyR2Sk0TGYtXk4NKtLI")
            .clientKey("pL1AaeRMhRWLLVwFfzNMgxhzMAqOtJW9bBu1tzH5")
            .server("https://parseapi.back4app.com/")
            .build()
    );


//    ParseObject parseObject = new ParseObject("DriverData");
//    parseObject.put("myNumber", "123");
//    parseObject.put("myString", "steve");
//
//    parseObject.saveEventually(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e == null) {
//          Log.i("Parse Result", "Successful!");
//        } else {
//          Log.i("Parse Result", "Failed" + e.toString());
//        }
//      }
//    });


//    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
