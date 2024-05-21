
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// File.java
public class FileWithGetterAndSetter
{
    private int Filesize;
    private String Filename; // must contain extension
    private String FilePath;
    private String FileExtension;
    private boolean isDirectory;
    private List<File> files;


    public FileWithGetterAndSetter() {
        this.setFilesize(0);
        this.setFilename("");
        this.setFilePath("");
        this.setFileExtension("");
        this.setIsDirectory(false);
        this.files = new ArrayList<File>();
    }

    public FileWithGetterAndSetter(String directory, int filesize, boolean isDirectory) {
        this.setFilesize(filesize);
        this.setFilename(directory.substring(directory.lastIndexOf('/') + 1 , directory.length() ));
        this.setFilePath(directory);
        this.setFileExtension(directory.substring(directory.lastIndexOf('.') + 1 , directory.length() ));
        this.setIsDirectory(isDirectory);
        this.files = new ArrayList<File>();
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(File... files) {
        this.files = Arrays.asList(files);
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public int getFilesize() {
        return Filesize;
    }

    public void setFilesize(int filesize) {
        Filesize = filesize;
    }
}
