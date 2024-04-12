package ca.mcmaster.se2aa4.mazerunner;

public class Node {
    private Character mover;
    private Node root;
    private Node R;
    private Node L;
    private Node M;
    private Position position;
    private Direction dir;

    public Node(Position position, Direction dir, Character mover) {
        this.position = position;
        this.dir = dir;
        this.mover = mover;
    }

    public void addRightNode(Node right) {
        this.R = right;
        right.root = this;
    }

    public void addLeftNode(Node left) {
        this.L = left;
        left.root = this;
    }

    public void addForwardNode(Node forward) {
        this.M = forward;
        forward.root = this;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return dir;
    }

    public Character getMovement() {
        return mover;
    }

    public Node getRoot() {
        return root;
    }

    public Node getM() {
        return M;
    }

    public Node getR() {
        return R;
    }

    public Node getL() {
        return L;
    }
}
