package com.grotesque.saa.rank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grotesque.saa.R;
import com.grotesque.saa.common.fragment.BaseActionBarFragment;
import com.grotesque.saa.common.api.service.RetrofitInterface;
import com.grotesque.saa.util.HttpsUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RankDetailFragment extends BaseActionBarFragment {

    private static final String DEFAULT_URL = "score.sports.media.daum.net/record/soccer/epl/";
    private static final String TEAM_ID = "detail_rank_team_id";

    private String mTeamName;

    private RecyclerView mPlayerRank;
    private RecyclerView mPleyerRankDetail;

    RetrofitInterface mService;



    public static RankDetailFragment newInstance(String teamId){
        RankDetailFragment fragment = new RankDetailFragment();
        Bundle args = new Bundle();
        args.putString(TEAM_ID, teamId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.fragment_rank_detail;
    }

    @Override
    protected void initOnCreate(Bundle paramBundle) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            getArguments().getString(TEAM_ID);
        }


        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(DEFAULT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void registerView(View view) {
        super.registerView(view);

        //Call<String> call = mService.getCommentList("index.php?act=dispBoardContentCommentList&document_srl=" + document_srl);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank_detail, container, false);
        mPlayerRank = (RecyclerView) view.findViewById(R.id.list_rank_detail_person);
        mPleyerRankDetail  = (RecyclerView) view.findViewById(R.id.list_rank_detail);

        return view;
    }

    @Override
    protected void onInitCreated(Bundle paramBundle) {

    }

    private class RankDetailTask extends AsyncTask<Void, Void, String>{
        private int teamId;

        public RankDetailTask(int teamId) {
            super();
            this.teamId = teamId;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

        @Override
        protected String doInBackground(Void... params) {
            return HttpsUtils.getHtml(DEFAULT_URL + teamId);
        }
    }
}
