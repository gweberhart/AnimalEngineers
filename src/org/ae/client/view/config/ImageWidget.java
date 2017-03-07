package org.ae.client.view.config;

import gwtupload.client.BaseUploadStatus;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;

import org.ae.client.AEHOME;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.Uploader;

import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.reveregroup.gwt.imagepreloader.ImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.ImageLoadHandler;
import com.reveregroup.gwt.imagepreloader.ImagePreloader;

/**
 * Most of the functionality in this class only works when deployed to GAE. The
 * mock up ImagesServices doesn't work the same as the real deal.
 * 
 * @author geberhart
 * 
 */
public class ImageWidget extends Composite implements LeafValueEditor<String> {

  private static ImageWidgetUiBinder uiBinder = GWT
      .create(ImageWidgetUiBinder.class);

  interface ImageWidgetUiBinder extends UiBinder<Widget, ImageWidget> {
  }

  @UiField
  Image image;

  @Ignore
  String blobkey;

  @Ignore
  String servingUrl;

  private ImageOptionsPopup imageOptionsPopup;
  private ImageDialog imageDialog;
  private Timer popupTimer;
  private boolean monitorMouseMovements = true;

  private boolean imageLoaded = false;

  public ImageWidget() {
    this(null);
  }

  public ImageWidget(String aServingUrl) {
    initWidget(uiBinder.createAndBindUi(this));
    if (aServingUrl != null) {
      image.setUrl(aServingUrl);
    }
    setServingUrl(aServingUrl);
    imageDialog = new ImageDialog();
    imageDialog.setAutoWidth(true);
    imageDialog.setAutoHeight(true);
    imageDialog.setAutoHide(true);
    imageOptionsPopup = new ImageOptionsPopup(this);
    imageOptionsPopup.setAnimationEnabled(true);
    bind();
  }

  private void bind() {
    if (AEHOME.eventBus == null) {
      return;
    }
    AEHOME.eventBus.addHandler(CampaignEvent.TYPE, new CampaignEventHandler() {

      @Override
      public void onEvent(CampaignEvent event) {
        switch (event.getEvent()) {
        case CampaignEvent.DELETE_IMAGE:
          imageOptionsPopup.hide();
          break;
        }
      }
    });
    AEHOME.eventBus.addHandler(CampaignEvent.TYPE, new CampaignEventHandler() {

      @Override
      public void onEvent(CampaignEvent event) {
        switch (event.getEvent()) {
        case CampaignEvent.IMAGE_VIEW_CLOSED:
          imageDialog.hide();
          monitorMouseMovements = true;
          break;
        }
      }
    });
  }

  void doUpload() {
    final FileUploadPopup upload = new FileUploadPopup();
    upload.upload.setValidExtensions("jpg", "gif", "png");
    upload.upload.addOnFinishUploadHandler(new OnFinishUploaderHandler() {

      @Override
      public void onFinish(IUploader uploader) {
        Document doc = XMLParser.parse(uploader.getServerResponse());
        NodeList list = doc.getElementsByTagName("blobkey");
        String blobKey = list.item(0).getFirstChild().getNodeValue();
        list = doc.getElementsByTagName("servingUrl");
        setServingUrl(list.item(0).getFirstChild().getNodeValue());
        image.setUrl(servingUrl);
        setBlobkey(blobKey);
        upload.hide();
      }
    });
    upload.show();

  }

  class FileUploadPopup extends Dialog {
    Uploader upload = new Uploader();

    public FileUploadPopup() {
      upload.setStatusWidget(new BaseUploadStatus());
      upload.setAutoSubmit(true);
      upload.getForm().setWidth("168px");
      upload.setSize("200px", "50px");
      upload.avoidRepeatFiles(false);
      setButtons(Dialog.CLOSE);
      add(upload);
    }
  }

  class UploadStatusBar extends BaseUploadStatus {

  }

  class ImageDialog extends Dialog {
    private ImageView imageView;

    public ImageDialog() {
      setHideOnButtonClick(true);
      imageView = new ImageView();
      add(imageView);
    }

    void setImageUrl(String aUrl) {
      imageView.setValue(aUrl);
    };
  }

  @UiHandler("image")
  void onImageClick(ClickEvent event) {
    // **** Warning (This code only works after depolyed to GAE!!!!)....
    String url = getServingUrl();
    if (url == null || url.contains(ClientUtils.DEFAULT_IMAGE)) {
      Info.display("No Image", "No image avaiable to show.");
      return;
    }
    Integer i = Window.getClientWidth() - 100;
    url = url.replace("=s240", "=w" + i.toString());
    // url = ClientUtils.getImagePath() + "1.png";
    monitorMouseMovements = false;
    imageOptionsPopup.hide();
    imageDialog.setImageUrl(url);
    if (!imageLoaded) {
      ClientUtils.messageBox = MessageBox.wait("Downloading Image",
          "Loading Image, please wait...", "downloading...");
      imageLoaded = true;
    }
    ImagePreloader.load(url, new ImageLoadHandler() {
      public void imageLoaded(ImageLoadEvent event) {
        if (event.isLoadFailed()) {
          Window.alert("Image " + event.getImageUrl() + " failed to load.");
        } else {
          imageDialog.show();
          imageDialog.center();
          ClientUtils.hideMessageBox();
        }
      }
    });
  }

  public String getBlobkey() {
    return blobkey;
  }

  public void setBlobkey(String blobkey) {
    this.blobkey = blobkey;
  }

  public String getServingUrl() {
    return servingUrl;
  }

  public void setServingUrl(String servingUrl) {
    this.servingUrl = servingUrl;
  }

  @UiHandler("image")
  void onImageMouseMove(MouseMoveEvent event) {
    if (monitorMouseMovements) {
      imageOptionsPopup.setPopupPosition(ImageWidget.this.getAbsoluteLeft(),
          ImageWidget.this.getAbsoluteTop());
      imageOptionsPopup.show();
      if (popupTimer != null) {
        popupTimer.cancel();
      }
      popupTimer = new Timer() {

        @Override
        public void run() {
          imageOptionsPopup.hide();
        }
      };
      popupTimer.schedule(1000);
    }
  }

  class ImageOptionsPopup extends PopupPanel {
    ImageOptionsEditor editor = new ImageOptionsEditor(ImageWidget.this);

    public ImageOptionsPopup(ImageWidget imageWidget) {
      super(true);
      setWidget(editor);
    }
  }

  @Override
  public void setValue(String aServingUrl) {
    if (aServingUrl == null) {
      aServingUrl = ClientUtils.DEFAULT_IMAGE;
    }
    image.setUrl(aServingUrl);
    setServingUrl(aServingUrl);
  }

  @Override
  public String getValue() {
    return servingUrl;
  }

}
