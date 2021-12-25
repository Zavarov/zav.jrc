module zav.jcr.view {
  requires com.fasterxml.jackson.databind;
  requires com.google.guice;
  requires com.google.guice.extensions.assistedinject;
  requires java.desktop;
  requires java.inject;
  requires okhttp3;
  requires zav.jrc.api;
  requires zav.jrc.databind;
  requires static org.eclipse.jdt.annotation;
  
  exports view;
  exports view.guice;
  
  opens view to com.google.guice;
}