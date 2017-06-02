package com.gangzi.im.controller.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gangzi.im.R;
import com.gangzi.im.base.BaseFragment;
import com.gangzi.im.controller.activity.LoginActivity;
import com.gangzi.im.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/5/5.
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private Button bt_exit;

    @Override
    public View initView() {
        View view=View.inflate(mContext, R.layout.fragment_setting,null);
        bt_exit= (Button) view.findViewById(R.id.bt_exit);
        bt_exit.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        bt_exit.setText("退出登录"+EMClient.getInstance().getCurrentUser());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_exit:
                exit();
                break;
        }
    }

    private void exit() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Model.getInstance().getDbManager().close();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"退出成功！",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                getActivity().finish();
                            }
                        });
                    }

                    @Override
                    public void onError(int i, final String s) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"退出失败！"+s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }
}
