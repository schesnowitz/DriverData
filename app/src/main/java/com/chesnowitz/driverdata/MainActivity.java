package com.chesnowitz.driverdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


//    check for user

    /*

    */

    ParseUser.logOut();

    if (ParseUser.getCurrentUser() != null) {
      Log.i("User", " is logged in with username: " + ParseUser.getCurrentUser().getUsername());
    } else {
      Log.i("User", " is NOT logged in ");
    }


  // Log user in

    /*
    ParseUser.logInInBackground("steve@chesnowitx.com", "password", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (user != null) {
          Log.i("Login", "Success");
        } else {
          Log.i("Login", "Failure " + e.toString());
        }
      }
    });

    */


    // Sign user in
    /*
       ParseUser user = new ParseUser();

    user.setUsername("steve@chesnowitx.com");
    user.setPassword("password");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          Log.i("Sign Up", "Success");
        } else {
          Log.i("Sign Up", "Failure");
          Log.i("Errors", e.getStackTrace().toString());

        }
      }
    });
    */



  }
}
