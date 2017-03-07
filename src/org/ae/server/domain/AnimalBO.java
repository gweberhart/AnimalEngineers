/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.server.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpServletRequest;

import org.ae.client.model.Animal;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Deprecated
// use ReqeustFactory.. See Campaign, CampaignProxy and CampaignRequest
@PersistenceCapable(table = "AnimalModel")
public class AnimalBO {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long key;
  @Persistent
  private String rescueURL;
  @Persistent
  private String name;
  @Persistent
  private String path;
  @Persistent
  private Text desc;
  @Persistent
  private Date dateLastChanged;
  @Persistent
  private Date dateCreated;
  @Persistent
  private String lastUpdatedByIP;
  @Persistent
  private User lastUpdatedByUser;
  @Persistent
  private String imageFileName;
  @Persistent
  private Boolean deleted;

  public static AnimalBO createModelFromAnimal(Animal animal) {
    AnimalBO model = new AnimalBO();
    model.setKey(animal.getKey());
    model.setDesc(animal.getDesc());
    model.setPath(animal.getPath());
    model.setName(animal.getName());
    model.setDateLastChanged(animal.getDateLastChanged());
    model.setImageFileName(animal.getImageFileName());
    model.setDeleted(animal.isDeleted());
    model.setLastUpdatedByIP(animal.getLastUpdatedByIP());
    model.setRescueURL(animal.getRescueURL());
    return model;
  }

  public void prePersistEvent(Animal animal, HttpServletRequest request) {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    setLastUpdatedByUser(user);
    animal.setLastUpdatedByEmailAddress(user.getEmail());
    Date today = new Date();
    animal.setDateLastChanged(today);
    setDateLastChanged(today);
    animal.setLastUpdatedByIP(request.getRemoteAddr());
    setLastUpdatedByIP(request.getRemoteAddr());
    if (getDesc().getValue() == null) {
      setDesc("");
    }
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setDesc(Text desc) {
    this.desc = desc;
  }

  public void setDesc(String desc) {
    this.desc = new Text(desc);
  }

  public Text getDesc() {
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
    return ToStringBuilder.reflectionToString(this);
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

  public void setLastUpdatedByUser(User lastUpdatedByUser) {
    this.lastUpdatedByUser = lastUpdatedByUser;
  }

  public User getLastUpdatedByUser() {
    return lastUpdatedByUser;
  }

  public void setRescueURL(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public String getRescueURL() {
    return rescueURL;
  }

}
