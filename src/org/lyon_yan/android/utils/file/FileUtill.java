package org.lyon_yan.android.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class FileUtill {
	private Context context = null;

	public FileUtill(Context context) {
		// TODO Auto-generated constructor stub
		setContext(context);
	}

	public File copyApkFromAssets(String fileName, File toFile) {
		try {
			InputStream is = getContext().getAssets().open(fileName);
			FileOutputStream fos = new FileOutputStream(toFile);
			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toFile;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 获取文件夹下的所有文件
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月26日 下午5:13:19
	 * @param dir
	 * @return
	 */
	public static List<File> getAllFiles(File dir) {
		List<File> filess = new ArrayList<File>();
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					// 这里面用了递归的算法
					filess.addAll(getAllFiles(file));
				} else {
					filess.add(file);
				}
			}
		}
		return filess;
	}
}
