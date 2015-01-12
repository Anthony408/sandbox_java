package problems.component_installer;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * ComponentTree Object.
 */
public class ComponentTree {

    HashMap<String, ComponentNode> nodes = new HashMap<String, ComponentNode>(); // stores String --> ComponentNode  mappings.
    LinkedList<String> installedComponents = new LinkedList<String>(); // list of installed components.

    public String executeDepend(String name, LinkedList<String> children){
        String result = "TODO";
        return result;
    }

    public String executeInstall(String name){
        String result = "TODO";
        return result;
    }

    public String executeRemove(String name){
        String result = "TODO";
        return result;
    }

    public String executeList(){
        String result = "TODO";
        return result;
    }

    private void insertNode(String name, LinkedList<String> parents, LinkedList<String> children){

        assert parents != null;
        assert children != null;

        ComponentNode node = null;

        /*
         * CREATE / GET the component node object.
         */
        // check if node already exists
        if( nodes.containsKey(name)){
            // if node already exists, this is to update the nodes children/parents.
            node = nodes.get(name);

            // update children.
            LinkedList<String> updatedChildren = new LinkedList<String>();
            updatedChildren.addAll(node.getChildren());
            updatedChildren.addAll(children);
            node.setChildren(updatedChildren);

            // update parents.
            LinkedList<String> updatedParents = new LinkedList<String>();
            updatedParents.addAll(node.getParents());
            updatedParents.addAll(parents);
            node.setParents(updatedParents);
        } else {
            // else the node does not already exist, create the node
            node = new ComponentNode(name);
            node.setChildren(children);
            node.setParents(parents);
        }
        

        /*
           Update the node based on the creation request.
           
           This is important for the insertion on the structure of our Component Data Tree and dependencies.
           
           Cases for insertion:
           
           #############
           ## NEW NODE 
           #############
           --- "INSTALL A1" or "DEPEND A1 AA1 AA2" where A1 has not been created via DEPEND or INSTALL yet.
           a.  create A1 NODE
           b.  if has children, then foreach child
                create child node, call it AA1
                i.  new node AA1
                ii.  A1 child pointer updated to have AA1 in it
                iii. AA1 parent pointer updated to have A1 in it.
           
           ##################
           ## EXISTING NODE
           ##################
           --- "INSTALL A1" or "DEPEND A0 A1" may have been already called, it may already have parents/children to update.
           --- this is an n-ary tree so nodes have up to N parents and N children.
           a.  node = nodes.get(name)
           b.  update / append parent nodes if 'parents' != null
           c.  update / append child nodes if 'children' != null
           
           Example of this would be if the following ("DEPEND TOP AA1 BB1") were called:
           DEPEND AA1 AAA1 AAA2
           DEPEND TOP AA1 BB1
           
           
           Notice this structure that would already exist.
            
            "DEPEND AA1 AAA1 AAA2"
                 AA1
                  |
            +-----------+
            |           |
            v           v
           AAA1        AAA2

            "DEPEND TOP AA1 BB1"
            AA1's parent has to be updated to point to TOP now.
            BB1 gets created and has its parent pointed to TOP
            TOP gets created and points to AA1 and BB1.

                        TOP
                         |
                  +--------------+
                  |              |
                  v              v
                 AA1            BB1
                  |
            +-----------+
            |           |
            v           v
           AAA1        AAA2

         */
        
        /*
         If parents node is empty, no need to update parents to point to this node.
         */
        if (parents.size() > 0){
            updateParentsReferences(node);
        }

        if ( children.size() > 0 ) {
            updateChildrenReferences(node);
        }

        // update the node tree
        nodes.put(name,node);
    }

    private void updateChildrenReferences(ComponentNode node) {
        LinkedList<String> children = node.getChildren();
        for (String child_name : children) {
            // make sure all the children's parent reference point to this node.
            ComponentNode child_node = getNode(child_name);
            // if the child's parent reference doesnt contain this node's name, add it.
            if (!child_node.getParents().contains(node.getName())){
                LinkedList<String> new_parent_list = new LinkedList<String>();
                new_parent_list.addAll(child_node.getParents());
                new_parent_list.add(node.getName());
                child_node.setParents(new_parent_list);
            }
        }
    }

    private void updateParentsReferences(ComponentNode node) {
        LinkedList<String> parents = node.getParents();
        for (String parent_name : parents) {
            // make sure all the children's parent reference point to this node.
            ComponentNode parent_node = getNode(parent_name);
            // if the child's parent reference doesnt contain this node's name, add it.
            if (!parent_node.getChildren().contains(node.getName())){
                LinkedList<String> new_children_list = new LinkedList<String>();
                new_children_list.addAll(parent_node.getParents());
                new_children_list.add(node.getName());
                parent_node.setChildren(new_children_list);
            }
        }
    }



    private String installNode(String name){ //TODO
        String result = "TODO";
        return result;
    }
    
    private String removeNode(String name){ //TODO
        String result = "TODO";
        return result;
    }

    private ComponentNode getNode(String name) {
        return nodes.get(name);
    }


}

