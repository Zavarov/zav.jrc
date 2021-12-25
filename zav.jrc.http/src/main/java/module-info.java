module zav.jrc.http {
  requires static org.eclipse.jdt.annotation;
  
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires okhttp3;
  requires org.apache.logging.log4j;
  
  requires zav.jrc.endpoint;
  
  exports zav.jrc.http;
}