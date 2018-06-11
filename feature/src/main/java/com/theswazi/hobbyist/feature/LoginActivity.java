package com.theswazi.hobbyist.feature;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

/*import com.google.android.gms.common.SignInButton;*/

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    TextView passwordRecovery;
    TextView register;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.createAccount);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        passwordRecovery = findViewById(R.id.forgot);
        passwordRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordRecoveryActivity.class);
                startActivity(intent);
            }
        });

        // Change Google Sign In Text
        signInButton = findViewById(R.id.button_googlePlusLogin);
        setGooglePlusButtonText(signInButton);



    }

    protected void setGooglePlusButtonText(SignInButton signInButton) {
        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(getResources().getString(R.string.btn_sign_in_with_google));
                return;
            }
        }
    }
}

