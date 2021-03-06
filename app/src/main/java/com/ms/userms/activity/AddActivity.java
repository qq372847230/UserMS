package com.ms.userms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ms.userms.R;
import com.ms.userms.utils.SQLiteDB;
import com.ms.userms.utils.UtilDao;

public class AddActivity extends AppCompatActivity {


    private EditText etName;
    private EditText etPhone;
    private Button btSubmit;

    private String name;
    private String phone;

    private UtilDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //init database
        initDataBase();
        //init View
        initView();

    }

    //初始化数据库
    private void initDataBase() {
//        SQLiteDB.getInstance(this);
        dao = new UtilDao(this);
    }

    //加载-绑定视图
    private void initView() {
        btSubmit = (Button) findViewById(R.id.bt_submit);
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                if (name.isEmpty()||phone.isEmpty()){
                    Toast.makeText(AddActivity.this, "用户名或手机号不能为空!", Toast.LENGTH_SHORT).show();
                }else {
                    //submit data
                    dao.addData("users",new String[]{"name","phone"},new String[]{name,phone});
                    setResult(1);
                    finish();
                }

            }
        });
    }
}