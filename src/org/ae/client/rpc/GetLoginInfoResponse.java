package org.ae.client.rpc;

import org.ae.client.frame.RPCResponse;
import org.ae.client.model.LoginInfo;

public class GetLoginInfoResponse extends RPCResponse {
  private static final long serialVersionUID = 1L;
  private LoginInfo loginInfo;

  public GetLoginInfoResponse() {
    loginInfo = null;
  }

  public GetLoginInfoResponse(LoginInfo loginInfo) {
    this.loginInfo = loginInfo;
  }

  public LoginInfo getDetails() {
    return loginInfo;
  }

}