package com.example.listviewpersonalisado;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private RadioButton selectedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listView);
        infoTextView = findViewById(R.id.textViewInfo);

        List<ItemModel> itemList = getItemData();
        listView.setAdapter(new GenericAdapter<ItemModel>(this, R.layout.list_item, itemList) {
            @SuppressLint("SetTextI18n")
            @Override
            public void bindView(ItemModel item, View view) {
                TextView titleTextView = view.findViewById(R.id.textTitle);
                TextView descriptionTextView = view.findViewById(R.id.textDescription);
                ImageView itemImageView = view.findViewById(R.id.itemImage);
                RadioButton itemRadioButton = view.findViewById(R.id.radioButton);

                titleTextView.setText(item.getTitle());
                descriptionTextView.setText(item.getDescription());
                itemImageView.setImageResource(item.getImageResId());

                itemRadioButton.setOnClickListener(v -> {
                    if (selectedRadioButton != null) {
                        selectedRadioButton.setChecked(false);
                        infoTextView.setText("No option selected");
                    }
                    selectedRadioButton = itemRadioButton;
                    selectedRadioButton.setChecked(true);
                    String selectFood = "Selected food: " + item.getTitle();
                    infoTextView.setText(selectFood);
                });
            }
        });
    }

    private List<ItemModel> getItemData() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.pizza, "PIZZA", "Cheesy, savory, customizable delight."));
        items.add(new ItemModel(R.drawable.hamburgue, "HAMBURGER", "Juicy beef, fresh toppings classic."));
        items.add(new ItemModel(R.drawable.hot_dog, "HOT DOG", "Grilled sausage, bun, toppings."));
        items.add(new ItemModel(R.drawable.tacos, "TACOS", "Spicy, stuffed, Mexican favorite."));
        items.add(new ItemModel(R.drawable.french_fries, "FRENCH FRIES", "Crispy, golden, salty perfection."));
        items.add(new ItemModel(R.drawable.sandwich, "SANDWICH", "Bread, fillings, portable meal."));
        items.add(new ItemModel(R.drawable.chicken_nuggets, "CHICKEN NUGGETS", "Crispy, bite-sized chicken pieces."));
        items.add(new ItemModel(R.drawable.onion_rings, "ONION RINGS", "Golden, crunchy, fried onion."));
        items.add(new ItemModel(R.drawable.mozzarella_stick, " MOZZARELLA STICKS", "Fried cheese, gooey inside."));
        items.add(new ItemModel(R.drawable.shawarma, "SHAWARMA", "Spiced meat, wrapped goodness."));
        return items;
    }
}


