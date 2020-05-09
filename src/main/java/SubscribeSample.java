import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * ���Ķ�
 */
public class SubscribeSample {

	public static void main(String[] args) throws MqttException {
		String HOST = "tcp://broker.emqx.io:1883";
		String TOPIC = "1/#";
		int qos = 0;
		String clientid = "subClient34325345";
		String userName = "null";
		String passWord = "null";
		try {
			// hostΪ��������testΪclientid������MQTT�Ŀͻ���ID��һ���Կͻ���Ψһ��ʶ����ʾ��MemoryPersistence����clientid�ı�����ʽ��Ĭ��Ϊ���ڴ汣��
			MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
			// MQTT����������
			MqttConnectOptions options = new MqttConnectOptions();
			// �����Ƿ����session,�����������Ϊfalse��ʾ�������ᱣ���ͻ��˵����Ӽ�¼����������Ϊtrue��ʾÿ�����ӵ������������µ��������
			options.setCleanSession(true);
			// �������ӵ��û���
			options.setUserName(userName);
			// �������ӵ�����
			options.setPassword(passWord.toCharArray());
			// ���ó�ʱʱ�� ��λΪ��
			options.setConnectionTimeout(10);
			// ���ûỰ����ʱ�� ��λΪ�� ��������ÿ��1.5*20���ʱ����ͻ��˷��͸���Ϣ�жϿͻ����Ƿ����ߣ������������û�������Ļ���
			options.setKeepAliveInterval(20);
			// ���ûص�����
			client.setCallback(new MqttCallback() {

				public void connectionLost(Throwable cause) {
					System.out.println("connectionLost");
				}

				@SuppressWarnings("static-access")
				public void messageArrived(String topic, MqttMessage message) throws Exception {

					MyThread thread = new MyThread(message);
					thread.start();

					// Thread.sleep(1000);

				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("deliveryComplete---------" + token.isComplete());
				}

			});
			client.connect(options);
			// ������Ϣ
			client.subscribe(TOPIC, qos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
