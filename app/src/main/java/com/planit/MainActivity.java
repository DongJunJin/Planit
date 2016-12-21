package com.planit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    FloatingActionButton fab;
    private DrawerLayout drawer;
    private GoogleApiClient client;
    public Context context;
    //    private GridView calendarView;
//    private GridCellAdapter adapter;
//    private Calendar _calendar = Calendar.getInstance(Locale.getDefault());
//    private int month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Default on Initialization to Monthly Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_parent, new Monthly());
        ft.commit();

//        calendarView = (GridView) this.findViewById(R.id.calendar_grid);
//        month = _calendar.get(Calendar.MONTH);
//        year = _calendar.get(Calendar.YEAR);
//        adapter = new GridCellAdapter(getApplicationContext(),R.id.calendar_grid, month, year);
//        adapter.notifyDataSetChanged();
//        calendarView.setAdapter(adapter);

        setDate();
//        syncCalendar();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        hideFloatingActionButton();
        //TODO: Button Function
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.Monthly);


//        setUpDrawerContent(navigationView);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void showFloatingActionButton(){
        fab.show();
    }

    public void hideFloatingActionButton(){
        fab.hide();
    }

    public void setDate(){
        getTitle();
        String title;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        cal.add(Calendar.MONTH, 1);
        int month = cal.get(Calendar.MONTH);
        String years = Integer.toString(year);
        String months;

        switch (month) {
            case 1:
                months = "Jan";
                break;

            case 2:
                months = "Feb";
                break;

            case 3:
                months = "Mar";
                break;

            case 4:
                months = "Apr";
                break;

            case 5:
                months = "May";
                break;

            case 6:
                months = "Jun";
                break;

            case 7:
                months = "Jul";
                break;

            case 8:
                months = "Aug";
                break;

            case 9:
                months = "Sep";
                break;

            case 10:
                months = "Oct";
                break;

            case 11:
                months = "Nov";
                break;

            case 12:
                months = "Dec";
                break;

            default:
                months = "Broken";
                break;
        }

        title = months + "/" + years;
        setTitle(title);
    }

    @Override
    public void onClick(View v){
        // TODO LATER - FIGURE OUT SLIDER METHOD (VIEWPAGER)
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                drawer.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        switch (menuItem.getItemId()){
                            case R.id.Daily:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.Weekly:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.Monthly:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.Yearly:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.Manage_Events:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.Manage_Days:
                                menuItem.setChecked(true);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                        }
                        return true;
                    }
                });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (id == R.id.Daily){
            fragment = new Daily();
            ft.replace(R.id.content_parent, fragment);
            ft.commit();
        }

        if (id == R.id.Weekly){
            fragment = new Weekly();
            ft.replace(R.id.content_parent, fragment);
            ft.commit();
        }

        if (id == R.id.Monthly){
            fragment = new Monthly();
            ft.replace(R.id.content_parent,fragment);
            ft.commit();
        }

        if (id == R.id.Yearly){
            fragment = new Yearly();
            ft.replace(R.id.content_parent,fragment);
            ft.commit();
        }

        if (id == R.id.Manage_Events){
            fragment = new ManageEvents();
            ft.replace(R.id.content_parent,fragment);
            ft.commit();

        }

        if (id == R.id.Manage_Days){
            fragment = new Manage_Days();
            ft.replace(R.id.content_parent,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.planit/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.planit/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    //TODO work on Calendar Events
//    public void syncCalendar(){
//
//        ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALENDAR);
//
//        String[] projection =
//        new String[]{
//                CalendarContract.Calendars._ID,
//                CalendarContract.Calendars.NAME,
//                CalendarContract.Calendars.ACCOUNT_NAME,
//                CalendarContract.Calendars.ACCOUNT_TYPE};
//
//        Cursor calCursor =
//                getContentResolver().
//                        query(CalendarContract.Calendars.CONTENT_URI, projection,
//                        CalendarContract.Calendars.VISIBLE+ " = 1", null,
//                        CalendarContract.Calendars._ID + " ASC");
//
//        if(calCursor.moveToFirst()) {
//            do{
//                long id = calCursor.getLong(0);
//                String displayName = calCursor.getString(0);
//            }while(calCursor.moveToNext());
//        }
//    }

}
