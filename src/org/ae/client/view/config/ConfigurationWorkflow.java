package org.ae.client.view.config;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.ae.client.AEHOME;
import org.ae.client.ViewENUM;
import org.ae.client.events.ViewChangeEvent;
import org.ae.client.events.ViewChangeEventHandler;
import org.ae.client.frame.ClientUtils;
import org.ae.shared.AERequestFactory;
import org.ae.shared.CampaignProxy;
import org.ae.shared.CampaignRequest;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class ConfigurationWorkflow extends Composite implements
    ConfigurationPagePresenter.Display {
  interface Driver extends
      RequestFactoryEditorDriver<CampaignProxy, CampaignEditView> {
  }

  private LayoutContainer pnlAnimalsAndAdds;
  private ContentPanel cntpnlMain;
  private DeckPanel deckPanel;
  private CampaignListView campaignListView;
  private CampaignEditView campaignEditView;
  private Driver editorDriver = GWT.create(Driver.class);
  private CampaignProxy campaign;

  private SimpleEventBus eventBus;
  private AERequestFactory requestFactory = AEHOME.requestFactory;

  public ConfigurationWorkflow(SimpleEventBus eventBus) {
    this.eventBus = eventBus;
    LayoutContainer pnlMain = new LayoutContainer();
    pnlMain.setAutoHeight(true);
    TableLayout tl_pnlMain = new TableLayout(1);
    tl_pnlMain.setCellHorizontalAlign(HorizontalAlignment.CENTER);
    tl_pnlMain.setWidth("100%");
    pnlMain.setLayout(tl_pnlMain);

    pnlAnimalsAndAdds = new LayoutContainer();
    pnlAnimalsAndAdds.setShadowOffset(8);
    TableLayout tl_pnlAnimalsAndAdds = new TableLayout(1);
    tl_pnlAnimalsAndAdds.setCellHorizontalAlign(HorizontalAlignment.CENTER);
    tl_pnlAnimalsAndAdds.setHeight("100%");
    tl_pnlAnimalsAndAdds.setWidth("100%");
    pnlAnimalsAndAdds.setLayout(tl_pnlAnimalsAndAdds);
    TableData td_pnlAnimalsAndAdds = new TableData();
    td_pnlAnimalsAndAdds.setPadding(5);
    td_pnlAnimalsAndAdds.setVerticalAlign(VerticalAlignment.TOP);
    td_pnlAnimalsAndAdds.setHeight("");

    cntpnlMain = new ContentPanel();
    cntpnlMain.setAutoHeight(true);
    cntpnlMain.setFrame(true);
    cntpnlMain.setHeading("Configuration");
    TableLayout tl_cntpnlTabs = new TableLayout(2);
    tl_cntpnlTabs.setWidth("");
    cntpnlMain.setLayout(tl_cntpnlTabs);
    ConfigNavBar configNavBar = new ConfigNavBar();
    TableData td_configNavBar = new TableData();
    td_configNavBar.setHorizontalAlign(HorizontalAlignment.LEFT);
    td_configNavBar.setVerticalAlign(VerticalAlignment.TOP);
    cntpnlMain.add(configNavBar, td_configNavBar);

    TableData td_cntpnlTabs = new TableData();
    td_cntpnlTabs.setHorizontalAlign(HorizontalAlignment.LEFT);
    td_cntpnlTabs.setWidth("");
    td_cntpnlTabs.setHeight("100%");
    td_cntpnlTabs.setPadding(5);
    td_cntpnlTabs.setVerticalAlign(VerticalAlignment.TOP);
    TableData td_campaignListView = new TableData();
    td_campaignListView.setWidth("100%");
    td_campaignListView.setVerticalAlign(VerticalAlignment.TOP);

    deckPanel = new DeckPanel();
    campaignListView = new CampaignListView(this);
    deckPanel.add(campaignListView);
    campaignEditView = new CampaignEditView(this);
    deckPanel.add(campaignEditView);
    deckPanel.setAnimationEnabled(true);
    deckPanel.showWidget(0);
    cntpnlMain.add(deckPanel, td_campaignListView);

    pnlAnimalsAndAdds.add(cntpnlMain, td_cntpnlTabs);
    cntpnlMain.setSize("", "");
    pnlMain.add(pnlAnimalsAndAdds, td_pnlAnimalsAndAdds);

    initComponent(pnlMain);
    pnlMain.setSize("935", "100%");

    loadListView();
    bind();
  }

  private void bind() {
    eventBus.addHandler(ViewChangeEvent.TYPE, new ViewChangeEventHandler() {
      @Override
      public void onEvent(ViewChangeEvent event) {
        if (event.getRequestedPage().equals(ViewENUM.CONFIGURATION)) {
          deckPanel.showWidget(deckPanel.getWidgetIndex(campaignListView));
        }
      }
    });
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  private CampaignRequest createContext() {
    return requestFactory.createCampaignRequest();
  }

  public void saveCampaign() {
    ClientUtils.messageBox = MessageBox.wait("Progress",
        "Saving Campaign, please wait...", "Saving...");
    RequestContext context = editorDriver.flush();
    if (editorDriver.hasErrors()) {
      MessageBox.alert("Error", "Errors detected locally", null);
      return;
    }
    if (campaign.getImagePaths().size() > 10) {
      MessageBox.alert("Error", "Sorry, only ten images area allowed.", null);
    }
    context.fire(new Receiver<Void>() {

      @Override
      public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
        ClientUtils.hideMessageBox();
        for (ConstraintViolation<?> violation : violations) {
          Info.display("Violatoin", violation.getMessage());
        }
      }

      @Override
      public void onSuccess(Void response) {
        showListView();
        ClientUtils.hideMessageBox();
      }

      @Override
      public void onFailure(ServerFailure error) {
        ClientUtils.hideMessageBox();
        if (!error.toString().contains("ConstraintViolation")) {
          MessageBox.alert("ServerFailure Error", error.toString(), null);
        }
      }

    });
  }

  private boolean promptForLogin() {
    if (!ClientUtils.isLoggedIn()) {
      MessageBox.alert("Authentication Required",
          "Please Sign In to make changes. ", null);
      return false;
    }
    return true;
  }

  public void addCampaign() {
    CampaignRequest context = requestFactory.createCampaignRequest();
    CampaignProxy campaign = context.edit(context.create(CampaignProxy.class));
    campaign.setAdTitle("New Campaign");
    campaign.setRescueURL(ClientUtils.getInstitutionID());
    context.persist().using(campaign);
    startEdit(campaign, context);
  }

  public void startEdit(CampaignProxy campaign, RequestContext requestContext) {
    this.campaign = campaign;
    if (promptForLogin()) {
      edit(requestContext);
    }
  }

  private void edit(RequestContext requestContext) {
    editorDriver = GWT.create(Driver.class);
    editorDriver.initialize(eventBus, requestFactory, campaignEditView);

    if (requestContext == null) {
      fetchAndEdit();
      return;
    }
    editorDriver.edit(campaign, requestContext);
    History.newItem("EditCampaign", false);
    deckPanel.showWidget(deckPanel.getWidgetIndex(campaignEditView));
    ClientUtils.hideMessageBox();
  }

  public void fetchAndEdit() {
    ClientUtils.messageBox = MessageBox.wait("Progress",
        "Fetching Campaign, please wait...", "Loading...");
    Request<CampaignProxy> fetchRequest = requestFactory
        .createCampaignRequest().findCampaign(campaign.getId());
    fetchRequest.with(editorDriver.getPaths());
    fetchRequest.to(new Receiver<CampaignProxy>() {

      @Override
      public void onSuccess(CampaignProxy aCampaign) {
        ConfigurationWorkflow.this.campaign = aCampaign;
        CampaignRequest context = requestFactory.createCampaignRequest();
        edit(context);
        context.persist().using(campaign);
      }
    }).fire();
  }

  public void showListView() {
    deckPanel.showWidget(deckPanel.getWidgetIndex(campaignListView));
    loadListView();
  }

  void deleteCampaign(CampaignProxy aProxy) {
    if (!promptForLogin()) {
      return;
    }
    createContext().remove().using(aProxy).fire(new Receiver<Void>() {

      @Override
      public void onSuccess(Void response) {
        loadListView();
      }
    });
  }

  void loadListView() {
    createContext().findCampaigns(ClientUtils.getInstitutionID()).fire(
        new Receiver<List<CampaignProxy>>() {

          @Override
          public void onSuccess(List<CampaignProxy> response) {
            campaignListView.cellTable.setRowData(response);
          }
        });
  }

  public void copyCampaign(CampaignProxy aCampaign) {
    if (aCampaign == null) {
      MessageBox.alert("Error", "Please select a campaign to copy.", null);
      return;
    }
    if (!promptForLogin()) {
      return;
    }
    ClientUtils.messageBox = MessageBox.wait("Progress",
        "Copying Campaign, please wait...", "Loading...");
    Request<CampaignProxy> fetchRequest = requestFactory
        .createCampaignRequest().copyCampaign(aCampaign.getId());
    fetchRequest.with(editorDriver.getPaths());
    fetchRequest.to(new Receiver<CampaignProxy>() {

      @Override
      public void onSuccess(CampaignProxy aCampaign) {
        ConfigurationWorkflow.this.campaign = aCampaign;
        CampaignRequest context = requestFactory.createCampaignRequest();
        edit(context);
        context.persist().using(campaign);
      }
    }).fire();
  }
}
