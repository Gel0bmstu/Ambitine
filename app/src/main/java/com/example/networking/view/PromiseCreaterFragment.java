package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.controller.NewPromiseController;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class PromiseCreaterFragment extends Fragment {
    private View rootView;
    // ToDo: Tmp solution for test, self implemented adapter for users ( with image ) coming soon, i PROMISE
    private NewPromiseController newPromiseController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.promise_creater, container, false);

        if (newPromiseController == null) {
            newPromiseController = new NewPromiseController(this);
        }

        newPromiseController.getUsersAutocompleteData();

        Button loginButton = rootView.findViewById(R.id.new_promise_send);
        loginButton.setOnClickListener(new View.OnClickListener() {

            // ToDo: Check good enough place to date/time checket
            @Override
            public void onClick(View v) {
                newPromiseController.onNewPromiseButtonClick();
                ((HomeActivity) Objects.requireNonNull(getActivity())).switchToFeed();
                clearAfterCreateNewPromise();
            }

        });

        return rootView;
    }

    public String getRecieverUsername() {
        AutoCompleteTextView recieverUsername = rootView.findViewById(R.id.users_autocomplete);
        return recieverUsername.getText().toString();
    }

    public Integer getDeposit() {
        EditText depositValue = rootView.findViewById(R.id.deposit_new_promise);
        return Integer.valueOf(depositValue.getText().toString());
    }

    public String getPromiseDescription() {
        EditText newPromiseDescription = rootView.findViewById(R.id.new_promise_description);
        return newPromiseDescription.getText().toString();
    }

    public Long getPastDue() {
        DatePicker datePicker = rootView.findViewById(R.id.newp_date_picker);
        TimePicker timePicker = rootView.findViewById(R.id.newp_time_picker);

        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        return calendar.getTimeInMillis();
    }

    private void clearAfterCreateNewPromise() {
        ((AutoCompleteTextView)rootView.findViewById(R.id.users_autocomplete)).setText("");
        ((EditText)rootView.findViewById(R.id.deposit_new_promise)).setText("");
        ((EditText)rootView.findViewById(R.id.new_promise_description)).setText("");
        // TODO: set default time and date
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
