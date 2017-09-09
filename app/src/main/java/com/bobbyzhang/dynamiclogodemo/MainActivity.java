package com.bobbyzhang.dynamiclogodemo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by BobbyZhang on 2017/9/9.
 * 欢迎访问我的博客：http://itbobby.top/
 * QQ：714301992
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity implements View.OnLayoutChangeListener{
    //用xUtils进行控件绑定
    @ViewInject(R.id.iv_login_logo)
    ImageView iv_login_logo;
    @ViewInject(R.id.ll_login_logobg)
    LinearLayout ll_login_logobg;
    @ViewInject(R.id.ll_login_root)
    LinearLayout activityRootView;//需要操作的布局

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);//控件绑定
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight(); //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRootView.addOnLayoutChangeListener(this);//给需要操作的布局设置监听
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right,
                               int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
       /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
        现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            ViewGroup.LayoutParams params = iv_login_logo.getLayoutParams();//获取布局，设置键盘弹起后logo的宽高
            params.height = 300;
            params.width = 300;
            iv_login_logo.setLayoutParams(params);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ll_login_logobg.getLayoutParams());
            lp.setMargins(0, 90, 0, 50);//设置包含logo的布局的位置
            ll_login_logobg.setLayoutParams(lp);
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {//键盘收回后，logo恢复原来大小，位置同样回到初始位置
            ViewGroup.LayoutParams params = iv_login_logo.getLayoutParams();
            params.height = 480;
            params.width = 480;
            iv_login_logo.setLayoutParams(params);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ll_login_logobg.getLayoutParams());
            lp.setMargins(0, 270, 0, 150);
            ll_login_logobg.setLayoutParams(lp);
        }
    }
}
