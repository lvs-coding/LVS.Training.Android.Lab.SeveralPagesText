package com.example.android.severalpagestext;

import android.content.Context;
import android.os.Handler;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import jedi.functional.Command;

/**
 * Created by laurent on 1/20/17.
 */

public class BookView extends ScrollView  {
    final String LOG_TAG = BookView.class.getSimpleName();
    private Handler scrollHandler;
    private float startX,startY,endX,endY,diffX,diffY;
    static final int MIN_DISTANCE = 150;
    boolean horizontalSwipe = false;

    int iteration100 = 0;
    int iterationSup100 = 0;


    public BookView(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.scrollHandler = new Handler();
        //tvText = (TextView)findViewById(R.id.tv_text);
    }



    @Override
    protected void onScrollChanged(int currentHOrigin,
                                   int currentVOrigin,
                                   int previousHOrigin,
                                   int previousVOrigin) {
        super.onScrollChanged(currentHOrigin, currentVOrigin, previousHOrigin, previousVOrigin);
        updateProgress();
    }


    private void updateProgress() {
        if(!horizontalSwipe) {
            //this.scrollTo(0, this.getTop());
        }

    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        switch(event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                float deltaX = x2 - x1;
//                if (Math.abs(deltaX) > MIN_DISTANCE)
//                {
//                    if(deltaX > 0) {
//                        Log.d("swipe","right");
//                        return super.onInterceptTouchEvent(event);
//                    } else {
//                        Log.d("swipe","left");
//                        return super.onInterceptTouchEvent(event);
//                    }
//
//                }
//                else
//                {
//                    // consider as something else - a screen tap for example
//                }
//                break;
//        }
//        return false;
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                Log.d(LOG_TAG,"ACTION_UP");
                Log.d(LOG_TAG,"Y scroll top : " + this.getScrollY());
                Log.d(LOG_TAG,"startX : " + startX);
                Log.d(LOG_TAG,"startY : " + startY);
                Log.d(LOG_TAG,"endX : " + endX);
                Log.d(LOG_TAG,"endY : " + endY);
                Log.d(LOG_TAG,"diffX : " + diffX);
                Log.d(LOG_TAG,"diffY : " + diffY);

                if(Math.abs(diffX) > 80) {
                    int visibleTextHeight = this.getHeight();
                    if(diffX < 0) {
                        this.scrollTo(0, getScrollY() + visibleTextHeight);
                    } else {
                        this.scrollTo(0, getScrollY() - visibleTextHeight);
                    }
                    //this.pageScroll(View.FOCUS_DOWN);

                } else {
                    Log.d(LOG_TAG, "==== NO SCROLL ====");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                iteration100 ++;
                endX = event.getX();
                endY = event.getY();

                diffX = endX - startX;
                diffY = endY - startY;

//                    int visibleTextHeight = this.getHeight();
//                    if(diffX != 0) {
//                        //this.pageScroll(View.FOCUS_UP);
//                        this.scrollTo(0, getScrollY() - visibleTextHeight);
//                    } else {
//                        //this.pageScroll(View.FOCUS_DOWN);
//                        //this.scrollTo(0, getScrollY() + visibleTextHeight);
//                    }

                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
        }


        return true;

//        horizontalSwipe = false;
//        switch(event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                y1 = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                y2 = event.getY();
//                float deltaX = x2 - x1;
//                if (Math.abs(deltaX) > MIN_DISTANCE)
//                {
//                    if(deltaX > 0) {
//                        // Swipe right
//                        this.scrollTo(0,150);
//                        horizontalSwipe = true;
//                        Log.d("swipe","right");
//                    } else {
//                        // Swipe left
//                        horizontalSwipe = true;
//                        Log.d("swipe","left");
//                    }
//
//                }
//                else
//                {
//                    // consider as something else - a screen tap for example
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
    }

}
