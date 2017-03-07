package org.ae.client.frame;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ListView;

public class AEListView<M extends ModelData> extends ListView<M> {

  public AEListView(ListStore<M> store) {
    super(store);
  }

  @Override
  protected void focusItem(int index) {
  }

}
