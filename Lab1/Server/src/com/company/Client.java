package com.company;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
// Получаем стаб регистратора с хоста, определенного в командной строке
// если в командной строке хост не указывается, то он считается как localhost
            Registry registry = LocateRegistry.getRegistry(host);
// получаем стаб удаленного объекта от регистратора сервера
            Interface stub = (Interface) registry.lookup("Hello");
            Scanner in = new Scanner(System.in);
            while(true) {
                System.out.print("Input string (0 to exit): ");
                String str = in.nextLine();
                if(str.equals("0"))
                {
                    break;
                }
                String response = stub.sayHello(str);
                byte bytes[] = response.getBytes("ISO-8859-1");
                String value = new String(bytes, "UTF-8");
                System.out.println(value);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

