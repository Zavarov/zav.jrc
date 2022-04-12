module zav.jrc.api {
  requires static org.eclipse.jdt.annotation;
  
  requires com.fasterxml.jackson.databind;
  requires zav.jrc.databind;
  
  exports zav.jrc.api;
  exports zav.jrc.api.endpoint;
}