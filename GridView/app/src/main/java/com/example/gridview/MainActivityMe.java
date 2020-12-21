package com.example.gridview;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivityMe extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ListView listView, listView1;
    Context context;
    ArrayList<RowModel> rowData;
    CustomAdapter customAdapter;
    RowModel rowModel;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayout2;
    TextView textView;
    Button Dn;
    int[] img = {R.drawable.voucher,R.drawable.coins,R.drawable.wallet,R.drawable.person,R.drawable.address,R.drawable.help,R.drawable.shop,R.drawable.gear,R.drawable.information};
    String[] a = {"Ví Voucher", "Shopee Xu" , "Thanh Toán", "Mời bạn bè" , "Địa chỉ", "Trung tâm Trợ giúp",
            "Ứng dụng chủ quán", "Cài đặt" , "Về Now"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_me);
        relativeLayout = findViewById(R.id.thongtin_pf);
        relativeLayout2 = findViewById(R.id.list_thongtin);
        Dn = findViewById(R.id.bt_lognout);
        textView = findViewById(R.id.tennguoidung);
        context = this;
        if (MainActivity.sdt=="")
        {
            relativeLayout.setVisibility(View.GONE);
            relativeLayout2.setVisibility(View.GONE);
            Dn.setText("Đăng Nhập");
        }
        else
        {
            Cursor cursor = MainActivity.database.rawQuery("Select * from TaiKhoan where SDT ='"+MainActivity.sdt+"'",null);
            cursor.moveToFirst();
            textView.setText(cursor.getString(0));
        }
        Dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.sdt=="")
                {
                    Intent intent = new Intent(MainActivityMe.this,DangNhapActivity.class);
                    startActivity(intent);
                }
                else {
                    MainActivity.sdt="";
                    relativeLayout.setVisibility(View.GONE);
                    relativeLayout2.setVisibility(View.GONE);
                    Dn.setText("Đăng Nhập");
                }
            }
        });
        Cursor cursor = MainActivity.database.rawQuery("Select * from TaiKhoan where SDT ='"+MainActivity.sdt+"'",null);
        cursor.moveToFirst();
        listView = findViewById(R.id.listView);
        rowData = new ArrayList<>();
        addRowData();
        customAdapter = new CustomAdapter(context, rowData);
        listView.setAdapter(customAdapter);

        listView1 = findViewById(R.id.listView1);
        rowData = new ArrayList<>();
        addRowData1();
        customAdapter = new CustomAdapter(context, rowData);
        listView1.setAdapter(customAdapter);
        //bottom menu
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent =new Intent(MainActivityMe.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.ic_giohang:
                        intent =new Intent(MainActivityMe.this,MainActivityGioHang.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.ic_me:

                        break;
                }
                return false;
            }
        });
    }

    private void addRowData() {
        for (int i = 0 ; i<= a.length-6; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
    private void addRowData1() {
        for (int i = 4 ; i<= a.length-1; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
}