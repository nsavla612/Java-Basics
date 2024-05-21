import java.util.Arrays;
import java.util.List;

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
    public static void find(File baseDir, FileRule rule) {
        if (baseDir.isDirectory()) {
            for (File f : baseDir.getFiles()) {
                if (f.isDirectory()) {
                    find(f, rule);
                } else {
                    checkFile(f, rule);
                }
            }
        } else {
            checkFile(baseDir, rule);
        }
    }

    private static void checkFile(File file, FileRule rule) {
        // System.out.println(file.getFilename() + " starts checking");
        if (rule.match(file)) {
            System.out.println(file.getFilename() + " matches");
        }
    }
}

