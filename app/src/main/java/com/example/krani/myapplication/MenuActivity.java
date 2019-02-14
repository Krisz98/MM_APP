package com.example.krani.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends AppCompatActivity implements LauncherFragment.OnLauncherFragmentInteractionListener, AchievementsFragment.OnAchievementFragmentInteractionListener{
    private final String mm_url = "http://www.mm.bme.hu/";
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //status bar beszínezése
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        LauncherFragment launcherFragment = LauncherFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame,launcherFragment);
        fragmentTransaction.commit();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        FragmentManager fragmentManager1 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        switch (menuItem.getItemId()){
                            case R.id.game_menu:
                                fragmentTransaction1.replace(R.id.content_frame,LauncherFragment.newInstance());
                                fragmentTransaction1.commit();
                                break;
                            case R.id.achievements:
                                fragmentTransaction1.replace(R.id.content_frame,AchievementsFragment.newInstance());
                                fragmentTransaction1.commit();
                                break;

                        }
                        return true;
                    }
                });
    }

    @Override
    public void onLauncherFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAchievementFragmentInteraction(Uri uri) {

    }
}
