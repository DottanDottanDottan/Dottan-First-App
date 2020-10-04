package com.example.myxbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout Draw;
    private FirebaseAuth mAuth;
    TextView ShowFullName, LogOutID;
    FirebaseUser user;
    DatabaseReference myRefUsers = FirebaseDatabase.getInstance().getReference("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        LogOutID = findViewById(R.id.Log_Out_ID);
        LogOutID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        // Read from the database
        String id = user.getUid();
        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UsersDB_Class INFO = dataSnapshot.child(user.getUid()).getValue(UsersDB_Class.class);
                if (INFO != null) {
                    ShowFullName = findViewById(R.id.ShowFullName);
                    ShowFullName.setText("Welcome " + INFO.getFullName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Draw = findViewById(R.id.drawer_layout);
        NavigationView Nav = findViewById(R.id.nav_view);
        Nav.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, Draw, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        Draw.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new What_IS_New()).commit();
            Nav.setCheckedItem(R.id.nav_NewOnGaming);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_NewOnGaming:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new What_IS_New()).commit();
                break;


            case R.id.nav_MyPofie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Fragment()).commit();
                break;

            case R.id.nav_Library_Games:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Library_Games_Fragment()).commit();
                break;

            case R.id.nav_Stream_Channel:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Twitch_Splash_Screen()).commit();
                break;

            case R.id.nav_BuyChannel:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Markert_Place()).commit();
                break;



            case R.id.nav_Contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Gmail_Contact()).commit();
                break;


        }

        Draw.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (Draw.isDrawerOpen(GravityCompat.START)) {
            Draw.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void ShowDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_message_log_out, null);


        Button AcceptLogOutButton = view.findViewById(R.id.AlertDialog_Accept_Log_Out);
        final Button CancelLogOutButton = view.findViewById(R.id.AlertDialog_Cancel_Log_Out);

        AcceptLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, Login_Page.class));
            }
        });

        CancelLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();


    }


}


