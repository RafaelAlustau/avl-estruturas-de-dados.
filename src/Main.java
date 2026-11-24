public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demo: 10, 20, 30, 40, 50 ===");
        AVL avl = new AVL();
        BST bst = new BST();
        for (int v = 10; v <= 50; v += 10) { avl.inserir(v); bst.inserir(v); }
        System.out.println("Raiz da AVL: " + avl.raiz());
        System.out.println("Altura BST: " + bst.altura());
        System.out.println("Altura AVL: " + avl.altura());
        System.out.print("Em ordem (AVL): ");
        avl.emOrdem();

        System.out.println("\n=== Batalha: 1 a 1000 em ordem ===");
        AVL avl2 = new AVL();
        BST bst2 = new BST();
        for (int i = 1; i <= 1000; i++) { avl2.inserir(i); bst2.inserir(i); }
        System.out.println("Altura BST: " + bst2.altura());
        System.out.println("Altura AVL: " + avl2.altura());
    }
}
