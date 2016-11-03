package com.r3sist3nt.GoRust.CLIPrint;

/**
 * Created by Cronixx on 03.11.2016.
 * <p>
 * CLIP 0.0.1
 * <p>
 * Das Teil hier soll zuständig sein um alle Ausgaben in die Kommandozeile zu machen,
 * so dass in den Klassen selbst eine Zeile reicht um schön formatierte Ausgaben zu bekommen.
 * <p>
 * Verschiedene Methoden für verschiedene Einsatzzwecke, die ein einheitliches Layout in sich vereinen.
 * <p>
 * TODO:
 *      - Formate für einzelne Typen, die modular kombiniert werden sollen
 *      - Automatische Identifizierung des entsprechenden Formates
 *      - Das ganze irgendwann zu ner DataPrint Lib ausbauen!
 */
public class Printer {
    private String WELCOMEVERSION = " GoRust - Best online Rust server scanner #canihazcoffe? ";
    private short SPACELEN = 10;
    private char LINECHAR = '-';

    public Printer() {
        printFormattedString(WELCOMEVERSION, '^');
        System.out.println("");
        System.out.println("");
    }

    /**
     * Wrapper for private Method
     * Prints a list of strings in a nicely manner. ;)
     *
     * @param list --< List of Strings, each Element is a new line
     */
    public void printData(String[] list) {
        printListOfStrings(list);
    }

    /**
     * Wrapper for private Method
     *
     * @param s --> input String
     */
    public void printData(String s) {
        printFormattedString(s);
    }

    /**
     * Prints a list of strings in center format,
     * with a linelength based on the length of the longest element.
     *
     * @param input --> List of Strings, each should be a new Line
     */
    private void printListOfStrings(String[] input) {
        int lengthOfLongestItem = 0;
        for (String s : input) {
            if (s.length() > lengthOfLongestItem) {
                lengthOfLongestItem = s.length();
            }
        }
        if (lengthOfLongestItem == 0) {
            System.out.println("Illegal Input, you criminal!");
            return;
        }
        int lineLength = lengthOfLongestItem + SPACELEN;
        printLineLn(lineLength);
        for (String s : input) {
            printFormattedString(s, lineLength);
        }
        printLineLn(lineLength);
    }

    private void printLine(int n, char c) {
        int i = 0;
        while (i < n) {
            System.out.print(c);
            i++;
        }
    }

    private void printLineLn(int n, char c) {
        int i = 0;
        while (i < n - 1) {
            System.out.print(c);
            i++;
        }
        System.out.println(c);
    }

    private void printLine(int n) {
        int i = 0;
        while (i < n) {
            System.out.print(LINECHAR);
            i++;
        }
    }

    private void printLineLn(int n) {
        int i = 0;
        while (i < n - 1) {
            System.out.print(LINECHAR);
            i++;
        }
        System.out.println(LINECHAR);
    }

    private void printLine() {
        printLine(SPACELEN, LINECHAR);
    }

    private void printLineLn() {
        printLine(SPACELEN, LINECHAR);
        System.out.println("");
    }

    private void printFormattedString(String input) {
        printLine(SPACELEN + input.length() + SPACELEN);
        System.out.println("");
        printLine(SPACELEN);
        System.out.print(input);
        printLine(SPACELEN);
        System.out.println("");
        printLine(SPACELEN + input.length() + SPACELEN);
        System.out.println("");
    }

    private void printFormattedString(String input, int lineLength) {
        printLine((lineLength / 2) - (input.length() / 2), ' ');
        System.out.print(input);
        printLine(lineLength - (lineLength / 2) - (input.length() / 2) + input.length(), ' ');
        System.out.println("");
    }

    private void printFormattedString(String input, char c) {
        printLine(SPACELEN + input.length() + SPACELEN, c);
        System.out.println("");
        printLine(SPACELEN, c);
        System.out.print(input);
        printLine(SPACELEN, c);
        System.out.println("");
        printLine(SPACELEN + input.length() + SPACELEN, c);
        System.out.println("");
    }

    private void printFormattedString(String input, int lineLength, char c) {
        printLine((lineLength / 2) - (input.length() / 2), c);
        System.out.print(input);
        printLine(lineLength - (lineLength / 2) - (input.length() / 2) + input.length(), c);
        System.out.println("");
    }
}
