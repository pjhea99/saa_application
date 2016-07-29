package com.grotesque.saa.common.fragment;

import android.app.Activity;
import android.view.View;

import com.grotesque.saa.activity.BaseActivity;
import com.grotesque.saa.common.actionbar.BaseActionBar;

import static com.grotesque.saa.util.LogUtils.makeLogTag;

/**
 * Created by 경환 on 2016-04-02.
 */
public abstract class BaseActionBarFragment
        extends BaseFragment
        implements BaseActionBar.OnActionBarListener {
    private static final String TAG = makeLogTag(BaseActionBarFragment.class);
    private ActionBarFragmentLayout b;
    private Activity c;

    public void onDestroy() {
        super.onDestroy();
    }

    protected void onKeyboardHide() {
    }

    protected void onKeyboardShow() {
    }

    public void onLeftButtonClicked() {

        if ((this.c instanceof BaseActivity)) {
            if (!((BaseActivity) this.c).isNavDrawerOpen()) {
                ((BaseActivity) this.c).openNavDrawer();
            }
            return;
        }
        getActivity().finish();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onRightButtonClicked() {}

    public void onSearchButtonClicked() {}

    public void onTitleTextClicked(){

    }

    protected void registerView(View view) {

        if ((view instanceof ActionBarFragmentLayout)) {
            this.b = ((ActionBarFragmentLayout) view);
            this.b.setOnActionBarListener(this);
        }
        this.c = getActivity();
    }
    public int getActionBarHeight(){
        if (this.b != null) {
            return this.b.getActionBarHeight();
        }
        return 0;
    }

    public void setActionBarBackgroundResource(int resource){
        if(this.b != null) {
            this.b.setActionBarBackgroundResource(resource);
        }
    }

    public void setActionBarColor(int paramInt) {
        if (this.b != null) {
            this.b.setActionBarColor(paramInt);
        }
    }

    public void setActionBarLeftButtonContentDescription(int paramInt) {
        if (this.b != null) {
            this.b.setLeftButtonContentDescription(paramInt);
        }
    }

    public void setActionBarLeftButtonUI(int paramInt) {
        if (this.b != null) {
            this.b.setLeftButton(paramInt);
        }
    }

    public void setActionBarRightButtonContentDescription(int paramInt) {
        if (this.b != null) {
            this.b.setRightButtonContentDescription(paramInt);
        }
    }

    public void setActionBarRightButtonUI(int paramInt) {
        if (this.b != null) {
            this.b.setRightButton(paramInt);
        }
    }

    public void setActionBarTitle(String paramString) {
        this.b.setActionBarTitle(paramString);
    }

    public void setActionBarTitleCount(int paramInt) {
        this.b.setActionBarTitleCount(paramInt);
    }

    public void setActionBarVisibility(boolean paramBoolean) {
        if (this.b != null) {
            this.b.setActionBarVisibility(paramBoolean);
        }
    }

    public void setHasNewMessage(boolean paramBoolean) {
        if (this.b != null) {
            this.b.setHasNewMessage(paramBoolean);
        }
    }

    protected void setOnSelectedTitleTab(BaseActionBar.OnSelectedTitleTab paramOnSelectedTitleTab) {
        if (this.b != null) {
            this.b.setOnSelectedTitleTab(paramOnSelectedTitleTab);
        }
    }

    protected void setSelectedTitleTab(BaseActionBar.TitleTap paramTitleTap) {

        if (this.b != null) {
            this.b.setSelectedTitleTab(paramTitleTap);
        }
    }
}
