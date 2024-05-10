package com.company;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Interface extends Remote {
    String sayHello(String str) throws RemoteException;
}

