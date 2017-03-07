package org.ae.client.view.adoptables;

import org.ae.client.events.LoginEvent;
import org.ae.client.events.LoginEventHandler;
import org.ae.client.frame.AEListView;
import org.ae.client.frame.ClientUtils;
import org.ae.client.view.AdsWidget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Widget;

public class AdoptablesPageView extends Composite implements
    AdoptablesPagePresenter.Display {
  private LayoutContainer pnlAnimalsAndAdds;
  private AnimalList animalList;
  private TabPanel tabPanel;
  private TabItem tbtmNewTabitem;
  private TabItem tbtmTabHowToAdopt;
  private ContentPanel cntntpnlHowToAdopt;
  private Html htmlnewhtmlcomponent;
  private Html htmlnewhtmlcomponent_1;
  private TabItem tbtmTabNoteToAdopters;
  private TabItem tbtmTabAdoptionFees;
  private ContentPanel cntntpnlAdoptionFees;
  private Html htmlnewhtmlcomponent2;
  private AdsWidget adsWidget;
  private SimpleEventBus eventBus;

  public AdoptablesPageView(SimpleEventBus eventBus) {
    this.eventBus = eventBus;

    LayoutContainer pnlMain = new LayoutContainer();
    pnlMain.setAutoHeight(true);
    TableLayout tl_pnlMain = new TableLayout(1);
    tl_pnlMain.setCellHorizontalAlign(HorizontalAlignment.CENTER);
    tl_pnlMain.setWidth("100%");
    pnlMain.setLayout(tl_pnlMain);

    pnlAnimalsAndAdds = new LayoutContainer();
    pnlAnimalsAndAdds.setShadowOffset(8);
    TableLayout tl_pnlAnimalsAndAdds = new TableLayout(2);
    tl_pnlAnimalsAndAdds.setHeight("100%");
    tl_pnlAnimalsAndAdds.setWidth("");
    pnlAnimalsAndAdds.setLayout(tl_pnlAnimalsAndAdds);
    TableData td_pnlAnimalsAndAdds = new TableData();
    td_pnlAnimalsAndAdds.setPadding(5);
    td_pnlAnimalsAndAdds.setVerticalAlign(VerticalAlignment.TOP);
    td_pnlAnimalsAndAdds.setHeight("");

    tabPanel = new TabPanel();
    tabPanel.setAutoHeight(true);

    tbtmNewTabitem = new TabItem("Available For Adoption");
    tbtmNewTabitem.setAutoHeight(true);
    tbtmNewTabitem.setAutoWidth(true);
    TableLayout tl_tbtmNewTabitem = new TableLayout(1);
    tl_tbtmNewTabitem.setHeight("100%");
    tl_tbtmNewTabitem.setWidth("100%");
    tbtmNewTabitem.setLayout(tl_tbtmNewTabitem);

    animalList = new AnimalList();
    animalList.setAutoWidth(true);
    animalList.setAutoHeight(true);
    TableData td_animalList = new TableData();
    td_animalList.setHeight("100%");
    tbtmNewTabitem.add(animalList, td_animalList);
    animalList.setHeight("100%");
    tabPanel.add(tbtmNewTabitem);
    tbtmNewTabitem.setHeight("100%");

    if (ClientUtils.isBulldog()) {
      tabPanel.add(createHowToAdoptTabPanel());
      tabPanel.add(createNoteToAdoptersTabPanel());
      tabPanel.add(createAdoptionFeesTabPanel());
    }
    tabPanel.setSize("100%", "100%");

    ContentPanel cntpnlTabs = new ContentPanel();
    cntpnlTabs.setFrame(true);
    cntpnlTabs.setHeading("Adoptables");
    cntpnlTabs.add(tabPanel);

    TableData td_cntpnlTabs = new TableData();
    td_cntpnlTabs.setHorizontalAlign(HorizontalAlignment.LEFT);
    td_cntpnlTabs.setWidth("742");
    td_cntpnlTabs.setHeight("100%");
    td_cntpnlTabs.setPadding(5);
    td_cntpnlTabs.setVerticalAlign(VerticalAlignment.TOP);
    pnlAnimalsAndAdds.add(cntpnlTabs, td_cntpnlTabs);
    cntpnlTabs.setWidth("741");

    adsWidget = new AdsWidget();
    TableData td_adsWidget = new TableData();
    td_adsWidget.setVerticalAlign(VerticalAlignment.TOP);

    if (ClientUtils.getInstitutionID().equals("Bulldog")) {
      pnlAnimalsAndAdds.add(adsWidget, td_adsWidget);
    }

    pnlMain.add(pnlAnimalsAndAdds, td_pnlAnimalsAndAdds);

    initComponent(pnlMain);
    pnlMain.setSize("1072px", "943px");
    bind();
  }

  private void bind() {
    eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
      @Override
      public void onEvent(LoginEvent event) {
        toggleAdiminFunctions(ClientUtils.isLoggedIn());
      }
    });
  }

  private TabItem createHowToAdoptTabPanel() {
    tbtmTabHowToAdopt = new TabItem("How To Adopt A Bulldog");

    cntntpnlHowToAdopt = new ContentPanel();
    cntntpnlHowToAdopt.setHeaderVisible(false);
    cntntpnlHowToAdopt.setFrame(true);
    cntntpnlHowToAdopt.setHeading("How To Adopt A Bulldog");
    cntntpnlHowToAdopt.setCollapsible(true);
    TableLayout tl_cntntpnlHowToAdopt = new TableLayout(2);
    tl_cntntpnlHowToAdopt.setWidth("100%");
    cntntpnlHowToAdopt.setLayout(tl_cntntpnlHowToAdopt);

    htmlnewhtmlcomponent = new Html(
        "<font size=\"3\">\r\n<b>We adopt within 100 miles of the Reno/Tahoe area.</b>\r\n<br>\r\n<br>\r\nIf you live out of our adption area, please consider adopting from\r\n<a target=\"_blank\" href=\"http://www.rescuebulldogs.org/display.pl\">The Bulldog Club of America Rescue Network<a/>\r\n<br>\r\n<br>\r\n<b>Adopting a Bulldog</b>\r\n<br>\r\n<br>\r\nNot all Bulldogs we get into rescue are suitable for rehoming\r\nwith children or other pets. Many of these dogs are being re-homed\r\nbecause they are not good with children or they fight with other dogs in\r\nthe family or dislike cats. It depends on the dog and many times the\r\nfamily adopting on whether they will be placed in such an environment or\r\nwith young children.\r\n<br>\r\nRarely if ever do we get puppies and if we do they are usually in\r\npretty bad shape or have special needs. Most of the rescues are at least\r\n4 years old, however, there are exceptions and we have rescued younger\r\ndogs.\r\n<br>\r\nThe cost is a donation to Northern\r\nNevada Bulldog Rescue, usually starting at $300 and up. This can\r\nfluctuate depending on the dog and possible vet bills. All the money\r\ndonated to the rescue is used for medical, spaying/neutering, shots,\r\nworming and any needs our dogs may have. All dogs will be spayed or\r\nneutered and up to date on shots. The volunteers are not paid and\r\nusually spend their own money for food and housing. All the dogs coming\r\ninto rescue are fostered in our homes. This is a huge plus, as it helps\r\nus identify any special needs or problems that a dog may have and it\r\nhelps us to determine a dogs temperament before rehoming, however,\r\nit&#8217;s important to point out, we don&#8217;t usually have a lot of\r\nhistory on the Bulldogs we take in.\r\n<br>");
    TableData td_htmlnewhtmlcomponent = new TableData();
    td_htmlnewhtmlcomponent.setVerticalAlign(VerticalAlignment.TOP);
    td_htmlnewhtmlcomponent.setPadding(15);
    cntntpnlHowToAdopt.add(htmlnewhtmlcomponent, td_htmlnewhtmlcomponent);

    htmlnewhtmlcomponent_1 = new Html(
        "<font size=\"3\">\r\nWe will tell the adopters as much as\r\nwe know about the dog including any health, medical or behavioral issues\r\nthat need to be addressed. If for any reason you are unable to keep the\r\ndog, we require that the dog be returned to us. We will work with the\r\nadopters to help make sure the placement goes smoothly as our goal is\r\nfor the adoption to work, both for the dog and the new family. Many\r\ntimes we have a waiting list for Bulldogs and we have to evaluate the\r\ndogs and their needs and then determine the best home suited for the dog\r\nso the family next in line will not always be the next to get a dog. We\r\nwill not place a Bulldog into a home with an uneutered or unspayed dog.\r\nWe do not ship our Bulldogs and prefer to adopt our dogs in the\r\nReno/Carson City/Lake Tahoe area or within a 100 mile radius of Reno\r\nNevada.\r\n<br>\r\nBefore beginning the process of\r\nadopting or buying from a breeder, please consider if this is the breed\r\nfor you. Like many purebred breeds, Bulldogs can be prone to numerous\r\nhealth issues. Some common conditions may include; skin and eye issues,\r\nelongated soft palate, small trachea, allergies, orthopedic problems and\r\nhip dysplasia. They are indoor dogs especially here in the Nevada desert\r\nwhere the temperature can be either too hot or too cold for outdoor\r\nliving. Bulldogs enjoy sunbathing and are not always wise enough to\r\nrealize they have reached their level of heat tolerance and they can\r\noverheat easily. This can apply to being left in a hot car also. Being\r\nflat faced, Bulldogs tend to have restricted breathing which can\r\ninterfere with the body&#8217;s ability to cool itself. \r\n<br>Bulldogs are one of the most loving\r\ndogs you will find. They need to be part of the family not yard\r\nornaments. They will give you unconditional love and many moments of\r\nlaughter and even though the Bulldogs that come through rescue may be in\r\nless than perfect condition, they are still wonderful dogs and special\r\nin their own way.\r\n<br>\r\nFill out an application at: <a target=\"_blank\" href=\"http://www.rescuebulldogs.org\">www.rescuebulldogs.org</a>\r\n");
    TableData td_htmlnewhtmlcomponent_1 = new TableData();
    td_htmlnewhtmlcomponent_1.setVerticalAlign(VerticalAlignment.TOP);
    td_htmlnewhtmlcomponent_1.setPadding(15);
    td_htmlnewhtmlcomponent_1.setWidth("50%");
    cntntpnlHowToAdopt.add(htmlnewhtmlcomponent_1, td_htmlnewhtmlcomponent_1);
    tbtmTabHowToAdopt.add(cntntpnlHowToAdopt);
    return tbtmTabHowToAdopt;
  }

  private TabItem createNoteToAdoptersTabPanel() {
    tbtmTabNoteToAdopters = new TabItem("Note To Adopters");

    cntntpnlHowToAdopt = new ContentPanel();
    cntntpnlHowToAdopt.setHeaderVisible(false);
    cntntpnlHowToAdopt.setFrame(true);
    cntntpnlHowToAdopt.setHeading("How To Adopt A Bulldog");
    cntntpnlHowToAdopt.setCollapsible(true);
    TableLayout tl_cntntpnlHowToAdopt = new TableLayout(1);
    tl_cntntpnlHowToAdopt.setWidth("100%");
    cntntpnlHowToAdopt.setLayout(tl_cntntpnlHowToAdopt);

    htmlnewhtmlcomponent = new Html(
        "<font size=\"3\">\r\n<b>Note To Adopters</b>\r\n<br>\r\n<br>\r\nLassie, Cleo, Rin Tin Tin and Toto don't show up in rescue. We don't get the elegantly coiffed, classically beautiful, completely trained, perfectly behaved dog.  We get the leftovers. Dogs that other people have incompetently bred, inadequately socialized, ineffectively \"trained,\" and badly treated.  Most Rescue dogs have had it.  They've been pushed from one lousy situation to another. They've never had proper veterinary care, kind and consistent training, or sufficient company.  They've lived outside, in a crate, or in the basement.  They're scared, depressed and anxious. Some are angry. Some are sick.  Some have given up.  But we are Rescue and we don't give up.  We never give up on a dog.  We know that a dog is a living being, with a spirit and a heart and feelings.  Our dogs are not commodities, things, or garbage. They are part of sacred creation and they deserve as much love and care and respect as the next Westminster champion.  So please, please don't come to rescue in the hopes of getting a \"bargain,\" or indeed of \"getting\" anything.  Come to Rescue to give, to love, to save a life -- and to mend your own spirit.  For Rescue's will reward you in ways you never thought possible.  I can promise you this -- a rescue dog will make you a better person.\r\n<br>\r\nWritten and permission to copy by: Diane Morgan");
    TableData td_htmlnewhtmlcomponent = new TableData();
    td_htmlnewhtmlcomponent.setVerticalAlign(VerticalAlignment.TOP);
    td_htmlnewhtmlcomponent.setPadding(15);
    cntntpnlHowToAdopt.add(htmlnewhtmlcomponent, td_htmlnewhtmlcomponent);
    htmlnewhtmlcomponent.setWidth("");
    tbtmTabNoteToAdopters.add(cntntpnlHowToAdopt);
    return tbtmTabNoteToAdopters;
  }

  private TabItem createAdoptionFeesTabPanel() {
    tbtmTabAdoptionFees = new TabItem("Adoption Fees");
    tbtmTabAdoptionFees.setAutoWidth(true);
    tbtmTabAdoptionFees.setAutoHeight(true);

    cntntpnlAdoptionFees = new ContentPanel();
    cntntpnlAdoptionFees.setHeaderVisible(false);
    cntntpnlAdoptionFees.setFrame(true);
    cntntpnlAdoptionFees.setHeading("How To Adopt A Bulldog");
    cntntpnlAdoptionFees.setCollapsible(true);
    TableLayout tl_cntntpnlHowToAdopt = new TableLayout(2);
    tl_cntntpnlHowToAdopt.setWidth("100%");
    cntntpnlAdoptionFees.setLayout(tl_cntntpnlHowToAdopt);

    htmlnewhtmlcomponent2 = new Html(
        "<font size=\"3\">\r\n<b>Adoption Fees</b>\r\n<br>\r\n<br>\r\nMany people decide to go through rescue because they have \"always wanted\" a Bulldog and they think by going through us they can get a Bulldog cheaper then the expensive puppies for sale. Bulldogs are not cheap to adopt, the fee can range from <b>$300 to $700</b> depending on the dogs age and health. Adopting is less expensive then buying a puppy but in the long run can get expensive because the dog may have issues either mental or physical that needs dealing with through training or even on going medical needs. We are selective on where the dogs are placed and into what homes they go, we want people to adopt our dogs for the right reasons and to have done research on the breed itself. Just having \"always wanted one\" is not always good enough, having a love of dogs and understanding this breed can be different is what we look for. They are indoor dogs, need to be one of the family, need to see a vet for check ups regularly, and they need to be given unconditional love, as that's what they will give you. Please don't come looking for a \"Cheap\" Bulldog from us.");
    htmlnewhtmlcomponent2.setAutoWidth(true);
    TableData td_htmlnewhtmlcomponent = new TableData();
    td_htmlnewhtmlcomponent.setVerticalAlign(VerticalAlignment.TOP);
    td_htmlnewhtmlcomponent.setPadding(15);
    td_htmlnewhtmlcomponent.setWidth("");

    cntntpnlAdoptionFees.add(htmlnewhtmlcomponent2, td_htmlnewhtmlcomponent);

    tbtmTabAdoptionFees.add(cntntpnlAdoptionFees);
    return tbtmTabAdoptionFees;
  }

  public AEListView<BeanModel> getListView() {
    return animalList.getListView();
  }

  @Override
  public Button getBtnAdd() {
    return animalList.getBtnAdd();
  }

  @Override
  public Button getBtnEdit() {
    return animalList.getBtnEdit();
  }

  @Override
  public Button getBtnDelete() {
    return animalList.getBtnDelete();
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void toggleAdiminFunctions(boolean on) {
    animalList.getBtnAdd().setVisible(on);
    animalList.getBtnEdit().setVisible(on);
    animalList.getBtnDelete().setVisible(on);
  }
}
