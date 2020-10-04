package com.example.myxbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class Library_Games_Fragment extends Fragment {
    String X;
    CircleImageView circleImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_library_games, container, false);

        circleImageView= (CircleImageView) view.findViewById(R.id.circleImageView);
        circleImageView.setColorFilter(Color.WHITE);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        view.findViewById(R.id.id_Action_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// missing 'http://' will cause crashed
                X = "https://www.pocket-lint.com/games/buyers-guides/132050-best-xbox-one-games-the-must-have-games-to-own-for-xbox";
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.id_Sport_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X = "https://www.androidcentral.com/best-sports-games-xbox-one"; // missing 'http://' will cause crashed
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.id_Racing_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X = "https://www.videogamer.com/top-games/xboxone-racing-games-all-time"; // missing 'http://' will cause crashed
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.id_Advanture_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X = "https://www.videogamer.com/top-games/xboxone-adventure-games-all-time"; // missing 'http://' will cause crashed
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.id_Scary_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X = "https://gameranx.com/features/id/145363/article/best-xbox-one-horror-games/"; // missing 'http://' will cause crashed
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.id_RPG_Game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              X="https://www.videogamer.com/top-games/xboxone-mmo-games-all-time"; // missing 'http://' will cause crashed
                Intent intent = new Intent(getActivity(), WebView_Page.class);
                intent.putExtra("EXTRA_SESSION_ID", X);
                startActivity(intent);
            }
        });


        return view;
    }
}
