import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int datenGroesse = 100;
        int[] datenArray = generiereZufallsArray(datenGroesse);
        int[] pruefSequenz = liesPruefSequenzVomBenutzer();

        fuegePruefSequenzEin(datenArray, pruefSequenz);
        int gefundenePosition = findePruefSequenz(datenArray, pruefSequenz);

        System.out.println("Prüffolge: " + Arrays.toString(pruefSequenz));
        if (gefundenePosition != -1) {
            System.out.println("Prüffolge wurde an Position " + gefundenePosition + " gefunden.");
        } else {
            System.out.println("Prüffolge wurde nicht gefunden.");
        }
    }

    private static int[] generiereZufallsArray(int groesse) {
        Random random = new Random();
        int[] array = new int[groesse];
        for (int i = 0; i < groesse; i++) {
            array[i] = random.nextInt(100); // Zufallszahlen zwischen 0 und 99
        }
        return array;
    }

    private static int[] liesPruefSequenzVomBenutzer() {
        Scanner scanner = new Scanner(System.in);
        int[] pruefSequenz = new int[4];
        while (true) {
            System.out.println("Bitte geben Sie vier Ganzzahlen für die Prüffolge ein (getrennt durch Leerzeichen):");
            String input = scanner.nextLine();
            String[] teile = input.split("\\s+");
            if (teile.length == 4) {
                try {
                    for (int i = 0; i < 4; i++) {
                        pruefSequenz[i] = Integer.parseInt(teile[i]);
                    }
                    break; // Gültige Eingabe, Schleife verlassen
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie vier Ganzzahlen ein.");
                }
            } else {
                System.out.println("Bitte geben Sie genau vier Zahlen ein.");
            }
        }
        return pruefSequenz;
    }

    private static void fuegePruefSequenzEin(int[] datenArray, int[] pruefSequenz) {
        Random random = new Random();
        int einfuegen = random.nextInt(datenArray.length - pruefSequenz.length + 1);
        for (int i = 0; i < pruefSequenz.length; i++) {
            datenArray[einfuegen + i] = pruefSequenz[i];
        }
    }

    private static int findePruefSequenz(int[] datenArray, int[] pruefSequenz) {
        for (int i = 0; i <= datenArray.length - pruefSequenz.length; i++) {
            boolean sequenzPasst = true;
            for (int j = 0; j < pruefSequenz.length; j++) {
                if (datenArray[i + j] != pruefSequenz[j]) {
                    sequenzPasst = false;
                    break;
                }
            }
            if (sequenzPasst) {
                return i; // Position der gefundenen Sequenz zurückgeben
            }
        }
        return -1; // -1 bedeutet nicht gefunden
    }
}
