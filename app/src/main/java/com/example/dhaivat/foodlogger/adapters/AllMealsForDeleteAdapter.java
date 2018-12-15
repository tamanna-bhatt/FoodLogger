package com.example.dhaivat.foodlogger.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.R;
import com.example.dhaivat.foodlogger.UpdateMealsActivity;
import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;

public class AllMealsForDeleteAdapter extends RecyclerView
        .Adapter<AllMealsForDeleteAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Meals> mealsArrayList;


    private Context context;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvMenuName,tvProtien,tvCarbohydrate,tvFat,tvCalories,tvDateTime,type;
        private LinearLayout llDelete;
        private LinearLayout llDateAndtype;


        public DataObjectHolder(View itemView) {
            super(itemView);
            tvMenuName = (TextView) itemView.findViewById(R.id.tvMenuName);
            tvProtien = (TextView) itemView.findViewById(R.id.tvProtien);
            tvCarbohydrate = (TextView) itemView.findViewById(R.id.tvCarbohydrate);
            tvFat = (TextView) itemView.findViewById(R.id.tvFat);
            tvCalories = (TextView) itemView.findViewById(R.id.tvCalories);
            llDelete= (LinearLayout) itemView.findViewById(R.id.llDelete);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            llDateAndtype= (LinearLayout) itemView.findViewById(R.id.llDateAndtype);
            type = (TextView) itemView.findViewById(R.id.type);
            llDelete.setVisibility(View.VISIBLE);

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

    public AllMealsForDeleteAdapter(Context context, ArrayList<Meals> mealsArrayList) {
        this.context = context;
        this.mealsArrayList = mealsArrayList;
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
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
//        holder.quote.setText(recommendedDishesArrayList.get(position).getQuote());
//
//
//
       holder.tvCarbohydrate.setText("CARBOHYDRATE : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvProtien.setText("PROTIEN : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvFat.setText("FAT : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvCalories.setText("CALORIES : "+mealsArrayList.get(position).getCalories());
        holder.tvMenuName.setText(mealsArrayList.get(position).getMealName());
        holder.tvDateTime.setText(mealsArrayList.get(position).getDate1() + " " + mealsArrayList.get(position).getTime1());


        if (mealsArrayList.get(position).isShowDate() == true){
            holder.llDateAndtype.setVisibility(View.VISIBLE);

        }else {
            holder.llDateAndtype.setVisibility(View.GONE);
        }
        holder.tvDateTime.setText(mealsArrayList.get(position).getDate1());

        holder.type.setText(" - "+mealsArrayList.get(position).getMealCategory());


//        holder.totalWeight.setText(airGroundServiceArrayList.get(position).get());

        holder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete " + mealsArrayList.get(position).getMealName() + " ?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        MyDatabase myDatabase = new MyDatabase(context);
                                        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();

                                        myDatabase.deleteMeals(mealsArrayList.get(position));
                                        mealsArrayList.remove(position);

                                        notifyDataSetChanged();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                            // Create the AlertDialog object and return it



            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(context, UpdateMealsActivity.class);
                it.putExtra("id", mealsArrayList.get(position).getId());
                context.startActivity(it);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mealsArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}