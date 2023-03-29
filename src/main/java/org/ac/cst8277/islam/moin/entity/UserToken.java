package org.ac.cst8277.islam.moin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("user-token")
public class UserToken {
    @Id
    String userId;
    String token;
   LocalDateTime lastAddedDate;

    public UserToken(String userId, String token,LocalDateTime lastAddedDate) {
        this.userId = userId;
        this.token = token;
        this.lastAddedDate=lastAddedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getlastAddedDate(){return lastAddedDate;}
    public void setlastAddeddate(LocalDateTime time) {this.lastAddedDate=time;}

}
