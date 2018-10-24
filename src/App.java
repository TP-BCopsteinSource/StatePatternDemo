
public class App {

	public static void main(String[] args) {
        Aviao a = new Aviao(1750);
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());
        
        a.ligar();
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());

        a.decolar(15000);
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());

        a.nivelar();
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());
        
        a.aterrisar();
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());
        
        a.desligar();
        System.out.println("Estado: "+a.getEstado()+", Consumo instantaneo: "+a.consumoInstantaneo());
	}
}
