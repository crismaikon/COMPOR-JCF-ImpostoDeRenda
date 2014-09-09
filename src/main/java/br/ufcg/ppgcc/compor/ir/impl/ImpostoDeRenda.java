package br.ufcg.ppgcc.compor.ir.impl;

import java.util.*;

import br.ufcg.ppgcc.compor.ir.*;

public class ImpostoDeRenda implements FachadaExperimento {

	private List<Titular> titulares = new ArrayList<Titular>();
	private List<FontePagadora> fontes = new ArrayList<FontePagadora>();
	private Map<Titular,List<FontePagadora>> fontePagadoras = new HashMap<Titular,List<FontePagadora>>();

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

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		
		if (fontePagadoras.containsKey(titular)){
			List<FontePagadora> font = fontePagadoras.get(titular);
			font.add(fonte);
			
		}
		fontes.add(fonte);
		fontePagadoras.put(titular, fontes);

	}

	public List<FontePagadora> listarFontes(Titular titular) {

		return fontes;
	}

}
