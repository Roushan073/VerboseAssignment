package com.roushan.verboseassignment;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SellerRegisterActivity extends AppCompatActivity {

    private EditText fname, lname, add, mob;
    private Button register;
    String f, l;
    Session session;
    private TextView go_back;

    public static String seller_name, seller_address, seller_phone;
    public static int seller_register_val = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        session = new Session(this);

        fname = (EditText) findViewById(R.id.et_seller_first_name);
        lname = (EditText) findViewById(R.id.et_seller_last_name);
        add = (EditText) findViewById(R.id.et_seller_add);
        mob = (EditText) findViewById(R.id.et_seller_mob);

        register = (Button) findViewById(R.id.btn_reg_seller);

        go_back = (TextView) findViewById(R.id.txt_go_back_s);
        go_back.setPaintFlags(go_back.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SelectBuyerSeller.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = fname.getText().toString();
                l = lname.getText().toString();
                seller_name = f + " " + l;
                seller_address = add.getText().toString();
                seller_phone = mob.getText().toString();

                if(!seller_name.equals("") && !seller_address.equals("") && !seller_phone.equals("")) {
                    seller_register_val = 1;

                    session.setLoggedIn(true);
                    session.setUserDetails(seller_name, seller_phone, seller_address);

                    Intent i = new Intent(SellerRegisterActivity.this, BuyerSellerNavActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(SellerRegisterActivity.this, "Enter empty field first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
