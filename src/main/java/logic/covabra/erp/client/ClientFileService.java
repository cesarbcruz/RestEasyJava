package logic.covabra.erp.client;

import java.io.*;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class ClientFileService {

    public static void main(String[] args) {
        try {
            String nomeArquivo = "testepoc2.txt";
            ClientRequest request = new ClientRequest("http://192.168.0.204:8080/ERP-web/fileserver/" + nomeArquivo + "/download");
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            criarArquivo(response.getEntity().getBytes(), nomeArquivo);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void criarArquivo(byte[] data, String nomeArquivo) throws Exception {
        try {

            FileOutputStream fop = null;
            File file;

            file = new File(nomeArquivo);
            fop = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }
            try {
                fop.write(data);

            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}