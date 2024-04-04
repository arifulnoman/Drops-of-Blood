package com.example.drops_of_blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CustomDonorAdapter extends ArrayAdapter<Donor> {

    private final Context context;
    private final ArrayList<Donor> donors;
    public CustomDonorAdapter(@NonNull Context context, @NonNull ArrayList<Donor> donors) {
        super(context, -1,donors);
        this.context = context;
        this.donors = donors;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.donor_row, parent, false);
        TextView donorName = rowView.findViewById(R.id.tvDonorName);
        TextView donorBloodGroup = rowView.findViewById(R.id.tvDonorGroup);
        TextView donorPhone = rowView.findViewById(R.id.tvDonorPhone);

        Donor donor = donors.get(position);

        donorName.setText(donor.name);
        donorBloodGroup.setText(donor.bloodGroup);
        donorPhone.setText(donor.phoneNo);
        return rowView;
    }
}
