open module jrc.endpoint.account {
  requires static org.eclipse.jdt.annotation;
  
  requires com.google.common;
  requires okhttp3;
  requires org.apache.logging.log4j;

  requires zav.jrc.api;
  requires zav.jrc.client;
  requires zav.jrc.databind;
  requires zav.jrc.http;
}