module zav.jrc.api {
  requires static org.eclipse.jdt.annotation;
  
  requires zav.jrc.databind;
  
  exports zav.jrc.api;
  exports zav.jrc.api.endpoint;
}