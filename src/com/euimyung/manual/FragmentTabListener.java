package com.euimyung.manual;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
 
public class FragmentTabListener<t extends Fragment> implements TabListener {

    public static final String TAG = "FragmentTabListener";
     
    private final FragmentActivity	mActivity;
    private final String            mTag;
    private final Class<t>          mClass;
    private final Bundle            mBundle;
    private Fragment                mFragment;
    private boolean mTwoPane;
 
    public FragmentTabListener(FragmentActivity mActivity, String mTag, Class<t> mClass) {
        this(mActivity, mTag, mClass, null, false);
    }
    
    public FragmentTabListener(FragmentActivity mActivity, String mTag, Class<t> mClass, boolean mTwoPane) {    	
        this(mActivity, mTag, mClass, null, mTwoPane);
    }

    public FragmentTabListener(FragmentActivity mActivity, String mTag, Class<t> mClass, Bundle mBundle, boolean mTwoPane) {
        super();
        this.mActivity = mActivity;
        this.mTag = mTag;
        this.mClass = mClass;
        this.mBundle = mBundle;
        this.mTwoPane = mTwoPane;
         
        FragmentManager fm = this.mActivity.getSupportFragmentManager();
        mFragment = fm.findFragmentByTag(mTag);

        if(mFragment != null && !mFragment.isDetached()) {
            FragmentTransaction ft = this.mActivity.getSupportFragmentManager().beginTransaction();
            ft.detach(mFragment);
            ft.commit();
        }
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
//        Log.e(TAG, "onTabSelected");    	
        if(mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), mBundle);
            ft.add(R.id.item_detail_container, mFragment, mTag);
        } else {
            ft.attach(mFragment);
        }
        /*
        if (mTwoPane) {
        	Fragment f = (Fragment) mActivity.getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
        	ft.detach(f);
        }*/
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
//        Log.e(TAG, "onTabUnselected");
        if(mFragment != null) {
            ft.detach(mFragment);
        }
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
//        Toast.makeText(mActivity, "ReSelected!!!", Toast.LENGTH_SHORT).show();
    }
}