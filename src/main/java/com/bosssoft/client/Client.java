/**
 * FileName: Client.java
 * Author: boss2
 * Date: 2024/5/15 11:06
 * Description:
 * Client客户端app
 */
package com.bosssoft.client;

import com.bosssoft.server.SocketManager;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: Client
 * @description:
 * @author: ShaoWen
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/
public class Client implements ClientInterface {
    private final SocketManager socketManager;
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public Client(SocketManager socketManager) {
        this.socketManager = socketManager;
    }

    public static void main(String[] args) {
        try {
            ClientInterface client = ClientFactory.createClient(HOST, PORT);
            if (client != null) {
                System.out.println("Connected to server");
                client.sendFile("@all", "E:\\Desktop\\abc\\boss-test\\src\\main\\java\\com\\bosssoft\\filesend\\file.xml");
                //client.showFileContent("file.xml"); // Uncomment this line if needed
                System.out.println("Connection closed");
            } else {
                System.out.println("Failed to connect to server");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFile(String receiver, String filePath) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPool.submit(() -> {
            try {
                byte[] data = Files.readAllBytes(Paths.get(filePath));
                String encodedContent = String.valueOf(Base64Utils.encode(data));
                String tempPath = "encoded" + filePath;
                Files.write(Paths.get(tempPath), encodedContent.getBytes());
                String message = "send " + receiver + tempPath;
                socketManager.send(message);
                System.out.println("Sent file to server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void showFileContent(String filePath) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPool.submit(() -> {
            try {
                socketManager.send("show " + filePath);
                String response = socketManager.receive();
                System.out.println("File content: " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
//
//public class Client {
//    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,5,2,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
//        try {
//            final Socket socket = new Socket("localhost", 12345);
//            System.out.println("Connected to server");
//
//            // 异步发送消息
//            Future<Void> sendFuture = threadPoolExecutor.submit(new Callable<Void>() {
//                @Override
//                public Void call() throws Exception {
//                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                    String message = "Hello from client";
//                    writer.write(message);
//                    writer.newLine();
//                    writer.flush();
//                    System.out.println("Sent message to server: " + message);
//                    return null;
//                }
//            });
//
//            // 异步接收响应
//            Future<String> receiveFuture = threadPoolExecutor.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    String response = reader.readLine();
//                    System.out.println("Received response from server: " + response);
//                    return response;
//                }
//            });
//
//            // 等待发送和接收完成
//            sendFuture.get();
//            String response = receiveFuture.get();
//
//            // 关闭连接
//            socket.close();
//            System.out.println("Connection closed");
//        } catch (IOException | InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }finally {
//            //关闭线程池
//            threadPoolExecutor.shutdown();
//        }
//    }
//}
