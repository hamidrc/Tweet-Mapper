package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;

public class MapMarkerSimple extends MapMarkerCircle {
    public static final double defaultMarkerSize = 30.0;
    public static final Color defaultColor = Color.green;

    public MapMarkerSimple(Layer layer, Coordinate coord) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(Color.green);
        setBackColor(defaultColor);
    }
}
