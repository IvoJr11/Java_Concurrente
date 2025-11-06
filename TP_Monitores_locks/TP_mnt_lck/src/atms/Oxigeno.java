package EjercicioAgua;

public class Oxigeno implements Runnable {
    AdminOH admin;

    public Oxigeno(AdminOH admin){
        this.admin = admin;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            admin.oListo();            
        } catch (Exception e) {
            System.out.println("el " + Thread.currentThread().getName() + " falló");
        }
    }
    
}
