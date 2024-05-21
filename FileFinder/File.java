import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// File.java
public class File
{
    private int Filesize;
    private String Filename; // must contain extension
    private String FilePath;
    private String FileExtension;
    private boolean isDirectory;
    private List<File> files;

    public File() {
        this.Filesize = 0 ;
        this.Filename = "";
        this.FilePath = "";
        this.FileExtension = "";
        this.isDirectory = false;
        this.files = new ArrayList<File>();
    }

    public File(Builder builder)
    {
        this.Filesize = builder.Filesize;
        this.FilePath = builder.FilePath;
        this.isDirectory = builder.isDirectory;
        this.Filename = builder.FilePath.substring(builder.FilePath.lastIndexOf('/') + 1 , builder.FilePath.length() );
        this.FileExtension = builder.FilePath.substring(builder.FilePath.lastIndexOf('.') + 1 , builder.FilePath.length());
        this.files = builder.files;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFilename() {
        return Filename;
    }

    public int getFilesize() {
        return Filesize;
    }

    public List<File> getFiles() {
        return files;
    }

    public static class Builder {
        private int Filesize;
        private String FilePath;
        private boolean isDirectory;
        private List<File> files;

        public Builder() {}

        public Builder size(int size) {
            Filesize = size; return this;
        };

        public Builder FilePath(String filePath) {
            FilePath = filePath; return this;
        }

        public Builder isDirectory(boolean isDirectory) {
            this.isDirectory = isDirectory; return this;
        }

        public Builder addFiles(File... files) {
            this.files = Arrays.asList(files); return this;
        }

        public File build()
        {
            return new File(this);
        }
    }
}
