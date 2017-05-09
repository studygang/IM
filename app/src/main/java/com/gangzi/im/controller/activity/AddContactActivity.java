package com.gangzi.im.controller.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gangzi.im.R;
import com.gangzi.im.model.Model;
import com.gangzi.im.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddContactActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.bt_add)
    Button bt_add;
    @BindView(R.id.find)
    TextView tv_find;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rela_layout)
    RelativeLayout mRelativeLayout;
    private String et_value;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
        bt_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.find:
                find();
                break;
            case R.id.bt_add:
                add();
                break;
        }
    }

    private void find() {
        et_value=et_name.getText().toString();
        if (TextUtils.isEmpty(et_value)){
            Toast.makeText(this,"输入的用户名称不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去服务器判断当前查找的用户是否存在
                userInfo=new UserInfo(et_value);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRelativeLayout.setVisibility(View.VISIBLE);
                        tv_name.setText(userInfo.getUserName());
                    }
                });
            }
        });
    }

    private void add() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环形服务器添加好友
                try {
                    EMClient.getInstance().contactManager().addContact(userInfo.getUserName(),"添加好友");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this,"发送好友邀请成功!",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this,"发送好友邀请失败!"+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
