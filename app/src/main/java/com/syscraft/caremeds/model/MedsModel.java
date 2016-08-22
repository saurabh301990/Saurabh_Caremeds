package com.syscraft.caremeds.model;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class MedsModel {
    String name;
    String id;
    String tabletName;
    String forward;
    String checkin;
    String waste;
    String admin;
    String stock;

    public String getName() {
        return name;
    }

    public String getTabletName() {
        return tabletName;
    }

    public void setTabletName(String tabletName) {
        this.tabletName = tabletName;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
