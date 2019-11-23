package com.example.networking.model.database;

import com.example.networking.model.models.UserToken;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserDB {
    public static final String TOKEN_NOT_FOUND = "token not found";

    public static void setToken(String token) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<UserToken> realmResults = realm.where(UserToken.class).findAll();
        if (realmResults.size() == 0) {
            UserToken userToken = realm.createObject(UserToken.class);
            userToken.setToken(token);
        } else {
            UserToken userToken = realmResults.first();
            userToken.setToken(token);
        }

        realm.commitTransaction();
    }

    public static String getToken() {
        String token;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<UserToken> realmResults = realm.where(UserToken.class).findAll();
        if (realmResults.size() == 0) {
            token = TOKEN_NOT_FOUND;
        } else {
            token = realmResults.first().getToken();
        }
        realm.commitTransaction();

        return token;
    }

    public static void deleteToken() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.where(UserToken.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
}
