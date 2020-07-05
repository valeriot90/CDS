/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 *
 * @author valtan
 */
@ServerEndpoint(value = "/Update")
public class SocketClass {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        System.out.println("Aggiunto client");
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer) {
        System.out.println("Rimosso client");
        peers.remove(peer);
    }

    @OnMessage
    public void broadcastMsg(String msg, Session session) throws IOException {
        System.out.println("broadcastUpdate " + msg);
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                //peer.getBasicRemote().sendObject();
                peer.getBasicRemote().sendText("Upload");
            }
        }
    }
}

