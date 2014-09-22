package com.euimyung.manual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class LibraryActivity extends ActionBarActivity implements ItemListFragment.Callbacks {

	/**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

	/** Called when the activity is first created. */
    @SuppressLint("NewApi")
    @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_item_list);
	    
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    Intent intent = getIntent();
	    actionBar.setTitle(intent.getStringExtra("title"));
	    
	    if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
/*	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.library_detail, menu); */
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case android.R.id.home:
				this.finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(LibraryDetailFragment.ARG_ITEM_ID, id);
            LibraryDetailFragment fragment = new LibraryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, LibraryActivity.class);
            detailIntent.putExtra(LibraryDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
	}

}
