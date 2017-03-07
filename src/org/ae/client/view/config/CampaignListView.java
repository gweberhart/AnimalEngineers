package org.ae.client.view.config;

import org.ae.client.frame.ClientUtils;
import org.ae.shared.CampaignProxy;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class CampaignListView extends Composite {

  private CampaignProxy selectedCampaign;
  private static CampaignListView2UiBinder uiBinder = GWT
      .create(CampaignListView2UiBinder.class);
  @UiField(provided = true)
  CellTable<CampaignProxy> cellTable = new CellTable<CampaignProxy>();
  @UiField
  Button btnNewCampaingn;
  @UiField
  Button btnCopy;
  ConfigurationWorkflow configurationWorkflow;

  interface CampaignListView2UiBinder extends
      UiBinder<Widget, CampaignListView> {
  }

  public CampaignListView() {
    this(null);
  }

  public CampaignListView(ConfigurationWorkflow configurationWorkflow) {
    this.configurationWorkflow = configurationWorkflow;
    cellTable.setTableLayoutFixed(true);
    addSelectionModel();
    createColumns();
    initWidget(uiBinder.createAndBindUi(this));
  }

  private void addSelectionModel() {
    final SingleSelectionModel<CampaignProxy> selectionModel = new SingleSelectionModel<CampaignProxy>();
    cellTable.setSelectionModel(selectionModel);
    selectionModel
        .addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
          public void onSelectionChange(SelectionChangeEvent event) {
            selectedCampaign = selectionModel.getSelectedObject();
          }
        });
  }

  private void createColumns() {
    addImageColumn();
    addActiveColumn();
    addTitleColumn();
    addSubCategoryColumn();
    addDescriptionColumn();
    addAdNumberColumn();
    addDblClickHandler();
  }

  private void addDblClickHandler() {
    CellPreviewEvent.Handler<CampaignProxy> handler = new CellPreviewEvent.Handler<CampaignProxy>() {

      @Override
      public void onCellPreview(CellPreviewEvent<CampaignProxy> event) {
        if (event.getNativeEvent().getType().contains("dblclick")) {
          onBtnEditCampaignClick(null);
        }
      }
    };

    cellTable.addCellPreviewHandler(handler);
  }

  private void addSubCategoryColumn() {
    Column<CampaignProxy, String> subCategory = new TextColumn<CampaignProxy>() {
      @Override
      public String getValue(CampaignProxy campaign) {
        return campaign.getSubCategory();
      }
    };
    cellTable.addColumn(subCategory, "Sub Category");
  }

  private void addTitleColumn() {
    Column<CampaignProxy, String> title = new TextColumn<CampaignProxy>() {
      @Override
      public String getValue(CampaignProxy campaign) {
        String title = campaign.getAdTitle();
        if (title != null) {
          title = campaign.getAdTitle().trim();
        }
        return title;
      }
    };
    cellTable.addColumn(title, "Title");
  }

  private void addActiveColumn() {
    Column<CampaignProxy, String> active = new Column<CampaignProxy, String>(
        new TextCell()) {

      @Override
      public String getValue(CampaignProxy campaign) {
        return campaign.getActive().toString();
      }
    };
    cellTable.addColumn(active, "Active");
    cellTable.setColumnWidth(active, "8em");
  }

  private void addImageColumn() {
    ImageCell imageCell = new ImageCell();
    Column<CampaignProxy, String> imageColumn = new Column<CampaignProxy, String>(
        imageCell) {
      @Override
      public String getValue(CampaignProxy campaign) {
        String url = campaign.getPrimaryImagePath();
        if (url.contains(ClientUtils.DEFAULT_IMAGE)) {
          url = ClientUtils.DEFAULT_IMAGE_SMALL;
        }
        String imageSize = "=s100";
        url = url.replace("=s240", imageSize);
        return url;
      }
    };
    cellTable.setColumnWidth(imageColumn, "120px");
    cellTable.addColumn(imageColumn);
  }

  private void addAdNumberColumn() {
    ClickableTextCell adCell = new ClickableTextCell();
    FieldUpdater<CampaignProxy, String> fu = new FieldUpdater<CampaignProxy, String>() {
      public void update(int index, CampaignProxy object, String value) {
        String url = "http://www.ksl.com/index.php?nid=218&ad=";
        url += object.getPubliserAdNumber();
        url += "&cat=";
        if (object.getSubCategory().contains("Cats")) {
          url += "112";
        } else if (object.getSubCategory().contains("Dogs")) {
          url += "105";
        } else if (object.getSubCategory().contains("Animal")) {
          url += "274";
        }
        Window.open(url, "_blank", "");
      }
    };
    Column<CampaignProxy, String> adNumber = new Column<CampaignProxy, String>(
        adCell) {
      @Override
      public String getValue(CampaignProxy campaign) {
        return campaign.getPubliserAdNumber();
      }

    };
    adNumber.setFieldUpdater(fu);
    cellTable.addColumn(adNumber, "Ad Number");
  }

  private void addDescriptionColumn() {
    Column<CampaignProxy, String> description = new TextColumn<CampaignProxy>() {
      @Override
      public String getValue(CampaignProxy campaign) {
        String d = campaign.getDescription();
        if (d.length() > 100) {
          d = d.substring(0, 99) + "...";
        }
        return d;
      }

      @Override
      public Cell<String> getCell() {
        AbstractCell<String> c = new AbstractCell<String>("dblclick") {

          @Override
          public void render(com.google.gwt.cell.client.Cell.Context context,
              String value, SafeHtmlBuilder sb) {
            render(context, value, sb);
          }
        };
        return c;
      };
    };
    cellTable.addColumn(description, "Description");
    cellTable.setColumnWidth(description, "15em");
  }

  @UiHandler("btnDeleteCampaingn")
  void onBtnDeleteCampaingnClick(ClickEvent event) {
    if (selectedCampaign != null) {
      MessageBox.confirm(selectedCampaign.getAdTitle(),
          "Delete Selected Campaign?", new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent be) {
              if (be.getButtonClicked().getText().equals("Yes")) {
                configurationWorkflow.deleteCampaign(selectedCampaign);
              }
            }

          });
    } else {
      Info.display("Selection Required", "Please select a campaign to delete.");
    }
  }

  @UiHandler("btnEditCampaign")
  void onBtnEditCampaignClick(ClickEvent event) {
    if (selectedCampaign != null) {
      configurationWorkflow.startEdit(selectedCampaign, null);
    } else {
      Info.display("Selection Required", "Please select a campaign to edit.");
    }
  }

  @UiHandler("btnNewCampaingn")
  void onBtnNewCampaingnClick(ClickEvent event) {
    configurationWorkflow.addCampaign();
  }

  @UiHandler("btnCopy")
  void onBtnCopyClick(ClickEvent event) {
    if (selectedCampaign != null) {
      configurationWorkflow.copyCampaign(selectedCampaign);
    } else {
      Info.display("Selection Required", "Please select a campaign to copy.");
    }
  }
}
