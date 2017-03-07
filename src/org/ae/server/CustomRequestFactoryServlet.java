package org.ae.server;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CustomRequestFactoryServlet extends RequestFactoryServlet {

  private static final long serialVersionUID = 1L;

  static class LoquaciousExceptionHandler implements ExceptionHandler {
    @Override
    public ServerFailure createServerFailure(Throwable throwable) {
      throwable.printStackTrace();
      return new ServerFailure(throwable.getMessage(), throwable.getClass()
          .getName(), null, true);
    }
  }

  public CustomRequestFactoryServlet() {
    super(new LoquaciousExceptionHandler());
  }
}
