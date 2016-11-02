package com.r3sist3nt.GoRust.database;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Julian on 02.11.2016.
 */
@Entity
@Table(name = "server_event")
public class ServerEventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serverid", nullable = false)
    private Long serverid;

    /**
     * booleans to show if this attribute has changed
     */
    @Column(name = "name", nullable = false)
    private boolean name;

    @Column(name = "seed", nullable = false)
    private boolean seed;

    @Column(name = "mapsize", nullable = false)
    private boolean mapsize;

    @Column(name = "slots", nullable = false)
    private boolean slots;

    @Column(name = "build", nullable = false)
    private boolean build;

    @Column(name = "nameto", nullable = false)
    private String nameto;

    @Column(name = "buildto", nullable = false)
    private String buildto;

    @Column(name = "seedto", nullable = false)
    private int seedto;

    @Column(name = "mapsizeto", nullable = false)
    private int mapsizeto;

    @Column(name = "slotsto", nullable = false)
    private int slotsto;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerid() {
        return serverid;
    }

    public void setServerid(Long serverid) {
        this.serverid = serverid;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isSeed() {
        return seed;
    }

    public void setSeed(boolean seed) {
        this.seed = seed;
    }

    public boolean isMapsize() {
        return mapsize;
    }

    public void setMapsize(boolean mapsize) {
        this.mapsize = mapsize;
    }

    public boolean isSlots() {
        return slots;
    }

    public void setSlots(boolean slots) {
        this.slots = slots;
    }

    public String getNameto() {
        return nameto;
    }

    public void setNameto(String nameto) {
        this.nameto = nameto;
    }

    public int getSeedto() {
        return seedto;
    }

    public void setSeedto(int seedto) {
        this.seedto = seedto;
    }

    public int getMapsizeto() {
        return mapsizeto;
    }

    public void setMapsizeto(int mapsizeto) {
        this.mapsizeto = mapsizeto;
    }

    public int getSlotsto() {
        return slotsto;
    }

    public void setSlotsto(int slotsto) {
        this.slotsto = slotsto;
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public String getBuildto() {
        return buildto;
    }

    public void setBuildto(String buildto) {
        this.buildto = buildto;
    }
}
