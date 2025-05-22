package com.example.siv1;

import android.content.Intent;
import  android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siv1.Adaptor.CategoryAdaptor;
import com.example.siv1.Adaptor.PopularAdaptor;
import com.example.siv1.Domain.CategoryDomain;
import com.example.siv1.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this, MainActivityCart7.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this, MainActivity5.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Chino", "cat_1"));
        category.add(new CategoryDomain("Paisa", "cat_2"));
        category.add(new CategoryDomain("TodoCarnes", "cat_3"));
        category.add(new CategoryDomain("Ranchero", "cat_4"));
        category.add(new CategoryDomain("PolloCamarón", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Arroz Chino", "pop_4", "Jamón, Camarón, Pollo Picado, Raiz China y 1/4 Pollo Frito", 22000));
        foodList.add(new FoodDomain("Arroz Paisa", "pop_5", "Chorizo, Salami, Chicharrón, Maduro, Maiz, Raiz China y 2 Costillas", 24000));
        foodList.add(new FoodDomain("Arroz Todo Carnes", "pop_5", "Jamón, Camarón Pollo Picado, Chorizo, Salami, Chicharrón, Maduro, Maiz, Raiz China y 2 Costillas", 26000));

        adapter2 = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}