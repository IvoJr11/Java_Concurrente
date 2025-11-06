package EjercicioAgua;

public class Espacio {
    public static void main(String[] args) {
        Thread atomos[] = new Thread[150]; 
        AdminOH administrador = new AdminOH();
        //contador de oxígenos
        int o = 0;
        //contador de hidrógenos
        int h = 0;

        //una repetitiva de 0 a la cantidad de atomos
        for (int i = 0; i<atomos.length; i++){
            if (i%2 == 0) {
                Oxigeno oxigeno = new Oxigeno(administrador);
                atomos[i] = new Thread(oxigeno, "oxígeno " + o);
                atomos[i].start();
                o++;
            } else{
                 Hidrogeno hidrogeno = new Hidrogeno(administrador);
                atomos[i] = new Thread(hidrogeno, "hidrogeno "+ h);
                atomos[i].start();
                h++;
            }
        }
    }
}
