package com.bank.DB;

import java.io.*;

public class Logger {
    private void open_file() throws IOException {
        File myObj = new File("src/main/java/com/bank/Files/bank.log");
    }

    public void logger(String message) throws IOException {
        try(FileWriter fw = new FileWriter("src/main/java/com/bank/Files/bank.log", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(message);
            out.println("****************************************************************************");
        } catch (IOException e) {
        }
    }
}
