package br.ufcg.ppgcc.compor.ir.impl;
import java.util.*;

import br.ufcg.ppgcc.compor.ir.*;

public class ImpostoDeRenda implements FachadaExperimento {

	private List<Titular> titulares = new ArrayList<Titular>();
	
	
	public void criarNovoTitular(Titular titular) {
		if (titular.getNome() == null) {
			throw new ExcecaoImpostoDeRenda("Usuario sem nome");
		}
		if(titular.getCpf() == null){
			throw new ExcecaoImpostoDeRenda("Usuario sem cpf");
		}
		titulares.add(titular);
	}

	public List<Titular> listarTitulares() {
		
		return titulares;
	}

}
