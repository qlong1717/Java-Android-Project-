package com.TEAM12.KidsUP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    TextInputLayout t1,t2,t3;
    ProgressBar bar;
    FirebaseAuth mAuth;
    private FirebaseFirestore Fstore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=(TextInputLayout)findViewById(R.id.email);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        t3=(TextInputLayout)findViewById(R.id.pwd2);
        bar=(ProgressBar)findViewById(R.id.progressBar3);
        mAuth = FirebaseAuth.getInstance();
        Fstore= FirebaseFirestore.getInstance();
    }

    public void gotosignin(View view)
    {
        startActivity(new Intent(MainActivity.this,login.class));
    }

    public void singup(View view)
    {
        bar.setVisibility(View.VISIBLE);

       final String email=t1.getEditText().getText().toString();
        final String password=t2.getEditText().getText().toString();
        String password2=t3.getEditText().getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            t1.setError("Email invalid");
            t1.requestFocus();
            return;
        }
        if(!password2.equals(password)){
            t3.setError("Password not match");
            t3.requestFocus();
            bar.setVisibility(View.GONE);
            return;
        }
        if(email.isEmpty()==false && password.length()>=8)
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (task.isSuccessful())
                            {
                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = Fstore.collection("Users").document(userID);

                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("email",email);
                                userMap.put("password",password);
                                documentReference.set(userMap);

                                bar.setVisibility(View.INVISIBLE);
                                if(user.isEmailVerified()){
                                    Toast.makeText(MainActivity.this, "Email has been registerd", Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);
                                }
                                else{
                                    user.sendEmailVerification();
                                    startActivity(new Intent(MainActivity.this, login.class));
                                    Toast.makeText(MainActivity.this, "Email has been sent. Please check your email to verify your account", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                            } else
                            {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(MainActivity.this, "Register Failed, Pleases Check Again", Toast.LENGTH_SHORT).show();

                            }
                            // ...
                        }
                    });

        }
        else
        {
            bar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Please input valid data",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        bar.setVisibility(View.GONE);
    }

}