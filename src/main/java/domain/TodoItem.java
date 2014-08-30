package domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TodoItem {
    private String title;
    private Boolean completed;
    private String url;
    private Integer order;

    public TodoItem(String title, Boolean completed, String url, Integer order) {
        this.title = title;
        this.completed = completed;
        this.url = url;
        this.order = order;
    }

    public TodoItem() {
    }

    public TodoItem(DBObject dbObject) {
        title = (String) dbObject.get("title");
        completed = (Boolean) dbObject.get("completed");
        url = (String) dbObject.get("url");
        order = (Integer) dbObject.get("order");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public BasicDBObject dbObject() {
        return new BasicDBObject("title", title)
                .append("completed", completed)
                .append("url", url)
                .append("order", order);
    }
}
