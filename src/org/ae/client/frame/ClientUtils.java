package org.ae.client.frame;

import org.ae.client.events.NavigateEvent;
import org.ae.client.model.LoginInfo;
import org.ae.client.view.DonationDialog;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.reveregroup.gwt.imagepreloader.ImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.ImageLoadHandler;
import com.reveregroup.gwt.imagepreloader.ImagePreloader;

public class ClientUtils {
  public static final String DEFAULT_IMAGE = "images/ae/addAnimalLogo.png";
  public static final String DEFAULT_IMAGE_SMALL = "images/ae/addAnimalLogoSmall.png";
  public static LoginInfo loginInfo;
  public static MessageBox messageBox;

  public static void mailto(String address, String subject) {
    mailtoImpl(address, URL.encode(subject));
  }

  private static native void mailtoImpl(String address, String subject) /*-{
		$wnd.location = "mailto:" + address + "?subject=" + subject;
  }-*/;

  public static boolean isLoggedIn() {
    if (loginInfo != null && loginInfo.isLoggedIn()) {
      return true;
    }
    return false;
  }

  public static boolean isAnimalEngineers() {
    return ClientUtils.getInstitutionID().equals("AnimalEngineers");
  }

  public static boolean isCaws() {
    return ClientUtils.getInstitutionID().equals("Caws");
  }

  public static boolean isBulldog() {
    return ClientUtils.getInstitutionID().equals("Bulldog");
  }

  public static boolean isGreyhounds() {
    return ClientUtils.getInstitutionID().equals("Greyhounds");
  }

  public static String getInstitutionID() {
    return getInstitutionID(GWT.getHostPageBaseURL());
  }

  private static String getInstitutionID(String baseURL) {
    String id = "AnimalEngineers";
    String idParm = Window.Location.getParameter("id");
    if (idParm != null) {
      id = idParm;
    } else {
      if (baseURL != null) {
        if (baseURL.contains("nnvbr.org")) {
          id = "Bulldog";
        } else if (baseURL.contains("nevadagreyhounds.org")) {
          id = "Greyhounds";
        }
      }
    }
    return id;
  }

  public static String createDestinationURL(NavigateEvent event) {
    boolean devMode = GWT.getPermutationStrongName().contains("HostedMode");
    StringBuilder sb = new StringBuilder();
    sb.append(GWT.getHostPageBaseURL()); // eg. http://127.0.0.1:8888/
    if (devMode) {
      sb.append("AEHOME.jsp?gwt.codesvr=127.0.0.1:9997");
    }
    if (sb.toString().contains("?")) {
      sb.append("&");
    } else {
      sb.append("?");
    }
    sb.append("id=" + getInstitutionID());
    if (event != null) {
      sb.append("#" + event.getContinuePage());
      if (event.getAnimalKey() != null) {
        sb.append(":" + event.getAnimalKey());
      }
    }
    return sb.toString();
  }

  public static void handleError(Throwable error) {
    if (!(error instanceof StatusCodeException)) {
      Window.alert(error.toString());
    }
    if (error.toString().contains("Not Signed In.")) {
      MessageBox.alert("Not Signed In", "Please sign in and try again.", null);
    }
  }

  public static String getImagePath() {
    return getImagePath(Window.Location.getHostName());
  }

  private static String getImagePath(String baseURL) {
    return "images/ae/" + getInstitutionID(baseURL) + "/";
  }

  public static void hideMessageBox() {
    if (messageBox != null) {
      messageBox.close();
    }
  }

  public static void showDonationDialog() {
    final DonationDialog d = new DonationDialog();
    ImagePreloader.load(DonationDialog.URL, new ImageLoadHandler() {
      
      @Override
      public void imageLoaded(ImageLoadEvent event) {
        d.show();
      }
    });
  }

}
