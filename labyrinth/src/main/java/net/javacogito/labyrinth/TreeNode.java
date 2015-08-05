package net.javacogito.labyrinth;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class TreeNode extends Node {
    private boolean isRoot;
    private Edge parent;
    private Set<Edge> children = new HashSet<Edge>();

    public TreeNode(Coord id) {
        super(id);
    }

    public TreeNode(Coord id, TreeNode parentNode) {
        super(id);
        this.parent = new Edge(this, parentNode);
    }

    public TreeNode(Coord id, TreeNode parentNode, int weight) {
        super(id, weight);
        this.parent = new Edge(this, parentNode);
    }

    public TreeNode(Coord id, int weight) {
        super(id, weight);
    }

    public TreeNode(Coord id, int weight, boolean isRoot) {
        super(id, weight);
        this.isRoot = isRoot;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public TreeNode getParentNode() {
        if (!isRoot()) {
            return (TreeNode) parent.endNode();
        }
        return null;
    }

    public void setParentNode(TreeNode parentNode) {
        parent = new Edge(this, parentNode);
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }


    Set<Edge> children(){
        return children;
    }

    @Override
    public void connectTo(INode node) {
        if (node instanceof TreeNode) {
            TreeNode treeNode = (TreeNode) node;
            treeNode.setParentNode(this);
            children.add(new Edge(this, treeNode));
        }
    }

    @Override
    public void connectTo(INode node, int weight) {
        if (node instanceof TreeNode) {
            TreeNode treeNode = (TreeNode) node;
            treeNode.setParentNode(this);
            children.add(new Edge(this, treeNode, weight));
        }
    }

    @Override
    public Set<INode> connectedNodes() {
        Set<INode> connectedNodesSet = new HashSet<INode>();
        for (Edge edge : allEdges()) {
            connectedNodesSet.add(edge.endNode());
        }
        return connectedNodesSet.isEmpty() ? Collections.<INode>emptySet() : connectedNodesSet;
    }

    @Override
    public boolean isConnectedTo(INode node) {
        for (Edge edge : allEdges()) {
            if (edge.endsWith(node)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public INode copyWithoutConnections() {
        return new TreeNode(id().copy(), getWeight(), isRoot);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id = ");
        sb.append(id());
        sb.append(", weight = ");
        sb.append(getWeight() == Integer.MAX_VALUE ? "INF": getWeight());
        if (isRoot()) {
            sb.append(", root ");
        } else {
            sb.append(", parent = ");
            sb.append(parent == null ? "" : parent.endNode().id());
        }
        sb.append(", children = ");
        boolean first = true;
        for (Edge edge : children) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(edge.endNode().id());
        }
        sb.append("}");
        return sb.toString();
    }

    private Set<Edge> allEdges() {
        Set<Edge> allEges = new HashSet<Edge>(children);
        allEges.add(parent);
        return allEges;
    }
}
