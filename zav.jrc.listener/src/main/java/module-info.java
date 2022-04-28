open module zav.jcr.listener {
  requires static org.eclipse.jdt.annotation;

  requires com.google.common;
  requires com.google.guice;
  requires java.inject;
  requires okhttp3;
  requires org.slf4j;
  
  requires zav.jrc.api;
  requires zav.jrc.client;
  requires zav.jrc.databind;
  requires zav.jrc.http;
  
  exports zav.jrc.listener;
  exports zav.jrc.listener.observable;
  exports zav.jrc.listener.observer;
  exports zav.jrc.listener.requester;
  exports zav.jrc.listener.event;
}