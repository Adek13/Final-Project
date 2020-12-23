package com.mBank.RestAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RequestMutasi {

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "UTC")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Etc/GMT+10")
    private Date endDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
