module zav.jrc.client {
  requires static org.eclipse.jdt.annotation;

  requires com.fasterxml.jackson.databind;
  requires com.google.common;
  requires com.google.guice;
  requires java.inject;
  requires okhttp3;
  requires org.apache.logging.log4j;
  
  requires zav.jrc.databind;
  requires zav.jrc.endpoint;
  requires zav.jrc.http;
  
  exports zav.jrc.client;
  exports zav.jrc.client.guice;
  
  opens zav.jrc.client to com.google.guice;
  opens zav.jrc.client.internal to com.google.guice;
}