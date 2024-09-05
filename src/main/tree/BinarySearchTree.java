package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    private Node root;

    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    // Método para inserir um elemento na árvore
    public void insereElemento(int valor) {
        root = insereRecursivo(root, valor);
    }

    private Node insereRecursivo(Node root, int valor) {
        if (root == null) {
            root = new Node(valor);
            return root;
        }

        if (valor < root.value) {
            root.left = insereRecursivo(root.left, valor);
        } else if (valor > root.value) {
            root.right = insereRecursivo(root.right, valor);
        }

        return root;
    }

    // Método para remover um elemento da árvore
    public void remove(int valor) {
        root = removeRecursivo(root, valor);
    }

    private Node removeRecursivo(Node root, int valor) {
        if (root == null) {
            return null;
        }

        if (valor < root.value) {
            root.left = removeRecursivo(root.left, valor);
        } else if (valor > root.value) {
            root.right = removeRecursivo(root.right, valor);
        } else {
            // Nó com um ou nenhum filho
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Nó com dois filhos: pegar o menor no lado direito (sucessor)
            root.value = minValor(root.right);
            root.right = removeRecursivo(root.right, root.value);
        }

        return root;
    }

    private int minValor(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    // Método de busca de elemento
    public boolean buscaElemento(int valor) {
        return buscaRecursiva(root, valor);
    }

    private boolean buscaRecursiva(Node root, int valor) {
        if (root == null) {
            return false;
        }
        if (root.value == valor) {
            return true;
        }
        return valor < root.value ? buscaRecursiva(root.left, valor) : buscaRecursiva(root.right, valor);
    }

    // Métodos para realizar as travessias (em ordem, pré-ordem, pós-ordem)

    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRecursiva(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdemRecursiva(Node root, List<Integer> result) {
        if (root != null) {
            emOrdemRecursiva(root.left, result);
            result.add(root.value);
            emOrdemRecursiva(root.right, result);
        }
    }

    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRecursiva(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdemRecursiva(Node root, List<Integer> result) {
        if (root != null) {
            result.add(root.value);
            preOrdemRecursiva(root.left, result);
            preOrdemRecursiva(root.right, result);
        }
    }

    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRecursiva(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdemRecursiva(Node root, List<Integer> result) {
        if (root != null) {
            posOrdemRecursiva(root.left, result);
            posOrdemRecursiva(root.right, result);
            result.add(root.value);
        }
    }
}
