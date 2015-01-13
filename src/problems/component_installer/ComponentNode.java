package problems.component_installer;

import java.util.LinkedList;
import java.util.List;

/**
 * Internal class representation of a component tree node.
 */
public class ComponentNode {

    private String name;
    private Boolean explicitly_installed;
    private List<String> children;
    private List<String> parents;

    /**
     * Constructor.
     * @param name name of the component created
     */
    public ComponentNode(String name){
        this.name = name;
        this.explicitly_installed = false; // initialize to false, set to true when installed explicitly.
        this.children = new LinkedList<>(); // initialize as empty, set when installing & updating links.
        this.parents = new LinkedList<>(); // initialize as empty, set when installing & updating links.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getExplicitly_installed() {
        return explicitly_installed;
    }

    public void setExplicitly_installed(Boolean explicitly_installed) {
        this.explicitly_installed = explicitly_installed;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return String.format(
                "\n--------------------------------\n" +
                "Name: %s\n" +
                "ExplicitlyInstalled: %s\n" +
                "Parents: %s\n" +
                "Children: %s" + 
                "\n--------------------------------\n",
                getName(), getExplicitly_installed(), getParents(), getChildren()
        );
    }
}