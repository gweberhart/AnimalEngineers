package org.ae.server;

import gwtupload.server.gae.BlobstoreFileItemFactory.BlobstoreFileItem;
import gwtupload.server.gae.BlobstoreUploadAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesServiceFactory;

public class AEUploadAction extends BlobstoreUploadAction {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getParameter("blob-key") != null) {
      blobstoreService.serve(new BlobKey(request.getParameter("blob-key")),
          response);
    } else if (request.getParameter("redirect") != null) {
      perThreadRequest.set(request);
      String ret = TAG_ERROR;
      Map<String, String> stat = getUploadStatus(request, null, null);
      List<FileItem> items = getSessionFileItems(request);
      int nitems = 0;
      if (items != null) {
        nitems = items.size();
        for (FileItem item : getSessionFileItems(request)) {
          BlobKey k = ((BlobstoreFileItem) item).getKey();
          BlobInfo i = blobInfoFactory.loadBlobInfo(k);
          if (i != null) {
            stat.put("ctype", i.getContentType() != null ? i.getContentType()
                : "unknown");
            stat.put("size", "" + i.getSize());
            stat.put("name", "" + i.getFilename());
          }
          String blobKey = k.getKeyString();
          stat.put("blobkey", blobKey);
          stat.put("message", k.getKeyString());
          appendServingURL(stat, blobKey);
        }
      }
      stat.put(TAG_FINISHED, "ok");
      ret = statusToString(stat);
      finish(request);
      logger.debug("BLOB-STORE-SERVLET: (" + request.getSession().getId()
          + ") redirect nitems=" + nitems + "\n" + ret);
      renderXmlResponse(request, response, ret, true);
      perThreadRequest.set(null);
    } else {
      super.doGet(request, response);
    }
  }

  private void appendServingURL(Map<String, String> stat, String blobKey) {
    if (blobKey != null) {
      BlobKey key = new BlobKey(blobKey);
      String path = ImagesServiceFactory.getImagesService().getServingUrl(key,
          240, false);
      String url = "http://0.0.0.0:8888"; // dev mode
      if (path.contains(url)) {
        path = path.replaceFirst(url, "");
      }
      stat.put("servingUrl", path);
    }
  }
}
