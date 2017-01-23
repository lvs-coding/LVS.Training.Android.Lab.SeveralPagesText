package com.example.android.severalpagestext;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by laurent on 1/23/17.
 */

public class BookWebView extends WebView {
    private final String LOG_TAG = BookView.class.getSimpleName();
    private final int SWIPE_LIMIT = 80;
    private float startX,diffX;

    public BookWebView(Context context) {
        super(context);
    }

    public BookWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:

                if(Math.abs(diffX) > SWIPE_LIMIT) {
                    int visibleTextHeight = this.getHeight();
                    if(diffX < 0) {
                        Log.d(LOG_TAG,"SCROLLING DOWN");
                        this.scrollTo(0, getScrollY() + visibleTextHeight);
                    } else {
                        Log.d(LOG_TAG,"SCROLLING UP");
                        this.scrollTo(0, getScrollY() - visibleTextHeight);
                    }

                } else {
                    Log.d(LOG_TAG, "==== NO SCROLL ====");
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                diffX = endX - startX;
                break;
        }

        return true;
    }
}
