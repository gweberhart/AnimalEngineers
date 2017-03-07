package org.ae.server.commands;

import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.server.frame.AECommand;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class GetUploadFormActionCommand extends AECommand {

  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    return new RPCResponse(getUploadFormAction());
  }

  private String getUploadFormAction() {
    BlobstoreService blobService = BlobstoreServiceFactory
        .getBlobstoreService();
    return blobService.createUploadUrl("/uploadOrig");
  }
}