package com.ms.userms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ms.userms.activity.AddActivity;
import com.ms.userms.adapter.MSRecycleAdapter;
import com.ms.userms.models.Info;
import com.ms.userms.utils.UtilDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btAdd;
    private Button btDelete;
    private Button btSend;
    private Button btClear;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Info> list;
    private UtilDao dao;
    private Context context;
    private Set<Integer> ck = new HashSet<>();
    private MSRecycleAdapter recycleAdapter;
    private MSRecycleAdapter.Callback callback;
    SmsManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        sManager = SmsManager.getDefault();

        //init Data
        initData();

        //init view
        initView(this);
    }

    private void initView(Context context) {

        btAdd = (Button) findViewById(R.id.bt_add);
        btDelete = (Button) findViewById(R.id.bt_delete);
        btSend = (Button) findViewById(R.id.bt_send);
        btClear = (Button) findViewById(R.id.bt_clear);

        //设置点击事件
        initClick();

        //回调
        callback = new MSRecycleAdapter.Callback() {
            @Override
            public void click(int v) {
                if (ck.contains(v)) {
                    ck.remove(v);
                } else {
                    ck.add(v);
                }
//                Toast.makeText(context, "" + ck.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        initAdapter(callback);

    }

    private void initAdapter(MSRecycleAdapter.Callback callback) {

        ck.clear();
        recyclerView = (RecyclerView) findViewById(R.id.recy);

        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        recycleAdapter = new MSRecycleAdapter(this, list, callback);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "initData: " + requestCode + "," + resultCode);
        if (requestCode == 11 && resultCode == 1) {
//            Toast.makeText(context, "" + requestCode, Toast.LENGTH_SHORT).show();
            initData();
            initAdapter(callback);
        }
    }

    private void initClick() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 11);
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(null);
                alertDialog.setTitle("Message");
                alertDialog.setMessage("确定要删除选择项?");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您单击了否", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = ck.toString();
//                        for (int str : ck) {
//                            System.out.println(str);
//                        }
                        s = s.replace("[", " ");
                        s = s.replace("]", " ");
                        Log.d("TAG", "onClick: " + s);
                        dao.delData("id in (" + s + ")", null);
                        Toast.makeText(MainActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                        initData();
                        initAdapter(callback);
                    }
                });

                alertDialog.show();
            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck.clear();
                initAdapter(callback);

            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(null);
                alertDialog.setTitle("Message");
                alertDialog.setMessage("确定群发信息吗?");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您单击了否", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        for (Info number : list) {
//                            // 发送短信
//                            sManager.sendTextMessage(number, null, content
//                                    .getText().toString(), pi, null);
//                        }
//                        // 提示短信群发完成
                        try {
                            StringBuilder s = new StringBuilder();
                            int[] ints = new int[ck.size()];
                            i = 0;
                            for (int ij : ck) {
                                ints[i] = ij;
                                i++;
                            }
                            for (int j = 0; j < ints.length; j++) {
                                for (int k = 0; k < list.size(); k++) {
                                    if (list.get(k).getId()==ints[j]){
                                        s.append(list.get(k).getPhone()).append(";");
                                    }
                                }
                            }
                            String s1 = s.substring(0, s.length() - 1);
                            Log.d("TAG", "onClick: " + s1);
                            Uri smsToUri = Uri.parse("smsto:" + s1);
                            Log.d("TAG", "onClick: "+"smsto:" + s1);
                            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

                            intent.putExtra("sms_body", "smsBody");

                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.show();
            }
        });
    }

    public void initData() {
//        for (int i = 1; i <= 40; i++) {
//            boolean l = i % 2 == 0;
//            list.add(new Info("xiao" + i, "xiao" + i, l));
//        }

        dao = new UtilDao(this);
        list = (ArrayList<Info>) dao.inquireData();
        Log.d("TAG", "initData: " + list);

    }
}