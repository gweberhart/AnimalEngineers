package org.ae.server.frame;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;

import org.ae.server.domain.AnimalBO;
import org.ae.server.domain.EMF;

public class ServerUtils {

  private static EntityManager entityManager;

  public static boolean isBulldog(String baseURL, String id) {
    return getInstitutionID(baseURL, id).equals("Bulldog");
  }

  public static boolean isGreyhounds(String baseURL, String id) {
    return getInstitutionID(baseURL, id).equals("Greyhounds");
  }

  public static final EntityManager getEntityManager() {
    if (entityManager == null) {
      entityManager = EMF.get().createEntityManager();
    }
    return entityManager;
  }

  public static String getImagePath(String id) {
    return "images/ae/" + id + "/";
  }

  public static String getInstitutionID(String baseURL, String idParm) {
    String id = "AnimalEngineers";
    if (idParm != null) {
      id = idParm;
    } else {
      if (baseURL != null) {
        if (baseURL.contains("www.animalengineers.org")) {
          id = "AnimalEngineers";
        } else if (baseURL.contains("nnvbr.org")) {
          id = "Bulldog";
        } else if (baseURL.contains("nevadagreyhounds.org")) {
          id = "Greyhounds";
        }
      }
    }
    return id;
  }

  public static void sendNotifications(String event, AnimalBO animalModel) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    Message msg = new MimeMessage(session);
    try {
      msg.setFrom(new InternetAddress("gary@animalengineers.org",
          "AnimalEngineers.com"));
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
          "gary@animalengineers.org", "Engineer"));
      msg.setSubject("Animal(" + animalModel.getName() + ") " + event
          + " - Animal Engineers");
      msg.setText("Animal(" + animalModel.getName() + ") " + event
          + " on animalengineers.com.\n\n" + animalModel.getDesc().getValue()
          + "\nLast Updated By IP:" + animalModel.getLastUpdatedByIP()
          + "\nLast Updated By User:" + animalModel.getLastUpdatedByUser()
          + "\nImage:" + animalModel.getPath());
      Transport.send(msg);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  public static void handleError(Throwable t) {
    t.printStackTrace();
  }
}