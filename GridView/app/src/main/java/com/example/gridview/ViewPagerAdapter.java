package com.example.gridview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gridview.Fragment.FragmentGoiY;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentGoiY("WHERE SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 and idDiaChi =1 GROUP by Shop.idShop LiMIT 5");
            case 1:
                return new FragmentGoiY("WHERE SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 AND thinhHanh =1 GROUP by Shop.idShop LiMIT 5");
            case 2:
                return new FragmentGoiY("WHERE SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop Order by avg(sao) desc LiMIT 5");
            default: return new FragmentGoiY("WHERE SanPham.idShop = Shop.idShop AND ChiTietShop.idShop = Shop.idShop AND sao >= 4 GROUP by Shop.idShop LiMIT 5");
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position)
        {
            case 0:
                title = "Gần tôi";
                break;
            case 1:
                title = "Bán chạy";
                break;
            case 2:
                title = "Đánh giá";
                break;
        }
        return title;
    }
}
