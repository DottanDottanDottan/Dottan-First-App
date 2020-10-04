package com.example.myxbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Fragment extends Fragment {

    EditText UserEmail, UserPass, UserFullName, UserPhoneNumber, UserAddress, UserRePassword,UserNickName;
    private FirebaseAuth mAuth;
    TextView ShowProfileName;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    CircleImageView circleImageView;
    private UsersDB_Class UserDetails;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);



        ((AppCompatActivity) getActivity()).getSupportActionBar().show();




        mAuth = FirebaseAuth.getInstance();
        UserEmail = view.findViewById(R.id.Email);
        UserPass = view.findViewById(R.id.PassWord);
        UserRePassword = view.findViewById(R.id.RePassWord);
        UserFullName = view.findViewById(R.id.UserFullName);
        UserPhoneNumber = view.findViewById(R.id.UserPhoneNumber);
        UserAddress = view.findViewById(R.id.UserAddress);
        UserNickName=view.findViewById(R.id.UserNickName);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(mAuth.getUid());
        circleImageView=view.findViewById(R.id.profileimage);

        



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserDetails = dataSnapshot.getValue(UsersDB_Class.class);
                if(UserDetails != null) {
                    UserPass.setText(UserDetails.getPassword());
                    UserRePassword.setText(UserDetails.getRePassword());
                    UserFullName.setText(UserDetails.getFullName());
                    UserPhoneNumber.setText(UserDetails.getPhoneNumber());
                    UserAddress.setText(UserDetails.getAddress());
                    UserNickName.setText(UserDetails.getNickName());
                    ShowProfileName = view.findViewById(R.id.ShowProfileName);
                    ShowProfileName.setText("Welcome " + UserDetails.getFullName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(getActivity(), "Failed to read value", Toast.LENGTH_SHORT).show();
            }
        };
        myRef.addListenerForSingleValueEvent(valueEventListener);

        (view.findViewById(R.id.BtnUpdate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = mAuth.getCurrentUser();
                //write on data base at Users section the new user under user uid object
                myRef = database.getReference("Users").child(mAuth.getUid());

                user.updatePassword(UserPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    UsersDB_Class temp = new UsersDB_Class(UserPass.getText().toString(),
                                            UserRePassword.getText().toString(),UserFullName.getText().toString(),UserPhoneNumber.getText().toString(),
                                            UserAddress.getText().toString(),UserNickName.getText().toString());
                                    myRef.setValue(temp);
                                    Toast.makeText(getActivity(), "Updated successfully!", Toast.LENGTH_SHORT).show();

                                    updateUI(user);
                                } else
                                    Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });




        return view;
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        } else {
            Toast.makeText(getActivity(), "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }











}
