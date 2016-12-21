//package com.planit;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.List;
//
///**
// * Created by Andrew on 7/14/2016.
// */
//public class DateSlider extends FragmentActivity {
//
//    MyPageAdapter pageAdapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.content_main);
//        List<Fragment> fragments = getFragments();
//
//        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
//
//        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
//
//        pager.setAdapter(pageAdapter);
//    }
//}
