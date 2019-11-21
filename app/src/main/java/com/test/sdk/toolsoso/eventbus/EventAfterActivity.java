package com.test.sdk.toolsoso.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.sdk.toolsoso.R;
import com.vs.toolsoso.eventbus.EventB;
import com.vs.toolsoso.eventbus.EventInfo;
import com.vs.toolsoso.utils.JsonUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAfterActivity extends Activity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_after);

        ButterKnife.bind(this);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventTestBean bean = new EventTestBean("after",3,false,new EventTestBean.EventIn("in after",4,true));
                EventInfo info = new EventInfo("after");
                info.put("bean",bean);
                info.put("str","after hahahaha");
                EventB.post(info);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventInfo info = new EventInfo("after2");
                info.put("str222","修改text");
                EventB.post(info);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventB.removeStickyEvent(EventInfo.class);
//                EventB.removeAllStickyEvent();
                EventB.unRegister(EventAfterActivity.this);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventB.register(EventAfterActivity.this);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setTv(EventInfo info){
        switch (info.getTag()){
            case "before":
                String str = (String) info.get("str");
                EventTestBean bean = (EventTestBean) info.get("bean");
                String string = str + "\n" + JsonUtil.toJSONString(bean);
                tv.setText(string);
                break;

            case "after2":
                String string2 = (String) info.get("str222");
                tv.setText(string2);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventB.removeAllStickyEvent();
        EventB.unRegister(this);
    }



}
