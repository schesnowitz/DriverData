package com.chesnowitz.driverdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

  public void signUp (View view) {

    EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
    EditText passwordInut = (EditText) findViewById(R.id.passwordInput);
//    Button bSignup = (Button) findViewById(R.id.bSignUp);

    if (usernameInput.getText().toString().matches("") || 
            passwordInut.getText().toString().matches("")
            ) {
      Toast.makeText(this, "Check email and/or password field(s).", Toast.LENGTH_LONG).show();
    } else {
      ParseUser user = new ParseUser();

      user.setUsername(usernameInput.getText().toString());
      user.setPassword(passwordInut.getText().toString());
      user.setEmail(usernameInput.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("Signup", " Success!");
          } else {
//            using e.getMessage() to throw an error
            Log.i("Signup", e.getMessage());
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
          }
        }
      });
    }

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    
  }
}
