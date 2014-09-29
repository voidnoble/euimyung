package com.euimyung.manual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class AboutDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private AboutItems.AboutItem mItem;
    
    private TextView mTextView;
    private WebView mWebView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AboutDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle arguments = getArguments();
        if (arguments != null) {
	        if (arguments.containsKey(ARG_ITEM_ID)) {
	            // Load the dummy content specified by the fragment
	            // arguments. In a real-world scenario, use a Loader
	            // to load content from a content provider.
	            mItem = AboutItems.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
	        }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_detail, container, false);
//        mTextView = (TextView) rootView.findViewById(R.id.item_detail);
        mWebView = (WebView) rootView.findViewById(R.id.item_detail);
        String encoding = "UTF-8",
        		mimeType = "text/html";
        
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
        	try {
        		String dir = "/mnt/wordpie/euimyung/";
        		String fileName = mItem.id +".html";
        		
        		File file = new File(dir, fileName);
        		if (!file.exists()) {
        			Toast.makeText(getActivity(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        			return null;
        		}
        		
        		FileInputStream fis = new FileInputStream(dir + fileName);
	        	InputStreamReader isReader = new InputStreamReader(fis, encoding);
	        	BufferedReader bfReader = new BufferedReader(isReader);
	        	
	        	StringBuffer strBuffer = new StringBuffer();
	        	String content = "", line = "";
	        	while( (line = bfReader.readLine()) != null ) {
	        		strBuffer.append(line);
	        	}
	        	bfReader.close();
	        	
	        	content = strBuffer.toString();

	        	// TextView 방식
//	        	mTextView.setText(Html.fromHtml(content));
	        	
	        	// WebView 방식
//	        	content = URLEncoder.encode(content,"UTF-8");
//	        	content = nl2br(content);
//	        	content = "<!DOCTYPE HTML><html><head><meta charset=\""+ encoding +"\"><meta name=\"viewport\" content=\"user-scalable=yes, initial-scale=1.0, width=device-width\"></head><body>"+ content +"</body></html>";
	        	mWebView.getSettings().setSupportZoom(true);
	        	mWebView.getSettings().setBuiltInZoomControls(true);
	        	mWebView.getSettings().setDisplayZoomControls(true);
	        	mWebView.getSettings().setUseWideViewPort(true);
	        	mWebView.getSettings().setDefaultTextEncodingName("UTF-8");	// 웹뷰 한글 깨짐 방지
	        	mWebView.loadDataWithBaseURL(null, content, mimeType, encoding, null);
	        	
	        	/* 아래로 로딩하면 한글 다 깨짐
	        	if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
	        		mWebView.loadData(content, "text/html", encoding);
	        	} else {
	        		mWebView.loadData(content, "text/html; charset=UTF-8", encoding);  // Android 4.1 이상
	        	}*/
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }

        return rootView;
    }
    
    /* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		
		// Inflate the menu items for use in the action bar
		inflater.inflate(R.menu.about_detail, menu);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		WebSettings settings = mWebView.getSettings();
//		float fontSize = 18.0f;
		switch(item.getItemId()) {
			case android.R.id.home:
				getActivity().finish();
				break;
			case R.id.font_increase:
				/*fontSize = mTextView.getTextSize();
				mTextView.setTextSize(fontSize + 1);*/
			    settings.setTextZoom(settings.getTextZoom() + 10);
				break;
			case R.id.font_decrease:
				/*fontSize = mTextView.getTextSize();
				fontSize = ((fontSize - 1) < 18.0f)? 18.0f : fontSize - 1; 
				mTextView.setTextSize(fontSize);*/
				settings.setTextZoom(settings.getTextZoom() - 10);
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    private String nl2br(String str) {
    	str = str.replaceAll("\r\n", "<br><br>");
    	str = str.replaceAll("\n\r", "<br><br>");
    	str = str.replaceAll("\r", "<br><br>");
    	str = str.replaceAll("\n", "<br><br>");
    	return str;
    }
}
