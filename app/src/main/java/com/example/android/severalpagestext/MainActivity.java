package com.example.android.severalpagestext;

import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = MainActivity.class.getSimpleName();
    WebView bwvText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookWebView bwvText = (BookWebView)findViewById(R.id.bwv_text);
        String text = "<html><body style=\"text-align:justify\"> %s </body></Html>";

        bwvText.loadData(String.format(text,getString(R.string.lorem_ipsum)),"text/html; charset=utf-8", "utf-8");

        WebSettings webSettings = bwvText.getSettings();
        int fontSize = webSettings.getDefaultFontSize();

        bwvText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    bwvText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    bwvText.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                int webViewHeight = bwvText.getMeasuredHeight();
                adjustHeight(webViewHeight,fontSize);
            }
        });
    }


    private void adjustHeight(int webViewHeight, int fontSize) {
        // Is there something I can do here ?
    }






}
