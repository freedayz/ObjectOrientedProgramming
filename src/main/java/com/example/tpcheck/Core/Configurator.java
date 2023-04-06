package com.example.tpcheck.Core;

import java.io.*;
import java.util.ArrayList;

public class Configurator {
    private final String fileName = "config.dat";
    private final ArrayList<Node> nodesToPush = new ArrayList<>();
    private ArrayList<Node> nodesToPull = new ArrayList<>();
    private Node n;
    private FileOutputStream fileOutput;

    private ObjectOutputStream objOutput;
    private FileInputStream fileInput;
    private ObjectInputStream objInput;
    private static Configurator instance;

    private Configurator() {

    }

    public void CommitNode(String name, String value) {
        this.n = new Node(name, value);
        nodesToPush.add(n);
    }

    public static Configurator getInstance() {
        if (instance == null) {
            instance = new Configurator();
        }

        return instance;
    }

    public void Push() {
        try {
            this.fileOutput = new FileOutputStream(fileName);
            this.objOutput = new ObjectOutputStream(fileOutput);
            objOutput.writeObject(nodesToPush);
            nodesToPull.clear();
        } catch (Exception e) {
            System.err.println("Error - " + e);
        }
    }

    public void Pull() {
        if (Exists()) {
            try {
                this.fileInput = new FileInputStream(fileName);
                this.objInput = new ObjectInputStream(fileInput);
                this.nodesToPull = (ArrayList) objInput.readObject();

                fileInput.close();
                objInput.close();
            }
            catch (Exception e) {
                System.out.println("Error occured while pulling from config file.");
            }
        }
    }

    public String Get(String name) {
        if (!nodesToPull.isEmpty() && Exists()) {
            for (Node n : nodesToPull) {
                if (n.getName().equals(name)) {
                    return n.getValue();
                }
            }
        }
        return null;
    }

    public boolean FoundNodes(String[] values) {
        boolean empty = false;
        if (Exists()) {
            for (String value : values) {
                if (Get(value) == null) {
                    empty = true;
                    break;
                }
            }
        }

        return empty;
    }

    public boolean Exists() {
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }

    public boolean Destroy() {
        File fileToDestroy = new File(fileName);
        return fileToDestroy.delete();
    }
}
