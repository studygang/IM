package com.gangzi.im.controller.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gangzi.im.R;
import com.gangzi.im.controller.activity.AddContactActivity;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ContactFragment extends EaseContactListFragment {
    @Override
    protected void initView() {
        super.initView();
        titleBar.setRightImageResource(R.drawable.em_add);
        View headView= View.inflate(getActivity(),R.layout.header_layout_contanct,null);
        listView.addHeaderView(headView);
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
    }
}
