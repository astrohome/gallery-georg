package org.georg.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by felix on 3/11/14.
 */
public class MvcWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration charEncodingfilterReg = servletContext.addFilter("characterFilter", CharacterEncodingFilter.class);

        charEncodingfilterReg.setInitParameter("encoding", "UTF8");
        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
        charEncodingfilterReg.addMappingForUrlPatterns(null, false, "*");

        servletContext.addFilter("HttpMethodFilter", HiddenHttpMethodFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");
/*
        servletContext.getJspConfigDescriptor().getJspPropertyGroups().add(new JspPropertyGroupDescriptor() {
            @Override
            public Collection<String> getUrlPatterns() {
                return new ArrayList<String>() {{add("*.jsp");}};
            }

            @Override
            public String getElIgnored() {
                return Boolean.FALSE.toString();
            }

            @Override
            public String getPageEncoding() {
                return "UTF-8";
            }

            @Override
            public String getScriptingInvalid() {
                return null;
            }

            @Override
            public String getIsXml() {
                return Boolean.FALSE.toString();
            }

            @Override
            public Collection<String> getIncludePreludes() {
                return null;
            }

            @Override
            public Collection<String> getIncludeCodas() {
                return null;
            }

            @Override
            public String getDeferredSyntaxAllowedAsLiteral() {
                return null;
            }

            @Override
            public String getTrimDirectiveWhitespaces() {
                return null;
            }

            @Override
            public String getDefaultContentType() {
                return null;
            }

            @Override
            public String getBuffer() {
                return null;
            }

            @Override
            public String getErrorOnUndeclaredNamespace() {
                return null;
            }
        });*/
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("org.georg.web.config");
        return context;
    }
}
