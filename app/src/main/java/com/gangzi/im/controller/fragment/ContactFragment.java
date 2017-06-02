package com.gangzi.im.controller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;

import com.gangzi.im.R;
import com.gangzi.im.controller.activity.AddContactActivity;
import com.gangzi.im.utils.Constant;
import com.gangzi.im.utils.SpUtils;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ContactFragment extends EaseContactListFragment {

    private ImageView mImageView;
    private LocalBroadcastManager mLocalBroadcastManager;
    private ContactInviteChangeReceiver mReceiver;

    @Override
    protected void initView() {
        super.initView();
        titleBar.setRightImageResource(R.drawable.em_add);
        View headView= View.inflate(getActivity(),R.layout.header_layout_contanct,null);
        listView.addHeaderView(headView);
        mImageView= (ImageView) headView.findViewById(R.id.iv_red_point);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddContactActivity.class));
            }
        });
        //初始化红点的显示
        boolean isNewInvite=SpUtils.getmSpUtils().getBoolean(SpUtils.IS_NEW_INVITE,false);
        mImageView.setVisibility(isNewInvite?View.VISIBLE:View.GONE);
        //注册广播
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(getActivity());
        mReceiver=new ContactInviteChangeReceiver();
        mLocalBroadcastManager.registerReceiver(mReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHANGE));
    }
    class ContactInviteChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
