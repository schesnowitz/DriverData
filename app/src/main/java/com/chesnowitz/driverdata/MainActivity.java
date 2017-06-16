package com.chesnowitz.driverdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  Boolean registerMode = true;
  TextView changeSignUpRegister;


  public void signUp (View view) {

    EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
    EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
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
}
