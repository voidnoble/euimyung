package com.euimyung.manual;

import android.util.TypedValue;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.widget.TextView;

public class TheSimpleOnScaleGestureListener extends SimpleOnScaleGestureListener {

	TextView mTextView;
	
	public TheSimpleOnScaleGestureListener(View v) {
		mTextView = (TextView) v;
	}

	@Override
    public boolean onScale(ScaleGestureDetector detector) {
        float size = mTextView.getTextSize();
        float factor = detector.getScaleFactor();
        int increase = 0;
        if(factor > 1.0f)
            increase = 2;
        else if(factor < 1.0f)
            increase = -2;

        size += increase;

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                  
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return true;
    }
}
