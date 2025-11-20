import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PizzasADomicilio {
    public static void main(String[]args){
        Monitor monitor = new Monitor();
        for(int i = 1; i<6; i++){
            new Thread(new GeneradorPedidos(monitor), "cliente" + i).start();
            new Thread(new MaestroPizzero(monitor), "pizzero" + i).start();
            new Thread(new Repartidor(monitor), "repartidor" + i).start();
        }
    }

    public static class Repartidor implements Runnable {
        private Monitor monitor;

        public Repartidor (Monitor m){
            monitor = m;
        }

        public void run(){
            while(true){
                for(int i=0;i<10;i++){
                monitor.buscaPedido();
                monitor.repartirPedido();
                }
                System.out.println(Thread.currentThread().getName() + " descansa y come pipsha");
                try {
                    Thread.sleep((long)(Math.random()*4000));
                } catch (Exception e) { }
            }
        }
    }

    public static class MaestroPizzero implements Runnable {
        private Monitor monitor;
        private char tipo;

        public MaestroPizzero (Monitor m){
            monitor = m;
            tipo = obtenerProfecion();
        }

        public char obtenerProfecion(){
            char p = ' ';
            switch ((int)(Math.random()*2)) {
                case 0: p = 'N';
                break;
                case 1: p = 'V';
                break;
            }
            return p;
        }
        
        public void run(){
            while(true){
                monitor.tomarPedido(tipo);
                monitor.preparaPizza(tipo);
                monitor.dejaPizza();
            }
        }
    }

    public static class GeneradorPedidos implements Runnable {
        private Monitor monitor;

        public GeneradorPedidos (Monitor m){
            monitor = m;
        }

        public char generarTipo(){
            char t = ' ';
            switch ((int)(Math.random()*2)) {
                case 0: t = 'N';
                break;
                case 1: t = 'V';
                break;
            }
            return t;
        }

        public String generarCliente(){
            String c = " ";
            switch ((int)(Math.random()*4)) {
                case 0: c = "Ivo";
                break;
                case 1: c = "Tomi";
                break;
                case 2: c = "Maca";
                break;
                case 3: c = "Santi";
                break;
            }
            return c;
        }
        
        public void run(){
            while(true){
                monitor.dejaPedido(generarTipo(),generarCliente());
                try {
                    Thread.sleep((long)(Math.random()*6000));
                } catch (Exception e) {
                }
            }
        }
    }

    public static class Pedido {
        private char tipo;
        private String nombre;

        public Pedido (char t, String n){
            tipo = t;
            nombre = n;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public void setTipo(char tipo) {
            this.tipo = tipo;
        }
        public String getNombre() {
            return nombre;
        }
        public char getTipo() {
            return tipo;
        }
    }

    public static class Monitor {
        private final ReentrantLock mostradorLock;
        private final ReentrantLock pedidosLock;
        private final Condition hayPedidos;
        private final Condition faltanPizzas;
        private final Condition hayPizzas;
        private final int MAXPEDIDOS = 10;
        private Queue <Pedido> pedidos;
        private int pizzas;


        public Monitor(){
            mostradorLock = new ReentrantLock();
            pedidosLock = new ReentrantLock();
            faltanPizzas = mostradorLock.newCondition();
            hayPizzas = mostradorLock.newCondition();
            hayPedidos = pedidosLock.newCondition();
            pedidos = new LinkedList<>();
            pizzas = 0;
        }

        public void buscaPedido(){
            mostradorLock.lock();
            try{
                while(pizzas == 0){
                    System.out.println(Thread.currentThread().getName() + " espera una pizza para entregar");
                    hayPizzas.awaitUninterruptibly();
                }
                pizzas--;
                System.out.println(Thread.currentThread().getName() + " recogio una pizza");
                faltanPizzas.signalAll();
            } finally {
                mostradorLock.unlock();
            }
        }

        public void repartirPedido(){
            System.out.println(Thread.currentThread().getName() + " esta entregando el pedido");
            try {
                Thread.sleep((long)(Math.random()*4000));
            } catch (Exception e) {}
        }

        public void tomarPedido(char tipo){
            pedidosLock.lock();
            try{
                while(pedidos.isEmpty() || !(pedidos.peek().getTipo() == tipo)){
                    System.out.println(Thread.currentThread().getName() + " espera un pedido de " + tipo + " para cocinar");
                    hayPedidos.awaitUninterruptibly();
                }
                System.out.println(Thread.currentThread().getName() + " toma un pedido de " + tipo);
                pedidos.remove();
            } finally {
                pedidosLock.unlock();
            }
        }

        public void preparaPizza(char tipo){
            System.out.println(Thread.currentThread().getName() + " prepara una pizza de " + tipo);
            try {
                Thread.sleep((long)(Math.random()*4000));
            } catch (Exception e) {}
        }

        public void dejaPizza(){
            mostradorLock.lock();
            try{
                while(pizzas == MAXPEDIDOS){
                    System.out.println(Thread.currentThread().getName() + " espera que se vacie el mostrador");
                    faltanPizzas.awaitUninterruptibly();
                }
                pizzas++;
                System.out.println(Thread.currentThread().getName() + " dejo una pizza en el mostrador");
                hayPizzas.signal();
            } finally {
                mostradorLock.unlock();
            }
        }

        public void dejaPedido(char tipo, String nombre){
            pedidosLock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + " realizo un pedido de " + tipo + " a nombre de " + nombre);
                pedidos.add(new Pedido(tipo, nombre));
                hayPedidos.signalAll();
            } finally {
                pedidosLock.unlock();
            }
        }
    }
}