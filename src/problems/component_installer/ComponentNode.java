package problems.component_installer;

import java.util.LinkedList;

/**
 * Internal class representation of a component tree node.
 */
public class ComponentNode {

    private String name;
    private Boolean explicitly_installed;
    private LinkedList<String> children;
    private LinkedList<String> parents;

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

    public LinkedList<String> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<String> children) {
        this.children = children;
    }

    public LinkedList<String> getParents() {
        return parents;
    }

    public void setParents(LinkedList<String> parents) {
        this.parents = parents;
    }
}