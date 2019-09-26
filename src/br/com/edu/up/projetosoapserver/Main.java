package br.com.edu.up.projetosoapserver;

import javax.xml.ws.Endpoint;

public class Main {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/Cep", new CepService());
		System.out.println("Aguardando Conexão.");
	}

}
