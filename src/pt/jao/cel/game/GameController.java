package pt.jao.cel.game;

import java.util.Scanner;

public class GameController {
    TabController tabController = new TabController();
    public static String currentPlayer = "X";

    public void play() {
        Scanner scanner = new Scanner(System.in);

        tabController.clearTab();
        System.out.println("checkWin method returned " + checkWin(currentPlayer));
        System.out.println("isFull method returned " + tabController.isFull());

        while (!tabController.isFull() && !checkWin(currentPlayer)) {
            System.out.println("[" + currentPlayer + "] [COLUNA] Escolha uma coluna: ");
            int column = scanner.nextInt();
            System.out.println("[" + currentPlayer + "] [LINHA] Escolha uma linha: ");
            int row = scanner.nextInt();

            if (!tabController.move(row, column, currentPlayer)) {
                System.out.println("Posição inválida, escolha outra.");
            } else {
                tabController.displayTab();

                if (checkWin(currentPlayer)) {
                    System.out.println("O jogador " + currentPlayer + " ganhou!");
                } else if (tabController.isFull()) {
                    System.out.println("Empate!");
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }
        }
    }

    public boolean checkWin(String currentPlayer) {
        String[][] tab = tabController.tab;

        // Verificar linhas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5 - 4; j++) {
                if (    tab[i][j] == currentPlayer &&
                        tab[i][j + 1] == currentPlayer &&
                        tab[i][j + 2] == currentPlayer &&
                        tab[i][j + 3] == currentPlayer &&
                        tab[i][j + 4] == currentPlayer) {
                    return true;
                }
            }
        }

        // Verificar Colunas
        for (int i = 0; i < 5 - 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (    tab[i][j] == currentPlayer &&
                        tab[i + 1][j] == currentPlayer &&
                        tab[i + 2][j] == currentPlayer &&
                        tab[i + 3][j] == currentPlayer &&
                        tab[i + 4][j] == currentPlayer) {
                    return true;
                }
            }
        }

        // Verificar Diagonais
        for (int i = 0; i < 5 - 4; i++) {
            for (int j = 0; j < 5 - 4; j++) {
                if (    tab[i][j] == currentPlayer &&
                        tab[i + 1][j + 1] == currentPlayer &&
                        tab[i + 2][j + 2] == currentPlayer &&
                        tab[i + 3][j + 3] == currentPlayer &&
                        tab[i + 4][j + 4] == currentPlayer) {
                    return true;
                }
            }
        }

        for (int i = 4; i < 5; i++) {
            for (int j = 0; j < 5 - 4; j++) {
                if (    tab[i][j] == currentPlayer &&
                        tab[i - 1][j + 1] == currentPlayer &&
                        tab[i - 2][j + 2] == currentPlayer &&
                        tab[i - 3][j + 3] == currentPlayer &&
                        tab[i - 4][j + 4] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }
}