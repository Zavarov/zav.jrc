module zav.jrc.databind {
  requires com.fasterxml.jackson.annotation;
  requires java.compiler;
  requires org.apache.logging.log4j;
  
  exports zav.jrc.databind;
  exports zav.jrc.databind.api;
  exports zav.jrc.databind.core;
}