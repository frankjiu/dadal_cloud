package com.resp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anarres.parallelgzip.ParallelGZIPOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixxcode.jvmbrotli.common.BrotliLoader;
import com.nixxcode.jvmbrotli.dec.BrotliInputStream;
import com.nixxcode.jvmbrotli.enc.BrotliOutputStream;
import com.nixxcode.jvmbrotli.enc.Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CompressUtils {

	private static HttpServletRequest req;
	private static HttpServletResponse resp;
	private static final int BUFFER_SIZE = 4096;
	private static final int GZIP_LEVEL = 9;
	private static final int BROTLI_LEVEL = 5;
	private static final int ZSTD_LEVEL = 8;

	private static ObjectMapper MAPPER;

	/**
	 * 令返回值使用gzip算法进行压缩
	 * 
	 * @throws Exception
	 */
	public static byte[] gzipResult(Object result, HttpServletResponse resp) {

		long begin = System.currentTimeMillis();
		resp.addHeader("Content-Encoding", "gzip");
		resp.addHeader("Content-Type", "application/json;charset=utf-8");
		byte[] bytes = null;
		try {
			bytes = MAPPER.writeValueAsBytes(result);
		} catch (JsonProcessingException e1) {
			log.info("json转换异常!", e1);
		}

		GzipParameters param = new GzipParameters();
		param.setCompressionLevel(Deflater.BEST_SPEED);
		try (GzipCompressorOutputStream os = new GzipCompressorOutputStream(resp.getOutputStream(), param);
				BufferedOutputStream bos = new BufferedOutputStream(os)) {
			IOUtils.write(bytes, bos);
			long end = System.currentTimeMillis();
			log.debug("使用gzip压缩并写入响应耗时:" + (end - begin));
			return bytes;
		} catch (IOException e) {
			log.error("使用gzip压缩失败!", e);
			throw new DEFException("Internal System Error");
		}

	}

	/**
	 * 令返回值使用gzip算法进行压缩
	 */
	public static void parallelgzipResult(Object result, HttpServletResponse resp) {

		long begin = System.currentTimeMillis();
		resp.addHeader("Content-Encoding", "gzip");
		resp.addHeader("Content-Type", "application/json;charset=utf-8");

		try (ParallelGZIPOutputStream os = new ParallelGZIPOutputStream(resp.getOutputStream());
				BufferedOutputStream bos = new BufferedOutputStream(os)) {
			MAPPER.writeValue(bos, result);
			long end = System.currentTimeMillis();
			log.debug("使用parallelgzip压缩并写入响应耗时:" + (end - begin));
		} catch (IOException e) {
			log.error("使用parallelgzip压缩失败!", e);
			throw new DEFException();
		}

	}

	/**
	 * 令返回值使用压缩,自动选取适用的压缩算法 如果请求头中允许br压缩算法,则会优先使用brotli压缩
	 */
	public static void compressResult(Object result) throws IOException {
		String encoding = req.getHeader("Accept-Encoding");
		if (StringUtils.isNotBlank(encoding) && encoding.contains("br")) {
			brotliResult(result, resp);
		} else {
			parallelgzipResult(result, resp);
		}
	}

	/**
	 * 解压经过brotli压缩的内容
	 */
	public static byte[] unBrotli(byte[] bytes) {

		try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); BrotliInputStream is = new BrotliInputStream(bais)) {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			log.error("使用brotli解压失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	/**
	 * 使用zstd进行压缩
	 */
	public static byte[] zstd(byte[] bytes, int level) {

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZstdCompressorOutputStream bos = new ZstdCompressorOutputStream(baos, level)) {
			IOUtils.write(bytes, bos);
			return baos.toByteArray();

		} catch (IOException e) {
			log.error("使用zstd进行压缩失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	/**
	 * 使用zstd进行压缩
	 */
	public static byte[] zstd(byte[] bytes) {
		return zstd(bytes, ZSTD_LEVEL);
	}

	/**
	 * 使用brotli压缩
	 */
	public static byte[] brotli(byte[] bytes, int level) {

		Encoder.Parameters param = new Encoder.Parameters().setQuality(level);

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); BrotliOutputStream bos = new BrotliOutputStream(baos, param)) {
			IOUtils.write(bytes, bos);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("使用brotli压缩写入响应失败", e);
			throw new DEFException("Internal System Error");
		}

	}

	/**
	 * 使用brotli压缩,默认的压缩等级是5
	 */
	public static byte[] brotli(byte[] bytes) {
		return brotli(bytes, BROTLI_LEVEL);
	}

	/**
	 * 令返回值使用brotli算法进行压缩 压缩等级为2
	 */
	public static byte[] brotliResult(Object result, HttpServletResponse resp) throws IOException {
		long begin = System.currentTimeMillis();
		resp.addHeader("Content-Encoding", "br");
		resp.addHeader("Content-Type", "application/json;charset=utf-8");
		byte[] bytes = MAPPER.writeValueAsBytes(result);
		Encoder.Parameters param = new Encoder.Parameters().setQuality(6);

		try (BrotliOutputStream os = new BrotliOutputStream(resp.getOutputStream(), param);
				BufferedOutputStream bos = new BufferedOutputStream(os);) {
			IOUtils.write(bytes, bos);
			long end = System.currentTimeMillis();
			log.debug("使用brotli压缩并写入响应耗时:" + (end - begin));
			return bytes;
		} catch (IOException e) {
			log.error("使用brotli压缩并写入响应失败", e);
			throw new DEFException("Internal System Error");
		}

	}

	/**
	 * 使用gzip进行压缩
	 */
	public static byte[] gzip(byte[] bytes, int level, byte[] byffer) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			gzip(new ByteArrayInputStream(bytes), baos, level, byffer);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("使用gzip进行压缩失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	/**
	 * 使用gzip进行压缩
	 */
	public static byte[] gzip(byte[] bytes, int level) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			gzip(new ByteArrayInputStream(bytes), baos, level, new byte[BUFFER_SIZE]);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("使用gzip压缩失败", e);
			throw new DEFException("Internal System Error");
		}

	}

	/**
	 * 解压经过gzip压缩的内容
	 */
	public static byte[] unGzip(byte[] bytes) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				GzipCompressorInputStream is = new GzipCompressorInputStream(bais)) {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			log.error("使用gzip解压内容失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	/**
	 * 使用gzip进行压缩
	 */
	public static void gzip(InputStream is, OutputStream os, int level, byte[] buffer) {
		GzipParameters param = new GzipParameters();
		param.setCompressionLevel(level);
		try (GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(os, param)) {
			IOUtils.copyLarge(is, gcos, buffer);
		} catch (IOException e) {
			log.error("使用gzip压缩失败", e);
			throw new DEFException("Internal System Error");
		}

	}

	public static byte[] parallelgzip(byte[] bytes) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			parallelgzip(new ByteArrayInputStream(bytes), baos, new byte[BUFFER_SIZE]);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("使用gzip压缩失败", e);
			throw new DEFException("Internal System Error");
		}

	}

	public static void parallelgzip(InputStream is, OutputStream os, byte[] buffer) {
		try (ParallelGZIPOutputStream pos = new ParallelGZIPOutputStream(os)) {
			IOUtils.copyLarge(is, pos, buffer);
		} catch (IOException e) {
			log.error("使用parallelgzip压缩失败", e);
			throw new DEFException();
		}
	}

	/**
	 * 使用gzip进行压缩
	 */
	public static void gzip(byte[] data, OutputStream os, int level, byte[] buffer) {
		gzip(new ByteArrayInputStream(data), os, level, buffer);
	}

	/**
	 * 使用gzip进行压缩
	 */
	public static void gzip(byte[] data, OutputStream os, int level) {
		gzip(new ByteArrayInputStream(data), os, level, new byte[BUFFER_SIZE]);
	}

	/**
	 * 使用gzip进行压缩
	 */
	public static void gzip(byte[] data, OutputStream os) {
		gzip(new ByteArrayInputStream(data), os, GZIP_LEVEL, new byte[BUFFER_SIZE]);
	}

	/**
	 * 使用zstd进行压缩
	 */
	public static void zstd(byte[] data, OutputStream os) {
		try (ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(os, ZSTD_LEVEL);
				BufferedOutputStream bos = new BufferedOutputStream(zcos)) {
			IOUtils.write(data, bos);
		} catch (IOException e) {
			log.error("使用zstd进行压缩失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	/**
	 * 使用zstd解压缩压缩
	 */
	public static byte[] unZstd(byte[] data) {

		try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
				ZstdCompressorInputStream is = new ZstdCompressorInputStream(bais)) {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			log.error("使用zstd进行压缩失败", e);
			throw new DEFException("Internal System Error");
		}
	}

	@Autowired
	public void init(HttpServletRequest req, HttpServletResponse resp) {
		CompressUtils.req = req;
		CompressUtils.resp = resp;
	}

	@Autowired
	public CompressUtils(ObjectMapper mapper) {
		MAPPER = mapper;
		if (!BrotliLoader.isBrotliAvailable()) {
			throw new RuntimeException("brotli compressor is unAvaliable!");
		}
	}

}
