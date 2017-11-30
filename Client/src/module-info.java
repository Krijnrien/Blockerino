module Blockerino {
    requires javafx.graphics;
    requires java.desktop;
    //TODO Deprecated as JDK 9! Probably expected to be included as dependency module! Using add-module runtime parameter as temporary fix.
    requires java.xml.bind;
    requires java.rmi;
    requires java.logging;

    opens blockerino.util;
    opens blockerino.entity;
    opens blockerino.entity.character;
    opens blockerino.items;
    opens blockerino.items.container;

    exports blockerino.items;
}