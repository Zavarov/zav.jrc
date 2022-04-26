module zav.jrc.databind {
  requires org.slf4j;
  
  requires com.fasterxml.jackson.annotation;
  requires transitive java.compiler;
  
  exports zav.jrc.databind;
  exports zav.jrc.databind.io;
  exports zav.jrc.databind.core;
}