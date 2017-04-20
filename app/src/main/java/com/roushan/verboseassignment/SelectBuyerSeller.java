package com.roushan.verboseassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBuyerSeller extends AppCompatActivity implements View.OnClickListener {

    private Button buyer, seller;
    public static String btn_select_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_buyer_seller);

        buyer = (Button) findViewById(R.id.btn_select_buyer);
        seller = (Button) findViewById(R.id.btn_select_seller);

        buyer.setOnClickListener(this);
        seller.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_select_buyer) {
            btn_select_str = "buyer";
            Intent i = new Intent(SelectBuyerSeller.this, BuyerRegisterActivity.class);
            startActivity(i);
            finish();
        } else {
            btn_select_str = "seller";
            Intent i = new Intent(SelectBuyerSeller.this, SellerRegisterActivity.class);
            startActivity(i);
            finish();
        }

    }
}
