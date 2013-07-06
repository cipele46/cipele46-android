package eu.fiveminutes.cipele46.app;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;
import eu.fiveminutes.cipele46.api.CipeleAPI;
import eu.fiveminutes.cipele46.utils.ImageCacheManager;
import eu.fiveminutes.cipele46.utils.ImageCacheManager.CacheType;
import eu.fiveminutes.cipele46.utils.RequestManager;


public class CipeleApp extends Application {
	
	private static int DISK_IMAGECACHE_SIZE = 1024*1024*10;
	private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;
	private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided
	


	@Override
	public void onCreate() {
		super.onCreate();
		
		CipeleAPI.get().init(this);
		RequestManager.init(this);
		createImageCache();
	}
	
	private void createImageCache(){
		ImageCacheManager.getInstance().init(this,
				this.getPackageCodePath()
				, DISK_IMAGECACHE_SIZE
				, DISK_IMAGECACHE_COMPRESS_FORMAT
				, DISK_IMAGECACHE_QUALITY
				, CacheType.MEMORY);
	}
}
