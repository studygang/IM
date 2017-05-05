package com.gangzi.im;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.gangzi.im.controller.fragment.ChatFragment;
import com.gangzi.im.controller.fragment.ContactFragment;
import com.gangzi.im.controller.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity{

    @BindView(R.id.fl_main)
    FrameLayout mFrameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rg_main;

    private ChatFragment mChatFragment;
    private ContactFragment mContactFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initFragment();
        initListener();
    }

    private void initView() {

    }

    private void initFragment() {
        mChatFragment=new ChatFragment();
        mContactFragment=new ContactFragment();
        mSettingFragment=new SettingFragment();
    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int i) {
                Fragment fragment=null;
                switch (i){
                    case R.id.rb_chat:
                        fragment=mChatFragment;
                        break;
                    case R.id.rb_contact:
                        fragment=mContactFragment;
                        break;
                    case R.id.rb_setting:
                        fragment=mSettingFragment;
                        break;
                }
                //实现fragment切换
                switchFragment(fragment);
            }
        });
        rg_main.check(R.id.rb_chat);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }
}
