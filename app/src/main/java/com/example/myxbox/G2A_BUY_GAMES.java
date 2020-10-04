package com.example.myxbox;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


public class G2A_BUY_GAMES extends Fragment {


    public String SITE;
    SearchView searchView;
    ListView listView;
    ArrayList<String> List;
    ArrayAdapter<String> Adapter;
    String NewQuery;
    String X;
    Button SearchButton, BackButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_games, container, false);



        SITE="https://www.g2a.com/search?query=";
        searchView = view.findViewById(R.id.SearchView);
        listView = view.findViewById(R.id.MyListView);
        SearchButton = view.findViewById(R.id.SearchButton);
        List = new ArrayList<String>();
        Adapter = new ArrayAdapter<>(getActivity(), R.layout.simplerow, List);
        listView.setAdapter(Adapter);
        List.add("Call OF Duty - WarZone ! ");
        List.add("GTA-5 ! ");
        List.add("The Division 2 ! ");
        List.add("Fortnite ! ");
        List.add("Forza Horizion - 4 ! ");
        List.add("FIFA - 20 ! ");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setQuery(List.get(position).toString(),true);
            }
        });



        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serach();
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                NewQuery = query;
                List.add(NewQuery);
                serach();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // אם אכניס את האות סי לדוגמא אקבל קול אוף דיוטי הכוונה שזה פילטר
                Adapter.getFilter().filter(newText);
                NewQuery = newText;
                List.add(NewQuery);
                return false;
            }
        });

        BackButton = view.findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Markert_Place()).commit();
            }
        });


        return view;
    }

    private void serach() {
        String[] strings = NewQuery.split(" ");
        NewQuery = "";
        for (int i = 0; i < strings.length; i++) {
            NewQuery += strings[i] + "%20";
        }
        X = SITE + NewQuery;
        Intent intent = new Intent(getActivity(), WebView_Page.class);
        intent.putExtra("EXTRA_SESSION_ID", X);
        startActivity(intent);
    }


}