package org.ae.client.toolbar;

import org.ae.client.ViewENUM;
import org.ae.client.events.NavigateEvent;
import org.ae.client.frame.ClientUtils;
import org.ae.client.frame.Presenter;
import org.ae.client.view.DonationDialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainToolBarPresenter implements Presenter {
  private final SimpleEventBus eventBus;
  private Display display;

  public interface Display {
    HasClickHandlers getBtnHome();

    HasClickHandlers getBtnAdoptables();

    HasClickHandlers getBtnCalendar();

    HasClickHandlers getBtnDonate();

    HasClickHandlers getBtnLoginAndLogout();

    HasClickHandlers getBtnMissionStatement();

    HasClickHandlers getBtnConfiguration();

    Widget asWidget();

    public void processNavEvent(ViewENUM page);

    HasClickHandlers getBtnFAQ();

  }

  public MainToolBarPresenter(SimpleEventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void bind() {
    addDisplayEventHandlers();
  }

  private void addDisplayEventHandlers() {
    display.getBtnHome().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new NavigateEvent(ViewENUM.HOME.getHistoryToken()));
      }
    });
    if (display.getBtnAdoptables() != null) {
      display.getBtnAdoptables().addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new NavigateEvent(ViewENUM.ADOPTABLES
              .getHistoryToken()));
        }
      });
    }
    display.getBtnCalendar().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new NavigateEvent(ViewENUM.CALENDAR
            .getHistoryToken()));
      }
    });
    if (display.getBtnDonate() != null) {
      display.getBtnDonate().addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
         ClientUtils.showDonationDialog(); 
        }
      });
    }
    HasClickHandlers btnConfiguration = display.getBtnConfiguration();
    if (btnConfiguration != null) {
      btnConfiguration.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new NavigateEvent(ViewENUM.CONFIGURATION
              .getHistoryToken()));
        }
      });
    }
    HasClickHandlers btnFAQ = display.getBtnFAQ();
    if (btnFAQ != null) {
      btnFAQ.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new NavigateEvent(ViewENUM.FAQ.getHistoryToken()));
        }
      });
    }
    HasClickHandlers btnMissionStatement = display.getBtnMissionStatement();
    if (btnMissionStatement != null) {
      btnMissionStatement.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new NavigateEvent(ViewENUM.MISSION
              .getHistoryToken()));
        }
      });
    }
    display.getBtnLoginAndLogout().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (ClientUtils.isLoggedIn()) {
          logOff();
        } else {
          eventBus.fireEvent(new NavigateEvent(
              ViewENUM.LOGIN.getHistoryToken(), History.getToken()));
        }
      }
    });
  }

  private void logOff() {
    Window.Location.replace(ClientUtils.loginInfo.getLogoutUrl());
  }

  private void createDisplay() {
    if (display == null) {
      display = new MainToolbarView(eventBus);
      bind();
    }
  }

  @Override
  public void go(HasWidgets container) {
    createDisplay();
    container.clear();
    container.add(display.asWidget());
  }
}
