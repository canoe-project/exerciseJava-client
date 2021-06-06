import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class ReliableTransPortSimulation{
    private double percentage = 70.0;
    private DataOutputStream outputStream = null;
    private Map<String, Timer> requestTimers = new HashMap<String, Timer>();
    private Map<String, Timer> responseTimers = new HashMap<String, Timer>();
    private Map<String, Request> requests = new HashMap<String, Request>();
    private Map<Integer, ACK> acks = new HashMap<Integer, ACK>();
    private long timeOutInterval = 1000;

    ReliableTransPortSimulation(){
    }

    public void setTimer(Request req){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                double random = Math.random();
                try{
                    if(random * 100.0 <= percentage){
                        outputStream.writeUTF(req.getReq());
                    }
                }catch (IOException e){e.printStackTrace();}
                System.out.println("request timer start");
            }
        };
        Timer timer = new Timer();
        requests.put("request"+req.getNumReq(), req);
        requestTimers.put("request"+req.getNumReq(), timer);
        timer.schedule(task, timeOutInterval, timeOutInterval);
    }

    public void setResponse(ACK ack){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                resendReq(ack.getNumAck());
                System.out.println("response error");
            }
        };
        Timer timer = new Timer();

        responseTimers.put("ACK"+ack.getNumAck(), timer);
        timer.schedule(task, timeOutInterval);
    }

    public void requestTimerCancel(int numReq){
        requestTimers.get("request"+numReq).cancel();
        requestTimers.remove(Integer.toString(numReq));
        System.out.println("request Timer Stop");
    }
    public void responseTimerCancel(int numReq){
        responseTimers.get("ACK"+numReq).cancel();
        responseTimers.remove(Integer.toString(numReq));
        System.out.println("ACK Timer Stop");
    }
    public void resendReq(int numReq){
        setTimer(requests.get("request"+numReq));
    }

    public void setOutputStream(DataOutputStream outputStream){
        this.outputStream = outputStream;
    }
    private static class InnerInstanceClass {
        private static final ReliableTransPortSimulation uniqueInstance = new ReliableTransPortSimulation();
    }

    public static ReliableTransPortSimulation getInstance() {
        return InnerInstanceClass.uniqueInstance;
    }
}