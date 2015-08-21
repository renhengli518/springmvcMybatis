package commons.utils;

import java.io.File;
import java.io.FileFilter;

public class FileSuffixFilter implements FileFilter {

	String condition = ".";

	public FileSuffixFilter(String uffix) {
		this.condition += uffix;
	}

	@Override
	public boolean accept(File pathname) {
		String filename = pathname.getName();
		if (filename.lastIndexOf(condition) != -1) {
			return true;
		} else
			return false;
	}

}
