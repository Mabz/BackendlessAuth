package com.theswazi.hobbyist.feature;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class PasswordRecoveryActivity extends AppCompatActivity {
    EditText emailField;
    Button passwordRecoveryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.API_KEY);

        // Layout Linking
        emailField = findViewById(R.id.email);
        passwordRecoveryButton = findViewById(R.id.btn_password_recovery);

        passwordRecoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPasswordRecoveryClicked();
            }
        });
    }

    private void onPasswordRecoveryClicked() {
        String emailText = emailField.getText().toString().trim();

        if (emailText.isEmpty()) {
            Toast.makeText(this, "Field 'email' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        Backendless.UserService.restorePassword(emailText, new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                AlertDialog.Builder builder = new AlertDialog.Builder( getApplicationContext());
                builder.setMessage(response.toString()).setTitle("Success");
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                AlertDialog.Builder builder = new AlertDialog.Builder( getApplicationContext());
                builder.setMessage(fault.getMessage()).setTitle("Fail");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
