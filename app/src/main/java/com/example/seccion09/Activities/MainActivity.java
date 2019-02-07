package com.example.seccion09.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.seccion09.Fragments.AlertsFragment;
import com.example.seccion09.Fragments.EmailFragment;
import com.example.seccion09.Fragments.InfoFragment;
import com.example.seccion09.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navview);

        setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        // Botones del navview
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        fragmentTransaction = true;
                        break;

                    case  R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentTransaction = true;
                        break;

                    case  R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;


                    case  R.id.menu_opcion_1:
                        Toast.makeText(MainActivity.this, "Opcion 1", Toast.LENGTH_SHORT).show();
                    break;


                    case  R.id.menu_opcion_2:
                        Toast.makeText(MainActivity.this, "Opcion 2", Toast.LENGTH_SHORT).show();
                        break;
                }

                if (fragmentTransaction){
                    changeFragment(fragment, menuItem);
                    drawerLayout.closeDrawers(); // Cierra el menu
                }

                return true ;
            }
        });
    }
    private void setToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Icono de menu
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault(){
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // Abrir menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
