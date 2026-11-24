/**
 * Implementação de Árvore Binária de Busca (BST) convencional sem auto-balanceamento.
 * Utilizada como base comparativa para demonstrar a degeneração em casos ordenados
 * versus a eficiência da Árvore AVL.
 */
public class BST {
    private static class No {
        int valor;
        No esq, dir;
        No(int v) { valor = v; }
    }

    private No raiz;

    public void inserir(int v) {
        if (raiz == null) {
            raiz = new No(v);
            return;
        }
        No atual = raiz;
        while (true) {
            if (v < atual.valor) {
                if (atual.esq == null) { atual.esq = new No(v); return; }
                atual = atual.esq;
            } else if (v > atual.valor) {
                if (atual.dir == null) { atual.dir = new No(v); return; }
                atual = atual.dir;
            } else return;
        }
    }

    public int altura() { return altura(raiz); }

    private int altura(No n) {
        return n == null ? 0 : 1 + Math.max(altura(n.esq), altura(n.dir));
    }
}
