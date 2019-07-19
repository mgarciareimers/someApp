package com.mgarciareimers.someApp.model;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return this.catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return this.bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public boolean containsFilter(String filter) {
        return this.name.contains(filter) || this.catchPhrase.contains(filter) || this.bs.contains(filter);
    }
}
