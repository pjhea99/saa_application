package com.grotesque.saa.post;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.grotesque.saa.R;
import com.grotesque.saa.common.api.RetrofitApi;
import com.grotesque.saa.common.fragment.BaseActionBarFragment;
import com.grotesque.saa.post.data.PostData;
import com.grotesque.saa.util.WebViewUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

/**
 * Created by 경환 on 2016-04-14.
 */
public class PreviewFragment extends BaseActionBarFragment {
    private static final String TAG = makeLogTag(PreviewFragment.class);

    private String mMid = "freeboard3";
    private String mTitle = "";
    private String mContent = "";
    private ArrayList<PostData> mPostData;
    private WebView mWebview;

    public static PreviewFragment newInstance(ArrayList<PostData> postDataList){
        PreviewFragment fragment = new PreviewFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("html", postDataList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void initOnCreate(Bundle paramBundle) {
        if(getArguments() != null){
            mMid = getArguments().getString("mid");
            mPostData = getArguments().getParcelableArrayList("html");
        }
    }

    @Override
    protected void onInitCreated(Bundle paramBundle) {
        getHtml();
        mWebview.loadDataWithBaseURL("","<body style='margin:0px;padding:0px;'>" + mContent, "text/html", "utf-8", null);
    }

    @Override
    protected void registerView(View view) {
        super.registerView(view);
        mWebview = (WebView) view.findViewById(R.id.webview_preview);
        WebViewUtils.setWebview(mWebview);
    }

    @Override
    public void onRightButtonClicked() {
        super.onRightButtonClicked();
        RetrofitApi.getInstance().submitDocument(mMid,
                "",
                mTitle,
                mContent,
                "",
                RetrofitApi.API_BASE_URL + "freeboard3"
                ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body() != null){
                    LOGE(TAG, response.body().toString());
                    if(response.body().get("message").toString().contains("등록했습니다.")){
                        if (getActivity() instanceof Listener) {
                            ((Listener) getActivity()).finish(response.body().get("document_srl").toString());
                        }
                    }else{
                        Toast.makeText(getActivity(), "등록에 실패 했습니다.", Toast.LENGTH_LONG).show();
                    }
                }else
                    Toast.makeText(getActivity(), "등록에 실패 했습니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), "등록에 실패 했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getHtml(){
        for(PostData p : mPostData){
            switch (p.getType()) {
                case "img":
                    mContent = mContent + "<img src=\"" + p.getHtml() + "\" style=\"max-width: 100%;width: 100%; height: auto\"><br></br>";
                    break;
                case "txt":
                    mContent = mContent + p.getHtml() + "<br />";
                    break;
                case "title":
                    mTitle = p.getHtml();
                    break;
                case "video":
                    mContent = mContent + p.getHtml() + "<br />";
            }

        }
        LOGE(TAG, mContent);
    }
    public interface Listener{
        void finish(String document_srl);
    }
}
