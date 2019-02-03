package com.example.yc.zhihudaily.View

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.example.yc.zhihudaily.R
import com.google.gson.Gson
import com.squareup.okhttp.Call
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response

import org.json.JSONException
import org.json.JSONObject

import java.io.IOException

class StoryBodyActivity : AppCompatActivity() {

    private var id: String? = null
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_body)

        webView = findViewById(R.id.webView)
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("id")
        }
        initData(id)
    }

    private fun initData(id: String?) {
        webView!!.loadUrl("http://daily.zhihu.com/story/" + id!!)
        //        WebSettings webSettings = webView.getSettings();
        //        webSettings.setLoadWithOverviewMode(true);
        //        webSettings.setSupportZoom(true);
        //        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        //            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //        }
        //        webSettings.setBlockNetworkImage(false);
    }
}
