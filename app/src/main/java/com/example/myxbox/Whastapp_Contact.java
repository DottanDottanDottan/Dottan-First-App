package com.example.myxbox;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Whastapp_Contact extends Fragment {

    TextView WhatsappMessage;
    EditText Edit_Text_WhatsApp;
    Button BtnWhatsAppBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.whatsapp, container, false);

        WhatsappMessage = (TextView) view.findViewById(R.id.text_view);
        SpannableString content = new SpannableString("Welcome To WhassApp Page !");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        WhatsappMessage.setText(content);

        Edit_Text_WhatsApp = view.findViewById(R.id.Edit_Text_WhatsApp);
        BtnWhatsAppBtn = view.findViewById(R.id.BtnWhatsAppBtn);

        BtnWhatsAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager X = getActivity().getPackageManager();
                try {
//                    HOW TO SEND GENEREAL WHATSAPP !!!!!!!
//                    ----------------------------------------
//                    Intent I = new Intent(Intent.ACTION_SEND);
//                    I.setType("text/plain");
//                    String string = Edit_Text_WhatsApp.getText().toString();
//                    PackageInfo packageInfo = X.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//                    I.setPackage("com.whatsapp");
//                    I.putExtra(I.EXTRA_TEXT, string);
//                    startActivity(Intent.createChooser(I, "Share With"));
//                  HOW TO SEND WHATSAPP TO 1 PERSON !!!!!
//                  --------------------------------------
                    String string = Edit_Text_WhatsApp.getText().toString();
                    Uri uri = Uri.parse("smsto:" + "972532440900");
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
//                    intent.setType("text/plain");
//                    PackageInfo packageInfo = X.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    Toast.makeText(getContext(),string,Toast.LENGTH_LONG).show();
                    intent.putExtra(Intent.EXTRA_TEXT,string);
                    intent.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(intent,"Share With"));
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Whatsapp Not Found !", Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.findViewById(R.id.BackToContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Contact_Page_Gmail_And_Whatsapp()).commit();
            }
        });

        return view;
    }
}