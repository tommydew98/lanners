package javashark;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Tommy on 2017-04-11.
 */
public class readFile {

    private String fileName;
    private FileChooser fileChooser = new FileChooser();

    private static readFile instance = null;
    protected readFile(){

    }
    public static readFile getInstance(){
        if(instance == null){
            instance = new readFile();
        }
        return instance;
    }

    public String read(){


        configureFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            System.out.print(file.getPath());
            fileName = file.getPath();


            return(fileName);
        }

        return null;
    }

    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Choose PCAP");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All", "*.*"),
                new FileChooser.ExtensionFilter("PCAP", "*.pcap")
        );
    }
    public FileChooser getFileChooser(){
        return fileChooser;
    }

    public String getFileName(){
        return fileName;
    }
}
