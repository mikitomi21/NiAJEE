package com.company.hightechcomapny.configuration.listener;

import com.company.hightechcomapny.datastore.DataStore;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateDataSource implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Creating datasource");
        event.getServletContext().setAttribute("datasource", new DataStore());
    }

}
