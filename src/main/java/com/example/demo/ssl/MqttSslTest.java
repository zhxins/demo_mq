package com.example.demo.ssl;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * 获取sslcontext
 * @author han.zhifeng
 *
 */
public class MqttSslTest {
	/**
	 * 获取sslcontext
	 * @return
	 */
	public static SSLContext getSSLContext() {
		final char[] JKS_PASSWORD = "hzfpwd".toCharArray();
		final char[] KEY_PASSWORD = "hzfpwd".toCharArray();
		try {
            /* Get the JKS contents */
			final KeyStore keyStore = KeyStore.getInstance("JKS");
			try (final InputStream is = new FileInputStream(
					fullPathOfKeyStore())) {
				keyStore.load(is, JKS_PASSWORD);
			}
			final KeyManagerFactory kmf = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, KEY_PASSWORD);
			final TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

            /*
             * Creates a socket factory for HttpsURLConnection using JKS
             * contents
             */
			final SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(),
					new java.security.SecureRandom());
//            final SSLSocketFactory socketFactory = sc.getSocketFactory();
//            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
			return sc;
		} catch (final GeneralSecurityException | IOException exc) {
			throw new RuntimeException(exc);
		}
	}

	/**
	 * 证书
	 * @return
	 */
	private static String fullPathOfKeyStore() {
		final String JKS_RESOURCE_PATH = "/ssl/my.ks";
		final URL url = MqttSslTest.class.getResource(JKS_RESOURCE_PATH);
		try {
			final Path path = Paths.get(url.toURI());
			return path.toAbsolutePath().toString();

		} catch (final URISyntaxException exc) {
			throw new RuntimeException(exc);
		}
	}

}