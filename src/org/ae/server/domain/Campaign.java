package org.ae.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.datanucleus.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@Entity
public class Campaign {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
  private String id;

  @Version
  @Column(name = "version")
  private Integer version;
  private String primaryImagePath;
  @Basic(fetch = FetchType.EAGER)
  public List<String> imagePaths;
  @NotEmpty(message = "Rescue URL is required.")
  private String rescueURL;
  @NotEmpty(message = "Please enter the category.")
  private String category = "Pets and Livestock";
  @NotEmpty(message = "Please enter a sub-category.")
  private String subCategory = "Cats";
  @NotEmpty(message = "Please enter the asking price.")
  private String price;
  @NotEmpty(message = "Please enter the title for this campaign.")
  private String adTitle;
  @TextNotEmpty(message = "Please enter a description for this campaign.")
  private Text description;
  @NotEmpty(message = "Please enter a contact name.")
  private String contactName;
  @NotEmpty(message = "Please enter a valid email address.")
  @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Please enter a valid email address.")
  private String emailAddress;
  @NotEmpty(message = "Please enter a valid phone number")
  private String phoneAreaCode;
  @NotEmpty(message = "Please enter a valid phone number")
  private String phonePrefix;
  @NotEmpty(message = "Please enter a valid phone number")
  private String phoneSuffix;
  @NotEmpty(message = "Please enter a street address.")
  private String street1;
  private String street2;
  @NotEmpty(message = "Please enter a valid city.")
  private String city;
  @NotEmpty(message = "Please enter a valid state code.")
  @Size(min = 2, max = 2, message = "Two digit state please.")
  private String state;
  @NotEmpty(message = "Please enter a valid zip code.")
  @Size(min = 5, max = 5, message = "Five digit zip code please.")
  @Digits(integer = 5, fraction = 0, message = "Please enter a valid zip code.")
  private String zipCode;
  @NotNull
  private Boolean active;
  private Date lastPostTime;
  private String publisher = "KSL";
  private String publiserAdNumber;
  private Date lastUpdated;
  private Boolean deleted = false;
  @Transient
  private ValidatorFactory factory = Validation.byDefaultProvider().configure()
      .buildValidatorFactory();

  public Campaign() {
  }

  public static final EntityManager entityManager() {
    return EMF.get().createEntityManager();
  }

  public static Campaign findHomePageCampaign(String rescueID) {
    EntityManager em = entityManager();
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("select o from Campaign o");
      sb.append(" where o.rescueURL = '" + rescueID + "'");
      sb.append(" and o.deleted = false");
      sb.append(" and o.adTitle = 'Home Page Images'");
      Query query = em.createQuery(sb.toString());
      return (Campaign) query.getSingleResult();
    } catch (RuntimeException e) {
      if (e.toString().contains("No results for query")) {
        return null;
      }
      throw e;
    } finally {
      em.close();
    }
  }

  public static List<Campaign> findCampaigns(String rescueID) {
    EntityManager em = entityManager();
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("select o from Campaign o");
      sb.append(" where o.rescueURL = '" + rescueID + "'");
      sb.append(" and ");
      sb.append(" o.deleted = false");
      sb.append(" order by o.active DESC, o.lastUpdated DESC");
      Query query = em.createQuery(sb.toString());
      @SuppressWarnings("unchecked")
      List<Campaign> list = (List<Campaign>) query.getResultList();
      list.size();
      return list;
    } finally {
      em.close();
    }
  }

  public static List<Campaign> findAllCampaigns() {
    EntityManager em = entityManager();
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("select o from Campaign o");
      sb.append(" order by o.lastPostTime ASC");
      @SuppressWarnings("unchecked")
      List<Campaign> list = em.createQuery(sb.toString()).getResultList();
      list.size();
      return list;
    } finally {
      em.close();
    }
  }

  public static Campaign findCampaign(String id) {
    if (id == null) {
      return null;
    }
    Key key = KeyFactory.stringToKey(id);
    EntityManager em = entityManager();
    try {
      Campaign campaign = em.find(Campaign.class, key);
      return campaign;
    } finally {
      em.close();
    }
  }

  public static Campaign copyCampaign(String id) {
    Campaign campaign = findCampaign(id);
    campaign.setId(null);
    campaign.setActive(false);
    return campaign;
  }

  public void persist() throws ValidationException {
    validate();
    setState(getState().toUpperCase());
    EntityManager em = entityManager();
    try {
      auditChanges(Audit.Operation.UPDATE);
      this.setLastUpdated(new Date());
      em.persist(this);
    } finally {
      em.close();
    }
  }

  private void validate() {
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Campaign>> violations = validator.validate(this);
    for (ConstraintViolation<Campaign> constraintViolation : violations) {
      throw new ValidationException(constraintViolation.getMessage());
    }
  }

  private void auditChanges(Audit.Operation operation) {
    Campaign aCampaign = findCampaign(this.getId());
    if (aCampaign == null) {
      new Audit(this, Audit.Operation.ADD);
    } else {
      new Audit(aCampaign, operation);
    }
  }

  public void remove() {
    EntityManager em = entityManager();
    try {
      auditChanges(Audit.Operation.DELETE);
      this.setActive(false);
      this.setDeleted(true);
      em.persist(this);
    } finally {
      em.close();
    }
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public String getAdTitle() {
    return adTitle;
  }

  public void setAdTitle(String adTitle) {
    this.adTitle = adTitle;
  }

  public String getDescription() {
    if (description == null) {
      description = new Text("");
    }
    return description.getValue();
  }

  public void setDescription(String description) {
    if (description == null) {
      description = "";
    }
    this.description = new Text(description);
  }

  public String getRescueURL() {
    return rescueURL;
  }

  public void setRescueURL(String rescueURL) {
    this.rescueURL = rescueURL;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getVersion() {
    return version;
  }

  public void setImagePaths(List<String> imagePaths) {
    this.imagePaths = (ArrayList<String>) imagePaths;
  }

  public List<String> getImagePaths() {
    return imagePaths;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public void setPrimaryImagePath(String primaryImagePath) {
    this.primaryImagePath = primaryImagePath;
  }

  public String getPrimaryImagePath() {
    return primaryImagePath;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactName() {
    return contactName;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPrice() {
    return price;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPhoneAreaCode() {
    return phoneAreaCode;
  }

  public void setPhoneAreaCode(String phoneAreaCode) {
    this.phoneAreaCode = phoneAreaCode;
  }

  public String getPhonePrefix() {
    return phonePrefix;
  }

  public void setPhonePrefix(String phonePrefix) {
    this.phonePrefix = phonePrefix;
  }

  public String getPhoneSuffix() {
    return phoneSuffix;
  }

  public void setPhoneSuffix(String phoneSuffix) {
    this.phoneSuffix = phoneSuffix;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getActive() {
    return active;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setLastPostTime(Date lastPostTime) {
    this.lastPostTime = lastPostTime;
  }

  public Date getLastPostTime() {
    return lastPostTime;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPubliserAdNumber(String publiserAdNumber) {
    this.publiserAdNumber = publiserAdNumber;
  }

  public String getPubliserAdNumber() {
    return publiserAdNumber;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Boolean getDeleted() {
    return deleted;
  }

}