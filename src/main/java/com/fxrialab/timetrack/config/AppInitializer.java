package com.fxrialab.timetrack.config;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.faces.webapp.FacesServlet;
import javax.servlet.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
        return new ApplicationContextInitializer<?>[]{new RootContextInitializer()};
    }

//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebConfig.class);
//
//        return context;
//    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] fs = super.getServletFilters();
        ArrayList<Filter> filters = new ArrayList<Filter>(Arrays.asList(fs != null ? fs : new Filter[]{}));

        filters.addAll(Arrays.asList(
                new FileUploadFilter()
        ));

        return filters.toArray(new Filter[filters.size()]);
    }

    @Override
    protected String getServletName() {
        return "timetrack-mvc-servlet";
    }


//    @Override
//    protected void registerContextLoaderListener(ServletContext servletContext) {
//        super.registerContextLoaderListener(servletContext);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("faces_servlet", FacesServlet.class);
//        servlet.addMapping("*.xhtml");
//        servlet.setLoadOnStartup(1);
//
//        servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springSecurity.taglib.xml");
//        servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
//        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
//        servletContext.setInitParameter("javax.faces.THEME", "bootstrap");
//        servletContext.setInitParameter("primefaces.UPLOADER", "commons");
//    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }


}
