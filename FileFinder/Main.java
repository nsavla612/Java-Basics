import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        File f1 = new File.Builder().
                FilePath("/home/local/savlnish/test.java").
                size(500).
                isDirectory(false).
                build();

        File f2 = new File.Builder().
                FilePath("/home/local/savlnish/test.xml").
                size(200).
                isDirectory(false).
                build();

        File f3 = new File.Builder().
                FilePath("/home/local/savlnish/test2.java").
                size(100).
                isDirectory(false).
                build();

        File f5 = new File.Builder().
                FilePath("/home/local/savlnish/folder/foldertest5.java").
                size(100).
                isDirectory(false).
                build();

        File f6 = new File.Builder().
                FilePath("/home/local/savlnish/folder/foldertest6.xml").
                size(400).
                isDirectory(false).
                build();

        File f7 = new File.Builder().
                FilePath("/home/local/savlnish/folder/foldertest.dir").
                size(0).
                isDirectory(true).
                addFiles(f5,f6).
                build();

        File f = new File.Builder().
                FilePath("/home/local/savlnish/test.dir").
                size(0).
                isDirectory(true).
                addFiles(f1,f2,f3,f7).
                build();

        FileRule rule = new FileRuleOr(
                new FileRuleExtension("java"),
                new FileRuleAnd(
                        new FileRuleExtension("java"),
                        new FileRuleSize(200, FileRuleSize.FileRuleSizeOp.GTE)
                )
        );

        FindFile.find(f, rule);
    }
}
