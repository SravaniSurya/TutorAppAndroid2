package com.example.androidprojecttutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class tutorreset extends AppCompatActivity {

    EditText et_sendEmail;
    Button btn_reset;


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tutorreset);


        et_sendEmail = findViewById(R.id.et_sendEmail);
        btn_reset = findViewById(R.id.btn_reset);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_sendEmail.getText().toString();
                if (email.equals("")){
                    Toast.makeText(tutorreset.this, "Email is empty", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(tutorreset.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(tutorreset.this, tutorsignin.class));
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(tutorreset.this, error, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}