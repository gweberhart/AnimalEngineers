package org.ae.server.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.server.frame.AECommand;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesServiceFactory;

public class GetLastUploadedImageServingURLCommand extends AECommand {

  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    String path = null;
    try {
      HttpSession session = request.getSession(true);
      BlobKey blobKey = new BlobKey(
          (String) session.getAttribute("lastUploadedImageBlobKey"));
      path = ImagesServiceFactory.getImagesService().getServingUrl(blobKey,
          240, false);
      String url = "http://0.0.0.0:8888"; // dev mode
      if (path.contains(url)) {
        path = path.replaceFirst(url, "");
      }
    } catch (Exception e) {
      throw new AEServicesException(e);
    }
    return new RPCResponse(path);
  }
}