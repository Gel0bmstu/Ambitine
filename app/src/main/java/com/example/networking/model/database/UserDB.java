package com.example.networking.model.database;

import com.example.networking.model.models.UserToken;

import org.jetbrains.annotations.NotNull;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserDB {
    public static final String TOKEN_NOT_FOUND = "token not found";

    public static void setToken(final String token) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NotNull Realm bgRealm) {
                RealmResults<UserToken> realmResults = bgRealm.where(UserToken.class).findAll();

                if (realmResults.size() == 0) {
                    UserToken userToken =   bgRealm.createObject(UserToken.class);
                    userToken.setToken(token);
                } else {
                    UserToken userToken = realmResults.first();
                    userToken.setToken(token);
                }
            }
        });
    }

    public static String getToken() {
        String token;
        Realm realm = Realm.getDefaultInstance();

        RealmResults<UserToken> realmResults = realm.where(UserToken.class).findAllAsync();

        if (realmResults.size() == 0) {
            token = TOKEN_NOT_FOUND;
        } else {
            token = realmResults.first().getToken();
        }
        return token;
    }

    public static void deleteToken() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.where(UserToken.class).findAll().deleteFirstFromRealm();
        realm.commitTransaction();
    }

    public static boolean isAuthorized() {
        return !getToken().equals(TOKEN_NOT_FOUND);
    }
}
