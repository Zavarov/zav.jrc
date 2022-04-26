open module zav.jrc.client {
  requires static org.eclipse.jdt.annotation;
  
  requires org.slf4j;
  requires com.fasterxml.jackson.databind;
  requires com.google.common;
  requires com.google.guice;
  requires okhttp3;
  
  requires zav.jrc.api;
  requires zav.jrc.databind;
  requires zav.jrc.http;

  requires transitive java.inject;
  
  exports zav.jrc.client;
  exports zav.jrc.client.guice;
}