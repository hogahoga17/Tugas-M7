package com.hoga.sessionmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button buttonLogin;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        sessionManagement = new SessionManagement(this);

        if (sessionManagement.isLoggedIn()) {
            goToActivity();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pass;
                user = editEmail.getText().toString();
                pass = editPassword.getText().toString();
                if  (user.matches("") || user.trim().isEmpty() || pass.matches("") || pass.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Username dan Password tidak boleh kosong atau berisi spasi !", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    sessionManagement.createLoginSession(user, pass);
                    goToActivity();
                }
            }
        });
    }
    private void goToActivity () {
        Intent mIntent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(mIntent);
    }

}