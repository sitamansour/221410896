package binary.search.tree;
import java.util.Arrays;

public class BinarySearchTree {
    Node root;
    public BinarySearchTree(String[] courses) {
        Arrays.sort(courses);
        this.root = buildBinarySearchTree(courses, null);
    }
    private Node buildBinarySearchTree(String[] courses, Node parent) {
        Node rootNode = null;
        if (courses.length > 0) {
            Arrays.sort(courses);
            int midIndex = courses.length / 2;
            rootNode = new Node(courses[midIndex], parent,
                    buildBinarySearchTree(Arrays.copyOfRange(courses, 0, midIndex), rootNode),
                    buildBinarySearchTree(Arrays.copyOfRange(courses, midIndex + 1, courses.length), rootNode));
        }
        return rootNode;
    }
    public void printTree() {
        inOrderTraversal(root);
    }
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }
    public void insert(String course) {
        root = insertRecursive(root, course, null);
    }
    private Node insertRecursive(Node node, String course, Node parent) {
        if (node == null) {
            return new Node(course, parent, null, null);
        }
        int compareResult = course.compareTo(node.data);
        if (compareResult < 0) {
            node.left = insertRecursive(node.left, course, node);
        } else if (compareResult > 0) {
            node.right = insertRecursive(node.right, course, node);
        } else {
            System.out.println("Course already exists: " + course);
            return node; 
        }

        return node;
    }
    
     public void delete(String value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node node, String value) {
        if (node == null) {
            return null;
        }
        int compareResult = value.compareTo(node.data);
        if (compareResult < 0) {
            node.left = deleteRecursive(node.left, value);
        } else if (compareResult > 0) {
            node.right = deleteRecursive(node.right, value);
        } else {
           
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = minValue(node.right);
            node.right = deleteRecursive(node.right, node.data);
        }

        return node;
    }

    private String minValue(Node node) {
        String minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }
    public int depth(Node node) {
    return depthRecursive(node, 0);
}

private int depthRecursive(Node node, int currentDepth) {
    if (node == null) {
        return currentDepth;
    } else {
        int leftDepth = depthRecursive(node.left, currentDepth + 1);
        int rightDepth = depthRecursive(node.right, currentDepth + 1);
        return Math.max(leftDepth, rightDepth);
    }
}
    public static void main(String[] args) {
        String[] courses = {"Computer", "Math", "English", "Arabic", "Physics", "Chemistry"};
        BinarySearchTree bst = new BinarySearchTree(courses);
        bst.printTree();
        bst.insert("Biology");
        bst.insert("Math");
        System.out.println("After Insertions:");
        bst.printTree();   
        bst.delete("Math");
        System.out.println("After Deletion:");
        bst.printTree();
  int rootDepth = bst.depth(bst.root);
System.out.println("\nDepth of the root node: " + rootDepth);
    }
}
