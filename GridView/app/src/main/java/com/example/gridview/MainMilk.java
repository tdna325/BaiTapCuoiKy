package com.example.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.gridview.Adapter.AdapterBoSuuTapTabLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainMilk extends AppCompatActivity {
    private ViewPager mVp;
    ImageView btback;
    TextView tenloaisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_milk);
        tenloaisp = findViewById(R.id.textView);
        initView();

        btback = (ImageView) findViewById(R.id.ig_back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.Vp_trasua);
        Intent intent =getIntent();
        int dk = intent.getIntExtra("LoaiSP",1);
        tenloaisp.setText(intent.getStringExtra("NameLSP"));
        List<String> dieukienlist = new ArrayList<>();
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND ThinhHanh = 1 GROUP by Shop.idShop");
        dieukienlist.add("WHERE Shop.idLoaiSP ="+dk+" AND SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop GROUP by Shop.idShop");
        mVp.setAdapter(new AdapterBoSuuTapTabLayout(getSupportFragmentManager(),dieukienlist));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_trasua);
        tabLayout.setupWithViewPager(mVp);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}