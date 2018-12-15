package com.example.dhaivat.foodlogger.adapters;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.R;
import com.example.dhaivat.foodlogger.models.Meals;


import java.util.ArrayList;
import java.util.Locale;

public class AllMealsAdapter extends RecyclerView
        .Adapter<AllMealsAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Meals> mealsArrayList;

    private Context context;
    private static MyClickListener myClickListener;
    private String filter;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvMenuName,tvProtien,tvCarbohydrate,tvFat,tvCalories,tvDateTime,type;
        private LinearLayout llDateAndtype;



        public DataObjectHolder(View itemView) {
            super(itemView);
            tvMenuName = (TextView) itemView.findViewById(R.id.tvMenuName);
            tvProtien = (TextView) itemView.findViewById(R.id.tvProtien);
            tvCarbohydrate = (TextView) itemView.findViewById(R.id.tvCarbohydrate);
            tvFat = (TextView) itemView.findViewById(R.id.tvFat);
            tvCalories
                    = (TextView) itemView.findViewById(R.id.tvCalories);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            type = (TextView) itemView.findViewById(R.id.type);
            llDateAndtype= (LinearLayout) itemView.findViewById(R.id.llDateAndtype);
//  *****for line on text********
//            cut_price.setPaintFlags(cut_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Log.i(LOG_TAG, "Adding Listener");
           // itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //       myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AllMealsAdapter(Context context, ArrayList<Meals> mealsArrayList,String filter) {
        this.context = context;
        this.mealsArrayList = mealsArrayList;
        this.filter = filter;
    }


    public void setdata(ArrayList<Meals> mealsArrayList, String filter){
        this.mealsArrayList = mealsArrayList;
        this.filter = filter;
        notifyDataSetChanged();
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menues, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//        holder.quote.setText(recommendedDishesArrayList.get(position).getQuote());
//
//
//

        String itemValue = mealsArrayList.get(position).getMealName();

        int startPos = itemValue.toLowerCase(Locale.US).indexOf(filter.toLowerCase(Locale.US));
        int endPos = startPos + filter.length();

        if (startPos != -1) // This should always be true, just a sanity check
        {
            Spannable spannable = new SpannableString(itemValue);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[]
                    { Color.parseColor("#000000") });
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.tvMenuName.setText(spannable);
        }
        else
            holder.tvMenuName.setText(itemValue);



        holder.tvCarbohydrate.setText("CARBOHYDRATE : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvProtien.setText("PROTIEN : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvFat.setText("FAT : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvCalories.setText("CALORIES : "+mealsArrayList.get(position).getCalories());


        if (mealsArrayList.get(position).isShowDate() == true){
            holder.llDateAndtype.setVisibility(View.VISIBLE);

        }else {
            holder.llDateAndtype.setVisibility(View.GONE);
        }

        holder.tvDateTime.setText(mealsArrayList.get(position).getDate1());

        holder.type.setText(" - "+mealsArrayList.get(position).getMealCategory());



//        holder.totalWeight.setText(airGroundServiceArrayList.get(position).get());

    }



    @Override
    public int getItemCount() {
        return mealsArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}