package com.javasensei.portfolio.labyrinth;

import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class Tree extends Graph {

    private boolean isFirstAdd = true;

    public Tree(){
        super();
    }

    public Tree(Set<INode> nodes){
        for(INode node : nodes){
            add(new TreeNode(node.id(), node.getWeight()));
        }
    }

    public void setRoot(Coord id) {
        if (contains(id)) {
        for(INode node: getNodes()){
            TreeNode treeNode = (TreeNode)node;
            treeNode.setRoot(false);
        }
            ((TreeNode) getNode(id)).setRoot(true);
        }
    }

    public INode getRoot(){
        for(INode node: getNodes()){
            TreeNode treeNode = (TreeNode)node;
            if(treeNode.isRoot()){
                return treeNode;
            }
        }
        return null;
    }

    @Override
    public void add(INode node) {
        if (node instanceof TreeNode) {
            TreeNode treeNode = (TreeNode) node;
            if (isFirstAdd) {
                isFirstAdd = false;
                treeNode.setRoot(true);
            }
            super.add(treeNode);
        }
    }

    @Override
    public void connect(INode parent, INode child) {
        connect(parent, child, 0);
    }

    public void connect(INode parent, INode child, int weight) {
        if ((parent instanceof TreeNode) && (child instanceof TreeNode)) {
            TreeNode childTreeNode = (TreeNode) child;
            TreeNode parentTreeNode = (TreeNode) parent;
            childTreeNode.setParentNode(parentTreeNode);
            super.connect(childTreeNode, parentTreeNode, weight);
        }
    }

    public Path pathToRoot(INode node) {
        if (!contains(node)) {
            return null;
        }
        if(!(node instanceof TreeNode)){
            return null;
        }
        Path path = new Path();
        TreeNode currentNode = (TreeNode)node;
        while (!currentNode.isRoot()) {
            path.addNode(currentNode);
            currentNode = currentNode.getParentNode();
        }
        path.addNode(getRoot());
        return path;
    }

}
