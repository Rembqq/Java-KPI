package org.example.lab6;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int data;
        Node left, right, parent;
        boolean color;
        Node(int data) {
            this.data = data;
            this.color = RED; // New nodes are initially red
        }
    }

    private Node root;

    // Left rotation
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if(y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null) {
            root = y;
        } else if(x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if(x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;

        if(y.parent == null) {
            root = x;
        } else if(y.parent.left == y) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;

    }

    public void insert(int data) {
        Node newNode = new Node(data);
        root = bstInsert(root, newNode);
        fixViolation(newNode);
    }

    private Node bstInsert(Node root, Node newNode) {
        if(root == null) {
            return newNode;
        }
        if(newNode.data < root.data) {
            root.left = bstInsert(root.left, newNode);
            root.left.parent = root;
        } else if (newNode.data > root.data) {
            root.right = bstInsert(root.right, newNode);
            root.right.parent = root;
        }
        return root;
    }

    // Fix red-black tree violations after insertion
    private void fixViolation(Node newNode) {
        Node parent, grandParent;
        while (newNode != root && newNode.color == RED && newNode.parent != null && newNode.parent.color == RED) {
            parent = newNode.parent;
            grandParent = parent.parent;

            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                // Case 1: Uncle is red
                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode = grandParent;
                } else {
                    // Case 2: Uncle is black, newNode is a right child
                    if (newNode == parent.right) {
                        leftRotate(parent);
                        newNode = parent;
                        parent = newNode.parent;
                    }
                    // Case 3: Uncle is black, newNode is a left child
                    rightRotate(grandParent);
                    boolean temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    newNode = parent;
                }
            } else { // Symmetrical cases for when parent is a right child
                Node uncle = grandParent.left;

                // Case 1: Uncle is red
                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode = grandParent;
                } else {
                    // Case 2: Uncle is black, newNode is a left child
                    if (newNode == parent.left) {
                        rightRotate(parent);
                        newNode = parent;
                        parent = newNode.parent;
                    }
                    // Case 3: Uncle is black, newNode is a right child
                    leftRotate(grandParent);
                    boolean temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    newNode = parent;
                }
            }
        }
        root.color = BLACK;
    }

    // In-order traversal
    public void inOrderTraversal(Node node) {
        if(node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    // Display the tree (wrapper for in-order traversal)
    public void displayTree() {
        System.out.println("In-order Traversal of Tree: ");
        inOrderTraversal(root);
        System.out.println();
    }

    // Getter for root (for traversal purposes)
    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Choose mode: ");
        System.out.println("1 - Add random numbers\n2 - Add ordered numbers\n3 - Enter own numbers");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: // Random numbers
                System.out.print("Number of elements: ");
                int count = scanner.nextInt();
                System.out.println("Added elems: ");
                for (int i = 0; i < count; ++i) {
                    int num = random.nextInt(100);
                    System.out.print(num + " ");
                    tree.insert(num);
                }
                System.out.println();
                break;
            case 2: // Sorted numbers
                System.out.print("Number of elements: ");
                count = scanner.nextInt();
                System.out.println("Added elems: ");
                int[] sorted = new int[count];
                for (int i = 0; i < count; ++i) {
                    sorted[i] = random.nextInt(100);
                }
                Arrays.sort(sorted);
                System.out.println("Added elems: ");
                for (int num : sorted) {
                    System.out.print(num + " ");
                    tree.insert(num);
                }
                System.out.println();
                break;
            case 3: // User input
                System.out.println("Enter numbers (space separated): ");
                scanner.nextLine();
                String[] inputs = scanner.nextLine().split(" ");
                for (String input : inputs) {
                    int num = Integer.parseInt(input);
                    tree.insert(num);
                }
                break;
            default:
                System.out.println("Wrong choice");
                return;
        }
        tree.displayTree();
    }
}




