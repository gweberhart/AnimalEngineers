package org.ae.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ae.client.frame.ClientUtils;

@Entity
public class Rescue {
  @Transient
  private static final String ANIMAL_ENGINEERS = "AnimalEngineers";
  @Transient
  private static HashMap<String, Rescue> map = new HashMap<String, Rescue>();
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Version
  @Column(name = "version")
  private Integer version;
  private String name;
  private String emailAddress;
  private String rescueURL;
  private String pageBackgroundColor;
  private String foregroundColor;
  private String calendarURL;
  private Boolean hasAdoptionApplication = false;
  private Boolean hasFosterApplication = false;
  private Boolean showLeadBar = false;
  private Integer homePageVersion = 1;

  static {
    addRescues();
  }

  public static List<Rescue> findAllRescues() {
    List<Rescue> list = new ArrayList<Rescue>(map.values());
    return list;
  }

  public static Rescue findRescueByRescueId(String rescueId) {
    Rescue r = map.get(rescueId);
    if (r == null) {
      r = map.get(ANIMAL_ENGINEERS);
    }
    return r;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getVersion() {
    return version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setRescueURL(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public String getRescueURL() {
    return rescueURL;
  }

  public void setPageBackgroundColor(String pageBackgroundColor) {
    this.pageBackgroundColor = pageBackgroundColor;
  }

  public String getPageBackgroundColor() {
    return pageBackgroundColor;
  }

  public void setForegroundColor(String foregroundColor) {
    this.foregroundColor = foregroundColor;
  }

  public String getForegroundColor() {
    return foregroundColor;
  }

  public void setCalendarURL(String calendarURL) {
    this.calendarURL = calendarURL;
  }

  public String getCalendarURL() {
    return calendarURL;
  }

  private static void addRescues() {
    Rescue r = new Rescue();
    r.setRescueURL(ANIMAL_ENGINEERS);
    r.setName("Animal Engineers");
    r.setCalendarURL("https://www.google.com/calendar/embed?src=cdfqv8ok1c4607m5arh0gpmc20%40group.calendar.google.com&color=%232952A3&src=en.usa%23holiday%40group.v.calendar.google.com&color=%23333333&ctz=America%2FPhoenix");
    r.setEmailAddress("gary@animalengineers.org");
    r.setPageBackgroundColor("black");
    r.setForegroundColor("white");
    r.setHasAdoptionApplication(false);
    r.setHasFosterApplication(false);
    r.setShowLeadBar(false);
    r.setHomePageVersion(3);
    map.put(r.getRescueURL(), r);
    r = new Rescue();
    r.setRescueURL("RE");
    r.setName("Real Estate Ads");
    r.setCalendarURL("https://www.google.com/calendar/embed?src=cdfqv8ok1c4607m5arh0gpmc20%40group.calendar.google.com&color=%232952A3&src=en.usa%23holiday%40group.v.calendar.google.com&color=%23333333&ctz=America%2FPhoenix");
    r.setEmailAddress("happycatmaddog@gmail.com");
    r.setPageBackgroundColor("black");
    r.setForegroundColor("white");
    r.setHasAdoptionApplication(false);
    r.setHasFosterApplication(false);
    r.setShowLeadBar(false);
    r.setHomePageVersion(3);
    map.put(r.getRescueURL(), r);
    r = new Rescue();
    r.setRescueURL("Bulldog");
    r.setName("Northern Nevada Bulldog Rescue");
    r.setEmailAddress("buly4me@gmail.com");
    r.setPageBackgroundColor("#ADCEEE");
    r.setForegroundColor("black");
    r.setCalendarURL("https://www.google.com/calendar/embed?src=nnvbr.org_u33hbg8r16gln53vsv06afk60o%40group.calendar.google.com&color=%232952A3&src=en.usa%23holiday%40group.v.calendar.google.com&color=%23333333&ctz=America%2FLos_Angeles");
    r.setHasAdoptionApplication(true);
    r.setHasFosterApplication(true);
    r.setShowLeadBar(true);
    r.setHomePageVersion(1);
    map.put(r.getRescueURL(), r);
    r = new Rescue();
    r.setRescueURL("Caws");
    r.setName("Community Animal Welfare Society");
    r.setEmailAddress("janitacoombs@yahoo.com");
    r.setPageBackgroundColor("#ADCEEE");
    r.setForegroundColor("black");
    r.setCalendarURL("https://www.google.com/calendar/embed?src=quqs3urhcae4i3on3vb7meiivc%40group.calendar.google.com&color=%232952A3&src=en.usa%23holiday%40group.v.calendar.google.com&color=%23333333&ctz=America%2FPhoenix");
    r.setHasAdoptionApplication(false);
    r.setHasFosterApplication(false);
    r.setShowLeadBar(false);
    r.setHomePageVersion(1);
    map.put(r.getRescueURL(), r);
    r = new Rescue();
    r.setRescueURL("Greyhounds");
    r.setName("Nevada Greyhounds Unlimited");
    r.setEmailAddress("houndluv@aol.com");
    r.setPageBackgroundColor("#EDEDED");
    r.setForegroundColor("black");
    r.setCalendarURL("https://www.google.com/calendar/embed?src=14an5qctteeudt3a2tfaoelp5k%40group.calendar.google.com&color=%232952A3&src=en.usa%23holiday%40group.v.calendar.google.com&color=%23333333&ctz=America%2FPhoenix");
    r.setHasAdoptionApplication(true);
    r.setHasFosterApplication(false);
    r.setShowLeadBar(true);
    r.setHomePageVersion(4);
    map.put(r.getRescueURL(), r);
  }

  public void setHasAdoptionApplication(Boolean hasAdoptionApplication) {
    this.hasAdoptionApplication = hasAdoptionApplication;
  }

  public Boolean getHasAdoptionApplication() {
    return hasAdoptionApplication;
  }

  public void setHasFosterApplication(Boolean hasFosterApplication) {
    this.hasFosterApplication = hasFosterApplication;
  }

  public Boolean getHasFosterApplication() {
    return hasFosterApplication;
  }

  public void setShowLeadBar(Boolean showLeadBar) {
    this.showLeadBar = showLeadBar;
  }

  public Boolean getShowLeadBar() {
    return showLeadBar;
  }

  public void setHomePageVersion(Integer homePageVersion) {
    this.homePageVersion = homePageVersion;
  }

  public Integer getHomePageVersion() {
    return homePageVersion;
  }

  public static Rescue getCurrentRescue() {
    return map.get(ClientUtils.getInstitutionID());
  }

}
