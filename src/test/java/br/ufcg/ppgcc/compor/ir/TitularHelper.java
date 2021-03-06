package br.ufcg.ppgcc.compor.ir;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;

public class TitularHelper {

	static Titular criarTitularMinimo() {
		Titular titular = new Titular();
		titular.setNome("Jose");
		titular.setCpf("000.000.000-00");
		return titular;
	}
	
	static Titular criarTitularPadrao() {
		Titular titular = criarTitularMinimo();
		titular.setDataNascimento(Calendar.getInstance());
		titular.setTituloEleitoral("000");
		titular.setEndereco(criarEnderecoPadrao());
		titular.setNaturezaOcupacao(10);
		titular.setOcupacaoPrincipal(100);
		return titular;
	}

	static Titular criarTitularPadrao2() {
		Titular titular = criarTitularMinimo();
		titular.setDataNascimento(Calendar.getInstance());
		titular.setTituloEleitoral("0001");
		titular.setEndereco(criarEnderecoPadrao());
		titular.setNaturezaOcupacao(101);
		titular.setOcupacaoPrincipal(1001);
		return titular;
	}
	
	static Endereco criarEnderecoPadrao() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Log");
		endereco.setNumero(0);
		endereco.setComplemento("A");
		endereco.setBairro("Bai");
		endereco.setMunicipio("Mun");
		endereco.setEstado("Est");
		endereco.setCEP("00000-000");
		return endereco;
	}

	static void verificaCriacaoTitulares(FachadaExperimento fachada, Titular... titulares) {
		for (Titular titular : titulares) {
			fachada.criarNovoTitular(titular);
		}
	
		List<Titular> titularesSalvos = fachada.listarTitulares();
		assertEquals(titulares.length, titularesSalvos.size());
		
		for (int i = 0; i < titulares.length; i++) {
			assertEquals(titulares[i], titularesSalvos.get(i));
		}
		
	}

	static void excecaoCriarTitular(FachadaExperimento fachada, Titular titular, String mensagem) {
		try {
			fachada.criarNovoTitular(titular);
			Assert.fail("A criação de Titular deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), mensagem);
		}
	}
}
