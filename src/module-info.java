module Blockerino {
    //TODO Deprecated as JDK 9! Probably expected to be included as dependency module! At time of writing module could not yet be found thus using add-module runtime parameter as temporary fix.
    requires javafx.graphics;
    requires java.desktop;

    opens blockerino.entity;
    opens blockerino.entity.character;
    opens blockerino.util;

    requires java.xml.bind;
}