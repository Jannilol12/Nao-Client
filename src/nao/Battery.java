package nao;

public class Battery {
    private Thread t;
    public synchronized void sendBattery(){
        if(t != null){
            return;
        }
        t = new Thread(){
            @Override
            public void run() {
                while (!this.isInterrupted() && !sender.isClosed()) {
                    sender.sendMessage("{\"type\":\"battery\"}");
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {}
                }
                t = null;
            }
        };
        t.start();
    }
    public synchronized void stopBattery(){
    	if(t == null) return;
    	
        t.interrupt();
        t = null;
    }
}
