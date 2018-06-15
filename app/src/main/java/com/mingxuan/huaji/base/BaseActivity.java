package com.mingxuan.huaji.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mingxuan.huaji.R;

import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/4/9.
 * 公司：铭轩科技
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected TextView toolbar_title,toolbar_subtitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initToolBar();
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) mToolbar.findViewById(R.id.toolbar_subtitle);
        if (mToolbar == null) {
            throw new NullPointerException("toolbar can not be null");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());

        /**是否显示子标题*/
        if(showSubtitle()){
            toolbar_subtitle.setVisibility(View.VISIBLE);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**toolbar除掉阴影*/
        getSupportActionBar().setElevation(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(0);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    protected void setToolbarTitle(String title) {
        toolbar_title.setText(title);
        getSupportActionBar().setTitle("");
    }

    /**
     * 设置子标题
     * @param title
     */
    protected void setSubtitle(String title) {
        toolbar_subtitle.setText(title);
    }

    /**
     * 是否显示副标题
     *
     * @return
     */
    protected boolean showSubtitle() {
        return false;
    }

    /**
     * 是否显示返回键
     *
     * @return
     */
    protected boolean showHomeAsUp() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
