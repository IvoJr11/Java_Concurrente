package EjercicioAgua;
import java.util.concurrent.Semaphore;


public class AdminOH {
    Semaphore oxigenoListo = new Semaphore(0);
    Semaphore hidrogenoListo = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
    
    private int aguasHechas = 0;
    private int repeticionesAgua = 5;
    private int recipientes = 0;
    
    public void oListo() throws Exception{ 
        System.out.println("llegó el " + Thread.currentThread().getName());
        //permite que dos hilos de hidrógeno pasen
        hidrogenoListo.release(2);
        //intenta pasar con el permiso de 2 hidrógenos
        oxigenoListo.acquire(2);
        System.out.println("El " + Thread.currentThread().getName() + " pasó su semáforo");
        //se llama al método de hacer agua
        hacerAgua();
    }
       
    public void hListo() throws Exception{
        //intenta pasar un hidrógeno
        hidrogenoListo.acquire(1);
        System.out.println("llegó el " + Thread.currentThread().getName());
        //le avisa al oxígeno que llegó un hidrógeno
        oxigenoListo.release(1);
    }
    
    public void hacerAgua() throws Exception{
        //agarra el permiso el primer oxígeno que llegó para evitar fallos en los contadores
        mutex.acquire();
        System.out.println("Se está formando un agua!");
        //se aumenta la cantidad de aguas que se hicieron
        this.aguasHechas++;
        //Si la cantidad de aguas hechas es múltiplo de la cantidad de aguas que hay que hacer, se guarda en un recipiente
        if (this.aguasHechas % this.repeticionesAgua == 0) {
            this.recipientes++;
            System.out.println("Se llenó un nuevo recipiente! - cantidad de recipientes llenados: " + this.recipientes );    
        }
        //se libera el semáforo para que otro oxígeno haga agua
        mutex.release();
    }
}
