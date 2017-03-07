package org.ae.server.frame;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.rpc.GetAnimalsResponse;
import org.ae.server.commands.GetAnimalsCommand;

public class CommandProcessor {

  static HashMap<String, AECommand> commands = new HashMap<String, AECommand>();

  public static RPCResponse execute(RPC<RPCResponse> action,
      HttpServletRequest request) throws AEServicesException {
    return getCommand(action).execute(action, request);
  }

  public static ArrayList<?> executeReturnList(RPC<?> action,
      HttpServletRequest request) throws AEServicesException {
    GetAnimalsResponse response = (GetAnimalsResponse) getCommand(action)
        .execute(action, request);
    return response.getDetails();
  }

  private static AECommand getCommand(RPC<?> action) throws AEServicesException {
    String classPackage = GetAnimalsCommand.class.getPackage().getName();
    String name = action.getClass().getSimpleName();
    name = name.substring(0, name.lastIndexOf("Request"));
    name += "Command";
    String className = classPackage + "." + name;
    try {
      if (!commands.containsKey(className)) {
        commands.put(className, (AECommand) Class.forName(className)
            .newInstance());
      }
    } catch (Exception e) {
      ServerUtils.handleError(e);
    }
    return commands.get(className);
  }
}
