package edu.curso;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/CalcularIMC")
//  /CalcularIMC?PESO=70&ALTURA=165 -> PARA RECEBER PARAMETROS NA PAGINA
public class CalculadoraIMC extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String strPeso = req.getParameter("PESO");
		String strAltura = req.getParameter("ALTURA");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		int peso = 0, altura = 0;
		String mensagem = "", info ="";
		double imc = 0.0;
			try {
				 peso = Integer.parseInt(strPeso);
				 altura = Integer.parseInt(strAltura);
				 imc = peso / (Math.pow((double) altura / 100.0,2));
				 mensagem = String.format("<p>Seu IMC é: %6.2f</p>%n", imc);
				 
			} catch (Exception e) {
				mensagem = "<p>Erro ao calcular o IMC</p>";
			}
			if(imc < 18.5) {
				info = "Abaixo do peso";
			}else if(imc >= 18.5 && imc < 24.9) {
				info ="Peso normal";
			}else if(imc >= 25.0 && imc < 29.9) {
				info ="Sobrepeso";
			}else if(imc >= 30.0 && imc < 34.9) {
				info ="Obesidade Grau I";
			}else if(imc >= 35.0 && imc < 39.9) {
				out.println("Obesidade Grau II");
			}else {
				info ="Obesidade Grau III";
			}
			
			out.println("<h2>Calculadora de IMC</h2>");
			out.println(mensagem);
			out.println(info);
		out.flush();
	}

}
