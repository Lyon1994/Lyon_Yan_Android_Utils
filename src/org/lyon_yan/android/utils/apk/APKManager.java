package org.lyon_yan.android.utils.apk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import dalvik.system.DexClassLoader;

public class APKManager {
	private Context context = null;

	public APKManager(Context context) {
		// TODO Auto-generated constructor stub
		setContext(context);
	}

	public void LoadAPK(Bundle paramBundle, String dexpath, String dexoutputpath) {
		ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
		DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
				dexoutputpath, null, localClassLoader);
		try {
			PackageInfo plocalObject = getContext().getPackageManager()
					.getPackageArchiveInfo(dexpath, 1);

			if ((plocalObject.activities != null)
					&& (plocalObject.activities.length > 0)) {
				String activityname = plocalObject.activities[0].name;
				// Log.d(TAG, "activityname = " + activityname);

				Class<?> localClass = localDexClassLoader.loadClass(activityname);
				Constructor<?> localConstructor = localClass
						.getConstructor(new Class[] {});
				Object instance = localConstructor.newInstance(new Object[] {});
				// Log.d(TAG, "instance = " + instance);

				Method localMethodSetActivity = localClass.getDeclaredMethod(
						"setActivity", new Class[] { Activity.class });
				localMethodSetActivity.setAccessible(true);
				localMethodSetActivity.invoke(instance, new Object[] { this });

				Method methodonCreate = localClass.getDeclaredMethod(
						"onCreate", new Class[] { Bundle.class });
				methodonCreate.setAccessible(true);
				methodonCreate.invoke(instance, new Object[] { paramBundle });
			}
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}public void LoadAPKService(Bundle paramBundle, String dexpath, String dexoutputpath) {
		ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
		DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
				dexoutputpath, null, localClassLoader);
		try {
			PackageInfo plocalObject = getContext().getPackageManager()
					.getPackageArchiveInfo(dexpath, 1);

			if ((plocalObject.activities != null)
					&& (plocalObject.activities.length > 0)) {
				String activityname = plocalObject.activities[0].name;
				// Log.d(TAG, "activityname = " + activityname);

				Class<?> localClass = localDexClassLoader.loadClass(activityname);
				Constructor<?> localConstructor = localClass
						.getConstructor(new Class[] {});
				Object instance = localConstructor.newInstance(new Object[] {});
				// Log.d(TAG, "instance = " + instance);

				Method localMethodSetActivity = localClass.getDeclaredMethod(
						"setActivity", new Class[] { Activity.class });
				localMethodSetActivity.setAccessible(true);
				localMethodSetActivity.invoke(instance, new Object[] { this });

				Method methodonCreate = localClass.getDeclaredMethod(
						"onCreate", new Class[] { Bundle.class });
				methodonCreate.setAccessible(true);
				methodonCreate.invoke(instance, new Object[] { paramBundle });
			}
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
