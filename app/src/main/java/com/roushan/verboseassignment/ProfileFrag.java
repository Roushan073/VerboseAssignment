package com.roushan.verboseassignment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


public class ProfileFrag extends Fragment {
    private TextView prof_title, name, phone, email;
    Session session;

    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        session = new Session(getActivity().getApplicationContext());

        prof_title = (TextView) v.findViewById(R.id.prof_title);
        name = (TextView) v.findViewById(R.id.prof_name);
        phone = (TextView) v.findViewById(R.id.prof_phone);
        email = (TextView) v.findViewById(R.id.prof_email);

        HashMap<String, String> user = session.getUserDetails();
        String user_name = user.get(Session.KEY_NAME);
        String user_phone = user.get(Session.KEY_PHONE);
        String user_email = user.get(Session.KEY_ADDRESS);

        name.setText("Name  :  " + user_name);
        phone.setText("Phone :  " + user_phone);
        email.setText("Email :  " + user_email);

        if(BuyerRegisterActivity.buyer_register_val == 1) {
            prof_title.setText("Buyer Info");
        }

        if(SellerRegisterActivity.seller_register_val == 1) {
            prof_title.setText("Seller Info");
        }

        return v;
    }

}
