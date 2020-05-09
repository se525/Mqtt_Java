import org.eclipse.paho.client.mqttv3.MqttClient; 
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *������
 */
public class PublishSample {
	public static void main(String[] args){

        String topic = "1";
        String content = "hello ����";
        int qos = 0;
        String broker = "tcp://broker.emqx.io:1883";
        String userName = "null";
        String password = "null";
        String clientId = "pubClient15466";
        // �ڴ�洢
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // �����ͻ���
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // �������Ӳ���
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // ��������������������ʱ��ס״̬
            connOpts.setCleanSession(false);
            // �������ӵ��û���
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // ��������
            sampleClient.connect(connOpts);
            // ������Ϣ
            MqttMessage message = new MqttMessage(content.getBytes());
            // ������Ϣ�ķ�������
            message.setQos(qos);
            // ������Ϣ
            sampleClient.publish(topic, message);
            // �Ͽ�����
            sampleClient.disconnect();
            // �رտͻ���
            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}