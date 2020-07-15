package java基础.IO流_毕向东.文件对象;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFilter implements FilenameFilter {

    private String suffix ;
    public SuffixFilter(String suffix) {
        super();
        this.suffix = suffix;
    }
    @Override
    public boolean accept(File dir, String name) {

        return name.endsWith(suffix);
    }

}

