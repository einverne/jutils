package com.jutils.douban;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Request.Builder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class ApiSignatureHelper {

	private static final String UserAgent = "Rexxar-Core/0.1.3 com.douban.frodo/6.0.1(138) Android/23 rom/android udid/5e3fff05888114b7ad825a4159565b89f86cccda Rexxar/1.2.151";

	private static String originB = "74CwfJd4+7LYgFhXi1cx0IQC35UQqYVFycCE+EVyw1E=";
	private static String originC = "MkFm2XdTnoPKFKXu1gveBQ==";

	private static String bKey = "0dad551ec0f84ed02907ff5c42e8ec70";
	private static  String cKey = "MkFm2XdTnoPKFKXu1gveBQ==";

	private static final String client_id = "0dad551ec0f84ed02907ff5c42e8ec70";
	private static final String client_secret = "9e8bb54dc3288cdf";

	/**
	 * 通过 request 生成  _sig 和 _ts
	 *
	 * @param request 请求参数
	 * @return left 为 sign  right 是 ts
	 */
	static Pair<String, String> sign(Request request) {
		if (request == null) {
			return null;
		}
		String header = request.header("Authorization");
		if (StringUtils.isNotEmpty(header)) {
			header = header.substring(7);
		}
		return sign(request.url().toString(), request.method(), header);
	}

	public static Pair<String, String> sign(String s, String method, String header) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		// 计算 B C 值
		String z = Base64.encodeToString("251B9378F53534484E257695A441B901".getBytes(), 0);
		bKey = AES.sign(originB, z);
		cKey = AES.sign(originC, z);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(method);
		s = HttpUrl.parse(s).encodedPath();
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		if (s.endsWith("/")) {
			s = s.substring(0, s.length() - 1);
		}
		stringBuilder.append("&");
		stringBuilder.append(URLEncoder.encode(s));
		if (StringUtils.isNotEmpty(header)) {
			stringBuilder.append("&");
			stringBuilder.append(header);
		}
//		long time = System.currentTimeMillis() / 1000;
		long time = 1534314680;
		stringBuilder.append("&");
		stringBuilder.append(time);
		return new ImmutablePair<>(HMACHash1.encode(bKey, stringBuilder.toString()),
			String.valueOf(time));
	}

	public static void main(String[] args) {
		Request req = new Builder().url(
			"https://frodo.douban.com/api/v2/movie/rank_list?start=0&count=20&udid=5e3fff05888114b7ad825a4159565b89f86cccda&rom=android&apikey=0dad551ec0f84ed02907ff5c42e8ec70&s=rexxar_new&channel=Google_Market&device_id=5e3fff05888114b7ad825a4159565b89f86cccda&os_rom=android&loc_id=118162")
			.header("Authorization", "Bearer a285566992301fe2e790033cb6905fa6")
			.build();
		Pair<String, String> sign = sign(req);
		System.out.println(sign);

//		RequestBody body = new MultipartBody.Builder()
//			.setType(MultipartBody.FORM)
//			.addFormDataPart("client_id", client_id)
//			.addFormDataPart("client_secret", client_secret)
//			.addFormDataPart("redirect_uri", "frodo://app/oauth/callback/")
//			.addFormDataPart("disable_account_create", "false")
//			.addFormDataPart("grant_type", "password")
//			.addFormDataPart("username", "")
//			.addFormDataPart("password", "")
//			.addFormDataPart("os_rom", "android")
//			.addFormDataPart("apikey", client_id)
//			.addFormDataPart("channel", "Google_Market")
//			.addFormDataPart("udid", "5e3fff05888114b7ad825a4159565b89f86cccda")
//			.build();
//		Request req = new Builder().url("https://frodo.douban.com/service/auth2/token")
//			.header("Authorization", "Bearer a285566992301fe2e790033cb6905fa6")
//			.post(body).build();
//		Pair<String, String> stringPair = sign(req);
//		System.out.println(stringPair);
	}
}
