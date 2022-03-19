module zav.jrc.databind {
  requires org.apache.logging.log4j;
  
  requires transitive com.fasterxml.jackson.databind;
  requires transitive java.compiler;
  
  exports zav.jrc.databind;
  exports zav.jrc.databind.io;
  exports zav.jrc.databind.core;
}