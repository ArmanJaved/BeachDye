package com.beachdye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainPage extends AppCompatActivity {


    DatabaseReference artistreference;

    GridView grid;
    String[] web = {
            "Mobiles",
            "Vehicles",
            "Bikes",
            "Property for Sale",
            "Job",
            "Animals"
    } ;

    String [] childfirebase = {
            "mobile",
            "vehicles",
            "bikes",
            "property",
            "job",
            "animals"

    };
    int[] imageId = {
            R.drawable.smartphone,
            R.drawable.car,
            R.drawable.motor_sports,
            R.drawable.house,
            R.drawable.businessman,
            R.drawable.dog


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Button btnadd = (Button)findViewById(R.id.submitadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainPage.this, MainActivity.class));
            }
        });

        CustomGrid adapter = new CustomGrid(MainPage.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



                    Intent intent = new Intent(MainPage.this, DisplayImagesActivity.class);
                    intent.putExtra("childfirebase", childfirebase[position]);
                    startActivity(intent);



            }
        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main,menu);
//        menu.add(menuactivity.NONE, MENU_DELETE, menuactivity.NONE, "Delete");
        return super.onCreateOptionsMenu(menu);


    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()) {
            case R.id.voicecommands:
                //do cool stuff
                signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signOut() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
