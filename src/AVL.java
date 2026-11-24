/**
 * Implementação de Árvore AVL (Adelson-Velsky e Landis).
 * Uma árvore binária de busca auto-balanceada onde a diferença
 * entre as alturas das subárvores esquerda e direita (fator de balanceamento)
 * de qualquer nó não ultrapassa 1 ou -1.
 */
public class AVL {
    /**
     * Estrutura do nó da árvore AVL.
     * A altura é guardada diretamente em cada nó (com valor inicial 1 para folhas recém-criadas).
     * Essa decisão de design é fundamental: evita ter que recalcular a altura
     * recursivamente do topo à base a cada verificação, permitindo obter e checar
     * o fator de balanceamento em tempo constante O(1).
     */
    static class No {
        int valor;
        int altura = 1;   // altura de uma folha é 1
        No esq, dir;

        No(int v) {
            valor = v;
        }
    }

    private No raiz;

    // Retorna a altura de um nó (0 se o nó for nulo)
    private int altura(No n) {
        return n == null ? 0 : n.altura;
    }

    // Recalcula a altura do nó a partir do máximo entre as alturas dos filhos + 1
    private void atualizar(No n) {
        n.altura = 1 + Math.max(altura(n.esq), altura(n.dir));
    }

    // Fator de balanceamento = altura(esq) - altura(dir)
    private int fb(No n) {
        return n == null ? 0 : altura(n.esq) - altura(n.dir);
    }

    /**
     * Rotação Simples à Direita (Caso Esq-Esq):
     * Aplicada quando o desbalanceamento ocorre na subárvore esquerda do filho esquerdo.
     * O filho esquerdo 'x' sobe para ser a nova raiz da subárvore e 'y' desce para a direita.
     * Ordem de atualização das alturas:
     * 'y' desceu e virou filho de 'x', então a altura de 'y' deve ser recalculada PRIMEIRO.
     * Em seguida, recalcula-se a altura de 'x' com base nos seus novos filhos (incluindo 'y').
     */
    private No rotacaoDireita(No y) {
        No x = y.esq;
        y.esq = x.dir;
        x.dir = y;
        atualizar(y);   // y desceu: atualiza primeiro
        atualizar(x);   // x é a nova raiz: atualiza depois
        return x;       // x é a nova raiz da subárvore
    }

    /**
     * Rotação Simples à Esquerda (Caso Dir-Dir):
     * Aplicada quando o desbalanceamento ocorre na subárvore direita do filho direito.
     * O filho direito 'y' sobe para ser a nova raiz da subárvore e 'x' desce para a esquerda.
     * Ordem de atualização:
     * 'x' desceu: atualiza primeiro 'x' e depois 'y'.
     */
    private No rotacaoEsquerda(No x) {
        No y = x.dir;
        x.dir = y.esq;
        y.esq = x;
        atualizar(x);   // x desceu: atualiza primeiro
        atualizar(y);   // y é a nova raiz: atualiza depois
        return y;
    }

    public void inserir(int v) {
        raiz = inserir(raiz, v);
    }

    /**
     * Inserção com rebalanceamento:
     * 1. Insere recursivamente conforme a regra de BST padrão.
     * 2. Na volta da recursão (subindo da folha até a raiz):
     *    - Atualiza a altura do nó atual.
     *    - Calcula o fator de balanceamento (fb).
     *    - Se |fb| > 1, aplica a rotação adequada imediatamente no nó desbalanceado mais profundo.
     */
    private No inserir(No n, int v) {
        if (n == null) return new No(v);
        if (v < n.valor)      n.esq = inserir(n.esq, v);
        else if (v > n.valor) n.dir = inserir(n.dir, v);
        else return n;                       // ignora duplicados

        // Rebalanceamento na volta da recursão
        atualizar(n);
        int bal = fb(n);

        // Caso Esq-Esq -> Rotação Simples à Direita
        if (bal > 1 && v < n.esq.valor)  return rotacaoDireita(n);

        // Caso Dir-Dir -> Rotação Simples à Esquerda
        if (bal < -1 && v > n.dir.valor) return rotacaoEsquerda(n);

        // Caso Esq-Dir -> Rotação Dupla à Direita (Esquerda no filho esquerdo, Direita no nó)
        if (bal > 1 && v > n.esq.valor) {
            n.esq = rotacaoEsquerda(n.esq);
            return rotacaoDireita(n);
        }

        // Caso Dir-Esq -> Rotação Dupla à Esquerda (Direita no filho direito, Esquerda no nó)
        if (bal < -1 && v < n.dir.valor) {
            n.dir = rotacaoDireita(n.dir);
            return rotacaoEsquerda(n);
        }

        return n;
    }

    public int altura() { return altura(raiz); }
    public int raiz() { return raiz.valor; }

    public void emOrdem() { emOrdem(raiz); System.out.println(); }
    private void emOrdem(No n) {
        if (n == null) return;
        emOrdem(n.esq);
        System.out.print(n.valor + " ");
        emOrdem(n.dir);
    }
}
