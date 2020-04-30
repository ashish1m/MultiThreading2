package model;

import java.util.Date;

public class Comment implements Activity {
    private Date createdAt;

    public Comment(Date createdAt){
        this.createdAt = createdAt;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "createdAt=" + createdAt +
                '}';
    }
}
