package com.theswazi.hobbyist.feature;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    Button registerButton;

    private String name;
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.API_KEY);

        // Change Google Sign In Text
        initUI();

    }

    private void initUI() {
        nameField = findViewById(R.id.full_name);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();
            }
        });
    }

    private void onRegisterButtonClicked() {
        String nameText = nameField.getText().toString().trim();
        String emailText = emailField.getText().toString().trim();
        String passwordText = passwordField.getText().toString().trim();

        if (emailText.isEmpty()) {
            Toast.makeText(this, "Field 'email' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        } else
            email = emailText;

        if (passwordText.isEmpty()) {
            Toast.makeText(this, "Field 'password' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            password = passwordText;

        if (!nameText.isEmpty()) {
            name = nameText;
        }

        BackendlessUser user = new BackendlessUser();
        user.setEmail(email);

        if (password != null) {
            user.setPassword(password);
        }

        if (name != null) {
            user.setProperty("name", name);
        }

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Resources resources = getResources();
                String message = String.format(resources.getString(R.string.registration_success_message), "Hobbyist");

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(message).setTitle(R.string.request_for_confirmation);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(fault.getMessage()).setTitle(R.string.registration_error);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}
