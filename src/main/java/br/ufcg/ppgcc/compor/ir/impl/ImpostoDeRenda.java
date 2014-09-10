package br.ufcg.ppgcc.compor.ir.impl;

import java.util.*;

import br.ufcg.ppgcc.compor.ir.*;

public class ImpostoDeRenda implements FachadaExperimento {

	private List<Titular> titulares = new ArrayList<Titular>();
	private Map<Titular, List<FontePagadora>> fontePagadoras = new HashMap<Titular, List<FontePagadora>>();
	private Map<Titular, List<Dependente>> dependentes = new HashMap<Titular, List<Dependente>>();
	
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
		fontePagadoras.put(titular, new ArrayList<FontePagadora>());
		dependentes.put(titular, new ArrayList<Dependente>());
	}

	public List<Titular> listarTitulares() {

		return titulares;
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		String cnpj = "\\d\\d.\\d\\d\\d.\\d\\d\\d"+"/"+"\\d\\d\\d\\d-\\d\\d";
	
		if(fonte.getNome() == null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if (fonte.getCpfCnpj()==null) {
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é obrigatório");
		}
		if (fonte.getRendimentoRecebidos()==0) {
			throw new ExcecaoImpostoDeRenda("O campo rendimentos recebidos é obrigatório");
		}
		if (fonte.getRendimentoRecebidos() <= 0) {
			throw new ExcecaoImpostoDeRenda("O campo rendimentos recebidos deve ser maior que zero");
			
		}
		if(fonte.getCpfCnpj().matches(cnpj)==false){
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é inválido");
		}
		if(fontePagadoras.containsKey(titular) == false){
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
				
		
		if(fontePagadoras.containsKey(titular)) {
			List<FontePagadora> listFont = fontePagadoras.get(titular);
			listFont.add(fonte);
		}
		
		
	}

	public List<FontePagadora> listarFontes(Titular titular) {
		return fontePagadoras.get(titular);
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		
		if(dependente.getCpf()==null){
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
		}
		if(dependente.getNome()==null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if(dependente.getTipo()==0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é obrigatório");
		}
		if(dependente.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false){
			throw new ExcecaoImpostoDeRenda("O campo CPF é inválido");
		}
		if(dependente.getTipo() <= 0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é inválido");
		}
		if(dependentes.containsKey(titular) == false){
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
		
		if(dependentes.containsKey(titular)){
			List<Dependente> listDependente = dependentes.get(titular);
			listDependente.add(dependente);
		}
		
		
		
		
		
		
	}

	public List<Dependente> listarDependentes(Titular titular) {
		return dependentes.get(titular);
	}

}
