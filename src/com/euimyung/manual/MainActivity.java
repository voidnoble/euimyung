package com.euimyung.manual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    hideNavigationBar();
	    
	    setContentView(R.layout.main);
	    
	    Typeface typeFace = Typeface.createFromAsset(getAssets(), "NanumMyeongjo.ttc");
	    
	    Button btn_about = (Button) findViewById(R.id.btn_about);
	    btn_about.setPaintFlags(btn_about.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	    btn_about.setTypeface(typeFace);
	    
	    Button btn_video_medicine = (Button) findViewById(R.id.btn_video_medicine);
	    btn_video_medicine.setPaintFlags(btn_video_medicine.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	    btn_video_medicine.setTypeface(typeFace);
	    
	    Button btn_video_famewealth = (Button) findViewById(R.id.btn_video_famewealth);
	    btn_video_famewealth.setPaintFlags(btn_video_famewealth.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	    btn_video_famewealth.setTypeface(typeFace);
	    
	    Button btn_abook = (Button) findViewById(R.id.btn_abook);
	    btn_abook.setPaintFlags(btn_abook.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	    btn_abook.setTypeface(typeFace);
	    
	    Button btn_library = (Button) findViewById(R.id.btn_library);
	    btn_library.setPaintFlags(btn_library.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	    btn_library.setTypeface(typeFace);	    
	}
	
	@SuppressLint("NewApi")
	private void hideNavigationBar() {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		
	    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	 
	    // This work only for android 4.4+ 
	    if (currentApiVersion >= 19) {
	        getWindow().getDecorView().setSystemUiVisibility(flags);
	        // Code below is for case when you press Volume up or Volume down. 
	        // Without this after pressing valume buttons navigation bar will 
	        // show up and don't hide 
	        final View decorView = getWindow().getDecorView();
	        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override 
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                	} 
                } 
	        }); 
	    }
	}

	public void onAboutButtonClicked(View v) {
		Intent i = new Intent(getApplicationContext(), AboutActivity.class);
		String title = getResources().getString(R.string.button_about);
		i.putExtra("title", title);
		startActivity(i);
	}
	
	public void onVideoFamewealthButtonClicked(View v) {
		Intent i = new Intent(getApplicationContext(), VideoActivity.class);
		String title = getResources().getString(R.string.button_video_famewealth);
		i.putExtra("title", title);
		startActivity(i);
	}
	
	public void onVideoMedicineButtonClicked(View v) {
		Intent i = new Intent(getApplicationContext(), VideoActivity.class);
		String title = getResources().getString(R.string.button_video_medicine);
		i.putExtra("title", title);
		startActivity(i);
	}
	
	public void onAbookButtonClicked(View v) {
		Intent i = new Intent(getApplicationContext(), LibraryActivity.class);
		String title = getResources().getString(R.string.button_abook);
		i.putExtra("title", title);
		startActivity(i);
	}
	
	public void onLibraryButtonClicked(View v) {
		Intent i = new Intent(getApplicationContext(), LibraryActivity.class);
		String title = getResources().getString(R.string.button_library);
		i.putExtra("title", title);
		startActivity(i);
	}

	public void onHomepageButtonClicked(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.euimyung.com")); 
		startActivity(intent);
	}
}
