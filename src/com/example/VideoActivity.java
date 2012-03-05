package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class VideoActivity extends Activity
{
    EditText myEditText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myEditText = (EditText) findViewById(R.id.edit);

    }
    
    public void doYoutube(View view){
        myEditText.setText(loadAsset("youtube.html"), TextView.BufferType.EDITABLE);
        doLoadText(view);
    }
    
    public void doYouku(View view){
        myEditText.setText(loadAsset("youku.html"), TextView.BufferType.EDITABLE);
        doLoadText(view);
    }
    
    public void doLoadText(View view) {
        String html = myEditText.getText().toString();

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setPluginsEnabled(true);

        webView.loadDataWithBaseURL("http://www.youtube.com/", html, "text/html", "utf-8", null);
//        webView.loadData(html, "text/html", "utf-8");
    }
    
    String loadAsset(String filename) {
        InputStream inputStream = null;

        try {
            inputStream = getAssets().open(filename);
            return IOUtils.toString(inputStream, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
           IOUtils.closeQuietly(inputStream);
        }

    }
    
}
