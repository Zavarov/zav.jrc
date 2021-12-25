module zav.jrc.databind {
  requires com.fasterxml.jackson.annotation;
  requires org.apache.logging.log4j;
  
  requires java.compiler;
  
  exports zav.jrc.databind;
  exports zav.jrc.databind.io;
  exports zav.jrc.databind.core;
}