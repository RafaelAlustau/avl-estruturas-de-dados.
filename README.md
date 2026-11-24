# avl-estruturas-de-dados.
# Árvores AVL em Java

> **Trabalho de Estruturas de Dados 2 — Unipê — Prof. Ed**

### Integrantes
- Pedro Henrique Neves Vitorino
- Rafael Ribeiro de Alustau

---

## Sobre

Este projeto apresenta a implementação prática de uma **Árvore AVL** (*Adelson-Velsky e Landis*) em Java, comparando seu comportamento e desempenho com uma **Árvore Binária de Busca convencional (BST)**.

Uma Árvore AVL é uma árvore binária de busca auto-balanceada. A sua principal invariante é que a diferença entre a altura da subárvore esquerda e da subárvore direita (conhecida como **fator de balanceamento**) de qualquer nó deve pertencer ao conjunto \(\{-1, 0, 1\}\).

Para garantir essa propriedade durante as inserções, o algoritmo recalcula as alturas na volta da recursão e executa operações de rotação quando identifica desbalanceamento (\(|fb| > 1\)):
1. **Rotação Simples à Direita (Esq-Esq):** aplicada quando a inserção desbalanceia o filho esquerdo para a esquerda.
2. **Rotação Simples à Esquerda (Dir-Dir):** aplicada quando a inserção desbalanceia o filho direito para a direita.
3. **Rotação Dupla à Direita (Esq-Dir):** rotação à esquerda no filho esquerdo seguida de rotação à direita no nó desbalanceado.
4. **Rotação Dupla à Esquerda (Dir-Esq):** rotação à direita no filho direito seguida de rotação à esquerda no nó desbalanceado.

A altura de cada nó é armazenada no próprio objeto `No`, permitindo calcular o fator de balanceamento em tempo constante \(O(1)\).

---

## Vídeo
[Link do vídeo](COLE_O_LINK_AQUI)

---

## Como compilar e executar

### Pré-requisitos
- JDK 11 ou superior instalado (`javac` e `java` no PATH).

### Passos
Acesse o diretório de código-fonte e execute:

```bash
cd src
javac *.java
java Main
```

---

## O que o programa mostra

O programa executa duas demonstrações principais:
1. **Demo com valores (10, 20, 30, 40, 50):** demonstração de rotações sucessivas, mostrando como a raiz se ajusta para `20`, mantendo a altura da AVL em apenas `3` (enquanto a BST atinge altura `5`), finalizando com a impressão do percurso em ordem.
2. **Batalha (inserção sequencial de 1 a 1000):** caso patológico onde dados ordenados transformam a BST clássica em uma lista ligada de altura `1000`, enquanto a AVL auto-balanceia a estrutura, mantendo a altura em apenas `10`.

### Saída de Execução Real:
```text
=== Demo: 10, 20, 30, 40, 50 ===
Raiz da AVL: 20
Altura BST: 5
Altura AVL: 3
Em ordem (AVL): 10 20 30 40 50 

=== Batalha: 1 a 1000 em ordem ===
Altura BST: 1000
Altura AVL: 10
```

---

## Complexidade

| Operação | BST Padrão (Pior Caso) | Árvore AVL (Pior Caso) |
| :--- | :---: | :---: |
| **Busca** | \(O(n)\) | \(O(\log n)\) |
| **Inserção** | \(O(n)\) | \(O(\log n)\) |
| **Remoção** | \(O(n)\) | \(O(\log n)\) |
| **Altura da Árvore** | \(O(n)\) *(degenerada em lista)* | \(O(\log n)\) *(estritamente balanceada)* |

Na inserção com dados sequenciais (ordenados), a BST não realiza balanceamento, resultando em complexidade linear \(O(n)\) para buscas e inserções subsequentes. Já a AVL garante altura logarítmica \(O(\log n)\) mesmo nos piores cenários de inserção através de suas rotações locais em tempo \(O(1)\).
