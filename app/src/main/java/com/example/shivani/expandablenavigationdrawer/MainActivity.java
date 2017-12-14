package com.example.shivani.expandablenavigationdrawer;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    // private ListView mDrawerList;
    private ExpandableListView mDrawerList;
    private LinearLayout navDrawerView;
    ExpandListAdapter customAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] productsTitles,dealsTitles;
    private int selectedPosition;
    List<Content> listParent;
    HashMap<String, List<String>> listDataChild;
    Toolbar toolbar;
    TextView text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        text=(TextView)findViewById(R.id.toolText); 
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        navDrawerView = (LinearLayout) findViewById(R.id.navDrawerView);
        productsTitles = getResources().getStringArray(R.array.product_array);
        dealsTitles = getResources().getStringArray(R.array.deals_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       
        mDrawerList = (ExpandableListView) findViewById(R.id.nav_left_drawer);
        listParent = new ArrayList<Content>();
        listDataChild = new HashMap<String, List<String>>();

       listParent.add(new Content("My DashBoard"));
        listParent.add(new Content("Products"));
        listParent.add(new Content("Deals"));
       // listParent.add(new Content("Suggestions"));
       // listParent.add(new Content("Rate Us"));

        listDataChild.put("My Dashboard",new ArrayList<String>());
        listDataChild.put("Products", Arrays.asList(productsTitles));
        listDataChild.put("Deals", Arrays.asList(dealsTitles));
       // listDataChild.put("Suggestions", new ArrayList<String>());
       // listDataChild.put("Rate Us", new ArrayList<String>());

        customAdapter = new ExpandListAdapter(this, listParent, listDataChild);
        
        mDrawerList.setAdapter(customAdapter);
        mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
      
        
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, 
                R.string.navigation_drawer_open, 
                R.string.navigation_drawer_close 
        ) {
            public void onDrawerClosed(View view) {
                toolbar.setTitle(mTitle);
                invalidateOptionsMenu();            }

            public void onDrawerOpened(View drawerView) {
                toolbar.setTitle(mDrawerTitle);
                invalidateOptionsMenu(); 
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        //getHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
                parent.setItemChecked(index, true);
                if(index==0){
                 
                  
                   // setTitle("My DashBoard");
                    
                    mDrawerLayout.closeDrawer(navDrawerView);                    
                }

                String parentTitle = ((Content) customAdapter.getGroup(groupPosition)).getTitle();

                if (parentTitle != getResources().getString(R.string.product) | parentTitle !=  getResources().getString(R.string.deals)) {
                    
                }

                return false;
            }
        });

        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Log.d("CHECK", "child click");

                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                if(getParent().getTitle()=="My Dashborad")
                {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    MyDashBoardFragment dashboardFragment = new MyDashBoardFragment();
                    transaction.add(R.id.content_frame,dashboardFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();  
                }else{
                parent.setItemChecked(index, true);
                selectItem(childPosition);
                childPosition=childPosition+1;
                setTitle("Item\t"+ childPosition);
                Toast.makeText(MainActivity.this, "Item at "+childPosition+" clicked",Toast.LENGTH_SHORT).show();}
                mDrawerLayout.closeDrawer(navDrawerView);

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(navDrawerView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

   
    
    private void selectItem(int position) {
        selectedPosition = position;
        switch (position) {
            case 0:
               // setTitle("Item One");
                //Toast.makeText(this,"Item One Clicked..",Toast.LENGTH_LONG).show();
            case 1:
               // setTitle("Item Two");
                //Toast.makeText(this,"Item Two Clicked..",Toast.LENGTH_LONG).show();
            case 2:
               // setTitle("Item Three");
                //Toast.makeText(this,"Item Three Clicked..",Toast.LENGTH_LONG).show();
            case 3:
               // setTitle("Item Four");
               // Toast.makeText(this,"Item Four Clicked..",Toast.LENGTH_LONG).show();
            case 4:
               // setTitle("Item Five");
                //Toast.makeText(this,"Item Five Clicked..",Toast.LENGTH_LONG).show();
             
        }
    }
  /*  private Fragment getHomeFragment() {
        switch (selectedPosition) {
            case 0:
                MyDashBoardFragment dashboardFragment = new MyDashBoardFragment();
                setTitle("My DashBoard");
                mDrawerLayout.closeDrawer(navDrawerView);
                return dashboardFragment;
            
            case 3:
                ProductFragment productFragment = new ProductFragment();
             //   setTitle(productsTitles[selectedPosition]);
                mDrawerLayout.closeDrawer(navDrawerView);
                return productFragment;
            case 4:
                DealsFragment dealsFragment = new DealsFragment();
               // setTitle(dealsTitles[selectedPosition]);
                mDrawerLayout.closeDrawer(navDrawerView);
                return dealsFragment;
            default:
                return new MyDashBoardFragment();
        }
    }*/
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

   

}
