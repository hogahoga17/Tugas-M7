package com.hoga.sessionmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    SessionManagement sm;
    TextView tvUser;
    Button btnLogout;
    HashMap<String, String> loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvUser = findViewById(R.id.tvUser);
        btnLogout = findViewById(R.id.btnLogout);

        sm = new SessionManagement(this);
        loginUser = sm.getUserInformation();

        Toast.makeText(this, sm.isLoggedIn()+"", Toast.LENGTH_LONG).show();
        tvUser.setText(loginUser.get(sm.KEY_USER));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutUser();
            }
        });

    }
}
