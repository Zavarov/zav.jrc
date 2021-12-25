module zav.jrc.api {
  requires static org.eclipse.jdt.annotation;
  
  requires com.fasterxml.jackson.databind;
  requires com.google.guice;
  requires com.google.guice.extensions.assistedinject;
  requires java.inject;
  requires okhttp3;
  
  requires zav.jrc.databind;
  requires zav.jrc.client;
  requires zav.jrc.endpoint;
  requires zav.jrc.http;
  
  requires java.desktop;
  
  exports zav.jrc.api;
  exports zav.jrc.api.guice;
  
  opens zav.jrc.api to com.google.guice;
}