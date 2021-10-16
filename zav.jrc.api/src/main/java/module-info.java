module zav.jrc.api {
  requires com.fasterxml.jackson.databind;
  requires com.google.common;
  requires com.google.guice;
  requires java.inject;
  requires org.apache.logging.log4j;
  requires okhttp3;
  requires zav.jrc.databind;
  requires static org.eclipse.jdt.annotation;
  
  exports zav.jrc;
  exports zav.jrc.client;
  exports zav.jrc.endpoint;
  exports zav.jrc.guice;
  exports zav.jrc.http;
  
  opens zav.jrc to com.google.guice;
  opens zav.jrc.client to com.google.guice;
}