package se.miun.toya1800.dt031g.bathingsites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddBathingSiteFragment extends Fragment {
    private EditText name_input;
    private EditText description_input;
    private EditText address_input;
    private EditText latitude_input;
    private EditText longitude_input;
    private RatingBar ratingBar;
    private EditText waterTemp_input;
    private EditText date_input;


    public AddBathingSiteFragment() {
        // Required empty public constructor
        super(R.layout.fragment_add_bathing_site);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_bathing_site, container, false);

        // Variables
        name_input = v.findViewById(R.id.name_input);
        description_input = v.findViewById(R.id.description_input);
        address_input = v.findViewById(R.id.address_input);
        latitude_input = v.findViewById(R.id.latitude_input);
        longitude_input = v.findViewById(R.id.longitude_input);
        ratingBar = v.findViewById(R.id.ratingBar);
        waterTemp_input = v.findViewById(R.id.waterTemp_input);
        date_input = v.findViewById(R.id.date_input);

        // Prefill today's date when the fragment is used
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date_input.setText(dateFormat.format(new Date()));

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                save_inputs();
                break;
            case R.id.clear_button:
                clear_inputs();
                break;
            case R.id.settings_button:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void save_inputs() {
        boolean valid = true;
        if(TextUtils.isEmpty(name_input.getText().toString())) {
            name_input.setError("Name is required");
            valid = false;
        }

        // If both address and latitude + longitude are empty
        if(TextUtils.isEmpty(address_input.getText().toString())
                & (TextUtils.isEmpty(latitude_input.getText().toString()) &
                TextUtils.isEmpty(longitude_input.getText().toString()))) {
            address_input.setError("Address is required.");
            latitude_input.setError("Latitude is required");
            longitude_input.setError("Longitude is required");
            valid = false;
        }


        /*
        /  If no address is written and
        /  only one of the long or latitudes are written,
        /   it is not valid
         */
        if(TextUtils.isEmpty(address_input.getText().toString())){
            if((!TextUtils.isEmpty(latitude_input.getText().toString()) &
                    TextUtils.isEmpty(longitude_input.getText().toString())) ||
                    (TextUtils.isEmpty(latitude_input.getText().toString()) &
                            !TextUtils.isEmpty(longitude_input.getText().toString()))) {
                if(TextUtils.isEmpty(latitude_input.getText().toString())) {
                    latitude_input.setError("Latitude is required");
                } else if(TextUtils.isEmpty(longitude_input.getText().toString())) {
                    longitude_input.setError("Longitude is required");
                }
                valid = false;
            }
        }

        /*
        /  If it's all valid we will show a snackBar
        /  with all input from the user
        */
        if(valid) {
            // Create the string with all the info about a bath site
            String bath_site_data = "Name: " + name_input.getText().toString() + "\n";

            if(!TextUtils.isEmpty(description_input.getText().toString())) {
                bath_site_data += "Description: " + description_input.getText().toString() + "\n";
            }

            if(!TextUtils.isEmpty(address_input.getText().toString())) {
                bath_site_data += "Address: " + address_input.getText().toString() + "\n";
            }

            if(!TextUtils.isEmpty(latitude_input.getText().toString())) {
                bath_site_data += "Latitude: " + latitude_input.getText().toString() + "\n";
            }

            if(!TextUtils.isEmpty(longitude_input.getText().toString())) {
                bath_site_data += "Longitude: " + longitude_input.getText().toString() + "\n";
            }

            bath_site_data += "Rating:" + ratingBar.getRating() + "\n";

            if(!TextUtils.isEmpty(waterTemp_input.getText().toString())) {
                bath_site_data += "Water temp: " + waterTemp_input.getText().toString() + "\n";
            }

            if(!TextUtils.isEmpty(date_input.getText().toString())) {
                bath_site_data += "Date for temp: " + date_input.getText().toString() + "\n";
            }

            // Show the info to the user
            Toast toast = Toast.makeText(getContext(), bath_site_data, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void clear_inputs() {
        name_input.setText(null);
        description_input.setText(null);
        address_input.setText(null);
        latitude_input.setText(null);
        longitude_input.setText(null);
        ratingBar.setRating(0);
        waterTemp_input.setText(null);
        date_input.setText(null);
    }
}