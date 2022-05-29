module zav.jrc.endpoint.test {
  requires static org.eclipse.jdt.annotation;

  requires com.google.guice;
  requires okhttp3;
  requires org.junit.jupiter.api;

  requires zav.jrc.databind;
  requires zav.jrc.client;
  
  exports zav.jrc.endpoint.test;
}