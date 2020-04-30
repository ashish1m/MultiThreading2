package model;

import java.util.Date;

public class Like implements Activity {

    private Date createdAt;

    public Like(Date createdAt){
        this.createdAt = createdAt;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Like{" +
                "createdAt=" + createdAt +
                '}';
    }
}
