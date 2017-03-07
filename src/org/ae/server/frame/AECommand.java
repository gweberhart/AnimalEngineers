package org.ae.server.frame;

import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public abstract class AECommand implements AECommandInterface {

  public abstract RPCResponse execute(RPC<?> action,
      HttpServletRequest request) throws AEServicesException;

  public static void checkLoggedIn() throws AEServicesException {
    if (getUser() == null) {
      AEServicesException e = new AEServicesException(
          "Not Signed In. Please Sign In and try again.");
      throw e;
    }
  }

  private static User getUser() {
    UserService userService = UserServiceFactory.getUserService();
    return userService.getCurrentUser();
  }
}
