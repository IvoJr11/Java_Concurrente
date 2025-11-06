package EjercicioAgua;

public class Hidrogeno implements Runnable {
    private AdminOH admin;
    
    public Hidrogeno(AdminOH admin){
        this.admin = admin;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            admin.hListo();            
        } catch (Exception e) {
            System.out.println("el " + Thread.currentThread().getName() + " falló");
        }
    }
}
