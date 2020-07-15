package java基础.IO流_毕向东.文件对象;

import java.io.File;
import java.io.FileFilter;

public class FilterByHidden implements FileFilter {

    @Override
    public boolean accept(File pathname) {

        return !pathname.isHidden();
    }

}

