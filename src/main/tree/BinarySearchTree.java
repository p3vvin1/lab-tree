package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    private Node root;

    private class Node {
        int value, height;
        Node left, right;

        Node(int value) {
            this.value = value;
            height = 1; 
            left = right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    private int getAltura(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getAltura(node.left) - getAltura(node.right);
    }

    public void insereElemento(int valor) {
        root = insereRecursivo(root, valor);
    }

    private Node insereRecursivo(Node root, int valor) {
        if (root == null) {
            return new Node(valor);
        }

        if (valor < root.value) {
            root.left = insereRecursivo(root.left, valor);
        } else if (valor > root.value) {
            root.right = insereRecursivo(root.right, valor);
        } else {
            return root; 
        }

        root.height = 1 + Math.max(getAltura(root.left), getAltura(root.right));
        
        int balance = getBalance(root);

        if (balance > 1 && valor < root.left.value) {
            return rotacaoDireita(root);
        }

        if (balance < -1 && valor > root.right.value) {
            return rotacaoEsquerda(root);
        }

        if (balance > 1 && valor > root.left.value) {
            root.left = rotacaoEsquerda(root.left);
            return rotacaoDireita(root);
        }

        if (balance < -1 && valor < root.right.value) {
            root.right = rotacaoDireita(root.right);
            return rotacaoEsquerda(root);
        }

        return root;
    }

    
    private Node rotacaoDireita(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getAltura(y.left), getAltura(y.right)) + 1;
        x.height = Math.max(getAltura(x.left), getAltura(x.right)) + 1;

        return x;
    }
    private Node rotacaoEsquerda(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;
        x.height = Math.max(getAltura(x.left), getAltura(x.right)) + 1;
        y.height = Math.max(getAltura(y.left), getAltura(y.right)) + 1;

        return y;
    }
}