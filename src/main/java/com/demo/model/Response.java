package com.demo.model;


public class Response {
    private String dbPost;
    private String httpOutbound;

    public Response(String dbPost, String httpOutbound) {
        this.dbPost = dbPost;
        this.httpOutbound = httpOutbound;
    }

    public String getDbPost() {
        return dbPost;
    }

    public void setDbPost(String dbPost) {
        this.dbPost = dbPost;
    }

    public String getHttpOutbound() {
        return httpOutbound;
    }

    public void setHttpOutbound(String httpOutbound) {
        this.httpOutbound = httpOutbound;
    }
}
