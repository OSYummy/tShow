package com.wisedu.wechat.examples;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ShowUser {
    public static void main(String[] args) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.showUser("twitter4j");
            if (user.getStatus() != null) {
                System.out.println("@" + user.getScreenName() + " - " + user.getStatus().getText());
            } else {
                System.out.println("@" + user.getScreenName());
            }
            System.exit(0);
        } catch (TwitterException te) {
            System.out.println("Failed to show user: " + te.getMessage());
            System.exit(-1);
        }
    }
}