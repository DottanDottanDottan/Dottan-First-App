package com.example.myxbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity {

    private NotificationManagerCompat NotificationManager;
    EditText UserEmail, UserPass;
    private FirebaseAuth mAuth;
    FirebaseUser user;




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if (currentUser!=null){
           startActivity(new Intent(Login_Page.this,MainActivity.class));
       }
       else{
           // do nothing
       }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        NotificationManager = NotificationManagerCompat.from(this);
        UserEmail = findViewById(R.id.UserName);
        UserPass = findViewById(R.id.PassWord);
        mAuth = FirebaseAuth.getInstance();






        (findViewById(R.id.BtnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(UserEmail.getText().toString(),
                        UserPass.getText().toString())
                        .addOnCompleteListener(Login_Page.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login_Page.this, "Login Success", Toast.LENGTH_SHORT).show();
                                     user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    NotificationFunc();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login_Page.this, "Failed Not Working", Toast.LENGTH_SHORT).show();

                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });



        (findViewById(R.id.BtnSignUp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Page.this, Sign_Up_Page.class);
                startActivity(i);
            }
        });





    }

    private void updateUI(FirebaseUser user) {
        if (user!=null){
            Intent i = new Intent(Login_Page.this,MainActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(Login_Page.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void NotificationFunc(){


        String Title = UserEmail.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,NotificationClass.NotificationID)
                .setSmallIcon(R.drawable.ic_hello)
                .setContentTitle("Hello" + "    " + "-"+ "    " + Title)
                .setContentText("Welcome To My App , Successfully Login in ! ")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManager.notify(1,notification);

    }
}


