package com.gangzi.im.controller.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gangzi.im.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddContactActivity extends Activity {

    @BindView(R.id.bt_add)
    Button bt_add;
    @BindView(R.id.find)
    TextView tv_find;
    @BindView(R.id.et_name)
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
    }
}
