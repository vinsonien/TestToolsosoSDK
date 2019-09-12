package com.test.sdk.toolsoso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.vs.toolsoso.widget.view.LoadingErrorBgLayoutGroupView;
import com.vs.toolsoso.widget.view.ToolMenuLayout.OnToolMenuLayoutItemClickListener;
import com.vs.toolsoso.widget.view.ToolMenuLayout.ToolMenuLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolmenu)
    ToolMenuLayout toolmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        InitView();


    }

    private void InitView() {



    }


//    private void InitView() {
//        String[] tabTextArray = new String[]{"111","222","333"
//                ,"444","555","666","777","888"};
//        int[] tabIconArray = new int[]{R.mipmap.news_off,R.mipmap.news_on,
//                R.mipmap.map_off,R.mipmap.map_on,
//                R.mipmap.person_off,R.mipmap.person_on,
//                R.mipmap.news_off,R.mipmap.news_on,};
//
//        toolmenu.setTabTextArray(tabTextArray);
//        toolmenu.setTabIconArray(tabIconArray);
//        toolmenu.setRowItemCount(5);
//        toolmenu.setType(ToolMenuLayout.ALIGN_TYPE);
//        toolmenu.setOnMenuLayoutClickListener(onToolMenuLayoutItemClickListener);
//        toolmenu.init();
//    }
//
//    OnToolMenuLayoutItemClickListener onToolMenuLayoutItemClickListener = new OnToolMenuLayoutItemClickListener() {
//        @Override
//        public void onItemClick(int position) {
//            Log.e("toolsoso","pos==" + position);
//        }
//    };
}
