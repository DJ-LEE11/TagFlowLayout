package com.uwork.tagflowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CustomTagLayout mTagLayout;
    private EditText mEtInput;
    private LinearLayout mLlAdd;

    // 存放标签数据的数组
    String[] mTextStr = { "nba","cba"};

    ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
        initLayout(mList);
    }

    private void initView() {
        mTagLayout = (CustomTagLayout) findViewById(R.id.tagLayout);
        mEtInput = (EditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        mLlAdd = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.flow_add, null);

        mLlAdd.setOnClickListener(new AddClickListen());
    }

    private void initList() {
        for(int i=0;i<mTextStr.length;i++){
            mList.add(mTextStr[i]);
        }
    }

    private void initLayout(ArrayList<String> arr) {
        //移除所有自布局
        mTagLayout.removeAllViewsInLayout();


        for (int i = 0; i < arr.size(); i++) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.flow_layout, mTagLayout, false);
            view.setTag(i);
            TextView text = (TextView) view.findViewById(R.id.tv);
            text.setText(mList.get(i));
            //点击移除标签
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = (int)view.getTag();
                    mTagLayout.removeView(view);
                    mList.remove(i);
                    initLayout(mList);
                }
            });
            mTagLayout.addView(view);
        }
        mTagLayout.addView(mEtInput);
        mTagLayout.addView(mLlAdd);
    }

    //添加标签
    private class AddClickListen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String label = mEtInput.getText().toString().trim();
            if(label!=null&&!label.equals("")){
                mList.add(label);
                initLayout(mList);
                mEtInput.setText("");
            }
        }
    }

}
