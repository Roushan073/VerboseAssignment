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

public class BuyerRegisterActivity extends AppCompatActivity {

    private EditText fname, lname, add, mob;
    private Button register;
    private TextView go_back;
    String f, l;
    Session session;

    public static String buyer_name, buyer_address, buyer_phone;
    public static int buyer_register_val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        session = new Session(this);

        fname = (EditText) findViewById(R.id.et_buyer_first_name);
        lname = (EditText) findViewById(R.id.et_buyer_last_name);
        add = (EditText) findViewById(R.id.et_buyer_add);
        mob = (EditText) findViewById(R.id.et_buyer_mob);

        go_back = (TextView) findViewById(R.id.txt_go_back_b);
        go_back.setPaintFlags(go_back.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SelectBuyerSeller.class));
                finish();
            }
        });

        register = (Button) findViewById(R.id.btn_reg_buyer);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = fname.getText().toString();
                l = lname.getText().toString();
                buyer_name = f + " " + l;
                buyer_address = add.getText().toString();
                buyer_phone = mob.getText().toString();

                if(!f.equals("") && !l.equals("") && !buyer_address.equals("") && !buyer_phone.equals("")) {
                    buyer_register_val = 1;

                    session.setLoggedIn(true);
                    session.setUserDetails(buyer_name, buyer_phone, buyer_address);

                    Intent i = new Intent(BuyerRegisterActivity.this, BuyerSellerNavActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(BuyerRegisterActivity.this, "Enter empty field first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
