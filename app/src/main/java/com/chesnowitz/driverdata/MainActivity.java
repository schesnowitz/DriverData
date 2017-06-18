package com.chesnowitz.driverdata;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

  Boolean registerMode = true;
  TextView changeSignUpRegister;
  EditText usernameInput;
  EditText passwordInput;


  public void signUp (View view) {

    usernameInput = (EditText) findViewById(R.id.usernameInput);
    passwordInput = (EditText) findViewById(R.id.passwordInput);
//    Button bSignup = (Button) findViewById(R.id.bSignUp);

    if (usernameInput.getText().toString().matches("") ||
            passwordInput.getText().toString().matches("")
            ) {
      Toast.makeText(this, "Check email and/or password field(s).", Toast.LENGTH_LONG).show();
    } else {

//      check to see register mode true is so,  we run out code to create the user
      if (registerMode == true) {


//      create user
        ParseUser user = new ParseUser();

        user.setUsername(usernameInput.getText().toString());
        user.setPassword(passwordInput.getText().toString());
        user.setEmail(usernameInput.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Log.i("Signup", " Success!");

//              Redirecting user after sign up
              if (ParseUser.getCurrentUser() != null) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
              }


            } else {
//            using e.getMessage() to throw an error
              Log.i("Signup", e.getMessage());
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
          }
        });
      } else {
//        the user has an account and we log in
        ParseUser.logInInBackground(usernameInput.getText().toString(),
                passwordInput.getText().toString(), new LogInCallback() {
                  @Override
                  public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                      Log.i("Login", "Success");

//                      Redirect after log intent
                      Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                      startActivity(intent);


                    } else {
                      Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                      Log.i("Login", "Failed");
                    }
                  }
                });
      }
    }

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    changeSignUpRegister = (TextView) findViewById(R.id.changeSignUpRegister);
    changeSignUpRegister.setOnClickListener(this);
    passwordInput = (EditText) findViewById(R.id.passwordInput);

// hides keyboard when background clicked
    findViewById(R.id.mainLayout).setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
      }
    });

    passwordInput.setOnKeyListener(this);
  }


//  added implements View.OnClickListener then method.  this is called onCreate see onCreate
  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.changeSignUpRegister) {
      Button bSignUp = (Button) findViewById(R.id.bSignUp);
      if (registerMode == true) {
        registerMode = false;
        bSignUp.setText("Log In");
        changeSignUpRegister.setText("Register Here...");
      } else {
        registerMode = true;
        bSignUp.setText("Register");
        changeSignUpRegister.setText("Log In Here...");
      }
     }
  }

  @Override
  public boolean onKey(View view, int i, KeyEvent keyEvent) {

//    checks to see if enter key clicked on keyboard -- submits from keyboard
    if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
      signUp(view);
    }
    return false;
  }

}
