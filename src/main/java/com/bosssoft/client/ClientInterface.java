/**
 * FileName: ClientInterface.java
 * Author: ShaoWen
 * Date: 2024/5/16 9:33
 * Description:
 * 客户端接口
 */
package com.bosssoft.client;

/**
 * @interfaceName: ClientInterface
 * @description:
 * @author: ShaoWen
 * @date: 2024/5/16 9:33
 * @since 1.0
 **/
public interface ClientInterface {
    void sendFile(String receiver, String filePath);

    void showFileContent(String filePath);
}