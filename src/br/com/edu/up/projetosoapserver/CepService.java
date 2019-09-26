package br.com.edu.up.projetosoapserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.google.gson.Gson;

@WebService
public class CepService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL = "https://viacep.com.br/ws/";

	@WebMethod(operationName = "obter_endereco_por_cep")
	@WebResult(name = "json_endereco")
	public String obterEnderecoPorCep(@WebParam(name = "objeto_cep") CepObject obj) {
		URL objURL;

		try {
			String url = URL + obj.getCodigoPostal() + "/json/";
			objURL = new URL(url);
			HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(2000);// Set do timeout é feito em segundos
			con.setReadTimeout(3000);// Set do timeout é feito em segundos

			BufferedReader in;

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder aux = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				aux.append(inputLine);
			}

			Gson gson = new Gson();

			return gson.toJson(aux);

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	/*
	 * public static void main(String[] args) { CepService c = new CepService();
	 * CepObject obj = new CepObject(); obj.setCodigoPostal("81020120");
	 * c.obterEnderecoPorCep(obj); }
	 */

}
