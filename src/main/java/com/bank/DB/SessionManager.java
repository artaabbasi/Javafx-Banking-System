package com.bank.DB;

import java.io.*;

public class SessionManager {
    public int write_object(Session session){
        try (FileOutputStream fos = new FileOutputStream("src/main/java/com/bank/Files/session.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(session);
            return 1;

        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public Session read_object(){
        try (FileInputStream fis = new FileInputStream("src/main/java/com/bank/Files/session.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Session session = (Session) ois.readObject();
            return session;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            Session session = new Session();
            int result = this.write_object(session);
            System.out.println(result);
            return session;

        }
    }
}
