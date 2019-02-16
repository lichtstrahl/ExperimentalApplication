package root.iv.chat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import root.iv.app.App;

public class ChatService extends FirebaseMessagingService {
    public ChatService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        App.logI("From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            App.logI("Data: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            App.logI("Notification body: " + remoteMessage.getNotification().getBody());
        }
    }


}
