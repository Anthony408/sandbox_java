package problems.component_installer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * ComponentTree Object.
 */
public class ComponentTree {
    private final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    HashMap<String, ComponentNode> nodes = new HashMap<String, ComponentNode>(); // stores String --> ComponentNode  mappings.
    List<String> installedComponents = new ArrayList<String>(); // list of installed components.

    public void executeDepend(String name, List<String> children){

        List<String> parents = new ArrayList<String>();
        insertNode(name, parents, children);
    }

    public void executeInstall(String name){
        installNode(name, true); // top level explicitly calling install on this name (set to true)
    }

    public void executeRemove(String name){
        removeNode(name, true); // top level explicitly calling remove on this name (set to true)
    }

    public void executeList(){
        printInstalledComponents();
    }

    private void insertNode(String name){
        List parents = new ArrayList<String>();
        List children = new ArrayList<String>();
        insertNode(name, parents, children);
    }


    /*

    Cases for insertNode

    1. DEP A1 AA1 AA2 (node and children do not exist)

    2. DEP T1 A1 (node does not exist, but children do)

    3. INSTALL B1       (node exists, has been installed, but children do not exist, and child pointers dont exist yet)
       DEP B1 BB1 BB2


    1.  a. A1 created, and AA1 / AA2 also created if not existing already.
        b. Then AA1 and AA2 parent pointer updated to be A1.

    2.  a.  T1 created, and A1 not created again.
        b.  since A1 already exists, update its poiter to parent to now point to T1.

    3.  a. B1 exists now, update its child pointer.
        b. BB1 and BB2 do not exist, create them.

     */
    private void insertNode(String name, List<String> parents, List<String> children){

        ComponentNode node = null;

        // check if node already exists
        if( nodes.containsKey(name)){
            // if node already exists, this is to update the nodes children/parents.
            node = nodes.get(name);

            // update children.
            List<String> updatedChildren = new ArrayList<String>();
            updatedChildren.addAll(node.getChildren());
            updatedChildren.addAll(children);
            node.setChildren(updatedChildren);

            // update parents.
            List<String> updatedParents = new ArrayList<String>();
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
         * create children if they don't exist yet.
         */

        List<String> updatedParentList = new ArrayList<String>();
        
        for (String child_name : children) {
            updatedParentList.clear();
            updatedParentList.add(name);
            
            if(!nodeExists(child_name)){
                ComponentNode childNode = new ComponentNode(child_name);
                
                childNode.setParents(updatedParentList);
                nodes.put(child_name, childNode);
            } else {
                ComponentNode childNode = getNode(child_name);
                updatedParentList.addAll(childNode.getParents()); // add existing parent list.
                childNode.setParents(updatedParentList);
            }
        }

        /*
         * put the new node / updated node into the nodes hashtable.
         */
        nodes.put(name, node);
        System.out.println("DBG NODE:"+node);

    }

    private void removeUpdateChildrensParentReferences(ComponentNode node){
        List<String> children = node.getChildren();
        for (String child : children) {
            ComponentNode childNode = getNode(child);
            List<String> parentList = childNode.getParents();
            parentList.remove(node.getName());
            childNode.setParents(parentList);
        }
    }
    
    private void removeUpdateParentsChildReferences(ComponentNode node){
        List<String> parents = node.getParents();
        for (String parentName : parents) {
            ComponentNode parentNode = getNode(parentName);
            List<String> childList = parentNode.getParents();
            childList.remove(node.getName());
            parentNode.setParents(childList);
        }
    }

    /**
     * When removing a node (un-installing) the dependencies must be updated.
     * A-->B-->C
     * If removing A, then B's parent link needs to be updated to remove A
     * If removing B, then A's child link needs to be updated to remove B
     *                also, C's parent link needs to be updated to remove B
     * If removing C, then B's child link needs to be updated to remove B
     *
     * @param node node that is being uninstalled, so update its parents/children references to it.
     */
    private void removeUpdateLinks(ComponentNode node){
        removeUpdateChildrensParentReferences(node);
        removeUpdateParentsChildReferences(node);
    }
    
    private void installNode(String name, boolean explicit_install){ //TODO
        // make sure the node exists first, if not create it.
        if(!nodeExists(name)){
            insertNode(name);
        }

        // node now exists, install its dependencies first, then it.
        ComponentNode node = getNode(name);

        // make sure the node is not already installed
        if (installedComponents.contains(name)) {
            // only return an error if explicitly attempted to re-install
            if ( explicit_install ) {
                System.out.println(String.format("\t%s is already installed.", name));
            }
            // else dont print an error, just return
            return;
        }

        // node isnt already installed, proceed to install it.


        // check if the node has children, these must be installed first.
        List<String> children = node.getChildren();
        for (String child_name : children) {
            LOGGER.info(String.format("... attempting InstallNode(%s) on child", child_name));
            installNode(child_name, false);
        }
        node.setExplicitly_installed(explicit_install);
        installedComponents.add(name);
        System.out.println(String.format("\tInstalling %s", name));
    }

    private void removeNode(String name, boolean explicit_remove){

        // make sure the node exists first, if not return an error
        if(!nodeExists(name)){
            if (explicit_remove){
                System.out.println(String.format("\t%s has not been installed.", name));
            } else {
                return; // only return an error if explicitly told to remove.
            }
        }

        // node exists, remove it, THEN its children
        ComponentNode node = getNode(name);

        // make sure nothing depends on it (no parents)
        if (node.getParents().size() > 0) {
            if (explicit_remove){
                System.out.println(String.format("\t%s is still needed.", name));
            } else {
                System.out.println(String.format("\t...Cant remove(%s)! still has parents(%s)",
                        node.getName(), node.getParents()));
                // dont throw error if implicitly attempting, just return and dont remove.
            }
            return; // if there are parents, can not remove, return.
        }
        
        // make sure not an implicit removal attempt when it was explicitly installed, fail silently.
        if( node.getExplicitly_installed() ){
            if (! explicit_remove ) {
                // return quietly
                return;
            }
        }

        // remove it, then its children if they can be removed.
        System.out.println(String.format("\tRemoving %s.", name));
        installedComponents.remove(name);
        removeUpdateLinks(node);
        
        List<String> children = node.getChildren();
        for (String child_name : children) {
            removeNode(child_name, false); // explicit_remove set to false.
        }
    }

    private ComponentNode getNode(String name) {
        return nodes.get(name);
    }

    private void printInstalledComponents(){
        for (String installedComponent : installedComponents) {
            System.out.println(String.format("\t%s", installedComponent));
//            System.out.println(getNode(installedComponent));
        }
    }

    private boolean nodeExists(String name) {
        return nodes.containsKey(name);
    }


}

