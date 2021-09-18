module zav.jcr.listener {
  requires com.google.common;
  requires com.google.guice;
  requires com.google.guice.extensions.assistedinject;
  requires javax.inject;
  requires org.apache.logging.log4j;
  requires org.eclipse.jdt.annotation;
  requires zav.jrc.api;
  requires zav.jrc.databind;
  requires zav.jcr.view;
  
  exports zav.jrc.listener;
  exports zav.jrc.listener.guice;
  exports zav.jrc.listener.observer;
  exports zav.jrc.listener.requester;
}