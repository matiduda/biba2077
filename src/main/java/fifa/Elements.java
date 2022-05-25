package fifa;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

public class Elements {

    private Collection<Node> sceneElements = new LinkedList<Node>();

    void add(Node node) {
        sceneElements.add(node);
    }

    public Collection<Node> getElements() {
        return sceneElements;
    }
}
