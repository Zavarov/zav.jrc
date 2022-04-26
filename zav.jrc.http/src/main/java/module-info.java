module zav.jrc.http {
  requires static org.eclipse.jdt.annotation;
  
  requires com.fasterxml.jackson.databind;
  requires okhttp3;
  requires org.slf4j;
  
  requires zav.jrc.api;
  
  exports zav.jrc.http;
}