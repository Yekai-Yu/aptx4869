package com.aptx.demo.riata.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "preference")
    private List<String> preference;

    @JsonProperty(value = "rating")
    private float rating;

    @JsonProperty(value = "ratingCount")
    private int ratingCount;

    @JsonProperty(value = "postCount")
    private int postCount;

    public UserDTO() {
    }

    public UserDTO(String id, String name, List<String> preference, float rating, int ratingCount, int postCount) {
        this.id = id;
        this.name = name;
        this.preference = preference;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.postCount = postCount;
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.preference = user.getPreference();
        this.rating = user.getRating();
        this.ratingCount = user.getRatingCount();
        this.postCount = user.getPostCount();
    }

    public UserDTO(UserDO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.preference = user.getPreference();
        this.rating = user.getRating();
        this.ratingCount = user.getRatingCount();
        this.postCount = user.getPostCount();
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
