package com.roushan.verboseassignment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.HashMap;

public class BuyerSellerNavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TextView nav_header_tv;
    Session session;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_buyer_seller);
        toolbar = (Toolbar) findViewById(R.id.toolbar_nav);
        setSupportActionBar(toolbar);

        session = new Session(this);

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content_container, new HomeFrag());
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);             // opening drawer when activity opens
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this , R.color.nav_statusbar_color));
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nav_header_tv = (TextView) headerView.findViewById(R.id.nav_header_name);

        HashMap<String, String> user = session.getUserDetails();
        String user_name = user.get(Session.KEY_NAME);
        nav_header_tv.setText(user_name);

        if(SelectBuyerSeller.btn_select_str.equals("seller")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.nav_menu_seller);
            getSupportActionBar().setTitle("Home");
        //    nav_header_tv.setText(SellerRegisterActivity.seller_name);

        }
        else {
            getSupportActionBar().setTitle("Home");
        //    nav_header_tv.setText(BuyerRegisterActivity.buyer_name);
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buyer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Home");
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_container, new HomeFrag());
            ft.commit();
        } else if(id == R.id.nav_conversations) {
            getSupportActionBar().setTitle("Conversations");
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_container, new ConversationsFrag());
            ft.commit();
        } else if(id == R.id.nav_profile) {
            getSupportActionBar().setTitle("Profile");
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_container, new ProfileFrag());
            ft.commit();
        } else if(id == R.id.nav_posted) {
            getSupportActionBar().setTitle("Posted Requirements");
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_container, new PostedRequirements());
            ft.commit();
        } else if(id == R.id.nav_support) {
            getSupportActionBar().setTitle("Support");
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_container, new SupportFrag());
            ft.commit();
        } else if(id == R.id.nav_log_out) {
            session.log_out_user();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
