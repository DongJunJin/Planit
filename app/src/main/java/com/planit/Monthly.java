package com.planit;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Andrew on 7/22/2016.
 */
public class Monthly extends Fragment {

    private Calendar _calendar = Calendar.getInstance(Locale.getDefault());
    private int month = _calendar.get(Calendar.MONTH);
    private int year = _calendar.get(Calendar.YEAR);

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View monthly = inflater.inflate(R.layout.content_main_monthly, container, false);

        GridView calendarView = (GridView) monthly.findViewById(R.id.calendar_grid);
        _calendar.add(Calendar.MONTH,1);

        GridCellAdapter adapter = new GridCellAdapter(getActivity().getApplicationContext(), R.id.calendar_grid, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        //TODO: Switch Months
        //Tap to change Months
//        Button rightButton = (Button) monthly.findViewById(R.id.rightbutton);
//        rightButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GridCellAdapter grid = new GridCellAdapter(getActivity().getApplicationContext(),R.id.calendar_grid,month,year);
//                grid.upMonth();
//            }
//        });
//
//        Button leftButton = (Button) monthly.findViewById(R.id.leftbutton);
//        leftButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GridCellAdapter grid = new GridCellAdapter(getActivity().getApplicationContext(),R.id.calendar_grid,month,year);
//                grid.downMonth();
//            }
//        });


        //FloatingButtonClickAction
        //Button to go back to Activity
        ((MainActivity)getActivity()).showFloatingActionButton();
        ((MainActivity)getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventForm.class);
                startActivityForResult(intent,0);
            }
    });

        return monthly;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getActivity().getApplicationContext();
    }

    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener, View.OnLongClickListener{
        private static final String tag = "GridCellAdapter";
        private final Context _context;
        private final List<String> list;
        private final String[] weekdays = new String[] {"Sun" , "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private int month, year;
        int daysInMonth, prevMonthDays;
        private final int currentDayOfMonth;
        private Button gridcell;

        public GridCellAdapter(Context context, int textViewResourceId, int month, int year){
            super();
            this._context = context;
            this.list = new ArrayList<String>();
            this.month = month;
            this.year = year;

            Calendar calendar = Calendar.getInstance();
            currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            printMonth(month, year);
        }

        public String getItem(int position){
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        private int getFirstDayofMonth() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH,1);
            switch (cal.get(Calendar.DAY_OF_WEEK)){
                case Calendar.SUNDAY:
                    return 0;
                case Calendar.MONDAY:
                    return 1;
                case Calendar.TUESDAY:
                    return 2;
                case Calendar.WEDNESDAY:
                    return 3;
                case Calendar.THURSDAY:
                    return 4;
                case Calendar.FRIDAY:
                    return 5;
                case Calendar.SATURDAY:
                    return 6;
            }
            return 0;
        }

        public void upMonth(){
            month++;
            printMonth(month,year);
        }

        public void downMonth(){
            month--;
            printMonth(month,year);
        }

        private void printMonth(int mm, int yy){
            int trailingSpaces = 0;
//            int leadSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            GregorianCalendar cal = new GregorianCalendar(yy, mm, currentDayOfMonth);

            // Days in Current Month
            daysInMonth = daysOfMonth[mm];
            if (mm == 11)
            {
                prevMonth = 10;
                daysInPrevMonth = daysOfMonth[prevMonth];
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
            } else if (mm == 0)
            {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = daysOfMonth[prevMonth];
                nextMonth = 1;
            } else
            {
                prevMonth = mm - 1;
                nextMonth = mm + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = daysOfMonth[prevMonth];
            }

            // Compute how much to leave before before the first day of the
            // month.
            // getDay() returns 0 for Sunday.

            trailingSpaces = getFirstDayofMonth();
//            Log.d("Trailing",String.valueOf(trailingSpaces));

            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1)
            {
                ++daysInMonth;
            }

            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++)
            {
                list.add(String.valueOf((daysInPrevMonth - trailingSpaces + 1) + i) + "-GREY" + "-" + months[prevMonth] + "-" + prevYear);
            }

            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++)
            {
                if(i != currentDayOfMonth){
                    list.add(String.valueOf(i) + "-WHITE" + "-" + months[mm] + "-" + yy);
                }

                if(i==currentDayOfMonth){
                    list.add(String.valueOf(i)+ "-BLUE" + "-" + months[mm]+"-"+yy);
                }

            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++)
            {
//                Log.d(tag, "NEXT MONTH:= " + months[nextMonth]);
                list.add(String.valueOf(i + 1) + "-GREY" + "-" + months[nextMonth] + "-" + nextYear);
            }
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
//            Log.d(tag, "getView ...");
            View row = convertView;
            if (row == null)
            {
                // ROW INFLATION
//                Log.d(tag, "Starting XML Row Inflation ... ");
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.gridcell, parent, false);

//                Log.d(tag, "Successfully completed XML Row Inflation!");
            }

            gridcell = (Button) row.findViewById(R.id.gridcell);
            gridcell.setOnClickListener(this);
            gridcell.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            gridcell.setOnLongClickListener(this);

            // ACCOUNT FOR SPACING

//            Log.d(tag, "Current Day: " + currentDayOfMonth);
            String[] day_color = list.get(position).split("-");
            gridcell.setText(day_color[0]);
            gridcell.setTag(day_color[0] + "-" + day_color[2] + "-" + day_color[3]);

            if (day_color[1].equals("GREY")) {
                gridcell.setTextColor(Color.LTGRAY);
            }
            if (day_color[1].equals("WHITE")) {
                gridcell.setTextColor(Color.WHITE);
            }
            if (day_color[1].equals("BLUE")) {
                gridcell.setTextColor(Color.BLUE);
            }

            return row;
        }

        @Override
        public void onClick(View view)
        {
            String date_month_year = (String) view.getTag();
            Toast.makeText(getActivity().getApplicationContext(), date_month_year, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View view){
            Intent intent = new Intent(view.getContext(), EventForm.class);
            startActivityForResult(intent,0);
            EventForm eventForm = new EventForm();
            //TODO: Conversion to implement date to specific string
//            int day = currentDayOfMonth;
//
//            addDate = eventForm.addWhere();
            return true;
        }
    }
}
