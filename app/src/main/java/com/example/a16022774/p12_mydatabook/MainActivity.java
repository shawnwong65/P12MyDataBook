package com.example.a16022774.p12_mydatabook;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    CustomAdapter aa;
    String currentTitle;
    ActionBar ab;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerItems = new ArrayList<Item>();
        Item item1 = new Item("Bio");
        Item item2 = new Item("Vaccination");
        Item item3 = new Item("Anniversary");
        Item item4 = new Item("About Us");
        drawerItems.add(item1);
        drawerItems.add(item2);
        drawerItems.add(item3);
        drawerItems.add(item4);
        ab = getSupportActionBar();

        aa = new CustomAdapter(this,
                R.layout.items_row, drawerItems);
        drawerList.setAdapter(aa);

        // Set the list's click listener
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    position, long arg3) {

                Fragment fragment = null;
                if (position == 0)
                    fragment = new BioFragment();
                else if (position == 1)
                    fragment = new VaccinationFragment();
                else if (position == 2)
                    fragment = new AnniversaryFragment();
                else if (position == 3) {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    return;
                }


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.content_frame, fragment);
                trans.commit();

                // Highlight the selected item,
                //  update the title, and close the drawer
                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems.get(position).getText();
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            /** Would be called when a drawer has completely closed */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            /** Would be called when a drawer has completely open */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //action
                drawerLayout.openDrawer(drawerList);
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
