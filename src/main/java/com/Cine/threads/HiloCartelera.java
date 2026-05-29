package com.Cine.threads;

public class HiloCartelera extends Thread {

    private Runnable accion;

    public HiloCartelera(Runnable accion) {
        this.accion = accion;
    }

    @Override
    public void run() {

        while (true) {

            try {

                Thread.sleep(10000);

                accion.run();

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}