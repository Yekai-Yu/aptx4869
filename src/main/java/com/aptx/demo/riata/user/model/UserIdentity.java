package com.aptx.demo.riata.user.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user-identity")
public class UserIdentity {

    @Id
    private ObjectId uId;

    private String uName;

    private String uEmail;

    private String uPassword;

    // OAuth "type" or "REGULAR"
    private String type = UserType.REGULAR.getValue();

    private String uSignature;

    public UserIdentity() {
    }

    public UserIdentity(ObjectId uId, String uName, String uEmail, String uPassword) {
        this.uId = uId;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPassword = uPassword;
        this.uSignature = uId.toString();
    }

    public ObjectId getUId() {
        return uId;
    }

    public void setUId(ObjectId uId) {
        this.uId = uId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUEmail() {
        return uEmail;
    }

    public void setUEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUSignature() {
        return uSignature;
    }

    public void setUSignature(String uSignature) {
        this.uSignature = uSignature;
    }
}
