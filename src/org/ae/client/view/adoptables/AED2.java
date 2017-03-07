package org.ae.client.view.adoptables;

import org.ae.client.frame.ClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class AED2 extends Dialog implements AEDPresenter.Display {
  private TextField<String> txtAnimalName;
  private FileUploadField fileUploadField;
  private Image image;
  private FormPanel formPanelEditAnimal;
  private TextArea txaAnimalDescription;

  public AED2() {
    setSize("650px", "281px");
    setId("dlgAED");
    setModal(true);
    setHeading("Edit Animal");
    setLayout(new FitLayout());
    getButtonBar().getItem(0).setId("BTN_OK");

    formPanelEditAnimal = new FormPanel();
    formPanelEditAnimal.setPadding(1);
    formPanelEditAnimal.setMethod(Method.POST);
    formPanelEditAnimal.setEncoding(Encoding.MULTIPART);
    formPanelEditAnimal.setHeaderVisible(false);
    formPanelEditAnimal.setHeading("");
    formPanelEditAnimal.setCollapsible(true);
    formPanelEditAnimal.setLayout(new TableLayout(2));

    image = new Image(ClientUtils.DEFAULT_IMAGE);
    image.addLoadHandler(new LoadHandler() {
      public void onLoad(LoadEvent event) {
        txaAnimalDescription.setWidth(getWidth() - image.getWidth() - 32);
        txaAnimalDescription.setHeight(image.getHeight() - 27);
        if (image.getWidth() > 100) {
          fileUploadField.setWidth(image.getWidth());
        } else {
          fileUploadField.setWidth(200);
        }
        setHeight(image.getHeight() + 106);
        center();
      }
    });
    TableData td_image = new TableData();
    td_image.setPadding(2);
    formPanelEditAnimal.add(image, td_image);

    LayoutContainer layoutContainer = new LayoutContainer();
    layoutContainer.setLayout(new TableLayout(3));
    layoutContainer.add(new Text());

    Label lblAnimalName = new Label("Animal Name");
    TableData td_lblAnimalName = new TableData();
    td_lblAnimalName.setWidth("70");
    td_lblAnimalName.setHorizontalAlign(HorizontalAlignment.RIGHT);
    layoutContainer.add(lblAnimalName, td_lblAnimalName);

    txtAnimalName = new TextField<String>();
    txtAnimalName.setId("txtAnimalName");
    TableData td_txtAnimalName = new TableData();
    td_txtAnimalName.setPadding(2);
    layoutContainer.add(txtAnimalName, td_txtAnimalName);

    txaAnimalDescription = new TextArea();
    txaAnimalDescription.setId("txaAnimalDescription");
    TableData td_txaAnimalDescription = new TableData();
    td_txaAnimalDescription.setPadding(2);
    td_txaAnimalDescription.setWidth("100%");
    td_txaAnimalDescription.setColspan(3);
    layoutContainer.add(txaAnimalDescription, td_txaAnimalDescription);
    txaAnimalDescription.setSize("414px", "149px");
    TableData td_layoutContainer = new TableData();
    td_layoutContainer.setPadding(2);
    formPanelEditAnimal.add(layoutContainer, td_layoutContainer);

    fileUploadField = new FileUploadField();
    fileUploadField.setWidth(200);
    fileUploadField.setName("fileUploadField");
    TableData td_flpldfldSelectAnImage = new TableData();
    td_flpldfldSelectAnImage.setPadding(2);
    td_flpldfldSelectAnImage.setColspan(2);
    td_flpldfldSelectAnImage.setWidth("");
    formPanelEditAnimal.add(fileUploadField, td_flpldfldSelectAnImage);
    add(formPanelEditAnimal);
  }

  @Override
  public Button getBtnOK() {
    return (Button) getButtonBar().getItemByItemId(OK);
  }

  @Override
  public TextField<String> getTxtAnimalName() {
    return txtAnimalName;
  }

  @Override
  public TextArea getTextArea() {
    return txaAnimalDescription;
  }

  @Override
  public FileUploadField getFileUploadField() {
    return fileUploadField;
  }

  @Override
  public Image getImage() {
    return image;
  }

  @Override
  public Dialog asDialog() {
    return this;
  }

  public FormPanel getFormPanel() {
    return formPanelEditAnimal;
  }

}
