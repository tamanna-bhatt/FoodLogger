package com.example.dhaivat.foodlogger.adapters;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;


import com.example.dhaivat.foodlogger.AddMealsActivity;
import com.example.dhaivat.foodlogger.R;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;
import java.util.Locale;


public class SearchResultAdapter extends RecyclerView
        .Adapter<SearchResultAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Meals> searchResponseArrayList;

    private Context context;
    private static MyClickListener myClickListener;
    private String filter;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        private TextView tvName;

        public DataObjectHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);

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

    public SearchResultAdapter(Context context,
                               ArrayList<Meals> searchResponseArrayList,
                               String filter) {
        this.context = context;
        this.searchResponseArrayList = searchResponseArrayList;
        this.filter = filter;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_product, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {

        String itemValue = searchResponseArrayList.get(position).getMealName();

        int startPos = itemValue.toLowerCase(Locale.US).indexOf(filter.toLowerCase(Locale.US));
        int endPos = startPos + filter.length();

        if (startPos != -1) // This should always be true, just a sanity check
        {
            Spannable spannable = new SpannableString(itemValue);
            ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[]
                    { Color.parseColor("#000000") });
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

            spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.tvName.setText(spannable);
        }
        else
            holder.tvName.setText(itemValue);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(context, AddMealsActivity.class);
                it.putExtra("name",searchResponseArrayList.get(position).getMealName());
                it.putExtra("calory",searchResponseArrayList.get(position).getCalories());
                it.putExtra("protien",searchResponseArrayList.get(position).getProtien());
                it.putExtra("fat",searchResponseArrayList.get(position).getFat());
                it.putExtra("carbo",searchResponseArrayList.get(position).getCarbohydrate());
                context.startActivity(it);

            }
        });

     //   holder.addCart.setTypeface(custom_medium);
    }

    public void deleteItem(int index) {
        searchResponseArrayList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return searchResponseArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}