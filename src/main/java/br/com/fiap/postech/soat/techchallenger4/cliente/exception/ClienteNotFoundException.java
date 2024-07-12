package br.com.fiap.postech.soat.techchallenger4.cliente.exception;

public class ClienteNotFoundException extends Exception{

    public ClienteNotFoundException(){
        super("Cliente não encontrado");
    }
}
