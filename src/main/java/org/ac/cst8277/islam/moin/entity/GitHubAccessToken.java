package org.ac.cst8277.islam.moin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "github_access_tokens")
public class GitHubAccessToken {

    @Id
    private String id;

    private String accessToken;

    public GitHubAccessToken() {}

    public GitHubAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
