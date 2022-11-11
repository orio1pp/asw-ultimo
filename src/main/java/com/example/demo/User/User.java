package com.example.demo.User;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
public class User {
    @Id
    @Column
    private String username;
    @Column
    private Date created;
    @Column
    private int karma;
    @Column
    private String about;
    @Column
    private int maxvisit;
    @Column
    private int minaway;
    @Column
    private int delay;
    @Column
    private boolean showdead;
    @Column
    private boolean noprocrast;
    public User() {
    }

    public User(String username, Date created, int karma, String about, int maxvisit, int minaway, int delay, boolean showdead, boolean noprocrast) {
        this.username = username;
        this.created = created;
        this.karma = karma;
        this.about = about;
        this.maxvisit = maxvisit;
        this.minaway = minaway;
        this.delay = delay;
        this.showdead = showdead;
        this.noprocrast = noprocrast;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getMaxvisit() {
        return maxvisit;
    }

    public void setMaxvisit(int maxvisit) {
        this.maxvisit = maxvisit;
    }

    public int getMinaway() {
        return minaway;
    }

    public void setMinaway(int minaway) {
        this.minaway = minaway;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isShowdead() {
        return showdead;
    }

    public void setShowdead(boolean showdead) {
        this.showdead = showdead;
    }

    public boolean isNoprocrast() {
        return noprocrast;
    }

    public void setNoprocrast(boolean noprocrast) {
        this.noprocrast = noprocrast;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
