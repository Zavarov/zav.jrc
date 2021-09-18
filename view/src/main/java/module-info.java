module zav.jcr.view {
  requires com.google.guice.extensions.assistedinject;
  requires java.desktop;
  requires okhttp3;
  requires zav.jrc.api;
  requires zav.jrc.databind;
  requires javax.inject;
  requires org.eclipse.jdt.annotation;
  requires com.fasterxml.jackson.databind;
  requires com.google.guice;
  
  exports zav.jrc.view;
  exports zav.jrc.view.guice;
  
  opens zav.jrc.view to com.google.guice;
}