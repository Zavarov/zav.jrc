module zav.jrc.api {
  requires static org.eclipse.jdt.annotation;
  
  requires com.fasterxml.jackson.databind;
  requires com.google.common;
  requires com.google.guice;
  requires java.inject;
  requires org.apache.logging.log4j;
  requires okhttp3;
  
  requires zav.jrc.databind;
  requires zav.jrc.endpoint;
  requires zav.jrc.http;
  
  exports zav.jrc;
  exports zav.jrc.client;
  exports zav.jrc.guice;
  
  opens zav.jrc to com.google.guice;
  opens zav.jrc.client to com.google.guice;
}