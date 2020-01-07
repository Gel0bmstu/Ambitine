package com.example.networking.controller;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.NewPromiseResponce;
import com.example.networking.view.PromiseCreaterFragment;

import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPromiseController {

    private PromiseCreaterFragment newPromiseFragment;

    public NewPromiseController(PromiseCreaterFragment promiseFragment) {
        this.newPromiseFragment = promiseFragment;
    }

    private Long getNowTimestamp() {
        Date date= new Date();
        return date.getTime();
    }

    public void getUsersAutocompleteData() {
        ApiService service = Api.getApiService();
        Call<List<String>> call = service.getUsersAutocomplete();
        System.out.println("WE INB");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NotNull Call<List<String>> call, @NotNull Response<List<String>> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<String> usersForAutocompleteFromServer = response.body();
                    String[] usersArray = usersForAutocompleteFromServer.toArray(new String[0]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            Objects.requireNonNull(newPromiseFragment.getView()).getContext(),
                            android.R.layout.simple_dropdown_item_1line, usersArray);

                    AutoCompleteTextView textView = Objects.requireNonNull(newPromiseFragment
                                                    .getView()).findViewById(R.id.users_autocomplete);
                    textView.setAdapter(adapter);
                } else {
                    System.out.println("Another handle way");
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<String>> call, @NotNull Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
            }
        });
    }

    public void onNewPromiseButtonClick() {
        String username = newPromiseFragment.getRecieverUsername();
        Integer deposit = newPromiseFragment.getDeposit();
        String description = newPromiseFragment.getPromiseDescription();
        Long pastdue = newPromiseFragment.getPastDue();

        int compared = pastdue.compareTo(getNowTimestamp());
        if (compared < 0  || compared == 0) {
            Toast.makeText(Objects.requireNonNull(newPromiseFragment.getActivity())
                    .getApplicationContext(),"Available only if you are timetraveller!",Toast.LENGTH_SHORT).show();
        } else {
            NewPromiseResponce newPromiseReponce = new NewPromiseResponce(username, description, pastdue, deposit);
            sendNewPromise(newPromiseReponce);
        }

    }

    private void sendNewPromise(final NewPromiseResponce newPromiseResponce) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.sendNewPromise(newPromiseResponce);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                int responceCode = response.code();
                if (responceCode== 201) {
                    System.out.println("New promise send");
                } else if (responceCode == 409){
                    String recieverTrouble = Objects.requireNonNull(newPromiseFragment.getActivity()).getResources().getString(R.string.wrong_reciever_newpromise);
                    AmbitinedToast.getInstance().debugAboveTheKeyboard(newPromiseFragment.getActivity(), recieverTrouble);
                } else if (responceCode == 401){
                    String balanceTrouble = Objects.requireNonNull(newPromiseFragment.getActivity()).getResources().getString(R.string.wrong_balance_newpromise);
                    AmbitinedToast.getInstance().debugAboveTheKeyboard(newPromiseFragment.getActivity(), balanceTrouble);
                } else {
                    String promiseTrouble = Objects.requireNonNull(newPromiseFragment.getActivity()).getResources().getString(R.string.wrond_new_promise_failed) + "(" + responceCode + ")";
                    AmbitinedToast.getInstance().debugAboveTheKeyboard(newPromiseFragment.getActivity(), promiseTrouble);
                }
            }
            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                String promiseTrouble = Objects.requireNonNull(newPromiseFragment.getActivity()).getResources().getString(R.string.wrond_new_promise_failed);
                AmbitinedToast.getInstance().debugAboveTheKeyboard(newPromiseFragment.getActivity(), promiseTrouble);
            }
        });
    }

}