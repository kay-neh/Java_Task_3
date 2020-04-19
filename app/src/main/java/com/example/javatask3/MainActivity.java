package com.example.javatask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextInputEditText groceryItem,groceryPrice;
    MaterialButton materialButton;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<GroceryItem> details = new ArrayList<>();

        groceryItem = findViewById(R.id.grocery_item_input);
        groceryPrice = findViewById(R.id.grocery_price_input);

        ListView listView = findViewById(R.id.grocery_list_view);
        final GroceryAdapter detailsAdapter = new GroceryAdapter(this,details);
        listView.setAdapter(detailsAdapter);

        fab = findViewById(R.id.fab);

        materialButton = findViewById(R.id.textButton);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(groceryItem.getText().toString())&&!TextUtils.isEmpty(groceryPrice.getText().toString())){
                    final String item = groceryItem.getText().toString();
                    final int price = Integer.parseInt((groceryPrice.getText()).toString());
                    details.add(new GroceryItem(item, price));
                    detailsAdapter.notifyDataSetChanged();
                    fab.setVisibility(View.VISIBLE);
                    groceryItem.requestFocus();
                    groceryItem.setText("");
                    groceryPrice.setText("");
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putParcelableArrayListExtra("list",details);
                startActivity(intent);
            }
        });
    }
}
