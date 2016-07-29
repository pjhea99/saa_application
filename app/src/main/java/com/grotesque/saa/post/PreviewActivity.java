package com.grotesque.saa.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.grotesque.saa.R;
import com.grotesque.saa.post.data.PostData;

import java.util.ArrayList;

public class PreviewActivity extends Activity implements PreviewFragment.Listener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Bundle args = getIntent().getExtras();
        ArrayList<PostData> arrayList = new ArrayList<>();
        if(args != null){
            arrayList = args.getParcelableArrayList("html");
        }
        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_content,
                        PreviewFragment.newInstance(arrayList))
                .commit();
    }

    @Override
    public void finish(String document_srl) {
        Intent intent = getIntent();
        intent.putExtra("document_srl", document_srl);
        setResult(RESULT_OK, intent);
        finish();
    }
}
