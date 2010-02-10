/**
 * 
 */
package fm.last.android.activity;

import fm.last.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Help extends Activity {
	WebView mWebView;
	String mLastUrl;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.help);

	    mWebView = (WebView) findViewById(R.id.webview);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    if(savedInstanceState != null)
	    	mLastUrl = savedInstanceState.getString("url");
	    
	    if(mLastUrl != null && mLastUrl.length() > 0)
	    	mWebView.loadUrl(mLastUrl);
	    else
	    	mWebView.loadUrl("file:///android_asset/index.html");
	    mWebView.setWebViewClient(new WebViewClient() {
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        view.loadUrl(url);
		        mLastUrl = url;
		        return true;
		    }
	    });
	}
	
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("url", mLastUrl);
		super.onSaveInstanceState(outState);
	}

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
	        mWebView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
