package com.company;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class Server implements Interface {
    public Server() {}
    // реализация интерфейса
    public String sayHello(String str) {
        String factor = "AaEeIiOoUuYyАаОоИиЕеЁёЭэЫыУуЮюЯя";
        char[] c_factor = factor.toCharArray();
        char[] c_str = str.toCharArray();
        String final_s = "result: ";
        for(int i = 0; i < c_str.length; i++)
        {
            final_s += String.valueOf(c_str[i]);
            for(int j = 0; j < c_factor.length; j++)
            {
                if((Character.compare(c_str[i], c_factor[j]) == 0) && (i != c_str.length - 1))
                {
                    for (int k = 0; k < c_factor.length; k++)
                    {
                        if(Character.compare(c_str[i + 1], c_factor[k]) == 0)
                        {
                            final_s += String.valueOf(' ');
                            break;
                        }
                    }
                }
            }
        }
        return final_s;
    }
    public static void main(String args[]) {
        try {
// создаем и экспортируем удаленный объект
            Server obj = new Server();
            Interface stub =(Interface) UnicastRemoteObject.exportObject(obj, 0);
// Регистрируем удаленный объект в RMI-регистраторе под именем
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
