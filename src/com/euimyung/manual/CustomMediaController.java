package com.euimyung.manual;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;

public class CustomMediaController extends MediaController {
	View anchor;
	
	public CustomMediaController(Context context) {
		super(context);
	}
	
	public CustomMediaController(Context context, View anchor) {
		super(context);
		super.setAnchorView(anchor);
		this.anchor = anchor;
	}

	public CustomMediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomMediaController(Context context, boolean useFastForward) {
		super(context, useFastForward);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.widget.MediaController#setAnchorView(android.view.View)
	 */
	@Override
	public void setAnchorView(View view) {
		// TODO Auto-generated method stub
		super.setAnchorView(anchor);
	}

	/* (non-Javadoc)
	 * @see android.widget.MediaController#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
//		super.show(0);
	}

}
