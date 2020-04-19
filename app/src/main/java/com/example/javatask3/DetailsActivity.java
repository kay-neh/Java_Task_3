package com.example.javatask3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView total;
    FloatingActionButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final ArrayList<GroceryItem> details = intent.getParcelableArrayListExtra("list");

        share = findViewById(R.id.share);
        total = findViewById(R.id.total_price);

        total.setText(String.valueOf(computeTotal(details)));

        ListView listView = findViewById(R.id.grocery_details_list_view);
        GroceryAdapter detailsAdapter = new GroceryAdapter(this,details);
        listView.setAdapter(detailsAdapter);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] address = {"startNG@gmail.com"};
                String subject = "Grocery Shopping List";
                String body = displayList(details);
                composeEmail(address,subject,body);
            }
        });

    }
    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public String displayList(ArrayList<GroceryItem> details){
        String body = "";
        String message = "";
        for(int i = 0; i<= details.size()-1; i++){
            body += details.get(i).getItem() +" : " +"#" +details.get(i).getPrice() +"\n\n";
        }
        message = body + "Total : #" +computeTotal(details);
        return message;
    }
    public int computeTotal(ArrayList<GroceryItem> details){
        int[]prices = new int[details.size()];
        for(int i = 0; i<=details.size()-1; i++){
            prices[i] = details.get(i).getPrice();
        }

        int totalPrice = 0;
        for(int tp: prices){
            totalPrice += tp;
        }
        return totalPrice;
    }
}
