package algoritmo;

import java.util.Random;

public class Ladrao extends ProgramaLadrao {

	Random random = new Random();

	private final int VISAO_INDISPONIVEL = -2;
	private final int FORA_DO_AMBIENTE = -1;
	private final int VISAO_CELULA_VAZIA = 0;
	private final int BANCO = 3;
	private final int MOEDA = 4;
	private final int PASTINHA_DO_PODER = 5;
	private final int VISAO_PAREDE = 1;
	private final int VISAO_POUPADOR1 = 100;
	private final int VISAO_POUPADOR2 = 110;
	private final int VISAO_LADRAO = 200;

	private final int OLFATO_VAZIO = 0;
	private final int OLFATO_UM_ATRAS = 1;
	private final int OLFATO_DOIS_ATRAS = 2;
	private final int OLFATO_TRES_ATRAS = 3;
	private final int OLFATO_QUATRO_ATRAS = 4;
	private final int OLFATO_CINCO_ATRAS = 5;

	final int[] cimaVetorVisao = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	final int[] baixoVetorVisao = { 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
	final int[] direitaVetorVisao = { 3, 4, 8, 9, 12, 13, 17, 18, 22, 23 };
	final int[] esquerdaVetorVisao = { 0, 1, 5, 6, 10, 11, 14, 15, 19, 20 };

	final int[] cimaVetorOlfato = { 0, 1, 2 };
	final int[] baixoVetorOlfato = { 5, 6, 7 };
	final int[] esquerdaVetorOlfato = { 0, 3, 5 };
	final int[] direitaVetorOlfato = { 2, 4, 7 };

	private int moedasAtuais;

	private boolean poupadorPobre1 = false;
	private boolean poupadorPobre2 = false;
	private int turnosLonge1 = 0;
	private int turnosLonge2 = 0;

	public int calcularMovimento(int[] visao, int[] olfato) {

		int cima = calcularUltilidadeVisao("cima", visao) + calcularUltilidadeOlfato("cima", olfato);
		int baixo = calcularUltilidadeVisao("baixo", visao) + calcularUltilidadeOlfato("baixo", olfato);
		int esquerda = calcularUltilidadeVisao("esquerda", visao) + calcularUltilidadeOlfato("esquerda", olfato);
		int direita = calcularUltilidadeVisao("direita", visao) + calcularUltilidadeOlfato("direita", olfato);

		int maiorHeuristica = cima;
		int andar = 1;

		if (maiorHeuristica < baixo) {
			maiorHeuristica = baixo;
			andar = 2;
		}

		if (maiorHeuristica < direita) {
			maiorHeuristica = direita;
			andar = 3;
		}

		if (maiorHeuristica < esquerda) {
			maiorHeuristica = esquerda;
			andar = 4;
		}

		if (maiorHeuristica == 0) {
			if (visao[7] != 0 || visao[7] < 100) {
				andar = random.nextInt(4 - 3 + 1) + 3;
			}

			if (visao[12] != 0 || visao[12] < 100) {
				andar = random.nextInt(2 - 1 + 1) + 1;
			}

			if (visao[16] != 0 || visao[16] < 100) {
				andar = random.nextInt(4 - 3 + 1) + 3;
			}

			if (visao[11] != 0 || visao[11] < 100) {
				andar = random.nextInt(2 - 1 + 1) + 1;
			}

			andar = random.nextInt(4 - 0 + 1) + 0;
		}

		moedasAtuais = sensor.getNumeroDeMoedas();

		return andar;

	}

	public int calcularUltilidadeVisao(String tipo, int[] visao) {

		int peso = 0;

		if (tipo.equals("cima")) {
			if (visao[7] == 0 || visao[7] >= 100) {

				for (int i = 0; i < cimaVetorVisao.length; i++) {

					if (visao[cimaVetorVisao[i]] == VISAO_POUPADOR1) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre1 = true;
						}

						if (poupadorPobre1) {
							turnosLonge1 = 20;
							poupadorPobre1 = false;
						}

						if (turnosLonge1 != 0) {
							peso -= 100;
							turnosLonge1 -= 1;
						} else {
							peso += 100;
						}

					}

					if (visao[cimaVetorVisao[i]] == VISAO_POUPADOR2) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre2 = true;
						}

						if (poupadorPobre2) {
							turnosLonge2 = 20;
							poupadorPobre2 = false;
						}

						if (turnosLonge2 != 0) {
							peso -= 100;
							turnosLonge2 -= 1;
						} else {
							peso += 100;
						}
					}

				}
			}

		} else if (tipo.equals("baixo")) {

			if (visao[16] == 0 || visao[16] >= 100) {

				for (int i = 0; i < baixoVetorVisao.length; i++) {

					if (visao[baixoVetorVisao[i]] == VISAO_POUPADOR1) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre1 = true;
						}

						if (poupadorPobre1) {
							turnosLonge1 = 20;
							poupadorPobre1 = false;
						}

						if (turnosLonge1 != 0) {
							peso -= 100;
							turnosLonge1 -= 1;
						} else {
							peso += 100;
						}

					}

					if (visao[baixoVetorVisao[i]] == VISAO_POUPADOR2) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre2 = true;
						}

						if (poupadorPobre2) {
							turnosLonge2 = 20;
							poupadorPobre2 = false;
						}

						if (turnosLonge2 != 0) {
							peso -= 100;
							turnosLonge2 -= 1;
						} else {
							peso += 100;
						}
					}

				}

			}

		} else if (tipo.equals("direita")) {

			if (visao[12] == 0 || visao[12] >= 100) {

				for (int i = 0; i < direitaVetorVisao.length; i++) {

					if (visao[direitaVetorVisao[i]] == VISAO_POUPADOR1) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre1 = true;
						}

						if (poupadorPobre1) {
							turnosLonge1 = 20;
							poupadorPobre1 = false;
						}

						if (turnosLonge1 != 0) {
							peso -= 100;
							turnosLonge1 -= 1;
						} else {
							peso += 100;
						}

					}

					if (visao[direitaVetorVisao[i]] == VISAO_POUPADOR2) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre2 = true;
						}

						if (poupadorPobre2) {
							turnosLonge2 = 20;
							poupadorPobre2 = false;
						}

						if (turnosLonge2 != 0) {
							peso -= 100;
							turnosLonge2 -= 1;
						} else {
							peso += 100;
						}
					}

				}
			}

		} else if (tipo.equals("esquerda")) {

			if (visao[11] == 0 || visao[11] >= 100) {
				for (int i = 0; i < esquerdaVetorVisao.length; i++) {

					if (visao[esquerdaVetorVisao[i]] == VISAO_POUPADOR1) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre1 = true;
						}

						if (poupadorPobre1) {
							turnosLonge1 = 20;
							poupadorPobre1 = false;
						}

						if (turnosLonge1 != 0) {
							peso -= 100;
							turnosLonge1 -= 1;
						} else {
							peso += 100;
						}

					}

					if (visao[esquerdaVetorVisao[i]] == VISAO_POUPADOR2) {
						if (moedasAtuais < sensor.getNumeroDeMoedas()) {
							poupadorPobre2 = true;
						}

						if (poupadorPobre2) {
							turnosLonge2 = 20;
							poupadorPobre2 = false;
						}

						if (turnosLonge2 != 0) {
							peso -= 100;
							turnosLonge2 -= 1;
						} else {
							peso += 100;
						}
					}

				}
			}
		}

		return peso;
	}

	private int calcularUltilidadeOlfato(String tipo, int[] olfato) {

		int peso = 0;

		if (tipo.equals("cima")) {

			for (int i = 0; i < cimaVetorOlfato.length; i++) {

				if (olfato[cimaVetorOlfato[i]] == OLFATO_UM_ATRAS) {
					peso += 80;
				}

				if (olfato[cimaVetorOlfato[i]] == OLFATO_DOIS_ATRAS) {
					peso += 70;
				}

				if (olfato[cimaVetorOlfato[i]] == OLFATO_TRES_ATRAS) {
					peso += 60;
				}

				if (olfato[cimaVetorOlfato[i]] == OLFATO_QUATRO_ATRAS) {
					peso += 50;
				}

				if (olfato[cimaVetorOlfato[i]] == OLFATO_CINCO_ATRAS) {
					peso += 40;
				}
			}

		} else if (tipo.equals("baixo")) {

			for (int i = 0; i < baixoVetorOlfato.length; i++) {
				if (olfato[baixoVetorOlfato[i]] == OLFATO_UM_ATRAS) {
					peso += 80;
				}

				if (olfato[baixoVetorOlfato[i]] == OLFATO_DOIS_ATRAS) {
					peso += 70;
				}

				if (olfato[baixoVetorOlfato[i]] == OLFATO_TRES_ATRAS) {
					peso += 60;
				}

				if (olfato[baixoVetorOlfato[i]] == OLFATO_QUATRO_ATRAS) {
					peso += 50;
				}

				if (olfato[baixoVetorOlfato[i]] == OLFATO_CINCO_ATRAS) {
					peso += 40;
				}
			}

		} else if (tipo.equals("direita")) {

			for (int i = 0; i < direitaVetorOlfato.length; i++) {
				if (olfato[direitaVetorOlfato[i]] == OLFATO_UM_ATRAS) {
					peso += 80;
				}

				if (olfato[direitaVetorOlfato[i]] == OLFATO_DOIS_ATRAS) {
					peso += 70;
				}

				if (olfato[direitaVetorOlfato[i]] == OLFATO_TRES_ATRAS) {
					peso += 60;
				}

				if (olfato[direitaVetorOlfato[i]] == OLFATO_QUATRO_ATRAS) {
					peso += 50;
				}

				if (olfato[direitaVetorOlfato[i]] == OLFATO_CINCO_ATRAS) {
					peso += 40;
				}
			}

		} else if (tipo.equals("esquerda")) {

			for (int i = 0; i < esquerdaVetorOlfato.length; i++) {
				if (olfato[esquerdaVetorOlfato[i]] == OLFATO_UM_ATRAS) {
					peso += 80;
				}

				if (olfato[esquerdaVetorOlfato[i]] == OLFATO_DOIS_ATRAS) {
					peso += 70;
				}

				if (olfato[esquerdaVetorOlfato[i]] == OLFATO_TRES_ATRAS) {
					peso += 60;
				}

				if (olfato[esquerdaVetorOlfato[i]] == OLFATO_QUATRO_ATRAS) {
					peso += 50;
				}

				if (olfato[esquerdaVetorOlfato[i]] == OLFATO_CINCO_ATRAS) {
					peso += 40;
				}
			}

		}
		return peso;
	}

	public int acao() {
		int[] matrizvisao = sensor.getVisaoIdentificacao();
		int[] matrizolfato = sensor.getAmbienteOlfatoPoupador();
		return calcularMovimento(matrizvisao, matrizolfato);

	}

}