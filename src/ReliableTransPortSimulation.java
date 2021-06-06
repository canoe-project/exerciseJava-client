import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class ReliableTransPortSimulation{
    private double percentage = 0;
    private String bufferMessage = "";
    private DataOutputStream outputStream = null;
    private Map<String, Timer> requestTimers = new HashMap<String, Timer>();
    private Map<Integer, Timer> responseTimers = new HashMap<Integer, Timer>();
    private Map<Integer, Request> requests = new HashMap<Integer, Request>();
    private Map<Integer, ACK> acks = new HashMap<Integer, ACK>();
    private long timeOutInterval;

    ReliableTransPortSimulation(){
    }

    public void setMessage(String message){
        this.bufferMessage = message;
    };

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
            }
        };

        Timer timer = new Timer();

        requestTimers.put(Integer.toString(req.getNumReq()), timer);
        timer.schedule(task, timeOutInterval, timeOutInterval);
    }

    public void setResponse(ACK ack){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                double random = Math.random();
                try{
                    if(random * 100.0 <= percentage){
                        outputStream.writeUTF(requests.get(ack.getNumAck()).getReq());
                    }
                }catch (IOException e){e.printStackTrace();}
                responseTimers.remove(Integer.toString(ack.getNumAck()));
            }
        };
        Timer timer = new Timer();

        responseTimers.put(ack.getNumAck(), timer);
        timer.schedule(task, timeOutInterval);
    }

    public void requestTimerCancel(int numReq){
        requestTimers.get(Integer.toString(numReq)).cancel();
        requestTimers.remove(Integer.toString(numReq));
    }
    public void responseTimerCancel(int numReq){
        responseTimers.get(Integer.toString(numReq)).cancel();
        responseTimers.remove(Integer.toString(numReq));
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