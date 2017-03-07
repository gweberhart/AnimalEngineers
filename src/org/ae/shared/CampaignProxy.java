package org.ae.shared;

import java.util.Date;
import java.util.List;

import org.ae.server.domain.Campaign;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(Campaign.class)
public interface CampaignProxy extends EntityProxy {

  String getCategory();

  String getSubCategory();

  String getPrice();

  String getAdTitle();

  String getDescription();

  String getId();

  List<String> getImagePaths();

  String getPrimaryImagePath();

  String getRescueURL();

  String getContactName();

  String getEmailAddress();

  String getPhoneAreaCode();

  String getPhonePrefix();

  String getPhoneSuffix();

  String getStreet1();

  String getStreet2();

  String getCity();

  String getState();

  String getZipCode();

  Boolean getActive();

  Date getLastPostTime();

  String getPubliserAdNumber();

  void setCategory(String category);

  void setSubCategory(String subCategory);

  void setPrice(String price);

  void setAdTitle(String adTitle);

  void setDescription(String description);

  void setImagePaths(List<String> imagePaths);

  void setPrimaryImagePath(String path);

  void setRescueURL(String url);

  void setContactName(String name);

  void setEmailAddress(String emailAddress);

  void setPhoneAreaCode(String phoneAreaCode);

  void setPhonePrefix(String phonePrefix);

  void setPhoneSuffix(String phoneSuffix);

  void setStreet1(String street1);

  void setStreet2(String street2);

  void setCity(String city);

  void setState(String state);

  void setZipCode(String zipCode);

  void setActive(Boolean active);

  void setLastPostTime(Date lastPostTime);

  void setPubliserAdNumber(String adNumber);

}