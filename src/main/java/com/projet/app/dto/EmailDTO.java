package com.projet.app.dto;

public class EmailDTO {

    private String to;
    private String cc;
    private String subject;
    private String body;

    // Getter for to
    public String getTo() {
        return to;
    }

    // Setter for to
    public void setTo(String to) {
        this.to = to;
    }

    
    public String getCc() {
        return cc;
    }

    
    public void setCc(String cc) {
        this.cc = cc;
    }

    // Getter for subject
    public String getSubject() {
        return subject;
    }

    // Setter for subject
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Getter for body
    public String getBody() {
        return body;
    }

    // Setter for body
    public void setBody(String body) {
        this.body = body;
    }}

