package com.bank.DB;

import java.io.*;

public class FileManager {
    public int write_object(DB db){
        try (FileOutputStream fos = new FileOutputStream("src/main/java/com/bank/Files/db.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(db);
            return 1;

        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public DB read_object(){
        try (FileInputStream fis = new FileInputStream("src/main/java/com/bank/Files/db.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            DB db = (DB) ois.readObject();
            return db;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            DB db = new DB();
            int result = this.write_object(db);

            return db;

        }
    }
}
