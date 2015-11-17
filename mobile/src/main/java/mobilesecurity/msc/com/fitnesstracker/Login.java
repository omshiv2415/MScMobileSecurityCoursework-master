package mobilesecurity.msc.com.fitnesstracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends ActionBarActivity {

    //Declaration of Components such as EditText,Buttons.....
    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginBtn;
    protected Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       // Initialising Component to the Declared Variables above
        mUsername = (EditText) findViewById(R.id.UserName);
        mPassword = (EditText) findViewById(R.id.Password);
        mLoginBtn = (Button) findViewById(R.id.btnLogin);
        mRegisterBtn = (Button)findViewById(R.id.btnRegister);

        //-------------------------------------------------------//
       // Setting up onclick Listner for login button (Event Handler)
      mLoginBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              // get the user inputs from edit text and convert to string
              final String username = mUsername.getText().toString().trim();
              final String password = mPassword.getText().toString().trim();

              ParseUser.logInInBackground(username, password, new LogInCallback() {
                  @Override
                  public void done(ParseUser parseUser, ParseException e) {

                      if (e == null) {
                          // success !

                          Toast toast = Toast.makeText(Login.this, " Welcome ! " + username, Toast.LENGTH_LONG);
                          toast.setGravity(Gravity.CENTER, 0, 0);
                          toast.show();


                          // Take user to homepage
                          Intent takeUserHome = new Intent(Login.this, MainActivity.class);
                          mUsername.getText().clear();
                          mPassword.getText().clear();
                          startActivity(takeUserHome);
                          Login.this.finish();
                      } else {
                          // sorry there is a login problem
                          AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                          builder.setMessage(e.getMessage());
                          builder.setTitle("Sorry");
                          builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {

                              }
                          });
                          AlertDialog dialog = builder.create();
                          dialog.show();
                      }
                  }
              });




          }
      });



        //-------------------------------------------------------//
        // Setting up onclick listner for login button (Event Handler)
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent takeUserRegister = new Intent(Login.this, Register.class);
                startActivity(takeUserRegister);
                Login.this.finish();



            }
        });












    }
}
