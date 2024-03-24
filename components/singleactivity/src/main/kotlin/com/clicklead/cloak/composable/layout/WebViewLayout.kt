package com.clicklead.cloak.composable.layout

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import com.clicklead.cloak.tools.getActivity


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewLayout(
    url: String,
    onChangeUrl: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val webView: WebView by remember { mutableStateOf(WebView(context)) }
    var filePathCallback: ValueCallback<Array<Uri>>? by remember(webView) { mutableStateOf(null) }
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var results: Array<Uri?>? = null
            val dataString = result.data?.dataString
            val clipData = result.data?.clipData
            if (clipData != null) {
                results = arrayOfNulls(clipData.itemCount)
                for (i in 0 until clipData.itemCount) {
                    val item = clipData.getItemAt(i)
                    results[i] = item.uri
                }
            }
            if (dataString != null) {
                results = arrayOf(Uri.parse(dataString))
            }
            val _filePathCallback = filePathCallback
            try {
                _filePathCallback?.onReceiveValue(
                    results?.map { uri: Uri? -> uri!! }?.toTypedArray() ?: arrayOf()
                )
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Что-то пошло не так попробуйте по другому =)",
                    Toast.LENGTH_LONG
                ).show()
            }
            filePathCallback = null
        }
    }

    BackHandler() {
        webView.goBack()
    }
    AndroidView(
        modifier = Modifier.fillMaxHeight(0.9f),
        factory = { _ ->
            webView.apply {
                settings.apply {
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    setSupportZoom(true)
                    allowContentAccess = true
                    allowFileAccess = true
                    allowContentAccess = true
                    domStorageEnabled = true
                    userAgentString = System.getProperty("http.agent")
                    setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                        val request = DownloadManager.Request(
                            Uri.parse(url)
                        )
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) //Notify client once download is completed!
                        request.setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_DOWNLOADS,
                            URLUtil.guessFileName(
                                url,
                                contentDisposition.ifEmpty { null },
                                mimetype.ifEmpty { null })
                        )
                        val dm =
                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        dm.enqueue(request)
                        Toast.makeText(
                            context,
                            "Downloading File",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                webChromeClient = object : WebChromeClient() {
                    var fullscreenView: View? = null
                    override fun onShowFileChooser(
                        webView: WebView?,
                        _filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        filePathCallback = _filePathCallback
                        val intent = fileChooserParams?.createIntent()
                        intent?.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                        getContent.launch(intent)
                        return true
                    }

                    override fun onShowCustomView(
                        paramView: View,
                        paramCustomViewCallback: CustomViewCallback
                    ) {
                        if (fullscreenView != null) {
                            onHideCustomView()
                            return
                        }
                        context.getActivity()?.let {
                            fullscreenView = paramView
                            (it.window.decorView as FrameLayout).addView(
                                fullscreenView,
                                FrameLayout.LayoutParams(-1, -1)
                            )
                            fullscreenView?.requestFocus()
                        }
                    }

                    override fun onHideCustomView() {
                        context.getActivity()?.let {
                            (it.window.decorView as FrameLayout).removeView(fullscreenView)
                        }
                        fullscreenView = null
                    }
                }
                webViewClient =
                    object : WebViewClient() {

                        override fun doUpdateVisitedHistory(
                            view: WebView?,
                            url: String?,
                            isReload: Boolean
                        ) {
                            url?.let { onChangeUrl.invoke(it) }
                            super.doUpdateVisitedHistory(view, url, isReload)
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            web: WebResourceRequest?
                        ): Boolean {
                            val _url = web!!.url.toString()
                            if (URLUtil.isNetworkUrl(_url)) {
                                return false
                            }
                            return try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(_url))
                                startActivity(context, intent, bundleOf())
                                true
                            } catch (e: Exception) {
                                Toast.makeText(context, "App is not installed", Toast.LENGTH_LONG)
                                    .show()
                                false
                            }
                        }
                    }

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { _webView ->
            _webView.loadUrl(url)
        }
    )
}
