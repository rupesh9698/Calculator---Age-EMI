package com.alltools.calculator;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View navHeader;
    ImageView navigationDrawerLogo;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_AGE = "Age Calculator";
    private static final String TAG_EMI = "EMI Calculator";
    private static final String TAG_PERCENTAGE = "Percentage Calculator";
    private static final String TAG_PASSWORD = "Password Generator";
    public static String CURRENT_TAG = TAG_AGE;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load age fragment when user presses back key
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navHeader = navigationView.getHeaderView(0);
        navigationDrawerLogo = findViewById(R.id.navigationDrawerLogo);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        CURRENT_TAG = TAG_AGE;

        loadNavHeader();

        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_AGE;
            loadHomeFragment();
        }
    }

    private void loadNavHeader() {

        try {
            Picasso.get().load(R.drawable.logo_transparent).into(navigationDrawerLogo);
        } catch (Exception e) {
            //Toast.makeText(AddPostActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = () -> {
            // update the main content by replacing fragments
            Fragment fragment = getMainFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If mPendingRunnable is not null, then add to the message queue
        mHandler.post(mPendingRunnable);

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getMainFragment() {
        switch (navItemIndex) {// home
            case 1:
                return new EMIFragment();
            case 2:
                return new PercentageFragment();
            case 3:
                return new PasswordFragment();
            default:
                return new AgeFragment();
        }
    }

    private void setToolbarTitle() {
        Objects.requireNonNull(getSupportActionBar()).setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @SuppressLint("NonConstantResourceId")
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        // This method will trigger on item Click of navigation menu
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            //Check to see which item was being clicked and perform appropriate action
            switch (menuItem.getItemId()) {
                //Replacing the main content with ContentFragment Which is our Inbox View;
                case R.id.ageCalculatorMenu:
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_AGE;
                    break;
                case R.id.emiCalculatorMenu:
                    navItemIndex = 1;
                    CURRENT_TAG = TAG_EMI;
                    break;
                case R.id.percentageCalculatorMenu:
                    navItemIndex = 2;
                    CURRENT_TAG = TAG_PERCENTAGE;
                    break;
                case R.id.PasswordGeneratorMenu:
                    navItemIndex = 3;
                    CURRENT_TAG = TAG_PASSWORD;
                    break;
                case R.id.privacyPolicy:
                    drawerLayout.closeDrawers();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/allcalculator-privacypolicy/home")));
                    break;
                case R.id.rateUs:
                    drawerLayout.closeDrawers();
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(myAppLinkToMarket);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    return true;
                case R.id.share:

                    drawerLayout.closeDrawers();

                    String shareBody = "https://play.google.com/store/apps/details?id=com.alltools.calculator";

                    Intent sIntent = new Intent(Intent.ACTION_SEND);
                    sIntent.setType("text/plain");
                    sIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                    sIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sIntent, "Share Via"));

                    return true;
                default:
                    navItemIndex = 0;
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            menuItem.setChecked(!menuItem.isChecked());
            menuItem.setChecked(true);

            loadHomeFragment();

            return true;
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

}