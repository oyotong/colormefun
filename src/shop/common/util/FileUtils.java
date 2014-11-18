package shop.common.util;

import java.io.File;
import java.io.IOException;

import shop.common.context.ApplicationContext;
import shop.common.exception.ApplicationException;

public class FileUtils {
	public static String saveUploadImage(File image, String imageFileName) {
		String path = ApplicationContext.getContext().getConfiguration("Upload.Images.Path");
		
		String realpath = ApplicationContext.getContext().getApplication()
				.getRealPath(path);
		File dir = new File(realpath);
		if (!dir.exists())
			dir.mkdirs();
		File savefile = new File(dir,
				shop.common.util.StringUtils
						.generateFileName(imageFileName));
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();

		try {
			org.apache.commons.io.FileUtils.copyFile(image, savefile);
		} catch (IOException e) {
			throw new ApplicationException("Upload File Error!");
		}

		return path+(path.endsWith("/")?"":"/") + savefile.getName();
	}
}
