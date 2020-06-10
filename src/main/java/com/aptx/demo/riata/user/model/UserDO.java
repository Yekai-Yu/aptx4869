package com.aptx.demo.riata.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
public class UserDO {

    @Id
    private String id;

    private String name;

    private List<String> preference;

    private float rating;

    private int ratingCount;

    private int postCount;

    public UserDO() {
    }

    public UserDO(String id, String name, List<String> preference, float rating, int ratingCount, int postCount) {
        this.id = id;
        this.name = name;
        this.preference = preference;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.postCount = postCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPreference() {
        return preference;
    }

    public void setPreference(List<String> preference) {
        this.preference = preference;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
