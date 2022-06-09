package fifa;

import java.util.Collection;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hitboxes {

    private Collection<Node> hitboxes = new LinkedList<Node>();

    public Rectangle border;

    private final Color color = Color.BLUE;

    private final double slupekGrubosc = 10;
    private final double slupekHeight = 60;

    private final double R_offset_x = -5;
    private final double R_offset_y = 0;

    // Increase size for ball collision
    private final double yBorderOffset = 10;
    private final double yBorderHeight = 40;

    private final double scoreOffset = 5;

    // Special hitbox for checking score collisions
    private Rectangle collisionHitbox;

    public Hitboxes(PlayField field) {
        ObservableList<Double> ptsR = field.goalR.getPoints();
        ObservableList<Double> ptsPolygon = field.field.getPoints();

        Rectangle leftR = new Rectangle(ptsR.get(6) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc,
                slupekHeight);
        leftR.setFill(color);
        leftR.setOpacity(0.5);

        Rectangle rightR = new Rectangle(ptsR.get(0) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc,
                slupekHeight);
        rightR.setFill(color);
        rightR.setOpacity(0.5);

        Rectangle bottom = new Rectangle(ptsR.get(4), ptsR.get(5) + R_offset_y - scoreOffset, ptsR.get(0) - ptsR.get(6),
                slupekGrubosc);
        bottom.setFill(color);
        bottom.setOpacity(0.5);

        collisionHitbox = bottom;

        border = new Rectangle(ptsPolygon.get(2) - yBorderOffset,
                ptsR.get(5) + R_offset_y - yBorderOffset / 2 + scoreOffset,
                ptsPolygon.get(4) - ptsPolygon.get(2) + 2 * yBorderOffset,
                yBorderHeight);
        border.setFill(color);
        border.setOpacity(0.5);

        add(leftR);
        add(rightR);
        add(bottom);
    }

    void add(Node node) {
        hitboxes.add(node);
    }

    public Collection<Node> getElementsCollection() {
        return hitboxes;
    }

    public void showHitboxes(Collection<Node> elements) {
        for (Node n : hitboxes) {
            elements.add(n);
        }
        elements.add(border);
    }

    public Rectangle getGoalHitbox() {
        return collisionHitbox;
    }
}
