open module zav.jrc.endpoint.account {
  requires static org.eclipse.jdt.annotation;
  
  requires com.google.common;
  requires okhttp3;
  requires org.slf4j;

  requires zav.jrc.api;
  requires zav.jrc.client;
  requires zav.jrc.databind;
  requires zav.jrc.http;
}