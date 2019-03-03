package com.example.aly.instagramcloneassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignIn extends AppCompatActivity
{
    private EditText editEmail, editUserName, editUserPassword;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        editEmail = findViewById(R.id.editEmail);
        editUserName = findViewById(R.id.editUserName);
        editUserPassword = findViewById(R.id.editUserPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        getSupportActionBar().setTitle("Sign In");

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final ParseUser newUser = new ParseUser();
                newUser.setEmail(editEmail.getText().toString());
                newUser.setUsername(editUserName.getText().toString());
                newUser.setPassword(editUserPassword.getText().toString());

                newUser.signUpInBackground(new SignUpCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        if (e == null)
                        {
                            FancyToast.makeText(SignIn.this, newUser.get("username") +
                                            " has signed up successfully.", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();
                        }
                        else
                        {
                            FancyToast.makeText(SignIn.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ParseUser.logInInBackground(editUserName.getText().toString(),
                        editUserPassword.getText().toString(), new LogInCallback()
                        {
                            @Override
                            public void done(ParseUser user, ParseException e)
                            {
                                if (user != null && e == null)
                                    {
                                        FancyToast.makeText(SignIn.this, user.get("username") +
                                                    " has signed up successfully.", FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS, true).show();
                                    }
                                else
                                    {
                                        FancyToast.makeText(SignIn.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                            FancyToast.ERROR, true).show();
                                    }
                            }
                        });
            }
        });
    }
}
