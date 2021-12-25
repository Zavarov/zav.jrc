module zav.jcr.listener {
  requires static org.eclipse.jdt.annotation;

  requires com.google.common;
  requires com.google.guice;
  requires com.google.guice.extensions.assistedinject;
  requires java.inject;
  requires org.apache.logging.log4j;

  requires zav.jrc.api;
  requires zav.jrc.client;
  requires zav.jrc.databind;
  requires zav.jrc.http;
  
  exports zav.jrc.listener;
  exports zav.jrc.listener.guice;
  exports zav.jrc.listener.observer;
  exports zav.jrc.listener.requester;
}