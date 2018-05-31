package com.fxrialab.timetrack.config;

import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {	 
	
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/*" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		ArrayList<Filter> filters = new ArrayList<Filter>(Arrays.asList(super.getServletFilters()));
				
		filters.addAll(Arrays.asList(
				new DelegatingFilterProxy(),
				new FileUploadFilter()
				
				));		
		
		return filters.toArray(new Filter[filters.size()]);
	}

	@Override
	protected String getServletName() {
		return "timetrack-mvc-servlet";
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = (AnnotationConfigWebApplicationContext) super.createRootApplicationContext();
		
		rootContext.getServletContext().setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springSecurity.taglib.xml");
		rootContext.getServletContext().setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		rootContext.getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		rootContext.getServletContext().setInitParameter("javax.faces.THEME", "bootstrap");
		rootContext.getServletContext().setInitParameter("primefaces.UPLOADER", "commons");
		
		return rootContext;
	}

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		super.registerContextLoaderListener(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("faces_servlet", FacesServlet.class);
		servlet.addMapping("*.xhtml");
		
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{ AppRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ WebConfig.class };
	}


}
