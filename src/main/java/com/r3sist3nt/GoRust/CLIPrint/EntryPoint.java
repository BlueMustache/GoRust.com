package com.r3sist3nt.GoRust.CLIPrint;

/**
 * Created by Cronixx on 03.11.2016.
 *
 * Just for debugging and demonstration purposes.
 */
public class EntryPoint {

    public static void main(String[] args) {
        String[] input = {  "Lorem ipsum dolor sit amet,",
                            "consetetur sadipscing elitr,",
                            "sed diam nonumy eirmod",
                            "tempor invidunt ut labore et dolore magna aliquyam erat,",
                            "sed diam voluptua."};

        Printer printer = new Printer();
        printer.printData(input);
    }

}
