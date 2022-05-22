package fifa;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Node;

public class Elements {
    
    private Collection<Node> elements = new LinkedList<Node>();
    
    void add(Node node) {
        elements.add(node);
    }

    public Collection<Node> getElements() {
        return elements;
    }
}
