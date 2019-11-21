package com.test.sdk.toolsoso.eventbus;

import android.app.Activity;
import android.content.Intent;
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

public class EventBeforeActivity extends Activity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_before);

        ButterKnife.bind(this);
        EventB.register(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventTestBean bean = new EventTestBean("before",1,true,new EventTestBean.EventIn("in before",2,false));
                EventInfo info = new EventInfo("before");
                info.put("bean",bean);
                info.put("str","before hahahaha");
                EventB.postSticky(info);
                startActivity(new Intent(EventBeforeActivity.this,EventAfterActivity.class));

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setTv(EventInfo info){
        switch (info.getTag()){
            case "after":
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
