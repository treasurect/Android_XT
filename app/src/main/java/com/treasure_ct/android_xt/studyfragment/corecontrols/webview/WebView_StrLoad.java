package com.treasure_ct.android_xt.studyfragment.corecontrols.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.MyWebChormClient;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.BrowerSupport;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.MyWebviewClient;

/**
 * WebView的使用：
 * 1.WebView 的基本使用
 * 2.WebView控制，UI状态显示
 * back,forward
 * getUrl()获取网址
 *
 * 3.WebView 加载内容的方式
 * 4.WebView网址的拦截
 * 5.**********  WebView Java与JS的交互  ********
 */
public class WebView_StrLoad extends AppCompatActivity implements View.OnClickListener, BrowerSupport {

    private WebView webView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_webview_strload);
        Button gomove = (Button) findViewById(R.id.gomove2);
        Button gostop = (Button) findViewById(R.id.gostop2);
        Button goreload = (Button) findViewById(R.id.goreload2);
        webView = (WebView) findViewById(R.id.webView_str);
        mProgressBar = (ProgressBar) findViewById(R.id.webView_progress_bar2);
        //TODO:解决点击url调到自带浏览器的问题
        //设置一个WebViewClient对象，可以解决跳转到系统自带浏览器的问题
        //如果不希望拦截网址，使用系统的对象即可
        webView.setWebViewClient(new MyWebviewClient(this));

        //TODO:实现网页记载进度的显示/标题显示
        webView.setWebChromeClient(new MyWebChormClient(this));

        //TODO:部分网址需浏览器支持 / 打开JS
        WebSettings settings = webView.getSettings();
        //开启JS支持
        if (Build.VERSION.SDK_INT < 11) {
            settings.setJavaScriptEnabled(true);
        } else {
            settings.setDisplayZoomControls(true);
        }

//        在网页中显示放大缩小
        settings.setBuiltInZoomControls(true);
        //允许加载本地网页
        settings.setAllowFileAccess(true);
        //直接加载html源码，显示成网页
        String message = "<p><img src=\"img/bd_logo1.png\"/></p> \n<p> 　　“救命啊！谁能帮我打120？我女儿全身抽搐！” 4月6日下午3时，在广州地铁一号线黄沙站站厅内，一名女乘客大声呼喊。地铁站工作人员赶到现场 后大吃一惊，家长为防全身抽搐的女儿咬到舌头，竟然掏出硬币塞进女儿嘴中让她咬住！工作人员马上指导家长取出女孩口中硬币，换上消毒纱布让女孩咬住。经过 一番救助，女孩被安全送院救治。 </p> \n<p> 　　当天当班地铁员工林婧婧告诉记者，听到女乘客大声呼喊后，她马上赶去，约两岁的小女孩正浑身抽搐。“我 马上拨打120急救电话，我一转身，家长的行为让我大吃一惊！”林婧婧说，那对年轻的父母竟然掏出一枚一元硬币塞进女孩口中。为了防止女孩将硬币吞下造成 更严重后果，林婧婧立即指导家长小心翼翼地将手指探进女孩紧闭的口中，将硬币取出。随后，林婧婧在药箱找出一卷干净的消毒纱布给女孩咬住，然后将乘客接到 会议室，等待120救护车到来。增援的值班站长黎国华一直给女孩按摩手脚，缓解其抽搐的状况。 </p> \n<p> 　　小女孩的家长表示，小女孩每次发烧就会全身抽搐，并且会紧咬物品。地铁工作人员提醒家长，再紧急的情况也不能把硬币放进小孩口中，可以用衣服等随身物品代替。3时05分，救护车到达车站，车站员工和家长合力把小女孩送上救护车。 </p> \n<p> 　　广州地铁提醒乘客，不当的救助方式会给发病人员造成二次伤害。如在车站内发生紧急情况，可第一时间向工作人员寻求帮助；如在列车上发生紧急情况，可使用列车上的紧急报警按钮通知列车司机或在到站后向车站工作人员求助。 </p> \n<br>";
        //加载源码数据：
        //参数1：HTML源码，或者是经过Base64编码的数据
        //参数2：数据的类型，如果参数1 是HTML，那么text/html;charset=UTF-8
        //参数3：如果参数1 是经过Base64编码的数据，写base64，否则为null
        String html = "<html><head><style>img {width:100%}</style></head><body>" + message + "</body></html>";
        webView.loadData(html, "text/html;charset=UTF-8", null);
        //注意：如果HTML种包含了相对路径的图片/超链接  使用loadData（）无法加载
        //loadDataWithBaseURL
        webView.loadDataWithBaseURL("https://www.baidu.com",html,"text/html;charset=UTF-8",null,null);
//        webView.loadUrl("http://10.0.153.168:8080/");
        gomove.setOnClickListener(this);
        gostop.setOnClickListener(this);
        goreload.setOnClickListener(this);
    }

    //实现后退网页
    @Override
    public void onBackPressed() {
        //1.判断可以后退的权限，可以则后退
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gomove2:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.gostop2:
                //停止当前的页面加载
                webView.stopLoading();
                break;
            case R.id.goreload2:
                //刷新，重新加载当前网页
                webView.reload();
//                webView.loadUrl(webView.getUrl());
                break;
        }
    }

    @Override
    public void onReceivedTitle(WebView webView, String title) {
        setTitle(title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mProgressBar.setProgress(newProgress);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {

    }

    @Override
    public void loadAppurl(WebView view, String url) {

    }
}
