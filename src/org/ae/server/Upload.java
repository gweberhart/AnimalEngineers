/**
 * Copyright (c) 2010 Animal Engineers, All Rights Reserved
 */
package org.ae.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Upload extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BlobstoreService blobstoreService = BlobstoreServiceFactory
      .getBlobstoreService();

  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
    BlobKey blobKey = blobs.get("fileUploadField");
    session.setAttribute("lastUploadedImageBlobKey", blobKey.getKeyString());
  }
}