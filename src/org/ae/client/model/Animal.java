/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.client.model;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModelTag;

public class Animal implements BeanModelTag, Serializable {
  private static final long serialVersionUID = 1L;
  private Long key;
  private String name;
  private String path;
  private String desc;
  private Date dateLastChanged;
  private Date dateCreated;
  private String imageFileName;
  private boolean deleted;
  private String lastUpdatedByIP;
  private String lastUpdatedByEmailAddress;
  private String rescueURL;

  public Animal() {
    setDeleted(false);
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public Long getKey() {
    return key;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Name(" + getName() + ") Description(" + getDesc() + ") Image Path("
        + getPath() + ") KEY(" + getKey() + ")";
  }

  public void setDateLastChanged(Date dateLastChanged) {
    this.dateLastChanged = dateLastChanged;
  }

  public Date getDateLastChanged() {
    return dateLastChanged;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setLastUpdatedByIP(String lastUpdatedByIP) {
    this.lastUpdatedByIP = lastUpdatedByIP;
  }

  public String getLastUpdatedByIP() {
    return lastUpdatedByIP;
  }

  public void setImageFileName(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  public String getImageFileName() {
    return imageFileName;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setLastUpdatedByEmailAddress(String lastUpdatedByEmailAddress) {
    this.lastUpdatedByEmailAddress = lastUpdatedByEmailAddress;
  }

  public String getLastUpdatedByEmailAddress() {
    return lastUpdatedByEmailAddress;
  }

  public void setRescueURL(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public String getRescueURL() {
    return rescueURL;
  }

}
