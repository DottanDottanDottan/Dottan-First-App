package com.example.myxbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up_Page extends AppCompatActivity {


    private NotificationManagerCompat NotificationManager;
    EditText UserEmail, UserPass, UserFullName, UserPhoneNumber, UserAddress, UserRePassword, UserNickName;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        NotificationManager = NotificationManagerCompat.from(this);
        mAuth = FirebaseAuth.getInstance();
        UserEmail = findViewById(R.id.Email);
        UserPass = findViewById(R.id.PassWord);
        UserRePassword = findViewById(R.id.RePassWord);
        UserFullName = findViewById(R.id.UserFullName);
        UserPhoneNumber = findViewById(R.id.UserPhoneNumber);
        UserAddress = findViewById(R.id.UserAddress);
        UserNickName = findViewById(R.id.UserNickName);
        database = FirebaseDatabase.getInstance();


        (findViewById(R.id.BtnAlrdyUserBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_Up_Page.this, Login_Page.class));
            }
        });


        (findViewById(R.id.BtnSignUp1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(!VaildateEmailFunc() | !VaildateFullNameFunc() | !VaildatePasswordFunc() | !VaildateRePasswordFunc() | !VaildateNickNameFunc() |
                                        !VaildateAddressFunc() | !VaildatePhoneNumberFunc())) {
                    mAuth.createUserWithEmailAndPassword(UserEmail.getText().toString(),
                            UserPass.getText().toString())
                            .addOnCompleteListener(Sign_Up_Page.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                         //  Write a message to the database
                                        UsersDB_Class NewUser = new UsersDB_Class(
                                                UserPass.getText().toString(),
                                                UserRePassword.getText().toString(),
                                                UserFullName.getText().toString(),
                                                UserPhoneNumber.getText().toString(),
                                                UserAddress.getText().toString(),
                                                UserNickName.getText().toString());
                                        user = mAuth.getCurrentUser();
                                        myRef = database.getReference("Users").child(user.getUid());
                                        myRef.setValue(NewUser);
                                        updateUI(user);
                                        NotificationFunc();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        updateUI(null);
                                    }


                                }
                            });
                }else
                    {return;}
            }
        });


    }


    private void updateUI(FirebaseUser X) {
        if (user != null) {
            Intent i = new Intent(Sign_Up_Page.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(Sign_Up_Page.this, "You Alrdy signed up with this user",
                    Toast.LENGTH_SHORT).show();
        }
    }
    
    private boolean VaildateFullNameFunc() {
        String ValidateFullName = UserFullName.getText().toString();
        String noWhiteSpace = "(?=\\s+$)";
        if (ValidateFullName.isEmpty()) {
            UserFullName.setError("Please Fill Ur Full Name ");
            return false;
        } else if (ValidateFullName.length() >= 15) {
            UserFullName.setError("UserName Too Long !");
            return false;
        } else if (ValidateFullName.matches(noWhiteSpace)) {
            UserFullName.setError("White Space Are Not Allowed");
            return false;
        } else {
            UserFullName.setError(null);
            return true;
        }
    }

    private boolean VaildateEmailFunc() {
        String ValidateEmail = UserEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (ValidateEmail.isEmpty()) {
            UserEmail.setError("Please Fill Ur Email ");
            return false;
        } else if (!ValidateEmail.matches(emailPattern)) {
            UserEmail.setError("invaild Email Address");
            return false;
        } else {
            UserEmail.setError(null);
            return true;
        }
    }

    private boolean VaildatePasswordFunc() {
        String ValidatePassword = UserPass.getText().toString();

        if (ValidatePassword.isEmpty()) {
            UserPass.setError("Please Fill Ur Password ");
            return false;
        } else if (ValidatePassword.length() >= 15) {
            UserPass.setError("Your Password Too Long ! ");
            return false;
        } else {
            UserPass.setError(null);
            return true;

        }
    }

    private boolean VaildateRePasswordFunc() {
        String ValidateRePassword = UserRePassword.getText().toString();
        String ValidatePassword = UserPass.getText().toString();
        if (ValidateRePassword.isEmpty()) {
            UserRePassword.setError("Please Fill Your Password ");
            return false;
        } else if (!ValidateRePassword.matches(ValidatePassword) ) {
            UserRePassword.setError("Not The Same Pass!!");
            return false;
        } else {
            UserRePassword.setError(null);
            return true;

        }
    }

    private boolean VaildateNickNameFunc() {
        String ValidateNickName = UserNickName.getText().toString();

        if (ValidateNickName.isEmpty()) {
            UserNickName.setError("Please Fill Ur Nick Name ");
            return false;
        } else {
            UserNickName.setError(null);
            return true;
        }
    }

    private boolean VaildatePhoneNumberFunc() {
        String ValidatePhoneNumber = UserPhoneNumber.getText().toString();

        if (ValidatePhoneNumber.isEmpty()) {
            UserPhoneNumber.setError("Please Fill Ur Phone Number ");
            return false;
        } else if (UserPhoneNumber.length() <= 9) {
            UserPhoneNumber.setError("Your Number is Too Short!!");
            return false;
        } else {
            UserPhoneNumber.setError(null);
            return true;
        }
    }

    private boolean VaildateAddressFunc() {
        String ValidateAddress = UserAddress.getText().toString();

        if (ValidateAddress.isEmpty()) {
            UserAddress.setError("Please Fill Ur Full Address ");
            return false;
        } else {
            UserAddress.setError(null);
            return true;
        }
    }

    private void NotificationFunc(){


        String Title = UserFullName.getText().toString();

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
