
public class Aviao {
	public static final int ALTURA_CRUZEIRO = 11000;
	public static final int VELOCIDADE_CRUZEIRO = 700;

	public int velocidade;
	public int altura;
	public int velocidadeVento;
	public int peso;
	
	public State state;

	public Aviao(int peso) {
		super();
		this.velocidade = 0;
		this.altura = 0;
		this.velocidadeVento = 0;
		this.peso = peso;
		state =  new Desligado();
	}
		
	public void ligar() {
		if (!(state instanceof Desligado)) {
			throw new IllegalStateException("Nao pode ligar se nao esta desligado");
		}
		state = new Ligado();
	}

	public void desligar() {
		if (!(state instanceof Ligado || state instanceof Aterrisagem)) {
			throw new IllegalStateException("Nao pode desligar se nao ligou ou desceu");
		}
		state = new Desligado();
	}

	public void decolar(int alturaAlvo) {
		state = new Decolagem(alturaAlvo);
	}

	public void aterrisar() {
		if (!(state instanceof Nivelado)) {
			throw new IllegalStateException("Nao pode Aterrisar se nao nivelou");
		}
        state = new Aterrisagem();
	}

	public void nivelar() {
		if (!(state instanceof Decolagem)) {
			throw new IllegalStateException("Nao pode nivelar se nao decolou");
		}
		altura = ((Decolagem)state).getAlturaAlvo();
		velocidade = VELOCIDADE_CRUZEIRO;
		state = new Nivelado();
	}

	public void acelera(int vel) {
		throw new UnsupportedOperationException();
	}

	public void desacelera(int vel) {
		throw new UnsupportedOperationException();
	}

	public void setVento(int velVento) {
		throw new UnsupportedOperationException();
	}

	public String getEstado() {
		return state.getStateName();
	}

	public double consumoInstantaneo() {
		return state.consumoInstantaneo();
	}

	// Implementação dos estados
	//---------------------------------------------------------
	class Desligado implements State{
		@Override
		public String getStateName() {
			return "Desligado";
		}

		@Override
		public double consumoInstantaneo() {
			return 0.0;
		}
	}
	
	class Ligado implements State {
		@Override
		public String getStateName() {
			return "Ligado";
		}

		@Override
		public double consumoInstantaneo() {
			return 10.0;
		}		
	}

	class Decolagem implements State{
		private int alturaAlvo;
		
		public Decolagem(int alturaAlvo) {
			this.alturaAlvo = alturaAlvo;
		}
		
		public int getAlturaAlvo() {
			return alturaAlvo;
		}
		
		@Override
		public String getStateName() {
			return "Decolagem";
		}

		@Override
		public double consumoInstantaneo() {
			return 250.0+(alturaAlvo*0.01);
		}		
	}
	
	class Nivelado implements State{
		@Override
		public String getStateName() {
			return "Nivelado";
		}

		@Override
		public double consumoInstantaneo() {
			if (altura == ALTURA_CRUZEIRO ) {
				return VELOCIDADE_CRUZEIRO*0.3;
			}else {
				return VELOCIDADE_CRUZEIRO * 0.5;
			}
		}
	}

	class Aterrisagem implements State {
		@Override
		public String getStateName() {
			return "Aterrisagem";
		}

		@Override
		public double consumoInstantaneo() {
			return 300.0;
		}		
	}
}
