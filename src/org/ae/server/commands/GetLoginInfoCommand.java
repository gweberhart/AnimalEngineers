package org.ae.server.commands;

import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.LoginInfo;
import org.ae.client.rpc.GetLoginInfoRequest;
import org.ae.client.rpc.GetLoginInfoResponse;
import org.ae.server.frame.AECommand;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GetLoginInfoCommand extends AECommand {

  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    GetLoginInfoRequest loginAction = (GetLoginInfoRequest) action;
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    LoginInfo loginInfo = new LoginInfo();
    if (user != null) {
      loginInfo.setLoggedIn(true);
      loginInfo.setEmailAddress(user.getEmail());
      loginInfo.setNickname(user.getNickname());
      loginInfo.setLogoutUrl(userService.createLogoutURL(loginAction
          .getDestinationURL()));
    } else {
      loginInfo.setLoggedIn(false);
      loginInfo.setLoginUrl(userService.createLoginURL(loginAction
          .getDestinationURL()));
    }
    return new GetLoginInfoResponse(loginInfo);
  }
}