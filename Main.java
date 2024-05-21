import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        File f1 = new File("/home/local/savlnish/test.java" , 500, false);
        File f2 = new File("/home/local/savlnish/test.xml" , 200 , false);
        File f3 = new File("/home/local/savlnish/test2.java" , 100, false);

        File f5 = new File("/home/local/savlnish/folder/foldertest5.java" , 200, false);
        File f6 = new File("/home/local/savlnish/folder/foldertest6.xml" , 400, false);
        File f7 = new File("/home/local/savlnish/folder/foldertest.dir", 0 ,true);
        f7.setFiles( f5,f6);

        File f = new File("/home/local/savlnish/test.dir", 0 ,true);
        f.setFiles( f1,f2,f3, f7);

        FileRule rule = new FileRuleOr(
                new FileRuleExtension("xml"),
                new FileRuleAnd(
                        new FileRuleExtension("java"),
                        new FileRuleSize(200, FileRuleSize.FileRuleSizeOp.GTE)
                )
        );

        FindFile.find(f, rule);
    }
}


// FileRule.java
 interface FileRule {
    public boolean match(File f);
}


 class FileRuleAnd implements  FileRule {

    private final List<FileRule> andRules;

    public FileRuleAnd(FileRule... andRules) {
        this.andRules = Arrays.asList(andRules);
    }

    @Override
    public boolean match(File f) {
        for(FileRule rule : andRules)
        {
            if(!rule.match(f)) {
                return false;
            }
        }
        return true;
    }
}

 class FileRuleOr implements  FileRule {

    private final List<FileRule> orRules;

    public FileRuleOr(FileRule... orRules) {
        this.orRules = Arrays.asList(orRules);
    }

    @Override
    public boolean match(File f) {
        for(FileRule rule : orRules)
        {
            if(rule.match(f)) {
                return true;
            }
        }
        return false;
    }
}

 class FileRuleExtension implements FileRule {
    final private String ext;
    public FileRuleExtension(String ext)
    {
        this.ext = ext;
    }

    @Override
    public boolean match(File f) {
        return f.getFilename().endsWith("." + ext);
    }
}

 class FileRuleSize implements  FileRule {

    enum FileRuleSizeOp {
        LT,
        LTE,
        EQ,
        GT,
        GTE
    }
    private final int size;
    private final FileRuleSizeOp op;

    public FileRuleSize(int filesize, FileRuleSizeOp op) {
        this.size = filesize;
        this.op = op;
    }

    @Override
    public boolean match(File f) {
       switch (op) {
           case LT: return f.getFilesize() < size;
           case LTE:return f.getFilesize() <= size;
           case EQ:return f.getFilesize() == size;
           case GT:return f.getFilesize() > size;
           case GTE:return f.getFilesize() >= size;
       }
       return false;
    }
}

 class FindFile {
    public static void find( File baseDir, FileRule rule) {
        if(baseDir.isDirectory()) {
            for(File f : baseDir.getFiles())
            {
                if(f.isDirectory())
                {
                   find(f, rule);
                }
                else {
               //     System.out.println(f.getFilename() + " about to start with rule- " + rule.toString());
                    if (rule.match(f)) {
                        System.out.println(f.getFilename() + " matches");
                    }
                }
            }
        }
    }
}
