package com.euimyung.manual;

import kr.re.ksfm.manual.dummy.DummyContent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore.Video.Thumbnails;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class VideoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    
    private String filePath = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View rootView = inflater.inflate(R.layout.video_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
        	VideoView videoView = (VideoView) rootView.findViewById(R.id.item_detail);
//        	String filePath = "android.resource://"+ getActivity().getPackageName() +"/"+ R.raw.video_medicine_lecture01;
        	String dir = "/mnt/wordpie/euimyung/";	// Environment.getExternalStorageDirectory() + "/Movies/"
        	String fileName = "";
        	if (Integer.parseInt(mItem.id) < 10) {
        		fileName = "uimyung0"+ mItem.id +".mp4";
        	} else {
        		fileName = "uimyung"+ mItem.id +".mp4";
        	}
        	filePath = dir + "Video/"+ fileName;
        	
        	Bitmap bmThumbnail;
        	// Thumbnails.MINI_KIND = integer value 1 = 512 x 384
        	bmThumbnail = ThumbnailUtils.createVideoThumbnail(filePath, Thumbnails.MINI_KIND);
        	 
        	ImageButton videoThumbButton = (ImageButton) rootView.findViewById(R.id.thumbnail);
        	videoThumbButton.setImageBitmap(bmThumbnail);
        	videoThumbButton.setOnClickListener(videoThumbClicked);
        }

        return rootView;
    }
    
    View.OnClickListener videoThumbClicked = new View.OnClickListener() {		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
			intent.putExtra("filePath", filePath);
			startActivity(intent);
		}
	};
}
