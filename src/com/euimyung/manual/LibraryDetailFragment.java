package com.euimyung.manual;

import java.io.File;

import kr.re.ksfm.manual.dummy.DummyContent;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.artifex.mupdfdemo.MuPDFActivity;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
@SuppressLint({"NewApi", "SetJavaScriptEnabled", "SdCardPath"})
public class LibraryDetailFragment extends Fragment {
	
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    
    @NonConfigurationInstance
    Integer pageNumber = 1;
    
    String urlPath = null, filePath = null;
    Integer pdfFileId;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LibraryDetailFragment() {
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
	            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
	        }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_detail, container, false);
        
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
//        	pdfFileId = R.raw.pdf_enterance_volume01;
//        	urlPath = "android.resource://"+ getActivity().getPackageName() +"/"+ pdfFileId;
//        	File dir = Environment.getExternalStorageDirectory();
//        	File file = new File(dir + "/library", "pdf_enterance_volume01.pdf");
//        	String dir = Environment.getExternalStorageDirectory() + "/library/"; // use Asset dir = /android_asset/
//        	dir = "/android_asset/library/";
        	String dir = "/mnt/wordpie/euimyung/";
        	String fileName = "pdf_enterance_volume01.pdf";
        	filePath = dir + fileName;
        	File file = new File(filePath);
        	if (file.exists()) {
        		RelativeLayout mupdfWrapper = (RelativeLayout)rootView.findViewById(R.id.mupdf_wrapper);
                MuPDFCore core = null;
				try {
					// Load the PDF document
					core = new MuPDFCore(getActivity(), filePath);
					// Create the document view
					MuPDFReaderView mDocView = new MuPDFReaderView(getActivity());
					// connect the core at the view
	                mDocView.setAdapter(new MuPDFPageAdapter(getActivity(), core));
	                // Add the MuPDFReaderView to this layout
	                mupdfWrapper.addView(mDocView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
        	} // if (file.exists())
        } // if (mItem != null)

        return rootView;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // destroy all menu and re-call onCreateOptionsMenu
        getActivity().invalidateOptionsMenu();  
    }
    
    /* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		
		// Inflate the menu items for use in the action bar
		inflater.inflate(R.menu.library_detail, menu);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case android.R.id.home:
				getActivity().finish();
				break;
			case R.id.action_search:
				showPdf();
				break;				
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	// 새 Activity로 PDF 띄워 보이기
	private void showPdf() {		
		Uri uri = Uri.parse(filePath);

		Intent intent = new Intent(getActivity(), MuPDFActivity.class);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}
}
