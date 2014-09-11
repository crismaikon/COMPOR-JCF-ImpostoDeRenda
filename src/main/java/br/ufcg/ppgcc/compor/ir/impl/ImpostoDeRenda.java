package br.ufcg.ppgcc.compor.ir.impl;

import java.text.DecimalFormat;
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
		String cnpj = "\\d\\d.\\d\\d\\d.\\d\\d\\d" + "/"
				+ "\\d\\d\\d\\d-\\d\\d";

		if (fonte.getNome() == null) {
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if (fonte.getCpfCnpj() == null) {
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é obrigatório");
		}
		if (fonte.getRendimentoRecebidos() == 0) {
			throw new ExcecaoImpostoDeRenda(
					"O campo rendimentos recebidos é obrigatório");
		}
		if (fonte.getRendimentoRecebidos() <= 0) {
			throw new ExcecaoImpostoDeRenda(
					"O campo rendimentos recebidos deve ser maior que zero");

		}
		if (fonte.getCpfCnpj().matches(cnpj) == false) {
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é inválido");
		}
		if (fontePagadoras.containsKey(titular) == false) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}

		if (fontePagadoras.containsKey(titular)) {
			List<FontePagadora> listFont = fontePagadoras.get(titular);
			listFont.add(fonte);
		}

	}

	public List<FontePagadora> listarFontes(Titular titular) {
		return fontePagadoras.get(titular);
	}

	public void criarDependente(Titular titular, Dependente dependente) {

		if (dependente.getCpf() == null) {
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
		}
		if (dependente.getNome() == null) {
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if (dependente.getTipo() == 0) {
			throw new ExcecaoImpostoDeRenda("O campo tipo é obrigatório");
		}
		if (dependente.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false) {
			throw new ExcecaoImpostoDeRenda("O campo CPF é inválido");
		}
		if (dependente.getTipo() <= 0) {
			throw new ExcecaoImpostoDeRenda("O campo tipo é inválido");
		}
		if (dependentes.containsKey(titular) == false) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}

		if (dependentes.containsKey(titular)) {
			List<Dependente> listDependente = dependentes.get(titular);
			listDependente.add(dependente);
		}

	}

	public List<Dependente> listarDependentes(Titular titular) {
		return dependentes.get(titular);
	}

	public Resultado declaracaoCompleta(Titular titular) {

		double aliquota = 0, parcelaDeducao = 0, impostoDevido = 0;
		Resultado resultado = new Resultado();
		double somatorioRendimentos = 0;
		for (FontePagadora fp : listarFontes(titular)) {
			somatorioRendimentos += fp.getRendimentoRecebidos();
		}
		if (somatorioRendimentos < 19645.33) {
			aliquota = 0;
			parcelaDeducao = 0;
		} else if (somatorioRendimentos >= 19645.33
				&& somatorioRendimentos <= 29442.0) {
			aliquota = 7.5 / 100;
			parcelaDeducao = 1473.4;
		} else if (somatorioRendimentos >= 29442.01
				&& somatorioRendimentos <= 39256.56) {
			aliquota = 15.0 / 100;
			parcelaDeducao = 3681.55;
		} else if (somatorioRendimentos >= 39256.57
				&& somatorioRendimentos <= 49051.8) {
			aliquota = 22.5 / 100;
			parcelaDeducao = 6625.79;
		} else if (somatorioRendimentos > 49051.80) {
			aliquota = 27.5 / 100;
			parcelaDeducao = 9078.38;
		}
		impostoDevido = (somatorioRendimentos * aliquota) - parcelaDeducao;
		resultado.setImpostoDevido(impostoDevido);
		return resultado;
	}

}
