package org.xkidea.dukesbookstore.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private BookRequestBean request;

    @PostConstruct
    public void createData() {

    }
}
