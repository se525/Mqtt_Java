import org.eclipse.paho.client.mqttv3.MqttMessage;

//public class user_app1 {
//    public static void main(String[] args)  {
//        MyThread thread = new MyThread();
//        thread.start();
//    }
//}
 
 
class MyThread extends Thread{
    private static int num = 0;
    MqttMessage message;
    public MyThread( MqttMessage msg){
        num++;
        message=msg;
    }
     
    @Override
    public void run() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("主动创建的第"+num+"个线程");
        System.out.println("message content:"+new String(message.getPayload()));
    }
}
 



