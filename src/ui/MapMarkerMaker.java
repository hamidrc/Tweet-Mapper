package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import twitter4j.Status;
import twitter4j.User;
import util.ImageCache;

import java.awt.*;

public class MapMarkerMaker extends MapMarkerCircle {
    private final Image image;
    private final User user;
    private final Status status;

    public User getUser() { return user; }
    public Status getStatus() { return status; }

    public MapMarkerMaker(Layer layer, String name, Coordinate coord, Color color, Status o){
        super(layer, name, coord, 30, STYLE.FIXED, getDefaultStyle());
        setColor(Color.orange);
        setBackColor(color);
        status = o;
        this.user = o.getUser();
        String imageURL = this.user.getMiniProfileImageURL();
        this.image = ImageCache.getInstance().getImage(imageURL);
    }
    @Override
    public void paint(Graphics g, Point position, int radius) {
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D gA = (Graphics2D)g;
            gA.setComposite(AlphaComposite.getInstance(3));
            gA.setPaint(this.getBackColor());
            Rectangle r = new Rectangle(position.x , position.y, radius, radius);
            g.fillRect(
                    (int)r.getX(),
                    (int)r.getY(),
                    (int)r.getWidth(),
                    (int)r.getHeight());
            g.drawImage(this.image, position.x, position.y, radius, radius, null);
            gA.setComposite(gA.getComposite());
        }
        g.setColor(this.getColor());
        g.drawRect(position.x, position.y, radius, radius);
    }
}