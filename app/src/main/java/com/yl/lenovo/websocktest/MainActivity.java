package com.yl.lenovo.websocktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    /**
     * websocket连接，接收服务器消息
     */
    private WebSocketClient  mWebSocketClient;
    private String address = "wss://10.10.10.224:8080/fly-admin/websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initSocketClient();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeConnect();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg("哈哈");
            }
        });

    }


    private void initSocketClient() throws URISyntaxException {
        if(mWebSocketClient == null) {
            mWebSocketClient = new WebSocketClient(new URI(address)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
//连接成功
                    showInfo("opened connection");
                }


                @Override
                public void onMessage(String s) {
//服务端消息
                    showInfo("received:" + s);
                }


                @Override
                public void onClose(int i, String s, boolean remote) {
//连接断开，remote判定是客户端断开还是服务端断开
                    showInfo("Connection closed by " + ( remote ? "remote peer" : "us" ) + ", info=" + s);
                    //
                    closeConnect();
                }


                @Override
                public void onError(Exception e) {
                    showInfo("error:" + e);
                }
            };
        }
    }

    private void showInfo(String s) {
        Log.v("1234",s);
        //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    //连接
    private void connect() {
        Log.v("1234","连接中");

        mWebSocketClient.connect();
    }


    //断开连接
    private void closeConnect() {
        if (mWebSocketClient.isConnecting())
        mWebSocketClient.close();

    }


//发送消息
    /**
     *
     */
    private void sendMsg(String msg) {
        if (mWebSocketClient.isConnecting())
        mWebSocketClient.send(msg);
    }
  /*  private void initSocketClient(){
        List<BasicNameValuePair> extraHeaders = Arrays.asList( new BasicNameValuePair("Cookie", "session=abcd"));
        if (websocket==null)
       websocket=new WebSocketClient(URI.create(address), new WebSocketClient.Listener() {
           @Override
           public void onConnect() {
               Log.d("123", "Connected!");
           }

           @Override
           public void onMessage(String message) {
               Log.d("123", String.format("Got string message! %s", message));
           }

           @Override
           public void onMessage(byte[] data) {
               Log.d("123", String.format("Got binary message! %s", ""));
           }

           @Override
           public void onDisconnect(int code, String reason) {
               Log.d("123", String.format("Disconnected! Code: %d Reason: %s", code, reason));
           }

           @Override
           public void onError(Exception error) {
               Log.e("123", "Error!", error);
           }
        },extraHeaders);


    }

    private void showInfo(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    //连接
    private void connect() {
        websocket.connect();
    }

    //断开连接
    private void closeConnect() {
        websocket.disconnect();
    }*/


/*//发送消息

    *//**
     *
     *//*
    private void sendMsg(String msg) {
        websocket.send(msg);
    }*/
}
