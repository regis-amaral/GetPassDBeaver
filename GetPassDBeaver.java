import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;


public class GetPassDBeaver {

    private static final byte[] LOCAL_KEY_CACHE = new byte[] { -70, -69, 74, -97, 119, 74, -72, 83, -55, 108, 45, 101, 61, -2, 84, 74 };

    public static void main(String[] args) {
        String pathFileString = "";
        Path pathFile = null;
        String OS = System.getProperty("os.name").toLowerCase();
        if (args.length == 1) {
            pathFile = Paths.get(args[0]);
        }else{
            if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0){
                pathFileString = "/home/" + System.getProperty("user.name") + "/.local/share/DBeaverData/workspace6/General/.dbeaver/credentials-config.json";
                pathFile = Paths.get(pathFileString);
            }
        }

        try{
            System.out.println(showData(Files.readAllBytes(pathFile)));
            System.exit(1);
        }catch(Exception e){
            //e.printStackTrace();
        }finally{
            System.out.println("Não foi possível localizar o arquivo.\n" +
                            "Informe corretamente o local do arquivo credentials-config.json\n" +
                            "Exemplo:\n" +
                            "java GetPassDBeaver ~/.local/share/DBeaverData/workspace6/General/.dbeaver/credentials-config.json");
        }
        
    }

    private static String showData(byte[] contents)throws InvalidAlgorithmParameterException, InvalidKeyException,
    IOException, NoSuchPaddingException, NoSuchAlgorithmException{
        String senhasJson = decrypt(contents);
        
        //
        
        return senhasJson;
    }

    private static String inputStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static String decrypt(byte[] contents) throws InvalidAlgorithmParameterException, InvalidKeyException,
            IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        try (InputStream byteStream = new ByteArrayInputStream(contents)) {
            byte[] fileIv = new byte[16];
            byteStream.read(fileIv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey aes = new SecretKeySpec(LOCAL_KEY_CACHE, "AES");
            cipher.init(Cipher.DECRYPT_MODE, aes, new IvParameterSpec(fileIv));
            try (CipherInputStream cipherIn = new CipherInputStream(byteStream, cipher)) {
                return inputStreamToString(cipherIn);
            }
        }
    }

}
