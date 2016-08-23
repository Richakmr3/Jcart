package com.infy.jcart.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFiles {

	public static List<String> listFiles(String path) {
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				list.add(files);
			}
		}

		return list;

	}
}