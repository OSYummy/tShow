package com.wisedu.wechat.examples;

import facebook4j.*;
import facebook4j.auth.AccessToken;

public class SearchUsers {
    public static void main(String[] args){
        try {
            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthPermissions("email, publish_stream");
            facebook.setOAuthAccessToken(new AccessToken("diVuh0vWUwYH6GkYkjFwkv2lIvYFACFTBJ6tti3lfK2bjbikQ", 7200L));
            ResponseList<User> results = facebook.searchUsers("facebook4j");
            System.exit(0);
        } catch (FacebookException fce){
            System.out.println("Failed to search users: " + fce.getMessage());
            System.exit(-1);
        }
    }
}
