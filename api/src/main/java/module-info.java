module zav.jrc.api {
  requires com.fasterxml.jackson.databind;
  requires com.google.common;
  requires com.google.guice;
  requires javax.inject;
  requires org.apache.logging.log4j;
  requires org.eclipse.jdt.annotation;
  requires okhttp3;
  requires zav.jrc.databind;
  
  exports zav.jrc;
  exports zav.jrc.client;
  exports zav.jrc.endpoint;
  exports zav.jrc.guice;
  exports zav.jrc.http;
  
  opens zav.jrc to com.google.guice;
}