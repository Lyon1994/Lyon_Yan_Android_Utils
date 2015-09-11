package org.lyon_yan.android.utils.volley;

import com.android.volley.DefaultRetryPolicy;

public class Config {

	public static DefaultRetryPolicy DefaultRetryPolicy = new DefaultRetryPolicy(
			2 * 1000, 1, 1.0f);
}
