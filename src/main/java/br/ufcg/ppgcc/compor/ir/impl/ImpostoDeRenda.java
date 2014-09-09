package br.ufcg.ppgcc.compor.ir.impl;

import java.util.*;

import br.ufcg.ppgcc.compor.ir.*;

public class ImpostoDeRenda implements FachadaExperimento {

	private List<Titular> titulares = new ArrayList<Titular>();
	private List<FontePagadora> fontePagadores = new ArrayList<FontePagadora>();
	
	private Map<Titular, FontePagadora> fontePagadoras = new HashMap<Titular, FontePagadora>();

	public void criarNovoTitular(Titular titular) {
		if (titular.getNome() == null) {
				throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if (titular.getCpf() == null) {
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
		}

		if (titular.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false) {
			throw new ExcecaoImpostoDeRenda("O campo CPF está inválido");
		}

		titulares.add(titular);
	}

	public List<Titular> listarTitulares() {

		return titulares;
	}

	public boolean verificaCpfInvalido(Titular titular) {
		for (Titular a : titulares) {
			if (a.getCpf() == titular.getCpf()) {
				return true;
			}
		}
		return false;
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		fontePagadoras.put(titular, fonte);
		fontePagadores.add(fonte);

	}

	public List<FontePagadora> listarFontes(Titular titular) {

		return fontePagadores;
	}

}
