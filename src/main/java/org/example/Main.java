package org.example;


import org.example.panels.StartPanel;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        StartPanel.panel();
        StartPanel.select();
    }
}