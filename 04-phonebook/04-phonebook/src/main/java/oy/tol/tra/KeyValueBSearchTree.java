package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value cannot be null.");
        }

        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        } else {
            TreeNode<K, V> newNode = new TreeNode<>(key, value);
            root = insertAndBalance(root, newNode);
            count++;
            return true;
        }
    }

    private TreeNode<K, V> insertAndBalance(TreeNode<K, V> node, TreeNode<K, V> newNode) {
        if (node == null) {
            return newNode;
        }

        int cmp = newNode.key.compareTo(node.key);

        if (cmp < 0) {
            node.left = insertAndBalance(node.left, newNode);
        } else if (cmp > 0) {
            node.right = insertAndBalance(node.right, newNode);
        } else {
            // Duplicate key, update the value
            node.value = newNode.value;
            count--; // Adjust count as we are not adding a new node
            return node;
        }

        int balance = getBalance(node);

        // Left heavy
        if (balance > 1 && newNode.key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        // Right heavy
        if (balance < -1 && newNode.key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        // Left Right case
        if (balance > 1 && newNode.key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left case
        if (balance < -1 && newNode.key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getHeight(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int getBalance(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private TreeNode<K, V> rightRotate(TreeNode<K, V> y) {
        TreeNode<K, V> x = y.left;
        TreeNode<K, V> T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    private TreeNode<K, V> leftRotate(TreeNode<K, V> x) {
        TreeNode<K, V> y = x.right;
        TreeNode<K, V> T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }
}