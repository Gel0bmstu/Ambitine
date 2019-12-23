package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.conroller.NewPromiseController;
import com.example.networking.model.network.Retrofit.AutocompleteService;
import com.example.networking.model.network.Retrofit.Interceptors.AddCookiesInterceptor;
import com.example.networking.model.network.Retrofit.Interceptors.ReceivedCookiesInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PromiiseCreaterFragment extends Fragment {
    View rootView;
    // ToDo: Tmp solution for test, self implemented adapter for users ( with image ) coming soon, i PROMISE
    private List<String> usersAutocomplete = new ArrayList<>();
    private NewPromiseController newPromiseController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.promise_creater, container, false);

        if (newPromiseController == null) {
            newPromiseController = new NewPromiseController(this);
        }

        getUsersAutocompleteData();

        Button loginButton = rootView.findViewById(R.id.new_promise_send);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPromiseController.onNewPromiseButtonClick();
                ((HomeActivity)getActivity()).clickFeedButton();
                clearAfterCreateNewPromise();
            }
        });

//        EditText depositValue = rootView.findViewById(R.id.deposit_new_promise);
//        depositValue


        return rootView;
//        return super.onCreateView(inflater, containeer, savedInstanceState);
    }

    Gson gson = new GsonBuilder().create();
    OkHttpClient client = new OkHttpClient.Builder().
            addInterceptor(new AddCookiesInterceptor()).
            addInterceptor(new ReceivedCookiesInterceptor()).
            build();

    // use retrofit to create an instance of BookService
    AutocompleteService service = new Retrofit.Builder()
//            .baseUrl("http://www.mocky.io/")
            .baseUrl("http://35.228.98.103:9090/")
//            .baseUrl("http://35.228.98.103:9090/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(AutocompleteService.class);

    public void getUsersAutocompleteData() {
        Call<List<String>> call = service.getUsersAutocomplete();
        System.out.println("WE INB");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<String> usersForAutocompleteFromServer = response.body();
                    String[] usersArray = usersForAutocompleteFromServer.toArray(new String[0]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_dropdown_item_1line, usersArray);
                    AutoCompleteTextView textView = rootView.findViewById(R.id.users_autocomplete);
                    textView.setAdapter(adapter);
                } else {
                    System.out.println("Another handle way");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
            }
        });
    }

    public String getRecieverUsername() {
        AutoCompleteTextView recieverUsername = rootView.findViewById(R.id.users_autocomplete);
        String recieverUsernameString = recieverUsername.getText().toString();
        System.out.println("Reciever username from autocomplete");
        System.out.println(recieverUsernameString);
        return recieverUsernameString;
    }

    public Integer getDeposit() {
        EditText depositValue = rootView.findViewById(R.id.deposit_new_promise);
        Integer depositValueForNewPromise = Integer.valueOf(depositValue.getText().toString());
        System.out.println("Deposit value for new promise");
        System.out.println(depositValueForNewPromise);
        return  depositValueForNewPromise;
    }

    public String getPromiseDescription() {
        EditText newPromiseDescription = rootView.findViewById(R.id.new_promise_description);
        String promiseDescription = newPromiseDescription.getText().toString();
        System.out.println("Promise description");
        System.out.println(promiseDescription);
        return promiseDescription;
    }

    public Long getPastDue() {
        DatePicker datePicker = rootView.findViewById(R.id.newp_date_picker);
        TimePicker timePicker = rootView.findViewById(R.id.newp_time_picker);

        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        System.out.println("Calendar time");
        System.out.println(calendar.getTime());
        System.out.println("Calendar time im milis");
        System.out.println(calendar.getTimeInMillis());
        return calendar.getTimeInMillis();
    }

    public void clearAfterCreateNewPromise() {
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
