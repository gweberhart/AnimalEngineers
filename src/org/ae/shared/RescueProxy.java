package org.ae.shared;

import org.ae.client.model.Rescue;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Rescue.class)
public interface RescueProxy extends EntityProxy {
  String getName();

  String getEmailAddress();

  String getRescueURL();

  String getPageBackgroundColor();

  String getForegroundColor();

  String getCalendarURL();

  void setName(String name);

  void setEmailAddress(String emailAddress);

  void setRescueURL(String rescueURL);

  void setPageBackgroundColor(String color);

  void setForegroundColor(String color);

  void setCalendarURL(String calendarURL);

  void setHasAdoptionApplication(Boolean hasAdoptionApplication);

  Boolean getHasAdoptionApplication();

  void setHasFosterApplication(Boolean hasFosterApplication);

  Boolean getHasFosterApplication();

  public void setShowLeadBar(Boolean showLeadBar);

  public Boolean getShowLeadBar();

  public void setHomePageVersion(Integer homePageVersion);

  public Integer getHomePageVersion();
}
