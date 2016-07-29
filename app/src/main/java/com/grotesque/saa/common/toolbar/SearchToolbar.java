package com.grotesque.saa.common.toolbar;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.SoftInputUtils;

import static com.grotesque.saa.util.LogUtils.LOGE;

public class SearchToolbar extends RelativeLayout implements TextWatcher {
    private final String TAG = SearchToolbar.class.getSimpleName();
    private Context mContext = null;
    private EditText mEditText;
    private ImageButton mCancelBtn;
    private ImageButton mClearBtn;
    private SearchToolbar.SearchBarListener mSearchBarListener;

    public SearchToolbar(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
        initUI();
    }

    public SearchToolbar(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public SearchToolbar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        initUI();
        init(paramContext, paramAttributeSet);
    }

    private void initUI()
    {
        LayoutInflater.from(mContext).inflate(R.layout.layout_search_toolbar, this, true);
        LOGE(this.TAG, "initUI");

        this.mEditText = ((EditText)findViewById(R.id.search_edit));
        this.mEditText.addTextChangedListener(this);
        this.mEditText.setOnEditorActionListener(new editorActionListener(this));
        this.mEditText.setTypeface(FontManager.getInstance(mContext).getTypeface());
        this.mClearBtn = ((ImageButton)findViewById(R.id.search_clearButton));
        this.mClearBtn.setOnClickListener(new searchClickListenr(this));
        this.mCancelBtn = ((ImageButton)findViewById(R.id.search_cancelButton));
        this.mCancelBtn.setOnClickListener(new cancelClickListener(this));
    }

    private void init(Context paramContext, AttributeSet paramAttributeSet)
    {

        LOGE(this.TAG, "init");

    }

    public void afterTextChanged(Editable paramEditable)
    {
        if ((this.mSearchBarListener != null) && (paramEditable != null)) {
            this.mSearchBarListener.searchTextChanged(paramEditable.toString());
        }
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}

    public void hideSoftInput()
    {
        SoftInputUtils.hide(this.mEditText);
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
        if (paramCharSequence.length() > 0)
        {
            this.mClearBtn.setVisibility(View.VISIBLE);
            return;
        }
        this.mClearBtn.setVisibility(INVISIBLE);
    }

    public void setPlaceholder(int paramInt)
    {
        if (this.mEditText != null) {
            this.mEditText.setHint(paramInt);
        }
    }

    public void setSearchBarListener(SearchToolbar.SearchBarListener paramSearchBarListener)
    {
        this.mSearchBarListener = paramSearchBarListener;
    }

    public void setSearchKeyword(String paramString)
    {
        this.mEditText.setText(paramString);
    }

    public void showSoftInput()
    {
        this.mEditText.requestFocus();
        SoftInputUtils.show(this.mEditText);
    }

    public interface SearchBarListener
    {
        void cancelButtonClicked(View paramView);

        void doSearch(String paramString);

        void searchTextChanged(String paramString);
    }

    class cancelClickListener implements View.OnClickListener
    {
        cancelClickListener(SearchToolbar searchToolbar) {}

        public void onClick(View view)
        {
            if (mSearchBarListener != null) {
                mSearchBarListener.cancelButtonClicked(view);
            }
        }
    }
    class searchClickListenr
            implements View.OnClickListener
    {
        searchClickListenr(SearchToolbar searchToolbar) {}

        public void onClick(View paramView)
        {
            if (mEditText != null) {
                mEditText.setText("");
            }
        }
    }
    class editorActionListener
            implements TextView.OnEditorActionListener
    {
        editorActionListener(SearchToolbar searchToolbar) {}

        public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
        {
            if (mSearchBarListener != null)
            {
                mSearchBarListener.doSearch(paramTextView.getText().toString());
                SoftInputUtils.hide(mEditText);
            }
            return false;
        }
    }
}