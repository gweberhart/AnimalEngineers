package org.ae.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.ValidationException;

import org.datanucleus.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Entity
public class Audit {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
  private String id;

  @Version
  @Column(name = "version")
  private Integer version;
  private String entityName;
  private User changeByUser;
  private Date time;
  private Operation operation;
  private Object beforeChange;
  private Text beforeChangeAsString;

  public enum Operation {
    ADD,
    UPDATE,
    DELETE
  }

  public Audit(Object objectBeforeChange, Operation operation) {
    if (objectBeforeChange != null) {
      setBeforeChange(objectBeforeChange);
      setBeforeChangeAsString(new Text(objectBeforeChange.toString()));
      setOperation(operation);
      setEntityName(objectBeforeChange.getClass().getName());
      UserService userService = UserServiceFactory.getUserService();
      setChangeByUser(userService.getCurrentUser());
      setTime(new Date());
      persist();
    }
  }

  public static final EntityManager entityManager() {
    return EMF.get().createEntityManager();
  }

  public void persist() throws ValidationException {
    EntityManager em = entityManager();
    try {
      em.persist(this);
    } finally {
      em.close();
    }
  }

  public void remove() {
    EntityManager em = entityManager();
    try {
      em.remove(em.find(Audit.class, this.getId()));
    } finally {
      em.close();
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Object getBeforeChange() {
    return beforeChange;
  }

  public void setBeforeChange(Object beforeChange) {
    this.beforeChange = beforeChange;
  }

  public void setChangeByUser(User changeByUser) {
    this.changeByUser = changeByUser;
  }

  public User getChangeByUser() {
    return changeByUser;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public Operation getOperation() {
    return operation;
  }

  public void setBeforeChangeAsString(Text beforeChangeAsString) {
    this.beforeChangeAsString = beforeChangeAsString;
  }

  public Text getBeforeChangeAsString() {
    return beforeChangeAsString;
  }

}