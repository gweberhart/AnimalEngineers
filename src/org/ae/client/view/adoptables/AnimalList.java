package org.ae.client.view.adoptables;

import org.ae.client.frame.AEListView;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class AnimalList extends Composite {
  private AEListView<BeanModel> listView;
  private Button btnAdd;
  private Button btnEdit;
  private Button btnDelete;
  private ToolBar toolBar;

  public AEListView<BeanModel> getListView() {
    return listView;
  }

  public AnimalList() {

    ContentPanel cntpnlAnimalList = new ContentPanel();
    cntpnlAnimalList.setFrame(true);
    cntpnlAnimalList.setAutoWidth(true);
    cntpnlAnimalList.setAutoHeight(true);
    cntpnlAnimalList.setHeaderVisible(false);
    TableLayout tl_layoutContainer = new TableLayout(1);
    tl_layoutContainer.setHeight("100%");
    tl_layoutContainer.setWidth("100%");
    cntpnlAnimalList.setLayout(tl_layoutContainer);

    toolBar = new ToolBar();

    btnAdd = new Button("Add");
    btnAdd.setId("btnAddAnimal");
    getToolBar().add(btnAdd);

    btnEdit = new Button("Edit");
    btnEdit.setId("btnEditAnimal");
    getToolBar().add(btnEdit);

    btnDelete = new Button("Delete");
    btnDelete.setId("btnDeleteAnimal");
    getToolBar().add(btnDelete);
    TableData td_toolBar = new TableData();
    td_toolBar.setWidth("800");
    cntpnlAnimalList.add(getToolBar(), td_toolBar);

    listView = new AEListView<BeanModel>(new ListStore<BeanModel>());
    TableData td_listView = new TableData();
    td_listView.setHeight("100%");
    td_listView.setVerticalAlign(VerticalAlignment.TOP);
    cntpnlAnimalList.add(listView, td_listView);
    listView.setAutoHeight(true);
    listView.setLoadingText("Fetching Animals");
    listView.setItemSelector("div.thumb-wrap");
    listView.setTemplate(getTemplate());
    listView.setId("lstAnimals");
    listView.setVisible(true);

    initComponent(cntpnlAnimalList);
  }

  private native String getTemplate() /*-{
                                      return ['<tpl for=".">',
                                      '<div class="thumb-wrap" id="{name}" style="border: 1px solid white">',
                                      '<div class="thumb">',
                                      '<font size="3"><table><tr><td></tr>',
                                      '<tr>',
                                      '<td><img src="{path}"></td><td>&nbsp;</td><td><b>{name}</b><br>{desc}</td>',
                                      '</tr>',
                                      '</table></div>',
                                      '</div>',  
                                      '</tpl>',
                                      '<div class="x-clear"></div>'].join("");
                                      }-*/;

  public Button getBtnAdd() {
    return btnAdd;
  }

  public Button getBtnEdit() {
    return btnEdit;
  }

  public Button getBtnDelete() {
    return btnDelete;
  }

  public ToolBar getToolBar() {
    return toolBar;
  }

}
