package com.example.myxbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class Gmail_Contact extends Fragment {



    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    private Button ButtonSendEmail;

    private void SendMail(){
        String RecipientList = "GladiatorRDottan@gmail.com";
        String[] Recipient = RecipientList.split(",");
        String Subject = mEditTextSubject.getText().toString();
        String Message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,Recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
        intent.putExtra(Intent.EXTRA_TEXT,Message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose Email Bro"));

    }







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gmailpage, container, false);


        //((AppCompatActivity) getActivity()).getSupportActionBar().show();



        mEditTextSubject = view.findViewById(R.id.edit_text_subject);
        mEditTextMessage = view.findViewById(R.id.edit_text_message);
        ButtonSendEmail = view.findViewById(R.id.button_send);
        ButtonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMail();
            }
        });

        view.findViewById(R.id.BackToContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Fragment()).commit();
            }
        });



        return view;
    }
}
